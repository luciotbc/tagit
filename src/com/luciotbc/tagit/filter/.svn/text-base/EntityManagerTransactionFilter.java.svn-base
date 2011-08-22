package com.luciotbc.tagit.filter;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.luciotbc.tagit.dao.gae.DAO;

public class EntityManagerTransactionFilter implements Filter {

	private ServletContext context;
	private String[] excludeURIs;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		// System.out.println("EntityManagerTransactionFilter - begin");

		HttpServletRequest httpReq = (HttpServletRequest) req;

		if (excludeURI(httpReq.getRequestURI())) {

			System.out.println("Bypass: " + httpReq.getRequestURI());
			chain.doFilter(req, res);
			return;
		}

		EntityManagerFactory emf = (EntityManagerFactory) this.context
				.getAttribute("EntityManagerFactory");
		EntityManager em = null;
		EntityTransaction transaction = null;

		try {
			em = emf.createEntityManager();

			DAO.setEntityManager(em);

			transaction = em.getTransaction();
			transaction.begin();

			chain.doFilter(req, res);

			transaction.commit();

		} catch (Throwable t) {

			if (transaction != null) {
				transaction.rollback();
			}

			t.printStackTrace();

		} finally {

			DAO.resetEntityManager();

			if (em != null) {
				em.close();
			}
		}

		// System.out.println("EntityManagerTransactionFilter - end");
	}

	private boolean excludeURI(String path) {
		if (excludeURIs != null) {
			for (String uri : excludeURIs) {
				if (path.toLowerCase().endsWith(uri.toLowerCase())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void init(FilterConfig conf) throws ServletException {

		// System.out.println("EntityManagerTransactionFilter - INIT");
		this.context = conf.getServletContext();

		String exclude = conf.getInitParameter("exclude");
		if (exclude != null) {
			excludeURIs = exclude.split(",");
		}
	}

}
