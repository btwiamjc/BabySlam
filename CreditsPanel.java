import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreditsPanel extends JPanel{
	private Main main;
	public static JLabel creditsLabel;
	private JButton back;
	ImageIcon icon = new ImageIcon(getClass().getResource("creditsbg.gif"));
	
	public CreditsPanel(Main main){
		this.main = main;
		setLayout(null);
		
		creditsLabel = new JLabel(icon);
		creditsLabel.setLocation(0,0);
		creditsLabel.setSize(910,643);
		
		back = new JButton(new ImageIcon(getClass().getResource("back.png")));
        back.setRolloverIcon(new ImageIcon(getClass().getResource("backhover.png")));
		back.setLocation(10,10);
		back.setSize(132,40);
		creditsLabel.add(modifyButton(back));
		
		
		Handler handler = new Handler();
		back.addActionListener(handler);
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
			}
		}
	}
	
}