import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel{
	private Main main;
	public static JLabel menuLabel;
	private JButton highscore, credits, play, help, close;
	ImageIcon icon = new ImageIcon(getClass().getResource("bg.png"));
	
	public MenuPanel(Main main){
		this.main = main;
		setLayout(null);
		
		menuLabel = new JLabel(icon);
		menuLabel.setLocation(0,0);
		menuLabel.setSize(910,643);
		
		play = new JButton(new ImageIcon(getClass().getResource("play.png")));
        play.setRolloverIcon(new ImageIcon(getClass().getResource("playhover.png")));
		play.setLocation(256,308);
		play.setSize(266, 88);
		menuLabel.add(modifyButton(play));
		
		highscore = new JButton(new ImageIcon(getClass().getResource("highscore.png")));
        highscore.setRolloverIcon(new ImageIcon(getClass().getResource("highscorehover.png")));
		highscore.setLocation(256,389);
		highscore.setSize(266, 88);
		menuLabel.add(modifyButton(highscore));
		
		credits = new JButton(new ImageIcon(getClass().getResource("credits.png")));
        credits.setRolloverIcon(new ImageIcon(getClass().getResource("creditshover.png")));
		credits.setLocation(519,389);
		credits.setSize(266, 88);
		menuLabel.add(modifyButton(credits));
		
		help = new JButton(new ImageIcon(getClass().getResource("help.png")));
        help.setRolloverIcon(new ImageIcon(getClass().getResource("helphover.png")));
		help.setLocation(519,308);
		help.setSize(266, 88);
		menuLabel.add(modifyButton(help));
		
		close = new JButton(new ImageIcon(getClass().getResource("close.png")));
        close.setRolloverIcon(new ImageIcon(getClass().getResource("closehover.png")));
		close.setLocation(876,1);
		close.setSize(38,44);
		menuLabel.add(modifyButton(close));
		
		
		Handler handler = new Handler();
		close.addActionListener(handler);
		credits.addActionListener(handler);
		play.addActionListener(handler);
		//highscore.addActionListener(handler);
		help.addActionListener(handler);
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
				System.exit(0);
			}
			else if(event.getSource() == credits){
				main.switchCard("Credits");
				main.setContentPane(main.creditsPanel.creditsLabel);
			}
			else if(event.getSource() == play){
				main.switchCard("Play");
				main.setContentPane(main.playPanel.playLabel);
			}
			else if(event.getSource() == help){
				main.switchCard("Help");
				main.setContentPane(main.helpPanel.helpLabel);
			}
		}
	}
	
}