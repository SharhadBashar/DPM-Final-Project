package Board;

//Sharhad Bashar
//Micheal Abdallah
//the algothirm code
import java.util.ArrayList;

import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.UltrasonicSensor;

public class Board_Movement {

	
	private static UltrasonicSensor US = new UltrasonicSensor(SensorPort.S1);
	
	private static Map mapClass;
	private static Tiles[][] map;

	
	private static int xCount;
	private static int yCount;
	private static String heading;
	
	private static int iterationCounter = 0;
	
	//initialize array with every possible initial orientation
	private static ArrayList<Boolean> path = new ArrayList<Boolean>();
	//initialize arraylist of path of robot
	private static ArrayList<Double> distances = new ArrayList<Double>();
	//initialize arraylist of simulated paths
	private static ArrayList<Boolean> pathA = new ArrayList<Boolean>();
	
	private static String numRemaining = "";
	
	
	public Board_Movement(int[][] position, int sizeX, int sizeY){
		mapClass= new Map(position,sizeX,sizeY);
		map=mapClass.mapT;
	}
	public Board_Movement(Map givMap){
		mapClass= givMap;
		map=mapClass.mapT;
	}
	
	//executes computations and determines where the robot should go
	public static void run(boolean ifDeterministic) {
		
		boolean finished = false;
		
		distances.add(US.getDistance()+0.0);
		simulate();
		LCD.clear();
		numRemaining = calculateRemaining() + "";
		LCD.drawString("Remaining: " + numRemaining, 0, 5);
		LCD.drawString("Read Dist: " + US.getDistance(), 0, 6);
		
		
		
		//while not finished, code keeps executing
		//iteration limit at 20 in case code malfunctions and runs forever
		while (finished == false && iterationCounter < 20) {
						
			// if wall, rotate left
			if (US.getDistance()+0.0 < 35) { 			
				rotateCCW();
				iterationCounter = path.size();
				distances.add(US.getDistance()+0.0);
				simulate();	
				numRemaining = calculateRemaining() + "";
			}
			// if no wall, rotate or move forward				
			else {
					travelThirty(); //travel 30 cm

				iterationCounter = path.size();
				distances.add(US.getDistance()+0.0);
				simulate();	
				numRemaining = calculateRemaining() + "";
				LCD.clear();
				LCD.drawString("Remaining: " + numRemaining, 0, 4);
				LCD.drawString("Read Dist: " + US.getDistance(), 0, 5);
				Sound.beep();
			}
			
			iterationCounter = path.size();
			
			simulate();			
			finished = ifFinished(); //if finished, sets it to true
		}	
		
		Sound.buzz(); // indicates end of determining initial orientation
		getXYT();//gets the location and heading
	}
	
