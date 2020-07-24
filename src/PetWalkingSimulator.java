import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import processing.core.*;


/**
 * Pet Walking Simulator
 * Friday, January 25, 2019
 * 
 * THis program is a game in which one must create their 
 * character (choose name, pet's name, etc.) and fight 
 * enemies that are randomly placed on the map with their
 * pets.
 * 
 * @author Rachael Mohl
 */

public class PetWalkingSimulator extends PApplet {
	
	private String show = "titleScreen";
	private PFont f;
	private PImage img, img2, img3;

	
	//Variables for battle sequence
	
	private boolean h = true;					//If this is true, the player's home map will be printed on the screen.
	private boolean battleTime = false;			
	private boolean playerAttack = false;		
	private boolean defend = false;				
	private int oof;							//Used with "Cool Leash Whip" move to determine damage done.
	private int enemyAttack;
	private GameMap levelMap;
	private Enemy levelEnemy;
	private Character [] enemyVictim = new Character [2];
	

	//Variables used for intro visuals/error messages
	
	private String ans = "";
	private double opac = 0, opac2 = 0, opac3 = 0, opac4 = 0, opac5 = 0, opac6 = 0;			//Used for fade effect.
	private boolean reverseOpac = false;
	private boolean namer = false;
	private int timeStart;
	private boolean hasPet;
	
	private int intro = 0;
	private double count = 0;
	private boolean gameSaved = false;
	private boolean notSaved = false;
	private boolean noFile = false;
	private boolean lastLevel = false;

	
	//Variables for Player/player Pet creation
	
	Player player = null;
	Pet playerPet = null;
	HomeMap home = new HomeMap (this, "home", color(233,219,139), color(160,57,30), 12, 6);
	GameMap park = new GameMap (this, 1, "park", color(186,220,137), color(128,179,114), 12, 12, 14, 10, 5);
	GameMap park2 = new GameMap (this, 2, "park", color(186,220,137), color(128,179,114), 1, 1, 24, 14, 6);
	//Pet playerPet = new Pet ("DOGGO", "The first thing their owner said to them was .", 50, 5, "ho", "", "", "park");
	//Player player = new Player ("ho","When asked how they feel about getting the job, they responded with ", 10, 10, playerPet);
	
	private String playerName;
	private String petName;
	private String petType;
	private String initialMessage;
	private String petSound;

	public static void main(String[] args) {
		
		PApplet.main("PetWalkingSimulator");
		
	}
	
	public void settings(){
		
		size(1250,750);
		smooth();
		
	}

	public void setup() {
		
		frameRate(40);
		
		timeStart = millis();
		
		img = loadImage("aw.png");
		img2 = loadImage("petNapperJoe.png");
		img3 = loadImage("PetCoatMan.png");

		f = loadFont("Silom-40.vlw");

	}

