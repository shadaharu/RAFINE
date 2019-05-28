package model.object;

public class Time {
	int hour;
	int minute;
	int second;
	
	public Time(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	public void setHour(int hour) {
		this.hour = hour;
	}
	
	public int getHour() {
		return hour;
	}
	
	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	public int getMinute() {
		return minute;
	}
	
	public void setSecond(int second) {
		this.second = second;
	}
	
	public int getSecond() {
		return second;
	}
}
