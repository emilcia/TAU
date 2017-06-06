package com.example.hospitaldemo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "hospital.all", query = "Select h from Hospital h"),
	@NamedQuery(name = "hospital.byNumber", query = "Select h from Hospital h where h.hospitalNumber = :hospitalNumber")
})
public class Hospital {

	private Long id;
	private String name = "unknown";
	private String city = "Gdansk";
	private int hospitalNumber = 1;
	private Date registrationDate = new Date();

	private List<Patient> patients = new ArrayList<Patient>();

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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	@Temporal(TemporalType.DATE)
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	// Be careful here, both with lazy and eager fetch type
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Patient> getPatients() {
		return patients;
	}
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
	@Column(unique = true, nullable = false)
	public int getHospitalNumber() {
		return hospitalNumber;
	}
	public void setHospitalNumber(int hospitalNumber) {
		this.hospitalNumber = hospitalNumber;
	}
}
