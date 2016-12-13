package Odometer;

public class OdometryCorrection extends Thread{
	
	private Odometer odo;
	private headingCorrection hCorr;
	
	public OdometryCorrection(Odometer odo){
		this.odo = odo;	
		hCorr = new headingCorrection(odo);
	}
	
	public void run(){
		hCorr.start();
	}

}
