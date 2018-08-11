import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import javax.swing.JOptionPane;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Details extends JPanel{
	private static Main main;
	static String choice;
	public static JLabel detailsLabel;
	private JButton back,next;
	public static JButton start;
	public static JTextField nameTField,portTField,ipTField,numplayersTField;
	ImageIcon icon = new ImageIcon(getClass().getResource("details1.png"));
	public static ArrayList<Socket> sList;
	static String name;
	int players = 0, port;
	String ip;
	static Socket link;
	static BabySlamServer server;

	public Details(Main main){
		this.main = main;
		setLayout(null);

		detailsLabel = new JLabel(icon);
		detailsLabel.setLocation(0,0);
		detailsLabel.setSize(910,643);
		
		back = new JButton(new ImageIcon(getClass().getResource("back.png")));
        back.setRolloverIcon(new ImageIcon(getClass().getResource("backhover.png")));
		back.setLocation(10,10);
		back.setSize(132,40);
		detailsLabel.add(modifyButton(back));

		next = new JButton(new ImageIcon(getClass().getResource("next.png")));
        next.setRolloverIcon(new ImageIcon(getClass().getResource("nexthover.png")));
		next.setLocation(765,513);
		next.setSize(112,100);
		detailsLabel.add(modifyButton(next));

		start = new JButton(new ImageIcon(getClass().getResource("start.png")));
        start.setRolloverIcon(new ImageIcon(getClass().getResource("starthover.png")));
		start.setLocation(657,513);
		start.setSize(220,100);
		start.setVisible(false);
		detailsLabel.add(modifyButton(start));

		nameTField = new JTextField();
		nameTField.setLocation(369,170);
		nameTField.setSize(397,35);
		nameTField.setOpaque(false);
		nameTField.setForeground(Color.WHITE);
		nameTField.setFont(new Font("Helvetica", Font.BOLD, 16));
		detailsLabel.add(nameTField);

		portTField = new JTextField();
		portTField.setLocation(489,247);
		portTField.setSize(275,35);
		portTField.setOpaque(false);
		portTField.setForeground(Color.WHITE);
		portTField.setFont(new Font("Helvetica", Font.BOLD, 16));
		detailsLabel.add(portTField);

		portTField.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				char ch = e.getKeyChar();
				if(!isNumber(ch))
					e.consume();
			}
		});

		ipTField = new JTextField();
		ipTField.setLocation(368,369);
		ipTField.setSize(397,35);
		ipTField.setOpaque(false);
		ipTField.setFont(new Font("Helvetica", Font.BOLD, 16));
		ipTField.setForeground(Color.WHITE);
		detailsLabel.add(ipTField);
		
		numplayersTField = new JTextField();
		numplayersTField.setLocation(368,479);
		numplayersTField.setSize(397,35);
		numplayersTField.setOpaque(false);
		numplayersTField.setForeground(Color.WHITE);
		numplayersTField.setFont(new Font("Helvetica", Font.BOLD, 16));
		detailsLabel.add(numplayersTField);
		
		numplayersTField.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				char ch = e.getKeyChar();
				if(!isNumber(ch))
					e.consume();
			}
		});
		
		
		Handler handler = new Handler();
		back.addActionListener(handler);
		next.addActionListener(handler);
		start.addActionListener(handler);
	}

	private JButton modifyButton (JButton button){
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusable(false);
		return button;
	}
	
	public boolean isNumber(char ch){
		return ch >= '0' && ch <= '9';
	}

	private class Handler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == back){
				main.switchCard("Menu");
				main.setContentPane(main.menuPanel.menuLabel);
			}else if(event.getSource() == next){
				if(choice.equals("start")){
					if(checkInput()){
						next.setVisible(false);
						name = nameTField.getText();
						players = Integer.parseInt(numplayersTField.getText());
						port = Integer.parseInt(portTField.getText());
						ip = ipTField.getText();
						
						changeIcon(1,players);
						if(players==2){
							detailsLabel.setIcon(new ImageIcon(getClass().getResource("2players.png")));
						}else if(players==3){
							detailsLabel.setIcon(new ImageIcon(getClass().getResource("3players.png")));
						}else if(players==4){
							detailsLabel.setIcon(new ImageIcon(getClass().getResource("4players.png")));
						}

						server = new BabySlamServer(players, port, main);
						Thread thServer = new Thread(server);
						thServer.start();

						back.setVisible(false);
						nameTField.setVisible(false);
						numplayersTField.setVisible(false);
						portTField.setVisible(false);
						ipTField.setVisible(false);


						try {
							System.out.println("Trying to connect...");
							link = new Socket(ip, port);
							Client.link = link;
							// BabySlamServer.link = link;
							Client.showClient(link);
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							System.out.println("Host ID not found");
									
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}else if(choice.equals("join")){
					name = nameTField.getText();
					port = Integer.parseInt(portTField.getText());
					ip = ipTField.getText();
					
					try {
						System.out.println("Trying to connect...");
						link = new Socket(ip, port);
						main.clientPanel.link = link;
						Client.link=link;
						Client.showClient(link);
						main.switchCard("Client");
						// main.setContentPane(main.clientPanel.clientLabel);
						
						// c.showClient();
										
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						System.out.println("Host ID not found");
								
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	private boolean checkInput(){
		int num = Integer.parseInt(numplayersTField.getText());
		if(num < 4 && num > 1){
			return true;
		}
		return false;
	}

	public static void changeIcon(int record, int total){
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					if (record == 2 && total == 2) {
						detailsLabel.setIcon(new ImageIcon(getClass().getResource("2players2.png")));
						start.setVisible(true);
					}else if (record == 1 && total == 2) {
						detailsLabel.setIcon(new ImageIcon(getClass().getResource("2players.png")));
					}else if (record == 1 && total == 3) {
						detailsLabel.setIcon(new ImageIcon(getClass().getResource("3players.png")));
					}else if (record == 2 && total == 3) {
						detailsLabel.setIcon(new ImageIcon(getClass().getResource("3players2.png")));
					}else if (record == 3 && total == 3) {
						detailsLabel.setIcon(new ImageIcon(getClass().getResource("3players3.png")));
						start.setVisible(true);
					}else if (record == 1 && total == 4) {
						detailsLabel.setIcon(new ImageIcon(getClass().getResource("4players.png")));
					}else if (record == 2 && total == 4) {
						detailsLabel.setIcon(new ImageIcon(getClass().getResource("4players2.png")));
					}else if (record == 3 && total == 4) {
						detailsLabel.setIcon(new ImageIcon(getClass().getResource("4players3.png")));
					}else if (record == 4 && total == 4) {
						detailsLabel.setIcon(new ImageIcon(getClass().getResource("4players4.png")));
						start.setVisible(true);
					}
				}
			}
		);
	}

	public static void keme() throws Exception{
		Client.showClient(link);
	}
}