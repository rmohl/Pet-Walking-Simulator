import processing.core.*;


/**
 * Tile
 * 
 * Class that creates each tile that is used in
 * maps. Determines all fields of each tile.
 * 
 * @author Rachael Mohl
 */


public class Tile extends PApplet{
		
	//Tile colour variables:
	
	private int accessPrint;		
	private int nonAccessPrint;		

	//Boolean value variables:
	
	private boolean accessible;
	private boolean playerOn;
	private boolean endTile;
	private boolean enemyTile;
	private boolean grass;
	
	private PApplet app;
	
	public Tile (PApplet p, int accessPrint, int nonAccessPrint){
		
		this.app = p;
		
		this.accessPrint = accessPrint;
		this.nonAccessPrint = nonAccessPrint;
		accessible = true;
		playerOn = false;
		endTile = false;
		enemyTile = false;
		grass = false;
		
	}

	//All setters and getters:
	
	public int getAccessPrint() {
		return accessPrint;
	}
	
	public int getNonAccessPrint() {
		return nonAccessPrint;
	}

	public boolean isAccessible() {
		return accessible;
	}

	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}
	
	public boolean isGrass() {
		return grass;
	}

	public void setGrass(boolean grass) {
		this.grass = grass;
	}

	public boolean isPlayerOn() {
		return playerOn;
	}

	public void setPlayerOn(boolean playerOn) {
		this.playerOn = playerOn;
	}
	
	public boolean isEndTile() {
		return endTile;
	}

	public void setEndTile(boolean endTile) {
		this.endTile = endTile;
	}

	public boolean isEnemyTile() {
		return enemyTile;
	}

	public void setEnemyTile(boolean enemyTile) {
		this.enemyTile = enemyTile;
	}
	
	/**
	 * Determines every tile's colour. Used in the printMap()
	 * method so the computer knows what colour each tile is.
	 * 
	 * @return int (color of objects are stored in the int variable)
	 */

	public int tileColor() {
		
		if (this.isEndTile()) {						//Printing end tile.
			return app.color(255,0,0);
		}
		if (this.isEnemyTile() && this.isPlayerOn()) {		//Printing enemy tiles tile.
			return app.color(75,0,130);
		}
		else if (this.isGrass()){
			return app.color(184,218,149);
		}
		else if (this.isAccessible()){				//Printing accessible tiles.
			return app.color(this.getAccessPrint());
		}
		else{								//Printing non-accessible tiles.
			return app.color(this.getNonAccessPrint());
		}
	
	}
	
}
