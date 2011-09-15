package com.luciotbc.tagit.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@javax.persistence.Entity
public class Evaluation implements Entity {

	private static final long serialVersionUID = 2378468237641374653L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String appName;
	private String appDescription;
	private String goalEvaluation;
	private String video;
	private Long moderate;
	@Transient
	private List<User> evaluators;
	@Transient
	private List<Tagging> tagging;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppDescription() {
		return appDescription;
	}

	public void setAppDescription(String appDescription) {
		this.appDescription = appDescription;
	}

	public String getGoalEvaluation() {
		return goalEvaluation;
	}

	public void setGoalEvaluation(String goalEvaluation) {
		this.goalEvaluation = goalEvaluation;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public Long getModerate() {
		return moderate;
	}

	public void setModerate(Long moderate) {
		this.moderate = moderate;
	}

	public List<User> getEvaluators() {
		return evaluators;
	}

	public void setEvaluators(List<User> evaluators) {
		this.evaluators = evaluators;
	}

	public List<Tagging> getTagging() {
		return tagging;
	}

	public void setTagging(List<Tagging> tagging) {
		this.tagging = tagging;
	}
}
