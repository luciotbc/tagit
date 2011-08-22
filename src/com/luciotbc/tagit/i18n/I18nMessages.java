package com.luciotbc.tagit.i18n;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.validator.Validations;

@Component
@ApplicationScoped
public class I18nMessages extends Validations {
	
	private MyValidations validations = new MyValidations();

	public String i18n(String key) {
		return validations.i18n(key);
	}

	private class MyValidations extends Validations {
		
		public String i18n(String key) {
			return super.i18n(key);
		}
	};
}
