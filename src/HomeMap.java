import processing.core.*;


/**
 * HomeMap()
 * 
 * Creates HomeMap object, useful for manipulating 
 * tiles and designing the map.
 * 
 * @author Rachael Mohl
 */


public class HomeMap extends Map{
	
	public HomeMap(PApplet p, String n, int accessPrint, int nonAccessPrint, int startX, int startY) {
		
		super(p, n, accessPrint, nonAccessPrint, startX, startY);
		
		//Setting the non-accessible tiles:
		
		for (int i = 4; i < 12; i++) {
			this.notAccessible(i, 4);
		}
		for (int i = 15; i < 23; i++) {
			this.notAccessible(i, 4);
		}
		for (int i = 4; i < 23; i++) {
			this.notAccessible(i, 10);
		}
		for (int i = 5; i < 10; i++) {
			this.notAccessible(4, i);
		}
		for (int i = 5; i < 10; i++) {
			this.notAccessible(22, i);
		}
		
		//Setting grass tiles:
		
		for (int i = 1; i < 26; i++) {
			for(int a = 1; a < 4; a++) {
				this.setGrass(i, a);
			}
		}
		for (int i = 1; i < 26; i++) {
			for(int a = 11; a < 16; a++) {
				this.setGrass(i, a);
			}
		}
		
		for (int i = 1; i < 4; i++) {
			for(int a = 4; a < 11; a++) {
				this.setGrass(i, a);
			}
		}
		
		for (int i = 23; i < 26; i++) {
			for(int a = 4; a < 11; a++) {
				this.setGrass(i, a);
			}
		}
		
	}

}
