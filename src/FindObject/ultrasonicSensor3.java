package FindObject;
import constants.values;
import Odometer.Odometer;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class ultrasonicSensor3 extends Thread{
	
	UltrasonicSensor sensor = values.usN;
	private int distance = values.maxValue;
	private Odometer odo; 
	private double angle = 0;
	
	public ultrasonicSensor3 (Odometer odo){
		this.odo = odo;
		this.angle = odo.getTheta();
	}
	
	public void run(){
		findDistance();
	}

	private void findDistance() {
		int currentD =  sensor.getDistance();
		if (currentD < distance){
			distance = currentD; 
			angle = odo.getTheta();
		}

	}

	public double getAngle() {
		return angle;
	}
	public int getDistance() {
		return distance;
	}
	


}
