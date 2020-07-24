

/**
 * Player()
 * 
 * Creates user's character.
 * 
 * @author Rachael Mohl
 */


public class Player extends Character{
	
	private Pet pet;
	private int level = 1;
	
	public Player (String name, String des, int hp, int att, Pet pet) {
		
		super (name, des, hp, att);
		this.pet = pet;
		
	}
	
	
	//Getters and setters

	public Pet getPet() {
		return pet;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}
