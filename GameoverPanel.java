import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;

public class GameoverPanel extends JPanel{
	private Main main;
	public static JLabel gameoverLabel;
	ImageIcon icon = new ImageIcon(getClass().getResource("gameover.png"));
	static String name;
	static int place;
	private JButton close;
	static ArrayList<String> namesPlace = new ArrayList<String>();
	JTextField[] placeTField;	
	static int x = 0;

	public GameoverPanel(Main main){
		this.main = main;
		setLayout(null);
		
		gameoverLabel = new JLabel(icon);
		gameoverLabel.setLocation(0,0);
		gameoverLabel.setSize(910,643);

		placeTField = new JTextField[4];	
		int y=200;
		for(int i=0; i < 4; i++){
			placeTField[i] = new JTextField();
			placeTField[i].setLocation(270,y+=70);
			placeTField[i].setSize(397,35);
			placeTField[i].setOpaque(false);
			placeTField[i].setHorizontalAlignment(JTextField.CENTER);
			placeTField[i].setForeground(Color.WHITE);
			placeTField[i].setFont(new Font("Helvetica", Font.BOLD, 16));
			placeTField[i].setVisible(false);
			gameoverLabel.add(placeTField[i]);
		}

		close = new JButton(new ImageIcon(getClass().getResource("close.png")));
        close.setRolloverIcon(new ImageIcon(getClass().getResource("closehover.png")));
		close.setLocation(876,1);
		close.setSize(38,44);
		gameoverLabel.add(modifyButton(close));
		
		
		Handler handler = new Handler();
		close.addActionListener(handler);
	}
	
	private JButton modifyButton (JButton button){
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusable(false);
		return button;
	}
	
	private class Handler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == close){
				main.switchCard("Menu");
				main.setContentPane(main.menuPanel.menuLabel);
			}
		}
	}

	public static void printPlaces() throws IOException, ClassNotFoundException{	
		while(true) {
			x++;
			GameOverMessage mess = new GameOverMessage();
			ObjectInputStream inStream = new ObjectInputStream(Details.link.getInputStream());
			mess = (GameOverMessage) inStream.readObject();
			name = mess.getName();
			place = mess.getPlace();
			
			namesPlace.add(name);
			System.out.println(name + ": " + place + " place");

			if(x == Client.players)break;
		}		
	}

	public void showPlaces(){
		System.out.println(namesPlace);
		int count = Client.players-1;
		int c = 0;
		for(int i=count; i>=0; i--){
			placeTField[c].setVisible(true);
			if(i == count){
				placeTField[c++].setText(" 1st Place: " + namesPlace.get(i) + " ");
			}else if(i == count - 1){
				placeTField[c++].setText(" 2nd Place: " + namesPlace.get(i) + " ");
			}else if(i == count - 2){
				placeTField[c++].setText(" 3rd Place: " + namesPlace.get(i) + " ");
			}else if(i == count - 3){
				placeTField[c++].setText(" 4th Place: " + namesPlace.get(i) + " ");
			}
		}

		repaint();	
	}
	
}
