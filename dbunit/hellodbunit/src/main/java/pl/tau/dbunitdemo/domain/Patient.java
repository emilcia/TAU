package pl.tau.dbunitdemo.domain;;

public class Patient {
	
	private long id;
	private String name;
	private String drug;
	private int portion;
	private String frequency;
	private int patientNumber;
	
	
	public int getPatientNumber() {
		return patientNumber;
	}
	public void setPatientNumber(int patientNumber) {
		this.patientNumber = patientNumber;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDrug() {
		return drug;
	}
	public void setDrug(String drug) {
		this.drug = drug;
	}
	public int getPortion() {
		return portion;
	}
	public void setPortion(int portion) {
		this.portion = portion;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public Patient(String name, String drug, int portion, String frequency, int patientNumber) {
		super();
		this.name = name;
		this.drug = drug;
		this.portion = portion;
		this.frequency = frequency;
		this.patientNumber = patientNumber;
	}
	public Patient() {
		
	}
	
}