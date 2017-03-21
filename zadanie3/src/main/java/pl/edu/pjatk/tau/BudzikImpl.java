package pl.edu.pjatk.tau;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BudzikImpl implements Budzik {

	public Time time;
	
	private List<TimeImpl> timeList = new ArrayList<TimeImpl>(); 
	
	public List<TimeImpl> getTimeSet(){
		return this.timeList;
	}
	
	public boolean shouldRing() {
		String ringTime = time.getTime();
		
		for (TimeImpl x : this.timeList){
			if(x.alarmSet == true && x.alarmTime == ringTime){
				return true;
			}
		}
		
		return false;
	}

	public void addAlarmTime(String time) {
		this.timeList.add(new TimeImpl(time));
	}

	public void clearAlarmTime(String time) {
		timeList.remove(new TimeImpl(time));
		}
	
}
