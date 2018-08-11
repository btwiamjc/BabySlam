import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.net.Socket;

@SuppressWarnings("serial")
public class BattleField extends JPanel implements ActionListener{
	private Main main;
	private final int DELAY = 50;
	public static Timer timer;
	static Baby baby1, baby2, baby3, baby4;
	static Baby myBaby;
	static int baby2life = 3, baby3life = 3, baby4life = 3;
	static int baby2place, baby3place, baby4place;
	static String baby2name, baby3name, baby4name;
	static String baby2direction, baby3direction, baby4direction;
	static int place;
	private JButton close;
	static Socket link;
	public static JLabel battleLabel;
	ImageIcon icon = new ImageIcon(getClass().getResource("ring.png"));
	BufferedImage image;
	
	public BattleField(Main main){
		this.main = main;
		main.setVisible(false);
		main.setContentPane(this);
		main.setVisible(true);
		setLayout(null);
		battleLabel = new JLabel(icon);
		battleLabel.setLocation(0,0);
		battleLabel.setSize(910,643);

		close = new JButton(new ImageIcon(getClass().getResource("close.png")));
        close.setRolloverIcon(new ImageIcon(getClass().getResource("closehover.png")));
		close.setLocation(876,1);
		close.setSize(38,44);
		add(modifyButton(close));

		ButtonHandler handler = new ButtonHandler();
		close.addActionListener(handler);

		battleFieldShow();
	}

