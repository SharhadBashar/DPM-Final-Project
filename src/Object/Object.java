/**
 * Created November 4 2014
 * @author Phil Douyon
 */
package Object;
import constants.values;
import lejos.nxt.ColorSensor;
import lejos.nxt.SensorPort;

/**
 * The Class Object.
 */
public class Object {
	
	private ColorSensor cs = values.csObject;
	
	private boolean holdObject;
	
	/**
	 * The Enum color.
	 */
	public enum color {
/** The red. */
RED, 
 /** The blue. */
 BLUE, 
 /** The green. */
 GREEN, 
 /** The Yellow. */
 Yellow};
 
 
	private Object.color color; 
	
	/** The points. */
	int points; 
	
	
	/**
	 * Pick up.Pick Up the object
	 */
	public void pickUp(){
		
		setColor();
	}
	
	/**
	 * Drop off. Drop off the object
	 */
	public void dropOff(){
		
	}
	
	/**
	 * Checks if the robot is holding the object.
	 *
	 * @return true, if robot has the object object
	 */
	public boolean isHoldObject(){
		return holdObject;
	}
	
	/**
	 * Gets the points.
	 *
	 * @return the points of the object
	 */
	public int getPoints(){
		return points; 
	}

	/**
	 * Gets the color.
	 *
	 * @return the color of the object
	 */
	public Object.color getColor(){
		return color; 
	}
	
	private void setColor(){
		cs.setFloodlight(true);
		int color = findColor();
		//TODO: set color to color.
		//TODO: set the points and holding
	}

	private int findColor() {
		int curr = 0; 
		for (int i = 0; i< 20; i++){
			curr += cs.getColorID();
		}
		return curr/20; 
	}
}
