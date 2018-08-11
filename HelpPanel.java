import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpPanel extends JPanel{
	private Main main;
	public static JLabel helpLabel;
	private JButton back;
	ImageIcon icon = new ImageIcon(getClass().getResource("helpbg.png"));
	
	public HelpPanel(Main main){
		this.main = main;
		setLayout(null);
		
		helpLabel = new JLabel(icon);
		helpLabel.setLocation(0,0);
		helpLabel.setSize(910,643);
		
		back = new JButton(new ImageIcon(getClass().getResource("back.png")));
        back.setRolloverIcon(new ImageIcon(getClass().getResource("backhover.png")));
		back.setLocation(10,10);
		back.setSize(132,40);
		helpLabel.add(modifyButton(back));
		
		
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
