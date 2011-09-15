package com.luciotbc.tagit.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author luciocharallo
 *
 */
@javax.persistence.Entity
public class Tag implements Entity {

	private static final long serialVersionUID = 7555738561038526827L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long idTagging;
	private int tag;
	//private TagType tag;
	private String startTime;
	private String endTime;
	private String observations;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdTagging() {
		return idTagging;
	}
	public void setIdTagging(Long idTagging) {
		this.idTagging = idTagging;
	}
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
