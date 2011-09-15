package com.luciotbc.tagit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;

import com.luciotbc.tagit.dao.gae.DAO;
import com.luciotbc.tagit.i18n.I18nMessages;
import com.luciotbc.tagit.intercepts.Restrict;
import com.luciotbc.tagit.model.Entity;
import com.luciotbc.tagit.model.Evaluation;
import com.luciotbc.tagit.model.Tagging;
import com.luciotbc.tagit.model.User;
import com.luciotbc.tagit.util.Validators;

@Resource
public class EvaluationController {

	private DAO dao;
	private Result result;
	private Validator validator;
	private I18nMessages i18n;
	private HttpServletRequest req;

	public EvaluationController(DAO dao, Result result, Validator validator,
			I18nMessages i18n, HttpServletRequest request) {
		this.dao = dao;
		this.result = result;
		this.validator = validator;
		this.i18n = i18n;
		this.req = request;
	}

	@Restrict
	@Path("/evaluation/add")
	public void add() {
	}

	@Restrict
	@Path("/evaluation/save")
	public void add(Evaluation evaluation) {

		if (evaluation.getName().trim().length() < 3) {
			String msg = i18n.i18n("error.evaluation.name.small");
			validator.add(new ValidationMessage(msg, "error"));
		}
		if (evaluation.getGoalEvaluation().length() < 1) {
			String msg = i18n.i18n("error.evaluation.goal.small");
			validator.add(new ValidationMessage(msg, "error"));
		}
		if (!Validators.isYoutubeLink(evaluation.getVideo())) {
			String msg = i18n.i18n("error.evaluation.link.invalid");
			validator.add(new ValidationMessage(msg, "error"));
		}
		// add modarator
		evaluation.setModerate(getUser().getId());
		// vido parser
		evaluation.setVideo(Validators.getYouTubeId(evaluation.getVideo()));
		// redireciona página
		if (validator.hasErrors()) {
			validator.onErrorForwardTo(this.getClass()).add();
		} else {
			if (evaluation.getId() == null) {
				dao.save(evaluation);
				Tagging tagging = new Tagging();
				tagging.setIdUser(evaluation.getModerate());
				Entity evaluationTemp = dao.load(evaluation);
				tagging.setIdEvaluation(evaluationTemp.getId());
				dao.save(tagging);
			} else {
				Evaluation evaluationCurrent;
				evaluationCurrent = dao.findById(Evaluation.class,
						evaluation.getId());
				evaluationCurrent.setAppDescription(evaluation
						.getAppDescription());
				evaluationCurrent.setAppName(evaluation.getAppName());
				evaluationCurrent.setEvaluators(evaluation.getEvaluators());
				evaluationCurrent.setGoalEvaluation(evaluation
						.getGoalEvaluation());
				evaluationCurrent.setModerate(evaluation.getModerate());
				evaluationCurrent.setName(evaluation.getName());
				evaluationCurrent.setVideo(evaluation.getVideo());
				dao.update(evaluationCurrent);
			}
			ArrayList<ValidationMessage> flash = new ArrayList<ValidationMessage>();
			flash.add(new ValidationMessage(
					i18n.i18n("flash.evaluation.saved"), "flash"));
			result.include("flash", flash);
			//result.use(Results.logic()).redirectTo(HomeController.class).index();
			result.forwardTo(this.getClass()).edit(evaluation.getId());
		}
	}

	@Restrict
	@Path("/evaluation/add/user/")
	public void addUser(Long id, String userName, String userEmail) {
		// validation
		if (!Validators.isEmail(userEmail)) {
			String msg = i18n.i18n("error.register.user.email.invalid");
			validator.add(new ValidationMessage(msg, "error"));
		}
		if (validator.hasErrors()) {
			validator.onErrorRedirectTo(this.getClass()).edit(id);
		} else {
			User user = dao.findUserByEmail(userEmail);
			//caso o usuário ainda não seja registrado!
			if (user == null) {
				user = new User();
				user.setEmail(userEmail);
				user.setName(userName);
				// Gera senha numéria aleatória para novo usuário
				Random rnd = new Random();
				String password = "" + (rnd.nextInt(888888) + 111111);
				user.setPassword(password);

				dao.save(user);
				System.out.println("New user registered -> Email: "
						+ user.getEmail() + " Pass: " + password);
				// TODO enviar e-mail com senha para novo usuário e convite para
				// avaliação!!
				user = dao.findUserByEmail(userEmail);
			}
			Tagging tagging = new Tagging();
			tagging.setIdUser(user.getId());
			tagging.setIdEvaluation(id);
			dao.save(tagging);

			//ok
			ArrayList<ValidationMessage> flash = new ArrayList<ValidationMessage>();
			flash.add(new ValidationMessage(i18n.i18n("flash.evaluation.useradd"),
					"flash"));
			result.include("flash", flash);
			result.forwardTo(this.getClass()).edit(id);
		}
	}

	@Restrict
	@Path("/evaluation/delete/{id}")
	public void delete(Long id) {
		Evaluation evaluation = dao.findById(Evaluation.class, id);
		//Apaga avaliação
		dao.delete(evaluation);
		//Apaga referencias
		List<Tagging> tagging = dao.evaluationUsersfindByEvaluationId(id);
		for (Tagging evaluationUser : tagging) {
			dao.delete(evaluationUser);			
		}
		ArrayList<ValidationMessage> flash = new ArrayList<ValidationMessage>();
		flash.add(new ValidationMessage(i18n.i18n("flash.evaluation.deleted"),
				"flash"));
		result.include("flash", flash);
		result.use(Results.logic()).redirectTo(HomeController.class).index();
	}

	private User getUser() {
		HttpSession session = req.getSession(false);
		if (session == null) {
			return null;
		}
		User user = (User) session.getAttribute("user");
		return user;
	}

	@Restrict
	@Path("/evaluation/edit/{id}")
	public void edit(Long id) {
		Evaluation evaluation = dao.findById(Evaluation.class, id);
		List<User> users = dao.findUsersByEvaluation(id);
		if (evaluation == null) {
			String msg = i18n.i18n("error.evaluation.null");
			validator.add(new ValidationMessage(msg, "error"));
		}
		if (validator.hasErrors()) {
			validator.onErrorUse(Results.logic())
					.redirectTo(IndexController.class).index();
		} else {
			result.include("evaluation", evaluation);
			result.include("users", users);
		}
	}
	
	@Restrict
	@Path("/evaluation/delete/{id}/{userId}")
	public void deleteUser(Long id, Long userId) {
		Tagging tagging = dao.evaluationUsersfindByEvaluationIdAndUserID(id,userId);
		if(tagging == null){
			String msg = i18n.i18n("error.evaluation.user.null");
			validator.add(new ValidationMessage(msg, "error"));			
		}
		if (validator.hasErrors()) {
			validator.onErrorRedirectTo(this.getClass()).edit(id);
		} else {
			dao.delete(tagging);
			ArrayList<ValidationMessage> flash = new ArrayList<ValidationMessage>();
			flash.add(new ValidationMessage(i18n.i18n("flash.evaluation.user.deleted"),
					"flash"));
			result.include("flash", flash);
			result.forwardTo(this.getClass()).edit(id);
		}
	}

}
