package pl.tau.patientDemo.service;
// przyklad na podstawie przykladow J. Neumanna
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.tau.patientDemo.domain.Patient;
import pl.tau.patientDemo.service.PatientManagerImpl;

public class PatientManagerTest {

	PatientManagerImpl patientManager = new PatientManagerImpl();
	
	private final static String NAME_1 = "Janek";
	private final static String DRUG_1 = "Hemoglobina";
	private final static int PORTION_1 = 5;
	private final static String FREQUENCY_1 = "dziennie";
	private final static int PATIENTNUMBER_1 = 1;




	public PatientManagerTest() throws SQLException {
	}

	@After
    public void cleanup() throws SQLException {
        patientManager.clearPatient();
    }

	@Test
	public void checkConnection() {
	    assertNotNull(patientManager.getConnection());
	}
	@Test
	public void checkGetPatient() throws SQLException {
		Patient person = new Patient("Alina", "Polopirynka", 2, "tydzien", 3);
		patientManager.clearPatient();
		//System.out.print(person.getName());

		assertEquals(1,patientManager.addPatient(person));
			
			assertEquals("Alina", patientManager.getPatient(person).getName());
			assertEquals("Polopirynka", patientManager.getPatient(person).getDrug());
		//	assertEquals(PORTION_1, personRetrieved.getPortion());
		//	assertEquals(FREQUENCY_1, personRetrieved.getFrequency());
	//	assertEquals(person,patientManager.getPerson(person));
	//	System.out.println(person.getName());

	}
	
	@Test
	public void checkAdding() throws SQLException{
		Patient person = new Patient(NAME_1, DRUG_1, PORTION_1, FREQUENCY_1, PATIENTNUMBER_1);

		patientManager.clearPatient();
		assertEquals(1,patientManager.addPatient(person));
		
		List<Patient> persons = patientManager.getAllPatients();
		Patient personRetrieved = persons.get(0);
		
		assertEquals(NAME_1, personRetrieved.getName());
		assertEquals(DRUG_1, personRetrieved.getDrug());
		assertEquals(PORTION_1, personRetrieved.getPortion());
		assertEquals(FREQUENCY_1, personRetrieved.getFrequency());

	}
	@Test
	public void checkUpdate() throws SQLException{
		Patient updatePerson = new Patient("Marian", "Rutinoskorbin", 1, FREQUENCY_1, 5);
		Patient person = new Patient(NAME_1, DRUG_1, PORTION_1, FREQUENCY_1, 5);
		
	//	updatePerson.setId(person.getId());
		
		patientManager.clearPatient();
		assertEquals(1,patientManager.addPatient(person));
		assertEquals(1,patientManager.updatePatient(updatePerson));
				
		Patient personRetrieved = patientManager.getPatient(person);
		
		assertEquals("Marian", personRetrieved.getName());
		assertEquals("Rutinoskorbin", personRetrieved.getDrug());

	}
	@Test
	public void checkDelete() throws SQLException{
		Patient person = new Patient(NAME_1, DRUG_1, PORTION_1, FREQUENCY_1, PATIENTNUMBER_1);
		patientManager.clearPatient();
		assertEquals(1,patientManager.addPatient(person));
		assertEquals(1,patientManager.deletePatient(person));
		
		Patient personRetrieved = patientManager.getPatient(person);

		assertEquals(null, personRetrieved.getName());


	}

}
