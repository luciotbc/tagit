package com.luciotbc.tagit.dao;

import java.util.List;

import com.luciotbc.tagit.model.Entity;
import com.luciotbc.tagit.model.Evaluation;
import com.luciotbc.tagit.model.Tag;
import com.luciotbc.tagit.model.Tagging;
import com.luciotbc.tagit.model.User;

public interface DAOInterface {
		
		public void save(Entity obj);
		public void delete(Entity obj);
		public void update(Entity obj);
		public Entity load(Entity obj);
		public <T extends Entity> T findById(Class<T> clazz, Long id);
		public <T extends Entity> List<T> findAll(Class<T> clazz);
		public User findUserByEmail(String email);
		public List<Evaluation> findAllEvaluationsByModerator(Long id);
		public List<User> findUsersByEvaluation(Long idEvaluation);
		public Tagging evaluationUsersfindByEvaluationIdAndUserID(Long idEvaluation, Long idUser);
		public List<Evaluation> findEvaluationByUserId(Long idUser);
		public List<Tagging> evaluationUsersfindByEvaluationId(Long idEvaluation);
		public Tagging taggingFindByEvaluationIdAndUserID(Long idEvaluation, Long idUser);
		public List<Tag> findTagsByTagging(Long idTagging);
}
