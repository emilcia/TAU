package pl.tau.dbunitdemo.service;

import static org.junit.Assert.assertEquals;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import pl.tau.dbunitdemo.domain.Patient;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;

@RunWith(JUnit4.class)
public class PersonManagerTest extends DBTestCase {
	PatientManager patientManager;

    public PersonManagerTest() throws Exception {
        super("PatientManagerImpl test");
        patientManager = new PatientManagerImpl(this.getConnection().getConnection());
    }

    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.INSERT;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.TRUNCATE_TABLE;
    }

    /**
     * Gets the default dataset. This dataset will be the initial state of database
     * @return the default dataset
     * @throws Exception when there are errors getting resources
     */
    @Override
    protected IDataSet getDataSet() throws Exception {
        return this.getDataSet("dataset-pm.xml");
    }

    /**
     * Returns dataset for selected resource
     * @param datasetName filename in resources
     * @return flat xml data set
     * @throws Exception when there is a problem with opening dataset
     */
    protected IDataSet getDataSet(String datasetName) throws Exception {
        URL url = getClass().getClassLoader().getResource(datasetName);
        FlatXmlDataSet ret = new FlatXmlDataSetBuilder().build(url.openStream());
        return ret;
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        patientManager = new PatientManagerImpl(this.getConnection().getConnection());
    }
	
	@Test
	public void checkAdding() throws Exception {
		Patient p = new Patient("Marian", "Rutinoskorbin", 1, "daily", 3);

		assertEquals(1, patientManager.addPatient(p));
        // Data verification
        IDataSet dbDataSet = this.getConnection().createDataSet();
        ITable actualTable = dbDataSet.getTable("PATIENT");
        ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
                (actualTable, new String[]{"ID"});
        IDataSet expectedDataSet = getDataSet("dataset-pm-add-check.xml");
        ITable expectedTable = expectedDataSet.getTable("PATIENT");

        Assertion.assertEquals(expectedTable, filteredTable);
    }
	@Test
	public void checkGetPatient() throws SQLException, Exception {
		Patient person = new Patient("Alina", "Polopirynka", 2, "weekly", 3);
		patientManager.clearPatient();

		assertEquals(1,patientManager.addPatient(person));
			
			assertEquals("Alina", patientManager.getPatient(person).getName());
			assertEquals("Polopirynka", patientManager.getPatient(person).getDrug());
			
	        IDataSet expectedDataSet = getDataSet("dataset-pm-get.xml");
	        ITable expectedTable = expectedDataSet.getTable("PATIENT");

	        assertEquals(expectedTable.getValue(2, "name"), patientManager.getPatient(person).getName());
	}
	
	@Test
	public void checkUpdate() throws Exception{
		Patient updatePerson = new Patient("Mela", "hemoglobina", 4, "daily", 2);
				
		assertEquals(1,patientManager.updatePatient(updatePerson));
						
        IDataSet dbDataSet = this.getConnection().createDataSet();
        ITable actualTable = dbDataSet.getTable("PATIENT");
        ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
                (actualTable, new String[]{"ID"});
        IDataSet expectedDataSet = getDataSet("dataset-pm-update.xml");
        ITable expectedTable = expectedDataSet.getTable("PATIENT");

        Assertion.assertEquals(expectedTable, filteredTable);

	}
	@Test
	public void checkDelete() throws Exception{
		Patient person = new Patient("Stefan", "aspiryna", 1, "daily", 1);
		assertEquals(1,patientManager.deletePatient(person));
				
        IDataSet dbDataSet = this.getConnection().createDataSet();
        ITable actualTable = dbDataSet.getTable("PATIENT");
        ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
                (actualTable, new String[]{"ID"});
        IDataSet expectedDataSet = getDataSet("dataset-pm-delete.xml");
        ITable expectedTable = expectedDataSet.getTable("PATIENT");

        Assertion.assertEquals(expectedTable, filteredTable);


	}

}
