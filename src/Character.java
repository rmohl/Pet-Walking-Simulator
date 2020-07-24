import java.io.Serializable;


/**
 * Character()
 * 
 * Superclass of Player, Pet, and Enemy classes.
 * 
 * @author Rachael Mohl
 */


public class Character implements Serializable{
	
	private String name;
	private String description;
	private int hp;
	private int hpCap;
	private int att;
	private boolean alive;
	
	public Character(String name, String des, int hp, int att) {
		
		this.name = name;
		description = des;
		this.hp = hp;
		hpCap = hp;
		this.att = att;
		alive = true;
		
	}
	
	//Getters and setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getHpCap() {
		return hpCap;
	}

	public void setHpCap(int cap) {
		this.hpCap = cap;
	}

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		this.att = att;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public int getHp() {
		return hp;
	}

	
	/**
	 * setHP()
	 * 
	 * Sets a character's health (good for 
	 * attack sequences and refilling 
	 * health).
	 * 
	 * @param hp
	 */
	
	
	public void setHp(int hp) {		//Sets HP and changes the Character's alive value.
		this.hp = hp;
		if(this.hp < 1) {
			this.setAlive(false);
			this.hp = 0;
			System.out.println("\n"+this.getName()+" has been knocked out!");
		}
	}
	
	
	/**
	 * attack()
	 * 
	 * Executes a character's basic attack against
	 * an opponent.
	 * 
	 * @param boi
	 */
	
	
	public void attack(Character boi) {			
		boi.setHp(boi.getHp()-this.getAtt());
	}

}