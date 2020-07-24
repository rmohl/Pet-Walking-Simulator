

/**
 * Pet()
 * 
 * Creates pet character.
 * 
 * @author Rachael Mohl
 */


public class Pet extends Character{
	
	private String owner;
	private String type; 
	private String sound;
	private String favouritePlace;
	
	public Pet (String name, String des, int hp, int att, String owner, String type, String sound, String place) {
		
		super (name, des, hp, att);
		this.owner = owner;
		this.type = type;
		this.sound = sound;
		favouritePlace = place;
		
	}
	
	
	//Getters and setters:
	
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public String getFavouritePlace() {
		return favouritePlace;
	}

	public void setFavouritePlace(String favouritePlace) {
		this.favouritePlace = favouritePlace;
	}

}
