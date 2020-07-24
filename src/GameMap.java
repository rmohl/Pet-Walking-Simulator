import java.util.Scanner;
import processing.core.*;


/**
 * GameMap()
 * 
 * Creates GameMap object. Used to change tile
 * values and edit maps.
 * 
 * @author Rachael Mohl
 */


public class GameMap extends Map{

	Scanner sc = new Scanner (System.in);		//For battle sequence method.
	
	private Tile gameMapTiles[][];
	private int endX;
	private int endY;
	private int encounterChance;			//Ranges from 0 - 100, this is the percent chance of an enemy occurrence on any accessible tile on the map.
	private int level;
	
	private PApplet app;
	
	public GameMap(PApplet p, int level, String name, int accessPrint, int nonAccessPrint, int startX, int startY, int endX, int endY, int ec) {
		
		super(p, name, accessPrint, nonAccessPrint, startX, startY);
		
		this.app = p;
		
		gameMapTiles = this.getMapTiles();
		
		this.level = level;
		encounterChance = ec;
		this.endX = endX;
		this.endY = endY;
		gameMapTiles[endY][endX].setEndTile(true);
		
		if (this.level == 1) {
			
			//Setting up all tiles for each level of game maps:
			
			for (int a = 1; a < 15; a++) {
				this.notAccessible(14, a);
			}
			for (int a = 2; a < 13; a++) {	
				for (int b = 2; b < 15; b++) {
					this.notAccessible(a, b);
				}
			}
			
			for (int a = 16; a < 25; a++) {	
				for (int b = 2; b < 15; b++) {
					this.notAccessible(a, b);
				}
			}
			this.notAccessible(13, 14);
			this.notAccessible(15, 12);
			this.notAccessible(15, 14);
			
			for(int i = 3; i < 13; i++) {
				this.setAccessible(i, 11);
			}
			for(int i = 3; i < 13; i++) {
				this.setAccessible(3, i);
			}
			for(int i = 3; i < 12; i++) {
				this.setAccessible(i, 4);
			}
			for(int i = 5; i < 12; i++) {
				this.setAccessible(11, i);
			}
			for(int i = 14; i < 24; i++) {
				this.setAccessible(i, 13);
			}
			for(int i = 3; i < 14; i++) {
				this.setAccessible(23, i);
			}
			for(int i = 17; i < 23; i++) {
				this.setAccessible(i, 3);
			}
			for(int i = 3; i < 12; i++) {
				this.setAccessible(17, i);
			}
			for(int i = 17; i < 21; i++) {
				this.setAccessible(i, 11);
			}
			for(int i = 6; i < 12; i++) {
				this.setAccessible(20, i);
			}
			
		}
		
		else if (this.level == 2){
			this.notAccessible(22, 14);
			this.notAccessible(12, 6);
			this.notAccessible(14, 4);
			this.notAccessible(3, 7);
			this.notAccessible(9, 9);
			this.notAccessible(24, 10);
			this.notAccessible(18, 8);
			this.notAccessible(15, 13);
			this.notAccessible(7, 6);
			this.notAccessible(15, 1);
			this.notAccessible(3, 11);
			this.notAccessible(23, 3);
			this.notAccessible(25, 6);
			this.notAccessible(2, 13);
			this.notAccessible(5, 2);
			this.notAccessible(5, 12);
			this.notAccessible(9, 11);
			this.notAccessible(20, 9);
			this.notAccessible(13, 10);
		}
		
	}

	public int getEndX() {
		return this.endX;
	}

	public int getEndY() {
		return this.endY;
	}
	
	public int getLevel() {
		return this.level;
	}

	
	/**
	 * setEnemies()
	 * 
	 * Sets all enemies in a map. (First level is
	 * pre-determined enemy positions, all levels
	 * after are randomized).
	 * 
	 */
	
	
	public void setEnemies() {
		
		if(this.getLevel() == 1) {				//Level 1 enemies have predetermined positions, all other levels have enemies with randomly determined positions.
			gameMapTiles[0][16].setEnemyTile(true);
			gameMapTiles[14][8].setEnemyTile(true);
		}
		
		else {			//For each tile, a random number (1-100) will be determined. If the map's encounterChance is greater than or equal to this random number, an enemy will be set.
			for (int y = 0; y < gameMapTiles.length; y++) {
				for(int x = 0; x < gameMapTiles[y].length; x++) {
					
					if (gameMapTiles[y][x].isAccessible() && !(x == this.getPlayerX() && y == this.getPlayerY()) && !(x == this.endX && y == this.endY)) {	//Ensures that the tile is accessible, not the start tile, and not the finish tile.
						
						if (encounterChance >= (int)(Math.random()*100+1)) {			
							gameMapTiles[y][x].setEnemyTile(true);
						}
					}
				}
			}
		}
	}
}
