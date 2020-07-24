import processing.core.*;


/**
 * Map()
 * 
 * Creates map objects, which hold tiles in
 * 2D arrays. Superclass of GameMap and 
 * HomeMap.
 * 
 * @author Rachael Mohl
 */


public class Map extends PApplet{
	
	private String name;
	public Tile mapTiles [][];
	private int playerX;
	private int playerY;
	private int startX;
	private int startY;
	
	private String commandRight = "d";
	private String commandLeft = "a";
	private String commandUp = "w";
	private String commandDown = "s";
	
	private PApplet app;

	
	public Map (PApplet p, String name, int accessPrint, int nonAccessPrint, int startX, int startY) {
		
		this.app = p;
		
		this.name = name;
		
		mapTiles = new Tile[15][25];								//Creating tiles for each point on the map (y,x).
		for (int y = 0; y < mapTiles.length; y++) {
			for (int x = 0; x < mapTiles[y].length; x++) {
				Tile t = new Tile(app, accessPrint, nonAccessPrint);
				mapTiles[y][x] = t;
			}
		}
		
		this.startX = startX;
		this.startY = startY;
		playerX = startX;
		playerY = startY;
		mapTiles[playerY][playerX].setPlayerOn(true);				//Setting player's starting point.
		
	}

	public String getName() {
		return name;
	}

	public int getPlayerX() {
		return playerX;
	}

	public int getPlayerY() {
		return playerY;
	}
	
	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}
	
	public Tile getTile(int x, int y) {
		return mapTiles[y][x];
	}
	
	public Tile[][] getMapTiles(){
		return this.mapTiles;
	}

	
	/**
	 * printMap()
	 * 
	 * Method that prints all maps.
	 * 
	 */
	
	
	public void printMap() {
		
		for (int y = 0; y < mapTiles.length; y++) {
			for(int x = 0; x < mapTiles[y].length; x++) {
				app.strokeWeight(0);
				app.stroke(mapTiles[y][x].tileColor());
				app.fill(mapTiles[y][x].tileColor());
				app.rect(x*50,y*50,50,50);
				if (mapTiles[y][x].isPlayerOn()) {		//Prints player as a circle.
					app.fill(241,144,141);
					app.ellipse(x*50+25, y*50+25, 40,40);
					//app.image(img, x*50, y*50, 50, 50);
				}
				
			}
				
		}
		
	}
	
	
	/**
	 * SetPlayer()
	 * 
	 * Allows for the player to be set anywhere on the map.
	 * 
	 * @param x
	 * @param y
	 */
	
	
	public void setPlayer(int x, int y) {
		
		if (y > -1 && y < mapTiles.length && x > -1 && x < mapTiles[y].length && mapTiles[y][x].isAccessible()) {		//Checks if tile is part of map and if tile is accessible.
			
			mapTiles[playerY][playerX].setPlayerOn(false);		//Changes the tile that the player is set on.
			mapTiles[y][x].setPlayerOn(true);
			
			playerX = x;
			playerY = y;
			System.out.println("Player moved.");
			
		}
		
		else {
			
			System.out.println("You cannot go there.");
			
		}
		
	}
	
	
	/**
	 * playerInput()
	 * 
	 * Organizes all user input/decides what
	 * the user wants.
	 * 
	 * @param command
	 */
	
	
	public void playerInput (String command) {
		
		if (command.equals(commandRight)) {			//Move right.
			setPlayer(playerX + 1,playerY);
		}
		
		else if (command.equals(commandLeft)) {		//Move left.
			setPlayer(playerX - 1,playerY);
		}
		
		else if (command.equals(commandUp)) {		//Move up.
			setPlayer(playerX,playerY - 1);
		}
		
		else if (command.equals(commandDown)) {		//Move down.
			setPlayer(playerX,playerY + 1);
		}

		else {		//Command not recognized.
			System.out.println("Did not recognize command. Please use \"" + commandUp + "\", \""+ commandLeft + "\", \""+ commandDown +"\", or \""+ commandRight +"\".");
		}
		
	}
	
	public void notAccessible(int x, int y) {			//Used to set specific tiles on map to unaccessible.
		
		mapTiles[y-1][x-1].setAccessible(false);
		
	}
	
	public void setAccessible(int x, int y) {			//Used to set specific tiles on map to unaccessible.
		
		mapTiles[y-1][x-1].setAccessible(true);
		
	}
	
	public void setGrass(int x, int y) {			//Used to set specific tiles on map to grass tiles.
		
		mapTiles[y-1][x-1].setGrass(true);
		
	}
	
}
