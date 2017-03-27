package pl.edu.pjatk.tau;

public interface Budzik {
	
	boolean shouldRing();
	void addAlarmTime(String czas);
	void clearAlarmTime(String czas);

}
