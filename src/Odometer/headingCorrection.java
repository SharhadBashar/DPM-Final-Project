package Odometer;

import lejos.nxt.ColorSensor;
import lejos.nxt.SensorPort;

public class headingCorrection extends Thread{
	private ColorS c1 = new ColorS(SensorPort.S1);
	private ColorS c2 = new ColorS(SensorPort.S2);
	private Odometer odo;
	
	
	public headingCorrection (Odometer odo){
		this.odo = odo;	
	}
	
	public void run(){
		updateHeading();
	}

	private void updateHeading() {
		while(!c1.foundLine() && !c2.foundLine()){
			//TODO: correction algorithm
		}
		//TODO: update heading
		
	}



}