	//calculate number of remaining possible initial orientation
	public static int calculateRemaining() {
		int counter = 0;
		
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
					if (map[i][j].isPosN() == true) {
						counter++;
					}
					else if (map[i][j].isPosS() == true) {
						counter++;
					}
					else if (map[i][j].isPosE() == true) {
						counter++;
					}
					else if (map[i][j].isPosW() == true) {
						counter++;
					}
			}
		}
		return counter;
	}
	
	// for remaining possible starting positions, simulate if matches real orientation
	public static void simulate() {
		int counter = 0;
		int a, b;
		String c;
		int[] temp = new int[2];
		
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				for (int k=0; k<4; k++) { 
					
					a = i;
					b = j;
					if (k == 0) {c="North";}
					else if (k == 1) {c="West";}
					else if (k == 2) {c="South";}
					else {c="Est";}
					
					while (counter < iterationCounter) {
						
						// if simulated paths of every remaining initial orientation does not match
						// actual path of robot, then set to false
						if (path.get(counter) == true) {
							if ( (calculateArbDistance(a,b,c) == 50 && distances.get(counter) < 35)
									|| (calculateArbDistance(a,b,c) == 5 && distances.get(counter) >= 35) ) {
								if (k == 0) {
									map[i][j].setPosN(false);
								}
								else if (k == 1) {
									map[i][j].setPosW(false);
								}
								else if (k == 2) {
									map[i][j].setPosS(false);
								}
								else {
									map[i][j].setPosE(false);
								}
							}
							c = rotateACCW(c);
						}
						else {
							if ( (calculateArbDistance(a,b,c) == 50 && distances.get(counter) < 35) 
									|| (calculateArbDistance(a,b,c) == 5 && distances.get(counter) >= 35) ) {
								if (k == 0) {
									map[i][j].setPosN(false);
								}
								else if (k == 1) {
									map[i][j].setPosW(false);
								}
								else if (k == 2) {
									map[i][j].setPosS(false);
								}
								else {
									map[i][j].setPosE(false);
								}							}
							temp = travelAThirty(a,b,c);
							a = temp[0];
							b = temp[1];
						}
						counter++;
					}
					
					if ( (calculateArbDistance(a,b,c) == 50 && distances.get(counter) < 35) 
							|| (calculateArbDistance(a,b,c) == 5 && distances.get(counter) >= 35) ) { 
						if (k == 0) {
							map[i][j].setPosN(false);
						}
						else if (k == 1) {
							map[i][j].setPosW(false);
						}
						else if (k == 2) {
							map[i][j].setPosS(false);
						}
						else {
							map[i][j].setPosE(false);
						}					}
					counter = 0;
					clearAPath();
				}
			}
		}
	}
	//displays the original position
	//i represents row
	//j represents column
	//k represents direction (0 N, 1 W, 2 S, 3 E)
	public static String displayFinal() {
		
		String originalPosition = "";
		
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
					if (map[i][j].isPosN() == true) {
						originalPosition = i + "," + j + ", North";
					}
					else if (map[i][j].isPosS() == true) {
						originalPosition = i + "," + j + ", South";
					}
					else if (map[i][j].isPosE() == true) {
						originalPosition = i + "," + j + ", Est";
					}
					else if (map[i][j].isPosW() == true) {
						originalPosition = i + "," + j + ", West";
					}
				
			}
		}
		
		return originalPosition;
		
	}
	
	//checks if only one possible starting position remains
	public static boolean ifFinished() {
		int counter = calculateRemaining();
		if (counter == 1) {
			return true;
		}
		return false;
	}
	
	//empties arraylist that stores path for each possible orientation
	public static void clearAPath() {
		while (!(pathA.isEmpty())) {
			pathA.remove(0);
		}
	}
	
	
	//robot rotates counter-clockwise
	public static void rotateCCW() {
		path.add(true);
	}
	//simulated robot rotates counter-clockwise
	public static String rotateACCW(String t) {
		if (t == "Est") {
			t = "North";
		}
		else if (t == "North") {
			t = "West";
		}
		else if (t == "West") {
			t = "Est";
		}
		else {
			t = "South";
		}
		pathA.add(true);
		return t;
	}
	
	//robot moves forward by 30 cm
	public static void travelThirty() {
		path.add(false);
	}
	
	//simulated robot moves forward by 30 cm
	public static int[] travelAThirty(int x, int y, String t) {
		if (t == "North") {
			x--;
		}
		else if (t == "West") {
			y--;
		}
		else if (t == "South") {
			x++;
		}
		else {
			y++;
		}
		pathA.add(false);
		
		int[] output = new int[2];
		output[0] = x; 
		output[1] = y;
		
		return output;
	}
	
	//determines distance in front of simulated robot
	public static int calculateArbDistance(int i, int j, String k) {
		int arbDistance = 0;
		
		int xLook = i;
		int yLook = j;
		String tLook = k;
		
		if (tLook == "North") {
			xLook--;
		}
		else if (tLook == "West") {
			yLook--;
		}
		else if (tLook == "South") {
			xLook++;
		}
		else {
			yLook++;
		}
		
		if ( (xLook==0 && yLook==0) || (xLook==1 && yLook==2) || (xLook==1 && yLook==3) || (xLook==3 && yLook==1)
				|| (xLook==-1) || (xLook==4) || (yLook==-1) || (yLook==4) ) {
			arbDistance = 5;
		}
		else {
			arbDistance = 50;
		}
		return arbDistance;
	}
		
	//gets String that displays list of T's and M's
	//T represents turn
	//M represents translational movement of 30 cm
	public String getPath() {
		String pathString = "";
		for (int i=0; i<path.size(); i++) {
			if (path.get(i) == false) {
				pathString += "M";
			}
			else {
				pathString += "T";
			}
		}
		return pathString;
	}
	
	//gets orientation
	//i represents row
	//j represents column
	//k represents direction (0 N, 1 W, 2 S, 3 E)
	public static void getXYT() {
		
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				if (map[i][j].isPosN() == true) {
					xCount = i;
					yCount = j;	
					heading= "North";}
				else if (map[i][j].isPosS() == true) {
					xCount = i;
					yCount = j;
					heading = "South";
					}
				else if (map[i][j].isPosE() == true) {
					xCount = i;
					yCount = j;
					heading = "Est";
					}
				else if (map[i][j].isPosW() == true) {
					xCount = i;
					yCount = j;
					heading = "West";
					}
				
			}
		}
	}
	//gets row value
	public int getX() {
		return xCount;
	}
	//gets column value
	public int getY() {
		return yCount;
	}
	//gets direction value (0 N, 1 W, 2 S, 3 E)
	public String getHeading() {
		return heading;
	}
}
