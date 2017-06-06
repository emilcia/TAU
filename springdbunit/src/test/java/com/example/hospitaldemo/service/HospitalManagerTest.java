package com.example.hospitaldemo.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.hospitaldemo.domain.Hospital;
import com.example.hospitaldemo.domain.Patient;
import com.example.hospitaldemo.service.HospitalManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@Rollback
//@Commit
@Transactional(transactionManager = "txManager")
public class HospitalManagerTest {

	@Autowired
	HospitalManager hospitalManager;

	private final String NAME_1 = "SzpitalWiejski";
	private final int NUMBER_1 = 2;

	private final String NAME_2 = "Lolek";
	private final int NUMBER_2 = 3;


	@Test
	public void addHospitalCheck() {

		List<Hospital> retrievedHospitals = hospitalManager.getAllHospitals();

		for (Hospital hospital : retrievedHospitals) {
			if (hospital.getHospitalNumber()==NUMBER_1) {
				hospitalManager.deleteHospital(hospital);
			}
		}
		Hospital hospital = new Hospital();
		hospital.setName(NAME_1);
		hospital.setHospitalNumber(NUMBER_1);

		hospitalManager.addHospital(hospital);

		Hospital retrievedHospital = hospitalManager.findHospitalByNumber(NUMBER_1);

		assertEquals(NAME_1, retrievedHospital.getName());
		assertEquals(NUMBER_1, retrievedHospital.getHospitalNumber());
	}
	@Test
	public void deleteHospitalCheck() {
		
		Hospital hospital = new Hospital();
		hospital.setName("SuperSzpital");
		hospital.setHospitalNumber(3);
		
		hospitalManager.addHospital(hospital);
		List<Hospital> retrievedHospitals = hospitalManager.getAllHospitals();
		
		assertEquals(1, retrievedHospitals.size());
		
		hospitalManager.deleteHospital(hospital);
		retrievedHospitals = hospitalManager.getAllHospitals();
		
		assertEquals(0, retrievedHospitals.size());

	}

	@Test
	public void getAllHospitalsCheck() {
		
		Hospital hospital1 = new Hospital();
		hospital1.setName("Marian");
		hospital1.setHospitalNumber(100);
		hospitalManager.addHospital(hospital1);
		
		Hospital hospital2 = new Hospital();
		hospital2.setName("Chmura");
		hospital2.setHospitalNumber(101);
		hospitalManager.addHospital(hospital2);
		
		Hospital hospital3 = new Hospital();
		hospital3.setName("SzpitalWarszawski");
		hospital3.setHospitalNumber(102);
		hospitalManager.addHospital(hospital3);

		List<Hospital> retrievedHospitals = hospitalManager.getAllHospitals();

		assertEquals(3, retrievedHospitals.size());

	}

	@Test
	public void registerPatientCheck() {

		Hospital hospital = new Hospital();
		hospital.setName("SzpitalGdański");
		hospital.setHospitalNumber(200);

		hospitalManager.addHospital(hospital);

		Hospital retrievedHospital = hospitalManager.findHospitalByNumber(200);

		Patient patient = new Patient();
		patient.setName("Bolek");
		patient.setPatientNumber(5);

		Long patientId = hospitalManager.addNewPatient(patient);

		hospitalManager.registerPatient(retrievedHospital.getId(), patientId);

		List<Patient> patients = hospitalManager.patientsFromHospital(retrievedHospital);

		assertEquals(1, patients.size());
		assertEquals("Bolek", patients.get(0).getName());
	}

	@Test 
	public void releasePatientCheck() {
		
		Hospital hospital = new Hospital();
		hospital.setName("SzpitalGdański");
		hospital.setHospitalNumber(200);

		hospitalManager.addHospital(hospital);

		Hospital retrievedHospital = hospitalManager.findHospitalByNumber(200);

		Patient patient = new Patient();
		patient.setName("Bolek");
		patient.setPatientNumber(5);

		Long patientId = hospitalManager.addNewPatient(patient);

		hospitalManager.registerPatient(retrievedHospital.getId(), patientId);

		List<Patient> patients = hospitalManager.patientsFromHospital(retrievedHospital);

		assertEquals(1, patients.size());
		assertEquals("Bolek", patients.get(0).getName());
		
		hospitalManager.releasePatient(retrievedHospital, patient);
		
		assertEquals(0, hospitalManager.patientsFromHospital(retrievedHospital).size());
	}
	@Test
	public void addPatientCheck() {

		Patient patient = new Patient();
		patient.setName(NAME_2);
		patient.setPatientNumber(NUMBER_2);

		Long patientId = hospitalManager.addNewPatient(patient);

		Patient retrievedPatient = hospitalManager.findPatientById(patientId);
		assertEquals(NAME_2, retrievedPatient.getName());
		assertEquals(NUMBER_2, retrievedPatient.getPatientNumber());

	}

}
