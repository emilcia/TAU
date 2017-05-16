package pl.tau.patientDemo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import pl.tau.patientDemo.domain.Patient;
import pl.tau.patientDemo.service.PatientManagerImpl;

import javax.print.attribute.standard.Media;
import javax.servlet.annotation.WebServlet;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Simple web api demo -- it only shows how to get some sample data
 *
 * tryout: ()
 *
 * Created by tp on 24.04.17.
 */
@RestController
public class PersonApi {
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    PatientManagerImpl patientManager;
    
    @RequestMapping("/")
    public String index() {
        return "costam";
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/patient/{patientNumber}")
    @ResponseBody
    public Patient getPatient(@PathVariable int patientNumber) throws SQLException {
        Patient p = new Patient();
        p.setPatientNumber(patientNumber);

        return patientManager.getPatient(p);
    }
    
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, value = "/patient/add")
    @ResponseBody
    public int addPerson(@RequestBody Patient patient){
    	patient.setId(counter.incrementAndGet());
    	patient.setName(patient.getName());
    	patient.setPatientNumber(patient.getPatientNumber());

    	return patientManager.addPatient(patient);
    		
    }
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT, value = "/patient/update")
    @ResponseBody
    public int updatePatient(@RequestBody Patient patient) throws SQLException{
    	
        return patientManager.updatePatient(patient);    	
    }
    
    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.DELETE, value = "/patient/delete")
    @ResponseBody
    public int removePatient(@RequestBody Patient patient) throws SQLException{
    	
        return patientManager.deletePatient(patient);    	
    }
    
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET, value = "/patient/getall")
    @ResponseBody
    public List<Patient> getAllPatients() {
    	
    	return patientManager.getAllPatients();	
    }

}
