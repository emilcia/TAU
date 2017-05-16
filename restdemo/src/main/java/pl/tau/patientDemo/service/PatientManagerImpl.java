package pl.tau.patientDemo.service;

// w oparciu o przyklad J Neumanna, przerobiony przez T Puzniakowskiego

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import pl.tau.patientDemo.domain.Patient;
import pl.tau.patientDemo.web.PersonApi;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Component
public class PatientManagerImpl implements PatientManager{

	private Connection connection;

	private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	//private String createTablePerson =
	//		"CREATE TABLE Person(id bigint GENERATED BY DEFAULT AS IDENTITY, " +
	//				"name varchar(20), yob integer)";

	private String createTablePatient = "CREATE TABLE Patient (id bigint GENERATED BY DEFAULT AS IDENTITY, name varchar(50), drug varchar(254), portion int, frequency varchar(8), patientNumber int NOT NULL UNIQUE)";
	private PreparedStatement addPatientStmt;
	private PreparedStatement deletePatientStmt;
	private PreparedStatement getAllPatientsStmt;
	private PreparedStatement getPatientStmt;
	private PreparedStatement updatePatientStmt;


	private Statement statement;

	public PatientManagerImpl() throws SQLException {
		connection = DriverManager.getConnection(url);
		statement = connection.createStatement();

		ResultSet rs = connection.getMetaData().getTables(null, null, null,
				null);
		boolean tableExists = false;
		while (rs.next()) {
			if ("Patient".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
				tableExists = true;
				break;
			}
		}

		if (!tableExists)
			statement.executeUpdate(createTablePatient);

		addPatientStmt = connection
				.prepareStatement("INSERT INTO Patient (name, drug, portion, frequency, patientNumber) VALUES (?, ?, ?, ?, ?)");
		deletePatientStmt = connection
				.prepareStatement("DELETE FROM Patient where patientNumber = ?");
		getAllPatientsStmt = connection
				.prepareStatement("SELECT id, name, drug, portion, frequency, patientNumber FROM Patient");
		getPatientStmt = connection
				.prepareStatement("SELECT name, drug, portion, frequency, patientNumber FROM Patient where patientNumber = ?");
		updatePatientStmt = connection
				.prepareStatement("UPDATE Patient set name = ?, drug = ?, portion = ?, frequency = ? where patientNumber = ?");

	}
    @Override
	public Connection getConnection() {
		return connection;
	}

	public int deletePatient(Patient person) throws SQLException {
		deletePatientStmt.setLong(1, person.getPatientNumber());
		int count = deletePatientStmt.executeUpdate();
		return count;
	}
	public int updatePatient(Patient person) throws SQLException {
		updatePatientStmt.setString(1, person.getName());
		updatePatientStmt.setString(2, person.getDrug());
		updatePatientStmt.setInt(3, person.getPortion());
		updatePatientStmt.setString(4, person.getFrequency());
		updatePatientStmt.setInt(5, person.getPatientNumber());

		updatePatientStmt.setInt(5, person.getPatientNumber());
		return updatePatientStmt.executeUpdate();
	}

	public void clearPatient() throws SQLException {
		//connection.prepareStatement("drop schema public cascade").executeUpdate();
		connection.prepareStatement("delete from Patient").executeUpdate();

	}
	public int addPatient(Patient person) {
		int count = 0;
		try {
			addPatientStmt.setString(1, person.getName());
			addPatientStmt.setString(2, person.getDrug());
			addPatientStmt.setInt(3, person.getPortion());
			addPatientStmt.setString(4, person.getFrequency());
			addPatientStmt.setInt(5, person.getPatientNumber());

			count = addPatientStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public Patient getPatient(Patient person) throws SQLException {
		Patient p = new Patient();

		try {
			getPatientStmt.setInt(1, person.getPatientNumber());
			//System.out.print(person.getPatientNumber());
			ResultSet rs = getPatientStmt.executeQuery();
			while(rs.next()) {
			//	p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setDrug(rs.getString("drug"));
				p.setPortion(rs.getInt("portion"));
				p.setFrequency(rs.getString("frequency"));
				p.setPatientNumber(rs.getInt("patientNumber"));
				
			}
			System.out.print(person.getDrug());
			System.out.print(p.getName());

			System.out.print(p.getDrug());


		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	public List<Patient> getAllPatients() {
		List<Patient> persons = new ArrayList<Patient>();

		try {
			ResultSet rs = getAllPatientsStmt.executeQuery();

			while (rs.next()) {
				Patient p = new Patient();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setDrug(rs.getString("drug"));
				p.setPortion(rs.getInt("portion"));
				p.setFrequency(rs.getString("frequency"));
				p.setPatientNumber(rs.getInt("patientNumber"));


				persons.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return persons;
	}

}