	public void battleFieldShow(){
		try{
			image = ImageIO.read(new File("ring.png"));
		}catch(IOException e){}

		assignBaby();
		/*
			1. assign the places of each clients
			   (The server will decide which place each clients will be assigned)
			2. changing of input and output Streams 
			3. Maximum of 3 wins until the game ends
			4. Add more babies for 3 and 4 clients
			5. (Optional) AI?
		*/
		
		System.out.println("Adi ka na ha battleField");
		main.repaint();
		main.validate();
		repaint();
		validate();
		addKeyListener(new TAdapter());
        setFocusable(true);
        
        timer = new Timer(DELAY, this);
        timer.start();    
        
        Handler handler = new Handler(main);
        handler.start();
    }

	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);

        g.setFont(new Font("Helvetica", Font.BOLD, 20)); 
       	g.setColor(Color.WHITE);
        g.drawString( Client.name + ": " + Client.life, 20, 40);
        
        ////We need to send the lives
        if (Client.players == 2) {
        	g.setFont(new Font("Helvetica", Font.BOLD, 20)); 
       		g.setColor(Color.WHITE);
        	g.drawString(baby2name + ": " + baby2life, 20, 70);
        }
        
        else if (Client.players == 3) {
        	g.setFont(new Font("Helvetica", Font.BOLD, 20)); 
       		g.setColor(Color.WHITE);
        	g.drawString(baby2name + ": " + baby2life, 20, 70);
        	g.setFont(new Font("Helvetica", Font.BOLD, 20)); 
       		g.setColor(Color.WHITE);
        	g.drawString(baby3name + ": " + baby3life, 20, 90);
        }
        
        else if (Client.players == 4) {
        	g.setFont(new Font("Helvetica", Font.BOLD, 20)); 
       		g.setColor(Color.WHITE);
        	g.drawString(baby2name + ": " + baby2life, 20, 70);
        	g.setFont(new Font("Helvetica", Font.BOLD, 20)); 
       		g.setColor(Color.WHITE);
        	g.drawString(baby3name + ": " + baby3life, 20, 90);
        	g.setFont(new Font("Helvetica", Font.BOLD, 20)); 
       		g.setColor(Color.WHITE);
        	g.drawString(baby4name + ": " + baby4life, 20, 100);
        }
        
        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }
	
	public void assignBaby() {
		if (Client.players == 2) {
			if (Client.babyPos.equals("baby1")) {
				System.out.println("My baby is baby1");
				baby1 = new Baby(225,220);
				myBaby = baby1;
			    baby2 = new Baby(575,220);
			}
			
			else if (Client.babyPos.equals("baby2")) {
				System.out.println("My baby is baby2");
				baby2 = new Baby(225,220);
			    baby1 = new Baby(575,220);
			    myBaby = baby1;
			}
		}
		
		else if (Client.players == 3) {
			if (Client.babyPos.equals("baby1")) {
				System.out.println("My baby is baby1");
				baby1 = new Baby(225,220);
				myBaby = baby1;
			    baby2 = new Baby(575,220);
			    baby3 = new Baby(375,120);
			}
			
			else if (Client.babyPos.equals("baby2")) {
				System.out.println("My baby is baby2");
				baby2 = new Baby(225,220);
			    baby1 = new Baby(575,220);
			    myBaby = baby1;
			    baby3 = new Baby(375,120);
			}
			
			else if (Client.babyPos.equals("baby3")) {
				baby1 = new Baby(375,120);
				myBaby = baby1;
				baby2 = new Baby(225,220);
				baby3 = new Baby(575,220);
			}
		}
		
		else if (Client.players == 4) {
			if (Client.babyPos.equals("baby1")) {
				System.out.println("My baby is baby1");
				baby1 = new Baby(225,220);
				myBaby = baby1;
			    baby2 = new Baby(575,220);
			    baby3 = new Baby(375,120);
			    baby4 = new Baby(375,320);
			}
			
			else if (Client.babyPos.equals("baby2")) {
				System.out.println("My baby is baby2");
				baby2 = new Baby(225,220);
			    baby1 = new Baby(575,220);
			    myBaby = baby1;
			    baby3 = new Baby(375,120);
			    baby4 = new Baby(375,320);
			}
			
			else if (Client.babyPos.equals("baby3")) {
				baby1 = new Baby(375,120);
				baby2 = new Baby(225,220);
				baby3 = new Baby(575,220);
				baby4 = new Baby(375,320);
				myBaby = baby1;
			}
			
			else if (Client.babyPos.equals("baby4")) {
				baby4 = new Baby(375,120);
				baby2 = new Baby(225,220);
				baby3 = new Baby(575,220);
				baby1 = new Baby(375,320);
				myBaby = baby1;
			}
		}
	}
	private void doDrawing(Graphics g) {
		
		BoundsChecker bc = new BoundsChecker();
		
		Graphics2D g2d = (Graphics2D) g;
		
    	ImageIcon ii = new ImageIcon(myBaby.getImageName());
        Image image1 = ii.getImage();
        
        ImageIcon ii2 = new ImageIcon(baby2.getImageName());
        Image image2 = ii2.getImage();
        
//        g2d.drawImage(image1, myBaby.getX(), myBaby.getY(), this);
//        g2d.drawImage(image2, baby2.getX(), baby2.getY(), this);
        
        if(bc.isOutofBounds(myBaby) && Client.life > 0) {
        	g2d.drawImage(image1, myBaby.getX(), myBaby.getY(), this);
    	}
        
        else if (Client.life == 0) {
        	myBaby.setX(0);
        	myBaby.setY(0);
        	myBaby.setVisible(false);
        }
        
        else if (Client.life >= 0){
        	System.out.println("I'm out");
        	myBaby.setX(0);
        	myBaby.setY(0);
        	myBaby.setVisible(false);
        	Client.life--;
        	assignBaby();
        	
        }
        
        if (bc.isOutofBounds(baby2) && baby2life > 0) {
        	g2d.drawImage(image2, baby2.getX(), baby2.getY(), this);
        }
        else {
        	baby2.setX(0);
        	baby2.setY(0);
        	baby2.setVisible(false);
        }
        
        //handle bounce for player 1 and 2
        
        if(myBaby.getBounds().intersects(baby2.getBounds())) {
        	myBaby.bounce(baby2.getDx(), baby2.getDy());
      //   	System.out.println(baby2.getDx() +" "+ baby2.getDy());
    		// System.out.println(Client.name);
		}
        
        if (Client.players == 3) {
        	 ImageIcon ii3 = new ImageIcon(baby3.getImageName());
             Image image3 = ii3.getImage();
//             g2d.drawImage(image3, baby3.getX(), baby3.getY(), this);
             
             if (bc.isOutofBounds(baby3) && baby3life > 0) {
            	 g2d.drawImage(image3, baby3.getX(), baby3.getY(), this);
             }
             else {
             	baby3.setX(0);
             	baby3.setY(0);
             	baby3.setVisible(false);
             }
             
             if(myBaby.getBounds().intersects(baby2.getBounds())) {
             	myBaby.bounce(baby2.getDx(), baby2.getDy());
     		}
             
             if(myBaby.getBounds().intersects(baby3.getBounds())) {
             	myBaby.bounce(baby3.getDx(), baby3.getDy());
     		}
        }
        
        else if (Client.players == 4) {
        	ImageIcon ii3 = new ImageIcon(baby3.getImageName());
            Image image3 = ii3.getImage();
//            g2d.drawImage(image3, baby3.getX(), baby3.getY(), this);
//            g2d.drawImage(image4, baby4.getX(), baby4.getY(), this);
        
            ImageIcon ii4 = new ImageIcon(baby4.getImageName());
            Image image4 = ii4.getImage();
            
            if (bc.isOutofBounds(baby3) && baby3life > 0) {
           	 g2d.drawImage(image3, baby3.getX(), baby3.getY(), this);
            }
            else {
            	baby3.setX(0);
            	baby3.setY(0);
            	baby3.setVisible(false);
            }
            
            if (bc.isOutofBounds(baby4) && baby4life > 0) {
              	 g2d.drawImage(image4, baby4.getX(), baby4.getY(), this);
            }
            else {
               	baby4.setX(0);
               	baby4.setY(0);
               	baby4.setVisible(false);
            }
            
            if(myBaby.getBounds().intersects(baby2.getBounds())) {
            	myBaby.bounce(baby2.getDx(), baby2.getDy());
          //   	System.out.println(baby2.getDx() +" "+ baby2.getDy());
        		// System.out.println(Client.name);
    		}
            
            if(myBaby.getBounds().intersects(baby3.getBounds())) {
            	myBaby.bounce(baby3.getDx(), baby3.getDy());
    		}
            
            if(myBaby.getBounds().intersects(baby4.getBounds())) {
            	myBaby.bounce(baby4.getDx(), baby4.getDy());
    		}
        }
        
        myBaby.move();
        myBaby.deccelerate();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		BoundsChecker bc = new BoundsChecker();
    	
// 		if (Client.players == 2) {
// 			if(!bc.isBabyColliding(myBaby, baby2) && myBaby.isKeyPressed){
// 	    		myBaby.move();
// 	    	} else if(myBaby.isKeyPressed == false) {
// 	    		myBaby.deccelerate();
// 	    	} else {
// 	    		baby2.bounce();
// 	    		myBaby.bounce();
// 	    	}
	    	
// 	    	if(!bc.isOutofBounds(myBaby)) {
// //	    		System.out.println("OUT");
// 	    	}
// 		}
    	
// 		else if (Client.players == 3) {
// 			if(!bc.isBabyColliding(myBaby, baby2) && myBaby.isKeyPressed){
// 	    		myBaby.move();
// 	    	} else if(myBaby.isKeyPressed == false) {
// 	    		myBaby.deccelerate();
// 	    	} else {
// 	    		baby2.bounce();
// 	    		myBaby.bounce();
// 	    	}
			
// 			if(!bc.isBabyColliding(myBaby, baby3) && myBaby.isKeyPressed){
// 	    		myBaby.move();
// 	    	} else if(myBaby.isKeyPressed == false) {
// 	    		myBaby.deccelerate();
// 	    	} else {
// 	    		baby3.bounce();
// 	    		myBaby.bounce();
// 	    	}
	    	
// 	    	if(!bc.isOutofBounds(myBaby)) {
// 	    		myBaby.setVisible(false);
// 	    	}
// 		}
		
// 		else if (Client.players == 4) {
// 			if(!bc.isBabyColliding(myBaby, baby2) && myBaby.isKeyPressed){
// 	    		myBaby.move();
// 	    	} else if(myBaby.isKeyPressed == false) {
// 	    		myBaby.deccelerate();
// 	    	} else {
// 	    		baby2.bounce();
// 	    		myBaby.bounce();
// 	    	}
			
// 			if(!bc.isBabyColliding(myBaby, baby3) && myBaby.isKeyPressed){
// 	    		myBaby.move();
// 	    	} else if(myBaby.isKeyPressed == false) {
// 	    		myBaby.deccelerate();
// 	    	} else {
// 	    		baby3.bounce();
// 	    		myBaby.bounce();
// 	    	}
	    	
// 			if(!bc.isBabyColliding(myBaby, baby4) && myBaby.isKeyPressed){
// 	    		myBaby.move();
// 	    	} else if(myBaby.isKeyPressed == false) {
// 	    		myBaby.deccelerate();
// 	    	} else {
// 	    		baby4.bounce();
// 	    		myBaby.bounce();
// 	    	}
			
// 	    	if(!bc.isOutofBounds(myBaby)) {
// 	    		myBaby.setVisible(false);
// 	    	}
// 		}
    	
        repaint();
	}
	
	private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            myBaby.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            myBaby.keyPressed(e);
        }
    }

    private JButton modifyButton (JButton button){
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusable(false);
		return button;
	}
	
	private class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == close){
				try{
					link.close();
				}catch(Exception e){}
					
				main.switchCard("Menu");
				main.setContentPane(main.menuPanel.menuLabel);
				// System.exit(0);
			}
		}
	}
}
