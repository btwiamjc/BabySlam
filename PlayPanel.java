import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import javax.swing.JOptionPane;

public class PlayPanel extends JPanel{
	private Main main;
	public static JLabel playLabel;
	private JButton back, createButton, joinButton;
	ImageIcon icon = new ImageIcon(getClass().getResource("bg.png"));

	Scanner input = new Scanner(System.in);
	static String name;
	int players = 0, port;
	String ip;
	static Socket link;
	
	public PlayPanel(Main main){
		this.main = main;
		setLayout(null);
		
		playLabel = new JLabel(icon);
		playLabel.setLocation(0,0);
		playLabel.setSize(910,643);
		
		createButton = new JButton(new ImageIcon(getClass().getResource("creategame.png")));
        createButton.setRolloverIcon(new ImageIcon(getClass().getResource("creategamehover.png")));
		createButton.setLocation(256,389);
		createButton.setSize(266, 78);
		playLabel.add(modifyButton(createButton));
		
		joinButton = new JButton(new ImageIcon(getClass().getResource("joingame.png")));
        joinButton.setRolloverIcon(new ImageIcon(getClass().getResource("joingamehover.png")));
		joinButton.setLocation(519,389);
		joinButton.setSize(266, 78);
		playLabel.add(modifyButton(joinButton));
		
		back = new JButton(new ImageIcon(getClass().getResource("back.png")));
        back.setRolloverIcon(new ImageIcon(getClass().getResource("backhover.png")));
		back.setLocation(10,10);
		back.setSize(132,40);
		playLabel.add(modifyButton(back));
		
		
		Handler handler = new Handler();
		back.addActionListener(handler);
		createButton.addActionListener(handler);
		joinButton.addActionListener(handler);
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
			if(event.getSource() == back){
				main.switchCard("Menu");
				main.setContentPane(main.menuPanel.menuLabel);
			}else if(event.getSource() == createButton){
				Details.choice="start";
				Details.detailsLabel.setIcon(new ImageIcon(getClass().getResource("details1.png")));
				Details.numplayersTField.setVisible(true);
				main.switchCard("Details");
				main.setContentPane(main.detailsPanel.detailsLabel);
			}else if(event.getSource() == joinButton){
				Details.choice="join";
				Details.detailsLabel.setIcon(new ImageIcon(getClass().getResource("details2.png")));
				Details.numplayersTField.setVisible(false);
				main.switchCard("Details");
				main.setContentPane(main.detailsPanel.detailsLabel);
			}
		}
	}
	
}