/**
 * Created November 4 2014
 * @author Phil Douyon
 */
package FindObject;

import Navigation.Navigation;
import Odometer.Odometer;


/**
 * The Class FindObject.
 */
public class FindObject extends Thread {
	
	private Navigation nav;
	private Odometer odo; 
	private ultrasonicSensor3 us; 
	
	public FindObject(Navigation nav, Odometer odo){
		this.nav = nav;
		this.odo = odo;
		us = new ultrasonicSensor3 (odo);
	}

	public void run(){
		us.start();
		scan(); 
	}
	
	/**
	 * Scan. will locate the object. 
	 */
	public void scan(){
		nav.turnTo(odo.getTheta()-45);
		nav.turnTo(odo.getTheta()+90);
	}

	public double getAngle() {
		return us.getAngle();
	}

	public int getShortesDistance() {
		return us.getDistance();
	}



}
