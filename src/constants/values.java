package constants;

import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;


public class values {
	
	public static double radius = 1.95;
	public static double width = 21.5; 
	public static NXTRegulatedMotor leftMotor = Motor.A;
	public static NXTRegulatedMotor rightMotor = Motor.B;
	public static double squareLength = 30; 
	public static int forwardSpeed = 300;
	public static  int turnSpeed = 150; 
	public static  ColorSensor csObject = new ColorSensor (SensorPort.S1); 
	public static  ColorSensor csLeft =new ColorSensor (SensorPort.S1);  
	public static  ColorSensor csRight =new ColorSensor (SensorPort.S1); 
	public static   UltrasonicSensor usN = new UltrasonicSensor(SensorPort.S1) ; 
	public static   UltrasonicSensor usE= new UltrasonicSensor(SensorPort.S1) ; 
	public static   UltrasonicSensor usW= new UltrasonicSensor(SensorPort.S1) ; 
	public static int maxValue = 255;
	

}