	public void draw(){
		


//TITLESCREEN
		
		if (show == "titleScreen") {
			
			background(170,231,219);
			
			
			//Title
			
			fill(0);
			textFont(loadFont("Silom-70.vlw"));
			textAlign(CENTER);
			text("Pet Walking Simulator", 625,150);
			
			
			//Image
			
			image(img,55,180,600,600);

			
			//"New Game" Button

	        stroke(0);
	        strokeWeight(4);
	        noFill();
	        rect(734,270,400,160);
	        textFont(f,30);
	        textAlign(CENTER);
	        text ("New Game", 938,360);
	        
	        
	        //"Continue Game" Button

	        stroke (0);
	        strokeWeight(4);
	        noFill();
	        rect(734,510,400,160);
	        textFont (f,30);
	        textAlign(CENTER);
	        text ("Continue Game", 938,600);

		}
		
		
		
//PLAYER NAME INPUT
		
		if (show == "inputPlayerName") {
			
			background(0);
			
			if(opac < 255) {				//Used for "fading" effect.
				opac+=3;
			}
			
			fill(255,(int)opac);
			textFont(f,50);
			textAlign(CENTER);
			
			text("Hello.", 625,150);
			
			if ((millis() - timeStart)/1000 >= 3) {			//Used to determine when to display next message. (waits 3 sec)
				
				if(opac2 < 255) {
					opac2+=5;
				}
				
				stroke (255);
				fill(255,(int)opac2);
				textFont(f,50);
				textAlign(CENTER);
				
				text("What is your name?", 625,210);
				
			}
			
			if ((millis() - timeStart)/1000 >= 5) {			//waits 5 sec
				
				if(opac3 < 255) {
					opac3+=5;
				}
		        if (!namer) {								//Namer ensures that the user clicks on the text box before they start typing.
		            stroke(128,(int)opac3);
		            noFill();
		        }
		        else {
		            stroke(255,(int)opac3);
		            noFill();
		        }
		        
		        strokeWeight(4);
				noFill();
				rect(421,400,400,100);
				textFont(f,30);
				textAlign(LEFT);
				text(ans, 440,438,380,80);
			
			}
			
			if ((millis() - timeStart)/1000 >= 7) {			//waits 7 sec
				
				if(opac4 < 255) {
					opac4+=5;
				}

				fill(255,(int)opac4);
				textFont(f,15);
				textAlign(CENTER);
				text("(answer using textbox and press 'enter' key to continue)", 625,720);
			
			}
			
		}
		
		
		
//PET NAME INPUT 1 ("DO YOU HAVE A PET?")
		
		if (show == "inputPetName1") {
			
			background(0);
			
			if(opac < 255) {
				opac+=5;
			}
			
			fill(255,(int)opac);
			textFont(f,50);
			textAlign(CENTER);
			
			text("Welcome, " + playerName + ".", 625,150);
			
			if ((millis() - timeStart)/1000 >= 2) {					//wait 2 sec (since beginning of this "show")
				if(opac2 < 255) {
					opac2+=5;
				}	
				
				stroke (255);
				fill(255,(int)opac2);
				textFont(f,50);
				textAlign(CENTER);
				
				text("Do you have a pet?", 625,220);
				
			}
			
			if ((millis() - timeStart)/1000 >= 4) {					//wait 4 sec
				
				if(opac3 < 255) {
					opac3+=5;
				}
				
				stroke(255,(int)opac3);
		        strokeWeight(4);
				noFill();
				
				rect(421,410,160,110);
				
				fill(255,(int)opac3);
				textFont(f,30);
				textAlign(CENTER);
				
				text("Yes", 421,448,160,90);

				noFill();
				
				rect(661,410,160,110);
				
				fill(255,(int)opac3);
				textAlign(CENTER);
				
				text("No", 661,448,160,90);
				
			}

		}
		
		
		
//PET NAME INPUT 2 ("PET NAME?")
		
		if (show == "inputPetName2") {
			
			background(0);
			
			if(opac < 255) {
				opac+=5;
			}
			
			fill(255,(int)opac);
			textAlign(CENTER);
			
			if (hasPet) {								//hasPet variable is true if the user answered "yes" to previous question.
				textFont(f,50);
				text("Good.", 625,150);
			}
			
			else if (!(hasPet)) {
				textFont(f,40);
				text("How dare you. In this world, you must have a pet.", 625,150);
			}
			
			if ((millis()-timeStart)/1000 >= 2) {			//wait 2 sec
				
				if(opac2 < 255) {
					opac2+=5;
				}
			
				fill(255,(int)opac2);
				textAlign(CENTER);
				
				if (hasPet) {
					text("What is your pet's name?", 625,220);
				}
				
				else if (!(hasPet)) {
					text("What will your pet's name be?", 625,220);
				}	
			
			}
			
			if ((millis() - timeStart)/1000 >= 4) {			//wait 4 sec
				
				if(opac3 < 255) {
					opac3+=5;
				}
		        if (!namer) {
		            stroke(128,(int)opac3);
		            noFill();
		        }
		        else {
		            stroke(255,(int)opac3);
		            noFill();
		        }
		        
		        strokeWeight(4);
				noFill();
				rect(421,400,400,100);
				
				textFont(f,30);
				textAlign(LEFT);
				text(ans, 440,438,380,80);
			
			}
			
		}
		
		
		
//PET TYPE INPUT
		
		if (show == "inputPetType") {
			
			background(0);
			
			if(opac < 255) {
				opac+=5;
			}
			
			fill(255,(int)opac);
			textAlign(CENTER);
			textFont(f,40);
			text("Ah yes. " + playerName + " and " + petName + ".", 625,150);
			
			if((millis()-timeStart)/1000 >= 2) {			//2 sec
				
				if(opac2 < 255) {
					opac2+=5;
				}
			
				fill(255,(int)opac2);
				textAlign(CENTER);
				text("What kind of pet is " + petName + "?", 625,220);
			
			}
			
			if ((millis() - timeStart)/1000 >= 4) {			//4 sec
				
				if(opac3 < 255) {
					opac3+=5;
				}
				
		        if (!namer) {
		            stroke(128,(int)opac3);
		            noFill();
		        }
		        else {
		            stroke(255,(int)opac3);
		            noFill();
		        }
		        
		        strokeWeight(4);
				noFill();
				rect(421,400,400,100);
				
				textFont(f,30);
				textAlign(LEFT);
				text(ans, 440,438,380,80);
			
			}

		}
		
		
		
//INITIAL MESSAGE INPUT
		
		if (show == "inputMessage") {
			
			background(0);
			
			if(opac < 255) {
				opac+=5;
			}
			
			fill(255,(int)opac);
			textAlign(CENTER);
			textFont(f,40);
			text("A pet's relationship with their owner is important.", 625,150);
			
			if((millis()-timeStart)/1000 >= 2) {			//wait 2 sec
				
				if(opac2 < 255) {
					opac2+=5;
				}
			
				fill(255,(int)opac2);
				textAlign(CENTER);
				text("Talk to " + petName + ". Ask them something.", 625,220);
			
			}
			
			if ((millis() - timeStart)/1000 >= 4) {			//wait 4 sec
				
				if(opac3 < 255) {
					opac3+=5;
				}
				
		        if (!namer) {
		            stroke(128,(int)opac3);
		            noFill();
		        }
		        else {
		            stroke(255,(int)opac3);
		            noFill();
		        }
		        
		        strokeWeight(4);
				noFill();
				rect(421,400,400,100);
				
				textFont(f,30);
				textAlign(LEFT);
				text(ans, 440,438,380,80);
			
			}
			
		}
		
		
		
//PET SOUND INPUT

		if (show == "inputPetSound") {
			
			background(0);
			
			if(opac < 255) {
				opac+=5;
			}
			
			fill(255,(int)opac);
			textAlign(CENTER);
			textFont(f,50);
			text("It looks like your pet is responding...", 625,150);
			
			if((millis()-timeStart)/1000 >= 2) {			//wait 2 sec
				
				if(opac2 < 255) {
					opac2+=3;
				}
			
				fill(255,(int)opac2);
				textAlign(CENTER);
				textFont(f,65);
				text("\"" + initialMessage + "\"", 625,400);
			
			}
			
			if((millis()-timeStart)/1000 >= 5) {			//wait 5 sec
				
				background(0);
				
				if(opac3 < 255 && !(reverseOpac)) {
					opac3+=4;
				}
				if (opac3 >= 255) {
					reverseOpac = true;
				}
				if(opac3 > 0 && reverseOpac) {
					opac3-=4;
				}
				
				fill(255,(int)opac3);
				textAlign(CENTER);
				textFont(f,80);
				text("Hmm...", 625,385);

			}
			
			if((millis()-timeStart)/1000 >= 9) {			//wait 9 sec
			
				if(opac4 < 255) {
					opac4+=5;
				}
				
				fill(255,(int)opac4);
				textAlign(CENTER);
				textFont(f,40);
				text("It seems as though your pet needs a noise.", 625,150);
			
			}
			
			if((millis()-timeStart)/1000 >= 11) {			//wait 11 sec
				
				if(opac5 < 255) {
					opac5+=5;
				}
				
				fill(255,(int)opac5);
				textAlign(CENTER);
				textFont(f,40);
				text("What sound should they make?", 625,220);
			
			}
			
			if ((millis() - timeStart)/1000 >= 12) {			//wait 12 sec
				
				if(opac6 < 255) {
					opac6+=5;
				}
				
		        if (!namer) {
		            stroke(128,(int)opac6);
		            noFill();
		        }
		        else {
		            stroke(255,(int)opac6);
		            noFill();
		        }
		        
		        strokeWeight(4);
				noFill();
				rect(421,400,400,100);
				
				textFont(f,30);
				textAlign(LEFT);
				text(ans, 440,438,380,80);
			
			}
			
		}
		
		
		
//TRANSITION
		
		if (show == "transition") {
			
			if ((millis()-timeStart)/1000 <5) {
				
				if(opac < 254) {
					opac+=2;
				}
				
				background((int)opac);
				
				if(opac2 < 254) {
					opac2+=3;
				}
				
				textAlign(CENTER,CENTER);
				textFont(f,70);
				fill(255,(int)opac2);
				textLeading(200);
				
				text("Welcome\n"
						+ "To This\n"
						+ "World", 625,375);
				
			}
			
			if ((millis()-timeStart)/1000 >=5) {		//Where player and player pet objects are created.
				
        		playerPet = new Pet(petName, "First recorded thing said to them by owner was "+initialMessage+".", 50, 5, playerName, petType, petSound, "park");
				player = new Player (playerName,"First recorded thing they said to their pet was "+initialMessage+".", 100, 10, playerPet);
				show = "game";
				
			}
			
		}
		
		
		
//GAME
		
		if (show == "game") {

			background(186,220,137);
			
			if (h) {									//Player's home loop.
			
				home.printMap();
				
				fill(0);
				textFont(f, 40);
				textAlign(LEFT);
				
				text("Level: "+player.getLevel()+"\n"
						+ "Money: $0.00",30,60);
				
				textFont(f, 55);
				textAlign(RIGHT);
				
				text(player.getName()+"'s Home",1200,90);
						
				stroke(0);
				strokeWeight(5);
				
				textAlign(CENTER);
				textFont(f,25);
				fill(0);
				
				if (mouseX > 125 && mouseX < 125+250 && mouseY > 565 && mouseY < 565+125) {
					fill(125,125,125);
					stroke(125,125,125);
				}

				text("Start\nLevel "+player.getLevel(),250, 620);
				noFill();
				rect(125,565,250,125);
				
				stroke(0);
				fill(0);
				
				if (mouseX > 500 && mouseX < 500+250 && mouseY > 565 && mouseY < 565+125) {
					fill(125,125,125);
					stroke(125,125,125);
				}

				text("Save Game",625, 635);
				noFill();
				rect(500,565,250,125);
				
				stroke(0);
				fill(0);
				
				if (mouseX > 875 && mouseX < 875+250 && mouseY > 565 && mouseY < 565+125) {
					fill(125,125,125);
					stroke(125,125,125);
				}

				text("Quit Game",1000, 635);
				noFill();
				rect(875,565,250,125);
				
				if (player.getLevel() == 1) {
					introSequence();
				}
				
				/*												//Grid lines that were used for testing/designing levels.
			 	strokeWeight(1);
				stroke(255);
				for (int x = 50; x<1250; x+=50) {
					line(x,0,x,770);
				}
				for (int x = 50; x<1250; x+=50) {
					line(0,x,1250,x);
				}*/
				
			}
			
			else if (battleTime) {									//The main battle loop.
				
				fill(128,179,114);
				strokeWeight(0);
				stroke(128,179,114);
				rect(0,0,1250,400);
				image(levelEnemy.getImage(),425,100,400,400);
				
				battleSequence(player, levelEnemy, playerPet, levelMap);
				
			}
			
			else {												//Game map loop.
				
				levelMap.printMap();
				
				h = win(player);
				
				if (levelMap.getTile(levelMap.getPlayerX(),levelMap.getPlayerY()).isEnemyTile()) {			//If the player lands on an enemy tile.
					
					battleTime = true;
					
				}
			
			}
			
		}
		
		if (gameSaved) {								//Allows for a short message to appear on screen for a few seconds.
			
			textAlign(CENTER);
			textFont(f,30);
			text("Game Saved!",625,80);
			
			if ((millis()-timeStart)/1000 >=2) {
				gameSaved = false;
			}
			
		}
		
		if (notSaved) {
			
			textAlign(CENTER);
			textFont(f,20);
			text("Sorry, an error occurred.\nGame was not saved.",625,60);
			
			if ((millis()-timeStart)/1000 >=4) {
				notSaved = false;
			}
			
		}
		
		if (noFile) {
			
			textAlign(CENTER);
			textFont(f,25);
			text("Sorry, an error occurred. Could not find any save files.",625,700);
			
			if ((millis()-timeStart)/1000 >=4) {
				noFile = false;
			}
			
		}
		
		if (lastLevel) {
			
			textAlign(CENTER);
			textFont(f,20);
			text("Sorry, there are no more levels at the moment!",625,730);
			
			if ((millis()-timeStart)/1000 >=4) {
				lastLevel = false;
			}
			
		}
		
	}
	
	
	public void mouseReleased() {
		
		if (show == "titleScreen") {
			
			//"New Game" Button

	        if (mouseX >= 734 && mouseX <= 734+400 && mouseY >= 270 && mouseY <= 270+160) {
	          
	            background (0);
	            
	            show = "inputPlayerName";
	            timeStart = millis();
	          
	        }
	        
	      //"Continue Game" Button
	        
	        if (mouseX >= 734 && mouseX <= 734+400 && mouseY >= 510 && mouseY <= 510+160) {
		          
			   try {
				   FileInputStream fin = new FileInputStream("save.ser");
				   ObjectInputStream ois = new ObjectInputStream(fin);
				   
				   player = (Player) ois.readObject();
				   playerPet = (Pet) ois.readObject();
				   
				   ois.close();
				   
				   intro = 30;										//So that when the player loads their game they do not have to read the introductory instructions again.
				   show = "game";

			   } catch (FileNotFoundException e) {
				   timeStart = millis();							//Start the error messages.
				   noFile = true;
			   } catch (ClassNotFoundException e) {
				   timeStart = millis();
				   noFile = true;
			   } catch (IOException e) {
				   timeStart = millis();
				   noFile = true;
			   }
	          
	        }
			
		}
		
		if (show == "inputPlayerName" || show == "inputPetName2" || show == "inputPetType" || show == "inputMessage" || show == "inputPetSound") { 
		
	        if (mouseX > 421 && mouseX < 421+400 && mouseY > 400 && mouseY < 400+100) {			//Handles namer variable.
	            
	            namer = true;
	          
	        }
	        
	        if (!(mouseX > 421 && mouseX < 421+400 && mouseY > 400 && mouseY < 400+100)) {
	    
	            namer = false;
	          
	        }
        
		}
		
		if (show == "inputPetName1") { 
			
	        if (mouseX > 421 && mouseX < 421+160 && mouseY > 410 && mouseY < 410+110) {
	            
	            hasPet = true;
            	opac = 0;
            	opac2 = 0;
            	opac3 = 0;
            	opac4 = 0;
            	timeStart = millis();
	            show = "inputPetName2";
	          
	        }
	        
	        if (mouseX > 661 && mouseX < 661+160 && mouseY > 410 && mouseY < 410+110) {
	    
	            hasPet = false;
            	opac = 0;
            	opac2 = 0;
            	opac3 = 0;
            	opac4 = 0;
            	timeStart = millis();
	            show = "inputPetName2";
	          
	        }
        
		}
		
		if (show == "game" && h && intro > 24) {				//During the home map screen.
			
			//"Start Level..." Button
			
			if (mouseX > 125 && mouseX < 125+250 && mouseY > 565 && mouseY < 565+125) {
				
				Enemy petNapper = new Enemy (this,"Pet-napper Joe", "He has a nice name.", 40, 10, "STEARL PETRSG!! HARGHAHG!", "BLARGGGHARRRAG ONRE DARYG!!", img2);
				Enemy petCoatMan = new Enemy (this,"Pet Coat Man", "It's made out of purple "+playerPet.getType()+"s.", 30, 12, "More cooaaatsss for meeeee...", "I will haaaavvveee the furrr someeeday...", img3);
				
				if (player.getLevel() == 1) {
					levelMap = park;
					levelEnemy = petCoatMan;
				}
				
				if (player.getLevel() == 2) {
					levelMap = park2;
					levelEnemy = petNapper;
				}
				
				if (player.getLevel() == 3) {
					timeStart = millis();
					lastLevel = true;
				}
				
				if(player.getLevel() < 3) {
					levelMap.setEnemies();
					h = false;
				}
				
			}
			
			//"Save Game" Button
			
			if (mouseX > 500 && mouseX < 500+250 && mouseY > 565 && mouseY < 565+125) {
				
				try {
				  FileOutputStream fout = new FileOutputStream("save.ser");
				  ObjectOutputStream oos = new ObjectOutputStream(fout);
				  oos.writeObject(player);
				  oos.writeObject(playerPet);
				  oos.close();
				  
				  timeStart = millis();
				  gameSaved = true;

				}catch (FileNotFoundException e) {
					timeStart = millis();
					notSaved = true;
				} catch (IOException e) {
					timeStart = millis();
					notSaved = true;
				}
				
			}
			
			//"Quit Game" Button
			
			if (mouseX > 875 && mouseX < 875+250 && mouseY > 565 && mouseY < 565+125) {
				
				show = "titleScreen";
				
			}
			
		}
		
	}
	
