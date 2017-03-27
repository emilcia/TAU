package pl.edu.pjatk.tau;

public class TimeImpl {
	
	public boolean alarmSet;
	public String alarmTime;
	
	public TimeImpl(String time){
		this.alarmTime = time;
		this.alarmSet = true;
	}
}
