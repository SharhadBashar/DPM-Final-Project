package Board;
import java.util.Arrays;

public class Map {
	/** The map*/
	public Tiles[][] mapT;
	/** The size of the map*/
	int sizeX, sizeY;

	/**
	 * Instantiates a new Map of size XxY and has obstacle in the positions given by position[][]
	 * @param position position of obstacles [[x1;y1];[x2;y2];[...]]
	 * @param sizeX the size x of the map
	 * @param sizeY the size y of the map
	 */
	public Map(int[][] position, int sizeX, int sizeY) {
		mapT = new Tiles[sizeY][sizeX];
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		int k = 0;
		for (int i = 0; i < sizeY; i++) {
			for (int j = 0; j < sizeX; j++) {
				Tiles t;
				if (position[k][0] == i && position[k][1] == j) {
					t = new Tiles(true);
					if (k < position.length - 1) {
						k++;
					}
				} else {
					t = new Tiles(false);
				}
				mapT[i][j] = t;
			}
		}
		connectTiles(this);

	}
	
	/**
	 * main method
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		int[][] position = { { 0, 0 }, {1,2}, { 1, 3 },{3,1} };
		Map map1 = new Map(position, 4, 4);
		System.out.println("your obstacle are at the position");
		map1.printArray(position);
		map1.printMap();
	/*	boolean ffN = map1.mapT[0][0].getEst().getEst().getSouth().isPosN();
		boolean ffS = map1.mapT[0][0].getEst().getEst().getSouth().isPosS();
		boolean ffW = map1.mapT[0][0].getEst().getEst().getSouth().isPosW();
		boolean ffE = map1.mapT[0][0].getEst().getEst().getSouth().isPosE();
		boolean ffb = map1.mapT[0][0].getEst().getEst().getSouth().getSouth().obstacle;
		boolean ffa = map1.mapT[0][0].getEst().getEst().getSouth().getSouth().getEst().obstacle;
		System.out.print(ffN + " " + ffS + " " + ffW+" "+ffE);*/

	}

	/**
	 * printMap
	 * use the map context and prints it
	 * mapX.printMap();
	 */
	
	public void printMap() {
		for (int i = 0; i < this.sizeY; i++) {
			for (int k = 0; k < 3; k++) {
				for (int j = 0; j < this.sizeX; j++) {
					if (k == 0) {
						if (this.mapT[i][j].posN) {
							System.out.print("|  |  |");
						} else {
							System.out.print("|     |");
						}
					}

					if (k == 1) {
						if (this.mapT[i][j].obstacle == true) {

								System.out.print("|  X  |");

						} else {
							if (this.mapT[i][j].posW && !this.mapT[i][j].posE) {
								System.out.print("|-    |");
							} else if (this.mapT[i][j].posE && !this.mapT[i][j].posW) {
								System.out.print("|    -|");
							} else if (this.mapT[i][j].posW
									&& this.mapT[i][j].posE) {
								System.out.print("|-   -|");
							} else {
								System.out.print("|     |");
							}
						}
					}
					if (k == 2) {
						if (this.mapT[i][j].posS) {
							System.out.print("|  |  |");
						} else {
							System.out.print("|     |");
						}
					}
				}
				System.out.println("");
			}
		}
	}
	
	/**
	 * printArray 
	 * print a table of each cell in the double array
	 * @param position double array to print
	 */
	public void printArray(int[][] position) {
	    for (int[] row : position) 
	        System.out.println(Arrays.toString(row));       
	}

	/**
	 * connectTiles( map) 
	 * connects every tiles in the map
	 * get the initial position and say if it sees a wall or not and then do the first step of eliminating possible positions
	 * @param map
	 */
	
	public void connectTiles(Map map) {
		Tiles[][] locMap = map.mapT;
		for (int i = 0; i < map.sizeY; i++) {
			for (int j = 0; j < map.sizeX; j++) {
				Tiles t = locMap[i][j];
				if (i == 0 && j == 0) {
					t.setNorth(null);
					t.setWest(null);
					t.setEst(locMap[i][j + 1]);
					t.setSouth(locMap[i + 1][j]);

				} else if (i == map.sizeY - 1 && j == map.sizeX - 1) {
					t.setSouth(null);
					t.setEst(null);
					t.setWest(locMap[i][j - 1]);
					t.setNorth(locMap[i - 1][j]);

				} else if (i == 0 && j == map.sizeX - 1) {
					t.setNorth(null);
					t.setEst(null);
					t.setWest(locMap[i][j - 1]);
					t.setSouth(locMap[i + 1][j]);

				} else if (i == map.sizeY - 1 && j == 0) {
					t.setSouth(null);
					t.setWest(null);
					t.setEst(locMap[i][j + 1]);
					t.setNorth(locMap[i - 1][j]);

				} else if (i == 0 && j > 0 && j < map.sizeX) {
					t.setNorth(null);
					t.setWest(locMap[i][j - 1]);
					t.setEst(locMap[i][j + 1]);
					t.setSouth(locMap[i + 1][j]);
				} else if (i > 0 && i < map.sizeY && j == 0) {
					t.setWest(null);
					t.setNorth(locMap[i - 1][j]);
					t.setSouth(locMap[i + 1][j]);
					t.setEst(locMap[i][j + 1]);

				} else if (i == map.sizeY - 1 && j > 0 && j < map.sizeX) {
					t.setSouth(null);
					t.setWest(locMap[i][j - 1]);
					t.setEst(locMap[i][j + 1]);
					t.setNorth(locMap[i - 1][j]);

				} else if (i > 0 && i < map.sizeY && j == map.sizeX - 1) {
					t.setEst(null);
					t.setNorth(locMap[i - 1][j]);
					t.setSouth(locMap[i + 1][j]);
					t.setWest(locMap[i][j - 1]);
				} else {
					t.setNorth(locMap[i - 1][j]);
					t.setSouth(locMap[i + 1][j]);
					t.setEst(locMap[i][j + 1]);
					t.setWest(locMap[i][j - 1]);

				}
				if(t.obstacle){
					t.noPoss();
				}
				//System.out.println(t.isPosN()+" "+t.isPosS()+" "+t.isPosE()+" "+t.isPosW());


			}
		}

	}

}