package com.luciotbc.tagit.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.luciotbc.tagit.dao.gae.DAO;
import com.luciotbc.tagit.i18n.I18nMessages;
import com.luciotbc.tagit.model.User;
import com.luciotbc.tagit.util.Encrypt;
import com.luciotbc.tagit.util.Validators;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;

@Resource
public class IndexController {

	private DAO dao;
	private Result result;
	private Validator validator;
	private I18nMessages i18n;
	private HttpServletRequest req;
	
	public IndexController(DAO dao, Result result, Validator validator,	I18nMessages i18n, HttpServletRequest request) {
		this.dao = dao;
		this.result = result;
		this.validator = validator;
		this.i18n = i18n;
		this.req = request;
	}

	@Path("/")
	public void index() {
		if(getUser() != null){
			result.use(Results.logic()).redirectTo(HomeController.class).index();
		}
	}

	@Path("/register")
	public void register(User user, String password) {
		// first validate
		// name
		if (user.getName().length() < 3) {
			String msg = i18n.i18n("error.register.user.name.small");
			validator.add(new ValidationMessage(msg, "error"));
		}
		if (!Validators.isEmail(user.getEmail())) {
			String msg = i18n.i18n("error.register.user.email.invalid");
			validator.add(new ValidationMessage(msg, "error"));
		}
		// Pass
		if (!user.getPassword().equals(Encrypt.encrypt(password))) {
			String msg = i18n
					.i18n("error.register.user.password.does.not.match");
			validator.add(new ValidationMessage(msg, "error"));
		}
		// Pass lenght
		if (password.length() < 6) {
			String msg = i18n.i18n("error.register.user.password.small");
			validator.add(new ValidationMessage(msg, "error"));
		}

		User userTemp = dao.findUserByEmail(user.getEmail());
		if (userTemp != null) {
			String msg = i18n.i18n("error.register.user.already.exists");
			validator.add(new ValidationMessage(msg, "error"));
		}
		// redireciona p‡gina
		if (validator.hasErrors()) {
			validator.onErrorForwardTo(this.getClass()).index();
		} else {
			dao.save(user);
			user = dao.findUserByEmail(user.getEmail());
			storeUser(user);
			//TODO: Enviar email confirmando o registro

			ArrayList<ValidationMessage> flash = new ArrayList<ValidationMessage>();
			flash.add(new ValidationMessage(i18n.i18n("flash.user.joined.successfully"),"flash"));
			result.include("flash", flash);
//			result.use(Results.page()).redirectTo("/");
			result.use(Results.logic()).redirectTo(HomeController.class).index();
		}
	}

	@Path("/login")
	public void login(User user) {
		if (!Validators.isEmail(user.getEmail())) {
			String msg = i18n.i18n("error.login.email.mandatory");
			validator.add(new ValidationMessage(msg, "error"));
		} else {
			// Login
			User userTemp = dao.findUserByEmail(user.getEmail());
			if (userTemp == null) {
				String msg = i18n.i18n("error.login.invalid");
				validator.add(new ValidationMessage(msg, "error"));
			} else if (!userTemp.getPassword().equals(user.getPassword())) {
				String msg = i18n.i18n("error.login.invalid");
				validator.add(new ValidationMessage(msg, "error"));
			} else {
				storeUser(userTemp);
				ArrayList<ValidationMessage> flash = new ArrayList<ValidationMessage>();
				flash.add(new ValidationMessage(i18n.i18n("flash.login.successfully"),"flash"));
				result.include("flash", flash);
			}
		}
		// redireciona p‡gina
		if (validator.hasErrors()) {
			validator.onErrorForwardTo(this.getClass()).index();
		} else {
			result.use(Results.logic()).redirectTo(HomeController.class).index();
		}
	}

	private void storeUser(User user) {
		HttpSession session = req.getSession(true);
		session.setAttribute("user", user);
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
