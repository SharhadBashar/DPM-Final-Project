/**
 * Created November 4 2014
 * @author Phil Douyon
 */
package Odometer;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;



/**
 * The Class Odometer.
 */
public class Odometer extends Thread {
	// robot position
	private static final double radius=1.95;
	private static final double width=21.5;
	// odometer update period, in ms
	private static final long ODOMETER_PERIOD = 25;
	private static NXTRegulatedMotor leftMotor=Motor.A;
	private static NXTRegulatedMotor rightMotor=Motor.B;
	// lock object for mutual exclusion
	private Object lock;
	// robot position
	private double x, y, theta;
	public static int lastTachoL;  /* Tacho L at last sample */
	public static int lastTachoR;  /* Tacho R at last sample */
	public static int nowTachoL;/* Current tacho L */
	public static int nowTachoR;  /* Current tacho R */

	// default constructor
	/**
	 * Instantiates a new odometer.
	 */
	public Odometer() {
		x = 0.0;
		y = 0.0;
		theta = 0.0;
		lock = new Object();
		leftMotor.resetTachoCount(); 
		rightMotor.resetTachoCount(); 
		leftMotor.flt(); 
		rightMotor.flt(); 
		lastTachoL=leftMotor.getTachoCount(); 
		lastTachoL=rightMotor.getTachoCount();
	}

	// run method (required for Thread)
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		long updateStart, updateEnd;

		while (true) {
			updateStart = System.currentTimeMillis();
			// put (some of) your odometer code here

			
			synchronized (lock) {
				// don't use the variables x, y, or theta anywhere but here!
				double distL, distR, deltaD, deltaT,dX,dY;

				nowTachoL =leftMotor.getTachoCount(); /* get tacho counts */
				nowTachoR = rightMotor.getTachoCount();
				distL = Math.PI*radius*(nowTachoL-lastTachoL)/180;
				distR = Math.PI*radius*(nowTachoR-lastTachoR)/180;
				lastTachoL=nowTachoL;
				lastTachoR=nowTachoR;
				deltaD = 0.5*(distL+distR);
				deltaT = (distL-distR)/width;
				theta += deltaT;
				dX = deltaD * Math.sin(theta);
				dY = deltaD * Math.cos(theta);
				x = x + dX;
				y = y + dY;
			}
			
			// this ensures that the odometer only runs once every period
			updateEnd = System.currentTimeMillis();
			if (updateEnd - updateStart < ODOMETER_PERIOD) {
				try {
					Thread.sleep(ODOMETER_PERIOD - (updateEnd - updateStart));
				} catch (InterruptedException e) {
					// there is nothing to be done here because it is not
					// expected that the odometer will be interrupted by
					// another thread
				}
			}
		}
	}

	// accessors


	/**
	 * Gets the x position.
	 *
	 * @return the x position of the robot
	 */
	public double getX() {
		double result;

		synchronized (lock) {
			result = x;
		}

		return result;
	}

	/**
	 * Gets the y position.
	 *
	 * @return the y position of the robot
	 */
	public double getY() {
		double result;

		synchronized (lock) {
			result = y;
		}

		return result;
	}

	/**
	 * Gets the angle 
	 *
	 * @return the angle of the robot
	 */
	public double getTheta() {
		double result;

		synchronized (lock) {
			result = theta;
		}

		return result;
	}

	// mutators
	/**
	 * Sets the x position of the robot
	 *
	 * @param x the new x position
	 */
	public void setX(double x) {
		synchronized (lock) {
			this.x = x;
		}
	}

	/**
	 * Sets the yposition of the robot.
	 *
	 * @param y the new y position
	 */
	public void setY(double y) {
		synchronized (lock) {
			this.y = y;
		}
	}

	/**
	 * Sets the theta.
	 *
	 * @param theta the new theta of the robot
	 */
	public void setTheta(double theta) {
		synchronized (lock) {
			this.theta = theta;
		}
	}
}
