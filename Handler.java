import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Handler extends Thread{
	private static Main main;
	ObjectOutputStream outStream;
	ObjectInputStream inStream;
	int xpos, ypos;
	int dx, dy;
	String babypos, name, direction;
	int life, place;
	
	public Handler(){}

	public Handler(Main main){
		this.main = main;
	}

	public void run() {
		try {
			//lets add thread for receiving here
			
			while(true) {
				
				//This will send the position of the current baby, x, y, babyName
				outStream = new ObjectOutputStream(Details.link.getOutputStream());
				outStream.writeObject(new Position(BattleField.myBaby.getDx(), BattleField.myBaby.getDy(), BattleField.myBaby.getDirection(), BattleField.myBaby.getX(),BattleField.myBaby.getY(), Client.babyPos, Client.life, Client.name));
				
				for (int i = 0; i<Client.players; i++){
					try {
							
						Position pos = new Position();
						inStream = new ObjectInputStream(Details.link.getInputStream());
						pos = (Position) inStream.readObject();
						xpos = pos.getX();
						ypos = pos.getY();
						babypos = pos.getBabypos();
						life = pos.getLife();
						name = pos.getName();
						direction = pos.getDirection();
						dx = pos.getDx();
						dy = pos.getDy();
						
						if (name.equals("GAMEOVER")) {
							BattleField.timer.stop(); 
							main.switchCard("Gameover");
							main.setContentPane(main.gameOverPanel.gameoverLabel);
							System.out.println("GAAAAAMEOVEER");
							Client.life = 0;
							BattleField.baby2life = 0;
							BattleField.baby3life = 0;
							BattleField.baby4life = 0;
							GameoverPanel.printPlaces();
							main.gameOverPanel.showPlaces();
							main.repaint();
							main.validate();
							break;
						}
						
						else {
							
							/******* ASSIGNING OF BABIES *********/
							
							if (Client.players == 2) {
								if (Client.babyPos.equals("baby2") && babypos.equals("baby1")) {
//									System.out.println("x: " + xpos + "y: " + ypos + " pos: " + Client.babyPos);
//									System.out.println("Opponent is baby1");
									if(direction.equals("w")) {
										BattleField.baby2.loadImage("babyUp.png");
									} else if(direction.equals("a")) {
										BattleField.baby2.loadImage("babyLeft.png");
									} else if(direction.equals("d")) {
										BattleField.baby2.loadImage("babyRight.png");
									} else if(direction.equals("s")) {
										BattleField.baby2.loadImage("babyDown.png");
									} else {
										System.out.println("waray sulod");
									}
									
									BattleField.baby2.x = xpos;
									BattleField.baby2.y = ypos;
									BattleField.baby2.setDx(dx);
									BattleField.baby2.setDy(dy);
									BattleField.baby2life = life;
									BattleField.baby2place = place;
									BattleField.baby2name = name;
								}
								
								else if (Client.babyPos.equals("baby1") && babypos.equals("baby2")) {
									if(direction.equals("w")) {
										BattleField.baby2.loadImage("babyUp.png");
									} else if(direction.equals("a")) {
										BattleField.baby2.loadImage("babyLeft.png");
									} else if(direction.equals("d")) {
										BattleField.baby2.loadImage("babyRight.png");
									} else if(direction.equals("s")) {
										BattleField.baby2.loadImage("babyDown.png");
									} else {
										System.out.println("waray sulod");
									}	
									
									BattleField.baby2.x = xpos;
									BattleField.baby2.y = ypos;
									BattleField.baby2.setDx(dx);
									BattleField.baby2.setDy(dy);
									BattleField.baby2life = life;
									BattleField.baby2place = place;
									BattleField.baby2name = name;
								}
							}
							
							else if (Client.players == 3) {
								if (Client.babyPos.equals("baby1") && babypos.equals("baby2")) {
									if(direction.equals("w")) {
										BattleField.baby2.loadImage("babyUp.png");
									} else if(direction.equals("a")) {
										BattleField.baby2.loadImage("babyLeft.png");
									} else if(direction.equals("d")) {
										BattleField.baby2.loadImage("babyRight.png");
									} else if(direction.equals("s")) {
										BattleField.baby2.loadImage("babyDown.png");
									} else {
										System.out.println("waray sulod");
									}
									
									BattleField.baby2.x = xpos;
									BattleField.baby2.y = ypos;
									BattleField.baby2.setDx(dx);
									BattleField.baby2.setDy(dy);
									BattleField.baby2life = life;
									BattleField.baby2place = place;
									BattleField.baby2name = name;
	 							}
								
								else if (Client.babyPos.equals("baby1") && babypos.equals("baby3")) {
									if(direction.equals("w")) {
										BattleField.baby3.loadImage("babyUp.png");
									} else if(direction.equals("a")) {
										BattleField.baby3.loadImage("babyLeft.png");
									} else if(direction.equals("d")) {
										BattleField.baby3.loadImage("babyRight.png");
									} else if(direction.equals("s")) {
										BattleField.baby3.loadImage("babyDown.png");
									} else {
										System.out.println("waray sulod");
									}
									
									BattleField.baby3.x = xpos;
									BattleField.baby3.y = ypos;
									BattleField.baby3.setDx(dx);
									BattleField.baby3.setDy(dy);
									BattleField.baby3life = life;
									BattleField.baby3place = place;
									BattleField.baby3name = name;
								}
								
								else if (Client.babyPos.equals("baby2") && babypos.equals("baby1")) {
									if(direction.equals("w")) {
										BattleField.baby2.loadImage("babyUp.png");
									} else if(direction.equals("a")) {
										BattleField.baby2.loadImage("babyLeft.png");
									} else if(direction.equals("d")) {
										BattleField.baby2.loadImage("babyRight.png");
									} else if(direction.equals("s")) {
										BattleField.baby2.loadImage("babyDown.png");
									} else {
										System.out.println("waray sulod");
									}
									
									BattleField.baby2.x = xpos;
									BattleField.baby2.y = ypos;
									BattleField.baby2.setDx(dx);
									BattleField.baby2.setDy(dy);
									BattleField.baby2life = life;
									BattleField.baby2place = place;
									BattleField.baby2name = name;
								}
								
								else if (Client.babyPos.equals("baby2") && babypos.equals("baby3")) {
									if(direction.equals("w")) {
										BattleField.baby3.loadImage("babyUp.png");
									} else if(direction.equals("a")) {
										BattleField.baby3.loadImage("babyLeft.png");
									} else if(direction.equals("d")) {
										BattleField.baby3.loadImage("babyRight.png");
									} else if(direction.equals("s")) {
										BattleField.baby3.loadImage("babyDown.png");
									} else {
										System.out.println("waray sulod");
									}
									
									BattleField.baby3.x = xpos;
									BattleField.baby3.y = ypos;
									BattleField.baby3.setDx(dx);
									BattleField.baby3.setDy(dy);
									BattleField.baby3life = life;
									BattleField.baby3place = place;
									BattleField.baby3name = name;
								}
								
								else if (Client.babyPos.equals("baby3") && babypos.equals("baby1")) {
									if(direction.equals("w")) {
										BattleField.baby2.loadImage("babyUp.png");
									} else if(direction.equals("a")) {
										BattleField.baby2.loadImage("babyLeft.png");
									} else if(direction.equals("d")) {
										BattleField.baby2.loadImage("babyRight.png");
									} else if(direction.equals("s")) {
										BattleField.baby2.loadImage("babyDown.png");
									} else {
										System.out.println("waray sulod");
									}
									
									BattleField.baby2.x = xpos;
									BattleField.baby2.y = ypos;
									BattleField.baby2.setDx(dx);
									BattleField.baby2.setDy(dy);
									BattleField.baby2life = life;
									BattleField.baby2place = place;
									BattleField.baby2name = name;
								}
								
								else if (Client.babyPos.equals("baby3") && babypos.equals("baby2")) {
									if(direction.equals("w")) {
										BattleField.baby3.loadImage("babyUp.png");
									} else if(direction.equals("a")) {
										BattleField.baby3.loadImage("babyLeft.png");
									} else if(direction.equals("d")) {
										BattleField.baby3.loadImage("babyRight.png");
									} else if(direction.equals("s")) {
										BattleField.baby3.loadImage("babyDown.png");
									} else {
										System.out.println("waray sulod");
									}
									
									BattleField.baby3.x = xpos;
									BattleField.baby3.y = ypos;
									BattleField.baby3.setDx(dx);
									BattleField.baby3.setDy(dy);
									BattleField.baby3life = life;
									BattleField.baby3place = place;
									BattleField.baby3name = name;
								}
							}
							
							else if (Client.players == 4) {
								if (Client.babyPos.equals("baby1") && babypos.equals("baby2")) {
									if(direction.equals("w")) {
										BattleField.baby2.loadImage("babyUp.png");
									} else if(direction.equals("a")) {
										BattleField.baby2.loadImage("babyLeft.png");
									} else if(direction.equals("d")) {
										BattleField.baby2.loadImage("babyRight.png");
									} else if(direction.equals("s")) {
										BattleField.baby2.loadImage("babyDown.png");
									} else {
										System.out.println("waray sulod");
									}
									
									BattleField.baby2.x = xpos;
									BattleField.baby2.y = ypos;
									BattleField.baby2.setDx(dx);
									BattleField.baby2.setDy(dy);
									BattleField.baby2life = life;
									BattleField.baby2place = place;
									BattleField.baby2name = name;
								}
								
								else if (Client.babyPos.equals("baby1") && babypos.equals("baby3")) {
									if(direction.equals("w")) {
										BattleField.baby3.loadImage("babyUp.png");
									} else if(direction.equals("a")) {
										BattleField.baby3.loadImage("babyLeft.png");
									} else if(direction.equals("d")) {
										BattleField.baby3.loadImage("babyRight.png");
									} else if(direction.equals("s")) {
										BattleField.baby3.loadImage("babyDown.png");
									} else {
										System.out.println("waray sulod");
									}
									
									BattleField.baby3.x = xpos;
									BattleField.baby3.y = ypos;
									BattleField.baby3.setDx(dx);
									BattleField.baby3.setDy(dy);
									BattleField.baby3life = life;
									BattleField.baby3place = place;
									BattleField.baby3name = name;
								}
								else if (Client.babyPos.equals("baby1") && babypos.equals("baby4")) {
									if(direction.equals("w")) {
										BattleField.baby4.loadImage("babyUp.png");
									} else if(direction.equals("a")) {
										BattleField.baby4.loadImage("babyLeft.png");
									} else if(direction.equals("d")) {
										BattleField.baby4.loadImage("babyRight.png");
									} else if(direction.equals("s")) {
										BattleField.baby4.loadImage("babyDown.png");
									} else {
										System.out.println("waray sulod");
									}
									
									BattleField.baby4.x = xpos;
									BattleField.baby4.y = ypos;
									BattleField.baby4.setDx(dx);
									BattleField.baby4.setDy(dy);
									BattleField.baby4life = life;
									BattleField.baby4place = place;
									BattleField.baby4name = name;
								}
								
								else if (Client.babyPos.equals("baby2") && babypos.equals("baby1")) {
									if(direction.equals("w")) {
										BattleField.baby2.loadImage("babyUp.png");
									} else if(direction.equals("a")) {
										BattleField.baby2.loadImage("babyLeft.png");
									} else if(direction.equals("d")) {
										BattleField.baby2.loadImage("babyRight.png");
									} else if(direction.equals("s")) {
										BattleField.baby2.loadImage("babyDown.png");
									} else {
										System.out.println("waray sulod");
									}
									
									BattleField.baby2.x = xpos;
									BattleField.baby2.y = ypos;
									BattleField.baby2.setDx(dx);
									BattleField.baby2.setDy(dy);
									BattleField.baby2life = life;
									BattleField.baby2place = place;
									BattleField.baby2name = name;
								}
								
								else if (Client.babyPos.equals("baby2") && babypos.equals("baby3")) {
									if(direction.equals("w")) {
										BattleField.baby3.loadImage("babyUp.png");
									} else if(direction.equals("a")) {
										BattleField.baby3.loadImage("babyLeft.png");
									} else if(direction.equals("d")) {
										BattleField.baby3.loadImage("babyRight.png");
									} else if(direction.equals("s")) {
										BattleField.baby3.loadImage("babyDown.png");
									} else {
										System.out.println("waray sulod");
									}
									
									BattleField.baby3.x = xpos;
									BattleField.baby3.y = ypos;
									BattleField.baby3.setDx(dx);
									BattleField.baby3.setDy(dy);
									BattleField.baby3life = life;
									BattleField.baby3place = place;
									BattleField.baby3name = name;
								}
								
								else if (Client.babyPos.equals("baby2") && babypos.equals("baby4")) {
									if(direction.equals("w")) {
										BattleField.baby4.loadImage("babyUp.png");
									} else if(direction.equals("a")) {
										BattleField.baby4.loadImage("babyLeft.png");
									} else if(direction.equals("d")) {
										BattleField.baby4.loadImage("babyRight.png");
									} else if(direction.equals("s")) {
										BattleField.baby4.loadImage("babyDown.png");
									} else {
										System.out.println("waray sulod");
									}
									
									BattleField.baby4.x = xpos;
									BattleField.baby4.y = ypos;
									BattleField.baby4.setDx(dx);
									BattleField.baby4.setDy(dy);
									BattleField.baby4life = life;
									BattleField.baby4place = place;
									BattleField.baby4name = name;
								}
								
								else if (Client.babyPos.equals("baby3") && babypos.equals("baby1")) {
									if(direction.equals("w")) {
										BattleField.baby2.loadImage("babyUp.png");
									} else if(direction.equals("a")) {
										BattleField.baby2.loadImage("babyLeft.png");
									} else if(direction.equals("d")) {
										BattleField.baby2.loadImage("babyRight.png");
									} else if(direction.equals("s")) {
										BattleField.baby2.loadImage("babyDown.png");
									} else {
										System.out.println("waray sulod");
									}
									
									BattleField.baby2.x = xpos;
									BattleField.baby2.y = ypos;
									BattleField.baby2.setDx(dx);
									BattleField.baby2.setDy(dy);
									BattleField.baby2life = life;
									BattleField.baby2place = place;
									BattleField.baby2name = name;
								}
								
								else if (Client.babyPos.equals("baby3") && babypos.equals("baby2")) {
									if(direction.equals("w")) {
										BattleField.baby3.loadImage("babyUp.png");
									} else if(direction.equals("a")) {
										BattleField.baby3.loadImage("babyLeft.png");
									} else if(direction.equals("d")) {
										BattleField.baby3.loadImage("babyRight.png");
									} else if(direction.equals("s")) {
										BattleField.baby3.loadImage("babyDown.png");
									} else {
										System.out.println("waray sulod");
									}
									
									BattleField.baby3.x = xpos;
									BattleField.baby3.y = ypos;
									BattleField.baby3.setDx(dx);
									BattleField.baby3.setDy(dy);
									BattleField.baby3life = life;
									BattleField.baby3place = place;
									BattleField.baby3name = name;
								}
								
								else if (Client.babyPos.equals("baby3") && babypos.equals("baby4")) {
									if(direction.equals("w")) {
										BattleField.baby4.loadImage("babyUp.png");
									} else if(direction.equals("a")) {
										BattleField.baby4.loadImage("babyLeft.png");
									} else if(direction.equals("d")) {
										BattleField.baby4.loadImage("babyRight.png");
									} else if(direction.equals("s")) {
										BattleField.baby4.loadImage("babyDown.png");
									} else {
										System.out.println("waray sulod");
									}
									
									BattleField.baby4.x = xpos;
									BattleField.baby4.y = ypos;
									BattleField.baby4.setDx(dx);
									BattleField.baby4.setDy(dy);
									BattleField.baby4life = life;
									BattleField.baby4place = place;
									BattleField.baby4name = name;
								}
								
								else if (Client.babyPos.equals("baby4") && babypos.equals("baby1")) {
									if(direction.equals("w")) {
										BattleField.baby2.loadImage("babyUp.png");
									} else if(direction.equals("a")) {
										BattleField.baby2.loadImage("babyLeft.png");
									} else if(direction.equals("d")) {
										BattleField.baby2.loadImage("babyRight.png");
									} else if(direction.equals("s")) {
										BattleField.baby2.loadImage("babyDown.png");
									} else {
										System.out.println("waray sulod");
									}
									
									BattleField.baby2.x = xpos;
									BattleField.baby2.y = ypos;
									BattleField.baby2.setDx(dx);
									BattleField.baby2.setDy(dy);
									BattleField.baby2life = life;
									BattleField.baby2place = place;
									BattleField.baby2name = name;
								}
								
								else if (Client.babyPos.equals("baby4") && babypos.equals("baby2")) {
									if(direction.equals("w")) {
										BattleField.baby3.loadImage("babyUp.png");
									} else if(direction.equals("a")) {
										BattleField.baby3.loadImage("babyLeft.png");
									} else if(direction.equals("d")) {
										BattleField.baby3.loadImage("babyRight.png");
									} else if(direction.equals("s")) {
										BattleField.baby3.loadImage("babyDown.png");
									} else {
										System.out.println("waray sulod");
									}
									
									BattleField.baby3.x = xpos;
									BattleField.baby3.y = ypos;
									BattleField.baby3.setDx(dx);
									BattleField.baby3.setDy(dy);
									BattleField.baby3life = life;
									BattleField.baby3place = place;
									BattleField.baby3name = name;
								}
								
								else if (Client.babyPos.equals("baby4") && babypos.equals("baby3")) {
									if(direction.equals("w")) {
										BattleField.baby4.loadImage("babyUp.png");
									} else if(direction.equals("a")) {
										BattleField.baby4.loadImage("babyLeft.png");
									} else if(direction.equals("d")) {
										BattleField.baby4.loadImage("babyRight.png");
									} else if(direction.equals("s")) {
										BattleField.baby4.loadImage("babyDown.png");
									} else {
										System.out.println("waray sulod");
									}
									
									BattleField.baby4.x = xpos;
									BattleField.baby4.y = ypos;
									BattleField.baby4.setDx(dx);
									BattleField.baby4.setDy(dy);
									BattleField.baby4life = life;
									BattleField.baby4place = place;
									BattleField.baby4name = name;
								}
							}
						}
						
						
					} catch (IOException | ClassNotFoundException | NullPointerException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
					
				}
			}
		}
		
		catch(IOException e) {
			e.getStackTrace();
		} 
	}
}
