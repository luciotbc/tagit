package com.luciotbc.tagit.listener;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class EntityManagerFactoryLoaderListener implements
		ServletContextListener {

	public EntityManagerFactoryLoaderListener() {

//		System.out.println(" *** EntityManagerFactoryLoaderListener *** ");
	}

	@Override
	public void contextDestroyed(ServletContextEvent ctx) {

//		System.out.println("Closing entity manager factory -->EntityManagerFactoryLoaderListener");

		ServletContext context = ctx.getServletContext();
		EntityManagerFactory emf = (EntityManagerFactory) context
				.getAttribute("EntityManagerFactory");
		emf.close();

//		System.out.println("CLOSED: entity manager factory -->EntityManagerFactoryLoaderListener");
	}

	@Override
	public void contextInitialized(ServletContextEvent ctx) {

//		System.out.println("Loading entity manager factory -->EntityManagerFactoryLoaderListener");

		ServletContext context = ctx.getServletContext();
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("transactions-optional");
		context.setAttribute("EntityManagerFactory", emf);

//		System.out.println("LOADED: entity manager factory -->EntityManagerFactoryLoaderListener");
	}

}