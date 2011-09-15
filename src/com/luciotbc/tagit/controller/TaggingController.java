package com.luciotbc.tagit.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.luciotbc.tagit.dao.gae.DAO;
import com.luciotbc.tagit.i18n.I18nMessages;
import com.luciotbc.tagit.intercepts.Restrict;
import com.luciotbc.tagit.model.Evaluation;
import com.luciotbc.tagit.model.Tagging;
import com.luciotbc.tagit.model.Tag;
import com.luciotbc.tagit.model.User;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;

@Resource
public class TaggingController {

	private DAO dao;
	private Result result;
	private Validator validator;
	private I18nMessages i18n;
	private HttpServletRequest req;

	public TaggingController(DAO dao, Result result, Validator validator,
			I18nMessages i18n, HttpServletRequest request) {
		this.dao = dao;
		this.result = result;
		this.validator = validator;
		this.i18n = i18n;
		this.req = request;
	}

	@Restrict
	@Path("/tagging/{id}")
	public void index(Long id) {
		Evaluation evaluation = dao.findById(Evaluation.class, id);
		if (evaluation == null) {
			String msg = i18n.i18n("error.tagging.evaluation.is.null");
			validator.add(new ValidationMessage(msg, "error"));
		}
		Tagging tagging = dao.taggingFindByEvaluationIdAndUserID(id, getUser().getId());
		if (tagging == null) {
			String msg = i18n.i18n("error.tagging.is.null");
			validator.add(new ValidationMessage(msg, "error"));
		}
		List<Tag> tags = dao.findTagsByTagging(tagging.getId());
		if (validator.hasErrors()) {
			validator.onErrorForwardTo(HomeController.class).index();
		} else {
			result.include("tagging",tagging);
			result.include("evaluation", evaluation);
			result.include("tags", tags);
		}
	}
	
	@Restrict
	@Path("/tagging/tag/add")
	public void addTag(Tag tag, Long idEvaluation) {
		if (tag.getStartTime() == null) {
			String msg = i18n.i18n("error.tag.starttime.is.null");
			validator.add(new ValidationMessage(msg, "error"));
		}
		if (tag.getEndTime() == null) {
			String msg = i18n.i18n("error.tag.endtime.is.null");
			validator.add(new ValidationMessage(msg, "error"));
		}
		if (tag.getTag() < 1 && tag.getTag() > 13) {
			String msg = i18n.i18n("error.tag.tag.is.invalid");
			validator.add(new ValidationMessage(msg, "error"));
		}
		if(tag.getIdTagging() == null){
			String msg = i18n.i18n("error.tag.tagging.is.null");
			validator.add(new ValidationMessage(msg, "error"));			
		}
		if (validator.hasErrors()) {
			validator.onErrorForwardTo(this.getClass()).index(idEvaluation);
		} else {
			if(tag.getId() == null){
				dao.save(tag);
			} else {
				Tag tagTemp = dao.findById(Tag.class, tag.getId());
				tagTemp.setEndTime(tag.getEndTime());
				tagTemp.setStartTime(tag.getStartTime());
				tagTemp.setObservations(tag.getObservations());
				dao.update(tagTemp);
			}
		}
		ArrayList<ValidationMessage> flash = new ArrayList<ValidationMessage>();
		flash.add(new ValidationMessage(i18n.i18n("flash.tag.salved"),"flash"));
		result.include("flash", flash);
		result.redirectTo(this.getClass()).index(idEvaluation);
	}

	@Restrict
	@Path("/tagging/tag/delete/{idEvaluation}/{id}")
	public void tagDelete(Long idEvaluation, Long id) {
		Tag evaluation = dao.findById(Tag.class, id);
		dao.delete(evaluation);
		ArrayList<ValidationMessage> flash = new ArrayList<ValidationMessage>();
		flash.add(new ValidationMessage(i18n.i18n("flash.evaluation.deleted"),"flash"));
		result.include("flash", flash);
		result.redirectTo(this.getClass()).index(idEvaluation);
	}
	@Restrict
	@Path("/report/{id}")
	public void report(Long id) {
		Evaluation evaluation = dao.findById(Evaluation.class, id);
		if (evaluation == null) {
			String msg = i18n.i18n("error.tagging.evaluation.is.null");
			validator.add(new ValidationMessage(msg, "error"));
		}
		Tagging tagging = dao.taggingFindByEvaluationIdAndUserID(id, getUser().getId());
		if (tagging == null) {
			String msg = i18n.i18n("error.tagging.is.null");
			validator.add(new ValidationMessage(msg, "error"));
		}
		List<Tag> tags = dao.findTagsByTagging(tagging.getId());
		if (validator.hasErrors()) {
			validator.onErrorForwardTo(HomeController.class).index();
		} else {
			result.include("tagging",tagging);
			result.include("evaluation", evaluation);
			result.include("tags", tags);
			result.include("user", getUser());
		}
	}
	
	private User getUser() {
		HttpSession session = req.getSession(false);
		if (session == null) {
			return null;
		}
		User user = (User) session.getAttribute("user");
		return user;
	}
}
