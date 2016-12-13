package Board;

// TODO: Auto-generated Javadoc
/**
 * The Class Tiles.
 */
public class Tiles {
	/** connected Tiles. each tile is connected to 4 other tiles*/
	public Tiles North,South,Est,West;
	
	/**  boolean if there is an obstacle in the tile. */
	public boolean obstacle;
	
	/**  possible location with heading. */
	public boolean posN=true,posS=true,posE=true,posW=true;
	
	
	/**
	 * Instantiate a tile with the info of a obstacle.
	 *
	 * @param obst the obst
	 */
	public Tiles(boolean obst){
		this.obstacle=obst;

	}
	
	/**
	 * getter of the north tile.
	 *
	 * @return the north
	 */
	public Tiles getNorth(){
		return North;
	}
	
	/**
	 * setter of the north tile.
	 *
	 * @param t the new north
	 */
	
	public void setNorth(Tiles t){
		this.North=t;
		/*if(t.getSouth() != this){
					t.setSouth(this);
		}*/
	}
	
	/**
	 * getter of the south tile.
	 *
	 * @return the south
	 */
	public Tiles getSouth(){
		return South;
	}
	
	/**
	 * setter of the south tile.
	 *
	 * @param t the new south
	 */
	public void setSouth(Tiles t){
		this.South=t;
		/*if(t.getNorth() != this){
			t.setNorth(this);
		}*/
	}
	
	/**
	 * getter of the est tile.
	 *
	 * @return the est
	 */
	public Tiles getEst(){
		return Est;
	}
	
	/**
	 * setter of the est tile.
	 *
	 * @param t the new est
	 */
	public void setEst(Tiles t){
		this.Est=t;
		/*if(t.getWest() != this){
			t.setWest(this);
		}*/
	}
	
	/**
	 * getter of the west tile.
	 *
	 * @return the west
	 */
	public Tiles getWest(){
		return West;
	}
	
	/**
	 * setter of the west tile.
	 *
	 * @param t the new west
	 */
	public void setWest(Tiles t){
		this.West=t;
		/*if(t.getEst() != this){
			t.setEst(this);
		}*/
	}
	
	/**
	 * getter of the north position.
	 *
	 * @return true, if is pos n
	 */
	public boolean isPosN() {
		return posN;
	}
	
	/**
	 * setter of the north position.
	 *
	 * @param posN the new pos n
	 */
	public void setPosN(boolean posN) {
		this.posN = posN;
	}

	/**
	 * getter of the south position.
	 *
	 * @return true, if is pos s
	 */
	public boolean isPosS() {
		return posS;
	}

	/**
	 * setter of the south position.
	 *
	 * @param posS the new pos s
	 */
	public void setPosS(boolean posS) {
		this.posS = posS;
	}

	/**
	 * getter of the est position.
	 *
	 * @return true, if is pos e
	 */
	public boolean isPosE() {
		return posE;
	}

	/**
	 * setter of the est postion.
	 *
	 * @param posE the new pos e
	 */
	public void setPosE(boolean posE) {
		this.posE = posE;
	}

	/**
	 * getter of the west position.
	 *
	 * @return true, if is pos w
	 */
	public boolean isPosW() {
		return posW;
	}

	/**
	 * setter of the west position.
	 *
	 * @param posW the new pos w
	 */
	public void setPosW(boolean posW) {
		this.posW = posW;
	}

	/**
	 * make every position of a tile false if there is a obstacle in it.
	 */
	public void noPoss(){
		this.posN=false;
		this.posS=false;
		this.posE=false;
		this.posW=false;
	}
	
	
}
