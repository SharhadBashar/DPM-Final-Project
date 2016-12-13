/**
 * Created November 4 2014
 * @author Phil Douyon
 */
package Navigation;

import constants.values;
import constants.values;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import Odometer.Odometer;


/**
 * The Class Navigation.Used for Robot travel
 */
public class Navigation extends Thread {

	// **********************************************************

	// TODO: fix robot variables
	// wheel radius
	/** The radius. */
	final double radius =values.radius ;
	// robot width
	/** The width. */
	final double width = values.width;

	// TODO: change wheel motors
	// set motor
	/** The left motor. */
	NXTRegulatedMotor leftMotor = values.leftMotor;
	
	/** The right motor. */
	NXTRegulatedMotor rightMotor = values.rightMotor;

	// **********************************************************

	/** The square lenght. */
	static double squareLenght = values.squareLength;

	// set speeds
	/** The forward speed. */
	final int FORWARD_SPEED = values.forwardSpeed;
	
	/** The rotate speed. */
	final int ROTATE_SPEED = values.turnSpeed;

	// odometer
	/** The odometer. */
	Odometer odometer;

	// current positions
	/** The current theta. */
	double currentX, currentY, currentTheta;

	// destinations
	/** The destination. */
	char [] destination = null;
	char des = ' ';

	/**
	 * Instantiates a new navigation with odometer. 
	 *
	 * @param odometer the odometer
	 */
	public Navigation(Odometer odometer) {
		// initialise the start position
		currentX = odometer.getX();
		currentY = odometer.getY();
		currentTheta = odometer.getTheta();

		this.odometer = odometer;

		// set destination points
		destination = null;

	}

	/**
	 * Instantiates a new navigation with odometer and path.
	  *
	 * @param odometer the odometer
	 * @param des the destinations
	 */
	public Navigation(Odometer odometer, char [] des) {
		// initialise the start position
		currentX = odometer.getX();
		currentY = odometer.getY();
		currentTheta = odometer.getTheta();

		this.odometer = odometer;
		// set destination points
		destination = des.clone();

	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		if (destination != null) {
			for (int i = 0; i < destination.length; i++) {
				des = destination[i]; 
				currentX = odometer.getX();
				currentY = odometer.getY();
				currentTheta = odometer.getTheta();                                   
				if  (destination[i] == 'E') {
					travelTo(currentX - squareLenght, currentY);
				}else if (destination[i] == 'W'){
					travelTo(currentX + squareLenght, currentY);
				}else if (destination[i] == 'N'){
					travelTo(currentX, currentY + squareLenght);
				}else if (destination[i] == 'S'){
					travelTo(currentX, currentY - squareLenght);
				}else { 
				}
			}
		}
	}

	/**
	 * Travel to.
	 *
	 * @param x the x destination
	 * @param y the y destination
	 */
	public void travelTo(double x, double y) {
		// get the current possition
		currentX = odometer.getX();
		currentY = odometer.getY();

		// get the turning angle
		double newTheta = 0;

		// the difference between the current position and new
		double deltaX = x - currentX;
		double deltaY = y - currentY;

		// for each turn that goes parrallel to each axis, we set a bound cause
		// the difference might be greater than zero
		// no change in x position, so robot will go up or down
		if (Math.abs(deltaX) < 2) {
			// robot turns to go up
			if (y > currentY)
				newTheta = 90;
			// robot turns to go down
			else if (y < currentY)
				newTheta = 270;
			// when there is no change in the y position
		} else if (Math.abs(deltaY) < 2) {
			// robot turns to go right
			if (x > currentX)
				newTheta = 0;
			// robot turns to go left
			else if (x < currentX)
				newTheta = 180;
			// if the nxt is going in the x pos, y pos
		} else if ((deltaX > 0) && (deltaY > 0)) {
			newTheta = Math.abs(Math.atan((deltaY) / (deltaX)));
			newTheta = newTheta * 180 / Math.PI;
			// when the nxt is going in the x neg, y pos
		} else if ((deltaX < 0) && (deltaY > 0)) {
			newTheta = Math.abs(Math.atan((deltaY) / (deltaX)));
			newTheta = 180 - (newTheta * 180 / Math.PI);
			// when the nxt is going in the x pos, y neg
		} else if ((deltaX > 0) && (deltaY < 0)) {
			newTheta = -Math.abs(Math.atan((deltaY) / (deltaX)));
			newTheta = newTheta * 180 / Math.PI;
			// when the nxt is going in the x neg, y neg
		} else if ((deltaX < 0) && (deltaY < 0)) {
			newTheta = Math.abs(Math.atan((deltaY) / (deltaX)));
			newTheta = 180 + (newTheta * 180 / Math.PI);
		}

		// make the robot turn
		turnTo(newTheta);
		

		// get the distance the robot needs to travel
		double distance = Math.sqrt((deltaY) * (deltaY) + (deltaX) * (deltaX));

		// make the robot go forward
		leftMotor.setSpeed(FORWARD_SPEED);
		rightMotor.setSpeed(FORWARD_SPEED);

		// make the robot go the distance
		leftMotor.rotate(convertDistance(radius, distance), true);
		rightMotor.rotate(convertDistance(radius, distance), false);
	}

	/**
	 * Turn to.
	 *
	 * @param theta the angle to turn to
	 */
	public  void turnTo(double theta) {
		// get the current angle
		currentTheta = odometer.getTheta();
		// get the difference in angle
		double deltaT = currentTheta - theta;
		// find the shortest distance
		while (deltaT > 180)
			deltaT -= 360;
		while (deltaT < -180)
			deltaT += 360;
		// set the speed to rotate speed
		leftMotor.setSpeed(ROTATE_SPEED);
		rightMotor.setSpeed(ROTATE_SPEED);
		// rotate the robot
		leftMotor.rotate(convertAngle(radius, width, deltaT), true);
		rightMotor.rotate(-convertAngle(radius, width, deltaT), false);
	}

	// get the distance
	private static int convertDistance(double radius, double distance) {
		return (int) ((180.0 * distance) / (Math.PI * radius));
	}

	// get the angle
	private static int convertAngle(double radius, double width, double angle) {
		return convertDistance(radius, Math.PI * width * angle / 360.0);
	}
	
	public Odometer getOdo(){
		return this.odometer;
	}
	
	public char getDes(){
		return this.des;
	}

}
