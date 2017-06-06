package com.example.hospitaldemo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.hospitaldemo.domain.Hospital;
import com.example.hospitaldemo.domain.Patient;

@Component
@Transactional
public class SellingMangerHibernateImpl implements SellingManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addHospital(Hospital hospital) {
		hospital.setId(null);
		sessionFactory.getCurrentSession().persist(hospital);
	}
	
	@Override
	public void deleteHospital(Hospital hospital) {
		hospital = (Hospital) sessionFactory.getCurrentSession().get(Hospital.class,
				hospital.getId());
		
		// lazy loading here
		for (Patient p : hospital.getPatients()) {
			p.setInHospital(false);
			sessionFactory.getCurrentSession().update(p);
		}
		sessionFactory.getCurrentSession().delete(hospital);
	}

	@Override
	public List<Patient> patientsFromHospital(Hospital hospital) {
		hospital = (Hospital) sessionFactory.getCurrentSession().get(Hospital.class,
				hospital.getId());
		// lazy loading here - try this code without (shallow) copying
		List<Patient> patients = new ArrayList<Patient>(hospital.getPatients());
		return patients;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Hospital> getAllHospitals() {
		return sessionFactory.getCurrentSession().getNamedQuery("hospital.all")
				.list();
	}

	@Override
	public Hospital findHospitalByNumber(int hospitalNumber) {
		return (Hospital) sessionFactory.getCurrentSession().getNamedQuery("hospital.byNumber").setInteger("hospitalNumber", hospitalNumber).uniqueResult();
	}


	@Override
	public Long addNewPatient(Patient patient) {
		patient.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(patient);
	}

	@Override
	public void registerPatient(Long hospitalId, Long patientId) {
		Hospital hospital = (Hospital) sessionFactory.getCurrentSession().get(
				Hospital.class, hospitalId);
		Patient patient = (Patient) sessionFactory.getCurrentSession()
				.get(Patient.class, patientId);
		patient.setInHospital(true);
		hospital.getPatients().add(patient);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Patient> getNoRegisteredPatients() {
		return sessionFactory.getCurrentSession().getNamedQuery("patiet.notregistered")
				.list();
	}
	@Override
	public void releasePatient(Hospital hospital, Patient patient) {

		hospital = (Hospital) sessionFactory.getCurrentSession().get(Hospital.class,
				hospital.getId());
		patient = (Patient) sessionFactory.getCurrentSession().get(Patient.class,
				patient.getId());

		Patient toRemove = null;
		// lazy loading here (person.getCars)
		for (Patient aPatient : hospital.getPatients())
			if (aPatient.getId().compareTo(patient.getId()) == 0) {
				toRemove = aPatient;
				break;
			}

		if (toRemove != null)
			hospital.getPatients().remove(toRemove);

		patient.setInHospital(false);
	}

	@Override
	public Patient findPatientById(Long id) {
		return (Patient) sessionFactory.getCurrentSession().get(Patient.class, id);
	}

}
