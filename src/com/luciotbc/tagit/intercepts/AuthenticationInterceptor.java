	package com.luciotbc.tagit.intercepts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.luciotbc.tagit.controller.IndexController;
import com.luciotbc.tagit.i18n.I18nMessages;
import com.luciotbc.tagit.model.User;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;

@Intercepts
public class AuthenticationInterceptor implements Interceptor {

	private Validator validator;
	private I18nMessages i18n;
	private HttpServletRequest req;

	public AuthenticationInterceptor(Validator validator, I18nMessages i18n,
			HttpServletRequest request) {
		this.validator = validator;
		this.i18n = i18n;
		this.req = request;
	}

	@Override
	public boolean accepts(ResourceMethod method) {
		return  getUser()==null && method.containsAnnotation(Restrict.class);
	}

	@Override
	public void intercept(InterceptorStack arg0, ResourceMethod arg1,
			Object arg2) throws InterceptionException {
		String msg = i18n.i18n("error.user.is.not.logged");
		validator.add(new ValidationMessage(msg, "error"));
		validator.onErrorUse(Results.logic()).redirectTo(IndexController.class)
				.index();
	}

	private User getUser() {
		HttpSession session = req.getSession(false);
		if (session == null) {
			return null;
		}
		User customer = (User) session.getAttribute("user");
		return customer;
	}
}
