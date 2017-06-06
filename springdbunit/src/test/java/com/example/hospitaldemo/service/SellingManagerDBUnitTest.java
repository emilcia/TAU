package com.example.hospitaldemo.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.example.hospitaldemo.domain.Hospital;
import com.example.hospitaldemo.domain.Patient;
import com.example.hospitaldemo.service.SellingManager;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

class TableTimeFilter extends DefaultColumnFilter{
	public TableTimeFilter() {
		this.excludeColumn("*TIME");
	}
}

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@Rollback
@Transactional(transactionManager = "txManager")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
public class SellingManagerDBUnitTest {


	@Autowired
	SellingManager sellingManager;

	@Test
	@DatabaseSetup("/fullData.xml")
	@ExpectedDatabase(value = "/addHospitalData.xml", 
	assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void addHospitalCheck() {
        
        Hospital h = new Hospital();
        h.setName("SzpitalWiejski");
        h.setHospitalNumber(2);
        h.setRegistrationDate(new Date());
        
        sellingManager.addHospital(h);
        assertEquals(2, sellingManager.getAllHospitals().size());
        
	}
	@Test
	@DatabaseSetup("/addHospitalData.xml")
	@ExpectedDatabase(value = "/deleteHospitalData.xml", 
	assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void deleteHospitalCheck() {
        
		Hospital h = sellingManager.findHospitalByNumber(2);

        sellingManager.deleteHospital(h);
        assertEquals(1, sellingManager.getAllHospitals().size());
        
	}
	@Test
	@DatabaseSetup("/deleteHospitalData.xml")
	@ExpectedDatabase(value = "/registerPatientData.xml", 
	assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void registerPatientCheck() {
        
		Hospital h = sellingManager.findHospitalByNumber(1);

		Patient patient = new Patient();
		patient.setName("Kalinka");
		patient.setPatientNumber(30);

		Long patientId = sellingManager.addNewPatient(patient);

		sellingManager.registerPatient(h.getId(), patientId);

		List<Patient> patients = sellingManager.patientsFromHospital(h);

		assertEquals(1, patients.size());

        
	}
	
}
