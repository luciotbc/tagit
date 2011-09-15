package com.luciotbc.tagit.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author luciocharallo
 *
 */
@javax.persistence.Entity
public class Tagging implements Entity {

	private static final long serialVersionUID = 1347652564;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long idEvaluation;
	private Long idUser;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdEvaluation() {
		return idEvaluation;
	}
	public void setIdEvaluation(Long idEvaluation) {
		this.idEvaluation = idEvaluation;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
