import lejos.nxt.LCD;
import Navigation.Navigation;
import Odometer.Odometer;


public class Display extends Thread {
	
	private Odometer odo;
	private Navigation nav;

	public Display(Odometer odo, Navigation nav){
		this.odo = odo; 
		this.nav = nav;
	}
	
	public void run(){
		LCD.clear();
		System.out.println("X: " + odo.getX());
		System.out.println("Y: " + odo.getY());
		System.out.println("Theta: " + odo.getTheta());
		System.out.println("des: " + nav.getDes());
	}

}
