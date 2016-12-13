package Odometer;

import constants.values;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class positionCorrection {
	private UltrasonicSensor u1 = values.usN;
	private UltrasonicSensor u2 = values.usE;
	private UltrasonicSensor u3 = values.usW;
	private int disN = 0;
	private int disE = 0;
	private int disW = 0;
	
	private Odometer odo;
	
	public positionCorrection (Odometer odo){
		this.odo = odo;	
	}
	
	
	public void updatePosition(){
		//TODO: update odo
	}
	
	private int getNorth(){
		//TODO: get the value north us
		return 0;
	}
	
	private int getEast(){
		//TODO: get the value east us
		return 0;
		
	}
	
	private int getWest(){
		//TODO: get the value west us
		return 0;
		
	}

}
