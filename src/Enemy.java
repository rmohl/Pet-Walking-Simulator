import processing.core.PApplet;
import processing.core.PImage;


/**
 * Enemy()
 * 
 * Creates enemy character.
 * 
 * @author Rachael Mohl
 */


public class Enemy extends Character{
	
	private String winStatement;
	private String loseStatement;
	private int enemyAttack;
	private Character [] enemyVictim = new Character [2];
	
	private PApplet app;
	private PImage img;
	
	public Enemy (PApplet app, String name, String des, int hp, int att, String win, String lose, PImage img) {
		
		super (name, des, hp, att);
		winStatement = win;
		loseStatement = lose;
		this.img = img;
		
		this.app = app;
		
	}

	
	//Getters and setters:
	
	public String getWinStatement() {
		return winStatement;
	}

	public void setWinStatement(String winStatement) {
		this.winStatement = winStatement;
	}

	public String getLoseStatement() {
		return loseStatement;
	}

	public void setLoseStatement(String loseStatement) {
		this.loseStatement = loseStatement;
	}
	public PImage getImage() {
		return this.img;
	}

	public void setImage(PImage img) {
		this.img = img;
	}

}
