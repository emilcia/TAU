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

import pl.tau.patientDemo.domain.Patient;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public interface PatientManager {
	public Connection getConnection();
	public int deletePatient(Patient patient) throws SQLException;
	public void clearPatient() throws SQLException;
	public int addPatient(Patient patient);
	public Patient getPatient(Patient patient) throws SQLException;
	public List<Patient> getAllPatients();

}