	public void keyPressed() {

		if (show == "inputPlayerName" || show == "inputPetName2" || show == "inputPetType" || show == "inputMessage" || show == "inputPetSound") {
			
			if(namer) {
		
		        if ((key == BACKSPACE) && ans.length() > 0) {
		            
		            ans = ans.substring(0,ans.length()-1);
		            
		        }
		        
		        else if ((ans.length() < 15) && (key > 31) && (key != CODED)) {          //Excludes shift, caps lock, etc.
		          
		            ans = ans + key;
		          
		        }
		        
		        else if (key == ENTER || key == RETURN) {			//Changes most of the introductory screens.
		        	
	            	opac = 0;
	            	opac2 = 0;
	            	opac3 = 0;
	            	opac4 = 0;
	            	timeStart = millis();
	            	namer = false;
	            	ans = ans.trim();
		        	
		        	if (show == "inputPlayerName") {
	            	
		            	playerName = ans;
		            	show = "inputPetName1";
		            	
		        	}
		        	else if (show == "inputPetName2") {
		        		
		        		petName = ans;
		        		show = "inputPetType";

		        	}
		        	else if (show == "inputPetType") {
		        		
		        		petType = ans;
		        		show = "inputMessage";

		        	}
		        	else if (show == "inputMessage") {
		        		
		        		initialMessage = ans;
		        		show = "inputPetSound";

		        	}
		        	else if (show == "inputPetSound") {
		        		
		        		petSound = ans;
		        		show = "transition";

		        	}
		        	
		        	ans = "";				//Resets ans variable.
	            	
	            }
		        
			}
			
		}
		
		if (show == "game") {
			
			String a;
			
			if (h) {
				
				if ((keyCode == ENTER || keyCode == RETURN)) {
					
					intro++;
					
				}
				
			}
			
			if (battleTime) {
				
				if ((keyCode == ENTER || keyCode == RETURN)) {
					
					if (count == 8) {											//Resets battle variables.
						
						count = 0;
						resetBattle(player,levelEnemy,playerPet,levelMap);
						battleTime = false;
					}
					
					else if (count < 3 || count >= 4) {						//Helps switch screens during battles.
						count++;
					}

					else if (count >= 3.111 && count < 3.223 && count != 3.122 && count != 3.2) {
						count = (count*1000 + 1)/1000;
					}
					
				}
				
				//Battle Screens (Navigation)
				
				if (count == 3.1) {
					if(key == '1') {
						count = 3.111;
					}
					if(key == '2') {
						count = 3.121;
					}
				}
				
				if (count == 3.2) {
					if (playerPet.isAlive()) {
						if(key == '1') {
							count = 3.211;
						}
						if(key == '2') {
							count = 3.221;
						}
					}
				}
				
				if (count == 3) {
					if (key == '1') {
						count = 3.1;
					}
					if (key == '2') {
						count = 3.2;
					}
				}
				
				if((count == 3.1 || count == 3.2) && key == 'b') {
					count = 3;
				}
			
				a = "";					//To satisfy player input command
					
			}
			
			else if (keyCode == UP) {
				
				a = "w";
				
			}
			
			else if (keyCode == DOWN) {
				
				a = "s";
				
			}
			
			else if (keyCode == LEFT) {
				
				a = "a";
				
			}
			
			else if (keyCode == RIGHT) {
				
				a = "d";
				
			}
			else if (key == 'e') {
				
				h = false;
				a = "e";
				
			}
			else {
				
				a = "";
				
			}
			
			if(h == true) {
				home.playerInput(a);
			}
			else {
				levelMap.playerInput(a);
			}
			
		}
		
	}
	
	
	/**
	 * textBox()
	 * 
	 * Creates the black textbox with white border that is used 
	 * for most of the text in home and game maps. Takes the 
	 * desired text and text size and displays this text in the 
	 * formatted textbox.
	 * 
	 * @param text
	 * @param size
	 */
	
	
	public void textBox(String text, int size) {

		textAlign(LEFT);
		
		fill(0);
		strokeWeight(10);
		stroke(255);
		rect(10,560,1230,180);
		fill(255);
		textFont(f,size);
		textLeading(40);
		text(text, 50,600,1150,140);
		
	}
	
	
	/**
	 * textBox()
	 * 
	 * Overloaded version of textBox() that creates the 
	 * formatted textBox and splits it in half; one text
	 * with a certain size can be displayed on the left
	 * side of the box, and another text with a different
	 * size can be displayed on the right side of the box.
	 * 
	 * @param text
	 * @param size
	 * @param text2
	 * @param size2
	 */
	
	
	public void textBox (String text, int size, String text2, int size2) {
		
		textAlign(LEFT);
		
		fill(0);
		strokeWeight(10);
		stroke(255);
		rect(10,560,1230,180);
		
		fill(255);
		textFont(f,size);
		textLeading(40);
		text(text, 50,600,775,140);
		
		fill(255);
		textFont(f,size2);
		textLeading(40);
		text(text2, 925,600,400,140);
		
	}
	
	
	/**
	 * resetBattle()
	 * 
	 * Takes important variables from battles and
	 * resets them, preparing the battle
	 * sequence for the next time it's used.
	 * 
	 * @param player
	 * @param enemy
	 * @param playerPet
	 * @param map
	 */
	
	
	public void resetBattle(Player player, Enemy enemy, Pet playerPet, GameMap map){			
		map.getTile(map.getPlayerX(),map.getPlayerY()).setEnemyTile(false);
		enemy.setAlive(true);
		enemy.setHp(enemy.getHpCap());
		count = 0;
	}
	
	
	/**
	 * win()
	 * 
	 * Constantly checks if player has beaten the level 
	 * they're on or if they have lost. This method
	 * updates the player's level if they've won. 
	 * 
	 * @param player
	 * @return boolean (true if player has won the level)
	 */
	
	
	public boolean win(Player player) {			//Increases the player's level and refills Player and Pet HP.
		
		if (levelMap.getPlayerX() == levelMap.getEndX() && levelMap.getPlayerY() == levelMap.getEndY()) {			//If the player is on the finish tile.
			
			System.out.println("\nCongratulations, you have passed level "+player.getLevel()+"!\n");
			player.setLevel(player.getLevel() + 1);
			player.setHp(player.getHpCap());
			playerPet.setHp(playerPet.getHpCap());
			count = 0;
			
			return true;
			
		}
		
		else if(player.getHp() <= 0){			//If the player's health is 0.
			
			textBox("Sorry, you have lost level "+player.getLevel()+" and will have to attempt it again!", 30);
			player.setAlive(true);
			player.setHp(player.getHpCap());
			playerPet.setHp(playerPet.getHpCap());
			resetBattle(player, levelEnemy, playerPet, levelMap);
			levelMap.setPlayer(levelMap.getStartX(), levelMap.getStartY());
			count = 0;
			
			return true;
			
		}
		else {
			return false;
		}
		
	}
	
	
	/**
	 * introSequence()
	 * 
	 * Gives instructions to user when they first
	 * get to their home screen, and allows the 
	 * user to go at their own pace (you need to
	 * press the enter key to advance the text).
	 * 
	 */
	
	
	public void introSequence() {				//Each time the user presses enter, intro goes up by 1.
		
		if (intro == 0) {
			textBox("Welcome!\n"
					+ "(press enter to continue...)",30);
		}
		
		if (intro == 1) {
			textBox("Don't worry this isn't a serious game I pranked you.\n"
					+ "This is your new home.\n"
					+ "Was it what you were expecting?",30);
			textFont(f,10);
			text("(press enter to continue)", 1080, 720);
		}
		
		if (intro == 2) {
			textBox("Haha it kind of sucks cause like\n"
					+ "- You have no money.\n"
					+ "- Your home is empty.",30);
			textFont(f,10);
			text("(press enter to continue)", 1080, 720);
		}
		
		if (intro == 3) {
			textBox("lol",30);

		}
		
		if (intro == 4) {
			textBox("Well that's okay. I have a job for you!",30);

		}
		
		if (intro == 5) {
			textBox("\n(and you can't say no)",18);

		}
		
		if (intro == 6) {
			textBox("It's a pet walking job!\n"
					+ "It's really fun; you get to meet a whole bunch of different pets and you get to spend lots of time with " +playerPet.getName() + "!",30);

		}
		if (intro == 7) {
			textBox("And you'll end up getting paid for it!\n"
					+ "With money and decorations for your home!",30);

		}
		
		if (intro == 8) {
			textBox("\n(this is just a very beta version tho so no other pets or any payment for now hahaHHA just have fun with "+playerPet.getName()+")",18);

		}
		
		if (intro == 9) {
			textBox("Exciting right?",30);

		}
		
		if (intro == 10) {
			textBox("And guess what.....",30);

		}
		if (intro == 11) {
			textBox("YOU'RE HIRED!\n"
					+ "That's right, you get to work the job of your dreams!",30);

		}
		if (intro == 12) {
			textBox("YAYAYayayAYAyaaYAyaYAyAYayyAYyay",30);

		}
		if (intro == 13) {
			textBox("So yeah if your smart you might have already figured this out by now but the arrow keys are what you use to get around.",30);

		}
		if (intro == 14) {
			textBox("You can walk wherever you want (even in your yard!) but\n"
					+ "DON'T GO BEHIND THE TEXT BOX",30);

		}
		if (intro == 15) {
			textBox("Anyways...",30);

		}
		if (intro == 16) {
			textBox("You might be a bit surprised, but I'm contractually obligated to tell you this...",30);
		}
		if (intro == 17) {
			textBox("\nyour job might be a little dangerous",18);

		}
		if (intro == 18) {
			textBox("But it's no big deal, you can handle it!",30);

		}
		if (intro == 19) {
			textBox("Each walk you do is a certain level (you can see what level you are on in the top-left corner).",30);

		}
		if (intro == 20) {
			textBox("Keep in mind that the higher your level, the more difficult it will be to complete the level.",30);

		}
		if (intro == 21) {
			textBox("HOWEVER, after every walk you always come right back home, and your HP is automatically fully restored.",30);

		}
		if (intro == 22) {
			textBox("Okay so last thing I have to say;\n"
					+ "Use mouse to select \"Start Level\"/\"Save Game\"/\"Quit Game buttons\"\n"
					+ "AND PLEASE ALWAYS SAVE YOUR GAME",30);

		}
		if (intro == 23) {
			textBox("AND ALSO get to the red square when walking!\n"
					+ "Okay.\n"
					+ "Now you are ready.",30);

		}
		
		if (intro == 24) {
			textBox("And yeah for some weird reason no owners in the neighbourhood want you to walk their pets right now ahahaHAHA,\n"
					+ "so just walk "+playerPet.getName()+". Have fun!",30);

		}
		
	}
	
	
	/**
	 * battleSequence()
	 * 
	 * Commences and plays through a battle between 
	 * the player and an enemy. This methods deals damage
	 * to characters and orders every move made when the
	 * player is battling.
	 * 
	 * @param player
	 * @param enemy
	 * @param playerPet
	 * @param map
	 */
	
	
	public void battleSequence(Player player, Enemy enemy, Pet playerPet, GameMap map) {
		
		if (player.isAlive() && enemy.isAlive()) {
		
			if (count == 0) {
				
				String a;
				
				if (player.getLevel() == 1) {
					a = "oh looK ITS A FUR COAT ITS PROBABALY MADE OUT OF "+playerPet.getType().toUpperCase();
				}
				else {
					a = "Oh gOD ITS TERRIFYING! (but it has a nice name)";
				}
				
				this.textBox(a+"\nYou've been attacked by " + enemy.getName() + "!",30);
				textFont(f,10);
				text("(press enter to continue)", 1080, 720);
				
			}
				
			if(count == 1 && player.getLevel() == 1) {					//Rules and controls are only displayed when user is on level 1.
				
				this.textBox("RULES:\n"
						+ "The enemy will try to get your HP to 0 and knock you out!\n"
						+ "If you are knocked out, you will lose all of the pets you are walking!",20);
				textFont(f,10);
				text("(press enter to continue)", 1080, 720);
				
			}
			
			else if(count == 1) {
				this.textBox("Let's fight!", 30);
				textFont(f,10);
				text("(press enter to continue)", 1080, 720);
			}
	
			if(count == 2 && player.getLevel() == 1) {
				
				this.textBox("CONTROLS:\n"
						+ "Enter numbers ('1','2', etc.) to select your actions.",20);
				textFont(f,10);
				text("(press enter to continue)", 1080, 720);
		
			}
			
			else if (count == 2) {
				count = 3;
			}
	
			if(count == 3) {								//Main battle loop.
				
				textBox(player.getName()+": "+player.getHp()+"/"+player.getHpCap()+" HP.\n"
				+ playerPet.getName()+": "+playerPet.getHp()+"/"+playerPet.getHpCap()+" HP.\n"
				+ enemy.getName()+": "+enemy.getHp()+"/"+enemy.getHpCap()+" HP.",30, "1: Attack " + enemy.getName() + ".\n2: Use "+playerPet.getName() + ".", 20);
				
			}
			
			//Player attacks.
			
			if (count == 3.1) {			
				
				textBox("Slap: A move that always does 10 damage.\n"
						+ "CLW: PRETty awesome cause it can do up to 20 damage, but risky cause it can also do up to 10 damage against YOU.",20, "1: Slap.\n2: Cool Leash Whip.", 20);
				textFont(f,10);
				text("(press 'b' to go back)", 1100, 720);
				
			}
			
			
			//Slap (all of the different if statements help by printing what is happening in the battle and by allowing each statement to be separated by the user pressing enter)
			
			if(count == 3.111) {
				
				textBox("\n*slap*", 30);
				
			}
			
			if(count == 3.112) {
				
				textBox("\n"+player.getName() +" slaps " + enemy.getName() + "!", 30);
				
			}
			
			if(count == 3.113) {
				
				textBox("\nIt did "+player.getAtt()+" damage.", 30);
				
			}
			
			if(count == 3.114) {
				
				player.attack(enemy);
				playerAttack = true;
				count = 4;
				
			}
			
			
			//Cool Leash Whip
			
			if(count == 3.121) {
				
				textBox("\n"+player.getName() +" draws back their leash and .....", 30); 
				
			}
			
			if(count == 3.122) {
				
				oof = (int)(30*Math.random()+1);			//Generates random number between 1-30.
				count = 3.123;
				
			}
			
			if(count == 3.123) {
				
				if (oof <= 10) {							//If number is 10 or less, this amount of damage is done on the player.
					
					textBox("Misses " + enemy.getName() + " and hits themselves! Oh no!\n"
							+ "It did "+oof+" damage!",30);
					
				}
				
				else {										//If number is 11 or higher, this amount of damage (subtract 10) is done on the enemy.
					oof = oof - 10;
					textBox("***WAPSHHHH***\n"
							+ "Hits " + enemy.getName() + " with a super cool whip move!\n"
							+ "It did "+oof+" damage.", 30);
					oof = oof + 10;
				
				}
				
			}
			
			if (count == 3.124) {
				
				if (oof <= 10) {
					player.setHp(player.getHp()-oof);
				}
				else {
					oof = oof - 10;
					enemy.setHp(enemy.getHp()-oof);
				}
				playerAttack = true;
				count = 4;
				
			}
				
			if (playerPet.isAlive()) {				//Only if the pet is alive can it fight
			
				if (count == 3.2) {
					
					textBox("Bite: Always does 5 damage.\n"
							+ "Protect: "+playerPet.getName()+" will protect you from an enemy attack, but they will take the damage if the attack is for them.",25, "1: Bite " + enemy.getName() + ".\n2: Protect " + player.getName()+".", 20);
					textFont(f,10);
					text("(press 'b' to go back)", 1100, 720);
					
				}
				
				if (count == 3.211) {
					
					textBox("\n"+playerPet.getSound()+"!", 30);
					
				}
				if (count == 3.212) {
					
					textBox(playerPet.getName() +" bites " + enemy.getName() + "!\n"
							+ "It did "+playerPet.getAtt()+" damage.", 30);
					
				}
				
				if (count == 3.213) {
					
					playerPet.attack(enemy);
					playerAttack = true;
					count = 4;
					
				}
				
				if (count == 3.221) {
					
					textBox("\n"+playerPet.getSound()+"!",30);
					
				}
				
				if (count == 3.222) {
					
					textBox("\n"+playerPet.getName() +" protects " + player.getName() + "!",30);
					
				}
				
				if (count == 3.223) {
					
					defend = true;
					playerAttack = true;
					count = 4;
					
				}
				
			}
			else {
				
				if (count == 3.2) {
					
					textBox(player.getName()+": "+player.getHp()+"/"+player.getHpCap()+" HP.\n"
					+ playerPet.getName()+": "+playerPet.getHp()+"/"+playerPet.getHpCap()+" HP.\n"
					+ enemy.getName()+": "+enemy.getHp()+"/"+enemy.getHpCap()+" HP.",30, "You cannot use these skills when "+playerPet.getName()+" is knocked out!", 20);
					
					textFont(f,10);
					text("(press 'b' to go back)", 1100, 720);
				
				}
				
			}
			
			//Enemy Attack
			
			if (count == 4) {
				
				if (playerAttack && enemy.isAlive()){			//If the player has attacked and the enemy is alive.
					
					enemyVictim[0] = player;					//Adds player and player's pet to the enemyVictim list.
					enemyVictim[1] = playerPet;
						
					if (playerPet.isAlive()) {
					
						enemyAttack = (int)Math.round((Math.random()));			//Random integer 0 or 1.
					
					}
					else {
						
						enemyAttack = 0;
						
					}
					
					count = 5;
					
				}
				else {
					
				}
	
			}
			
			if (count == 5) {
					
				textBox("\n"+enemy.getName()+" throws a cage at " +enemyVictim[enemyAttack].getName()+"!", 30);
				
			}
			
			if (count == 6) {
	
				if (enemyAttack == 0 && defend) {			//If user has decided to use the "Protect" move, the enemy will not do damage on the player.
					
					textBox("\nBut "+playerPet.getName()+" stops it and " +player.getName()+" takes no damage!", 30);
					
				}
				
				else {
					textBox("\nIt did " + enemy.getAtt() + " damage!", 30);
				}
			
			}
			
			if (count == 7) {
	
				if (!(enemyAttack == 0 && defend)) {			//If user has decided to use the "Protect" move, the enemy will not do damage on the player.
					enemy.attack(enemyVictim[enemyAttack]);
				}
			
				defend = false;				//Variables are reset.
				playerAttack = false;
				count = 3;
				
			}
			
		}
		
		else if (battleTime){
			
			count = 8;
			
			if (count == 8) {

				if (player.isAlive()) {		//If player has won.
					
					textBox(player.getName() + " has won against " + enemy.getName() + "!\n"
							+ enemy.getName()+" says \""+enemy.getLoseStatement()+"\"", 30);

				}
				
				else {		//If player has lost.
					
					textBox(enemy.getName()+" says "+enemy.getWinStatement() + "\n"
							+ enemy.getName() + " has won against " + player.getName() + ", but "+playerPet.getName()+" was able to escape.",30);
					
				}
			
			}
			
		}
			
	}
	
	public void mouseMoved() {
		
		System.out.println(mouseX + ", " + mouseY + ", " + show + ", " + count);			//Mostly used for testing purposes.
		
	}

}
