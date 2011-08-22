package com.luciotbc.tagit.controller;

import java.util.ArrayList;

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
import com.luciotbc.tagit.model.Evaluation;
import com.luciotbc.tagit.model.User;
import com.luciotbc.tagit.util.Validators;

@Resource
public class EvaluationController {

	private DAO dao;
	private Result result;
	private Validator validator;
	private I18nMessages i18n;
	private HttpServletRequest req;
	
	public EvaluationController(DAO dao, Result result, Validator validator,	I18nMessages i18n, HttpServletRequest request){
		this.dao = dao;
		this.result = result;
		this.validator = validator;
		this.i18n = i18n;
		this.req = request;
	}
	
	@Path("/evaluation/add")
	public void add() {
		if(getUser() == null){
			String msg = i18n.i18n("error.user.is.not.logged");
			validator.add(new ValidationMessage(msg, "error"));
			validator.onErrorUse(Results.logic()).redirectTo(IndexController.class).index();
		}
	}

	@Path("/evaluation/save")
	public void add(Evaluation evaluation) {
		//add modarator 
		evaluation.setModerate(getUser().getId());
		//validation
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
		// redireciona p‡gina
		if (validator.hasErrors()) {
			validator.onErrorForwardTo(this.getClass()).add();
		} else {
			evaluation.setVideo(Validators.getYouTubeId(evaluation.getVideo()));
			dao.save(evaluation);
			ArrayList<ValidationMessage> flash = new ArrayList<ValidationMessage>();
			flash.add(new ValidationMessage(i18n.i18n("flash.evaluation.saved"),"flash"));
			result.include("flash", flash);
			result.use(Results.logic()).redirectTo(HomeController.class).index();
		}

	}
	
	@Path("/evaluation/delete/{id}")
	public void delete(Long id) {
		Evaluation evaluation =  dao.findById(Evaluation.class, id);
		dao.delete(evaluation);
		ArrayList<ValidationMessage> flash = new ArrayList<ValidationMessage>();
		flash.add(new ValidationMessage(i18n.i18n("flash.evaluation.deleted"),"flash"));
		result.include("flash", flash);
		result.use(Results.logic()).redirectTo(HomeController.class).index();
	}
	
	private User getUser() {
		HttpSession session = req.getSession(false);
		if( session == null ) {
			return null;
		}
		User customer = (User) session.getAttribute("user");
		return customer;
	}
}
