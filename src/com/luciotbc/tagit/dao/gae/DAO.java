package com.luciotbc.tagit.dao.gae;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.luciotbc.tagit.model.Entity;
import com.luciotbc.tagit.model.Evaluation;
import com.luciotbc.tagit.model.Tag;
import com.luciotbc.tagit.model.Tagging;
import com.luciotbc.tagit.model.User;

import br.com.caelum.vraptor.ioc.Component;

@Component
public class DAO{

	private static final ThreadLocal<EntityManager> ENTITY_MANAGER_STORE = new ThreadLocal<EntityManager>();

	public static void setEntityManager(EntityManager em) {
		ENTITY_MANAGER_STORE.set(em);
	}
	
	public static void resetEntityManager() {
		setEntityManager(null);
	}
	
	public static EntityManager getEntityManager() {
		return ENTITY_MANAGER_STORE.get();
	}

	public void save(Entity obj) {	
		EntityManager em = getEntityManager();
		em.persist(obj);
		em.flush();
	}

	public void delete(Entity obj) {
		EntityManager em = getEntityManager();
		if( !em.contains(obj) ) {
			obj = em.find(obj.getClass(), obj.getId());
		}
		em.remove(obj);
		em.flush();
	}

	public void update(Entity obj) {
		EntityManager em = getEntityManager();
		em.persist(obj);
		em.flush();
	}

	public Entity load(Entity obj) {
		EntityManager em = getEntityManager();
		obj = em.find(obj.getClass(), obj.getId());
		return obj;
	}
	
	public <T extends Entity> T findById(Class<T> clazz, Long id) {
		EntityManager em = getEntityManager();
		return em.find(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Entity> List<T> findAll(Class<T> clazz) {
		EntityManager em = getEntityManager();
		Query query = em.createQuery("select from " + clazz.getName());
		return query.getResultList();
	}

	public User findUserByEmail(String email) {
		EntityManager em = getEntityManager();              
		Query query = em.createQuery("SELECT FROM " + User.class.getName() + " WHERE email = :email");
		query.setParameter("email", email);
		try {
			return (User) query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Evaluation> findAllEvaluationsByModerator(Long id) {
		EntityManager em = getEntityManager();
		Query query = em.createQuery("SELECT FROM " + Evaluation.class.getName() + " WHERE moderate = :moderate");
		query.setParameter("moderate", id);
		try {
			return (List<Evaluation>) query.getResultList();
		} catch(NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findUsersByEvaluation(Long idEvaluation) {
		EntityManager em = getEntityManager();
		Query query = em.createQuery("SELECT FROM " + Tagging.class.getName() + " WHERE idEvaluation = :idEvaluation");
		query.setParameter("idEvaluation", idEvaluation);
		try {
			List<Tagging> tagging = (List<Tagging>) query.getResultList();
			ArrayList<User> users = new ArrayList();
			for (Tagging evaluationUsersTemp : tagging) {
				users.add(findById(User.class, evaluationUsersTemp.getIdUser()));
			}
			 return users;
		} catch(NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Tagging evaluationUsersfindByEvaluationIdAndUserID(Long idEvaluation, Long idUser) {
		EntityManager em = getEntityManager();
		Query query = em.createQuery("SELECT FROM " + Tagging.class.getName() + " WHERE idEvaluation = :idEvaluation");
		query.setParameter("idEvaluation", idEvaluation);
		try {
			List<Tagging> tagging = (List<Tagging>) query.getResultList();
			for (Tagging evaluationUsersTemp : tagging) {
				if(evaluationUsersTemp.getIdUser() == idUser){
					return evaluationUsersTemp;
				}
			}
			return null;
		} catch(NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Evaluation> findEvaluationByUserId(Long idUser) {
		EntityManager em = getEntityManager();
		Query query = em.createQuery("SELECT FROM " + Tagging.class.getName() + " WHERE idUser = :idUser");
		query.setParameter("idUser", idUser);
		try {
			List<Tagging> tagging = (List<Tagging>) query.getResultList();
			ArrayList<Evaluation> evaluations = new ArrayList();
			for (Tagging evaluationUsersTemp : tagging) {
				evaluations.add(findById(Evaluation.class, evaluationUsersTemp.getIdEvaluation()));
			}
			 return evaluations;
		} catch(NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Tagging> evaluationUsersfindByEvaluationId(Long idEvaluation) {
		EntityManager em = getEntityManager();
		Query query = em.createQuery("SELECT FROM " + Tagging.class.getName() + " WHERE idEvaluation = :idEvaluation");
		query.setParameter("idEvaluation", idEvaluation);
		try {
			List<Tagging> tagging = (List<Tagging>) query.getResultList();
			return tagging;
		} catch(NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Tagging taggingFindByEvaluationIdAndUserID(Long idEvaluation, Long idUser) {
		EntityManager em = getEntityManager();
		Query query = em.createQuery("SELECT FROM " + Tagging.class.getName() + " WHERE idEvaluation = :idEvaluation");
		query.setParameter("idEvaluation", idEvaluation);
		try {
			List<Tagging> taggings = (List<Tagging>) query.getResultList();
			for (Tagging taggingTemp : taggings) {
				if(taggingTemp.getIdUser() == idUser){
					return taggingTemp;
				}
			}
			return null;
		} catch(NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Tag> findTagsByTagging(Long idTagging) {
		EntityManager em = getEntityManager();
		Query query = em.createQuery("SELECT FROM " + Tag.class.getName() + " WHERE idTagging = :idTagging");
		query.setParameter("idTagging", idTagging);
		try {
			List<Tag> tagging = (List<Tag>) query.getResultList();
			return tagging;
		} catch(NoResultException e) {
			return null;
		}
	}
}
