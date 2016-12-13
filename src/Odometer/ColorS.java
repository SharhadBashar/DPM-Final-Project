package Odometer;

import lejos.nxt.ColorSensor;
import lejos.nxt.SensorPort;

public class ColorS extends Thread {
	
	
	private ColorSensor color;
	private long time;
	private boolean found;

	public ColorS(SensorPort sp){
		this.color = new ColorSensor(sp);
	}
	
	public void run (){
		findLine(); 
	}

	private void findLine() {
		// TODO run till it finds a line
		
	}

	private void setTime(){
		time = System.currentTimeMillis();
	}
	
	public long getTime(){
		return time; 	
	}
	
	public boolean foundLine(){
		return found; 
	}
}
