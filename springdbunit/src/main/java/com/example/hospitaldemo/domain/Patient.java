package com.example.hospitaldemo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "patiet.notregistered", query = "Select p from Patient p where p.inHospital = false")
})
public class Patient {

	private Long id;
	private String name;
	private String drug;
	private int portion;
	private String frequency;
	private int patientNumber;
	private Boolean inHospital = false;
	
	@Column(unique = true, nullable = false)
	public int getPatientNumber() {
		return patientNumber;
	}
	public void setPatientNumber(int patientNumber) {
		this.patientNumber = patientNumber;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDrug() {
		return drug;
	}
	public void setDrug(String drug) {
		this.drug = drug;
	}
	public int getPortion() {
		return portion;
	}
	public void setPortion(int portion) {
		this.portion = portion;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public Patient(String name, String drug, int portion, String frequency, int patientNumber) {
		super();
		this.name = name;
		this.drug = drug;
		this.portion = portion;
		this.frequency = frequency;
		this.patientNumber = patientNumber;
	}
	public Patient() {
		
	}
	public Boolean getInHospital() {
		return inHospital;
	}
	public void setInHospital(Boolean inHospital) {
		this.inHospital = inHospital;
	}
	
}