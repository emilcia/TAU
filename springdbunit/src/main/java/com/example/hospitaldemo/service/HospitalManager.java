package com.example.hospitaldemo.service;

import java.util.List;

import com.example.hospitaldemo.domain.Hospital;
import com.example.hospitaldemo.domain.Patient;

public interface HospitalManager {
	
	void addHospital(Hospital hospital);
	List<Hospital> getAllHospitals();
	void deleteHospital(Hospital hospital);
	Hospital findHospitalByNumber(int hospitalNumber);
	
	Long addNewPatient(Patient patient);
	List<Patient> getNoRegisteredPatients();
	void releasePatient(Hospital hospital, Patient patient);
	Patient findPatientById(Long id);

	List<Patient> patientsFromHospital(Hospital hospital);
	void registerPatient(Long hospitalId, Long patientId);

}
