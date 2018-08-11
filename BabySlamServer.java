import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class BabySlamServer implements Runnable{
	private static Main main;
	public int port;
	InetAddress host;
	public static int recordPlayers;
	public static int players;
	public static ArrayList<ObjectOutputStream> outStreams = new ArrayList<ObjectOutputStream>();
	public static ArrayList<Socket> socketList = new ArrayList<Socket>();
	static ServerSocket serverSocket;
	public String babyPos, name;
	// static Socket link;
	
	public BabySlamServer(int players, int port, Main main) {
		this.port = port;
		this.main = main;
		BabySlamServer.players = players;
	}
	
	public static class Handler extends Thread{
		Socket socket;
		public Handler() throws ClassNotFoundException{
			while(true) {
				try {
					socket = serverSocket.accept();
					
					recordPlayers++;
					System.out.println(BabySlamServer.recordPlayers + "/" + BabySlamServer.players);
					
					socketList.add(socket);
					Thread.sleep(100);
					Details.changeIcon(BabySlamServer.recordPlayers,BabySlamServer.players);

					if (BabySlamServer.recordPlayers == 2 && BabySlamServer.players == 2) {
						main.detailsPanel.detailsLabel.setIcon(new ImageIcon(getClass().getResource("2players2.png")));
						main.detailsPanel.start.setVisible(true);
					}else if (BabySlamServer.recordPlayers == 1 && BabySlamServer.players == 2) {
						main.detailsPanel.detailsLabel.setIcon(new ImageIcon(getClass().getResource("2players.png")));
						main.repaint();
					}else if (BabySlamServer.recordPlayers == 1 && BabySlamServer.players == 3) {
						main.detailsPanel.detailsLabel.setIcon(new ImageIcon(getClass().getResource("3players.png")));
					}else if (BabySlamServer.recordPlayers == 2 && BabySlamServer.players == 3) {
						main.detailsPanel.detailsLabel.setIcon(new ImageIcon(getClass().getResource("3players2.png")));
					}else if (BabySlamServer.recordPlayers == 3 && BabySlamServer.players == 3) {
						main.detailsPanel.detailsLabel.setIcon(new ImageIcon(getClass().getResource("3players3.png")));
						main.detailsPanel.start.setVisible(true);
					}else if (BabySlamServer.recordPlayers == 1 && BabySlamServer.players == 4) {
						main.detailsPanel.detailsLabel.setIcon(new ImageIcon(getClass().getResource("4players.png")));
					}else if (BabySlamServer.recordPlayers == 2 && BabySlamServer.players == 4) {
						main.detailsPanel.detailsLabel.setIcon(new ImageIcon(getClass().getResource("4players2.png")));
					}else if (BabySlamServer.recordPlayers == 3 && BabySlamServer.players == 4) {
						main.detailsPanel.detailsLabel.setIcon(new ImageIcon(getClass().getResource("4players3.png")));
					}else if (BabySlamServer.recordPlayers == 4 && BabySlamServer.players == 4) {
						main.detailsPanel.detailsLabel.setIcon(new ImageIcon(getClass().getResource("4players4.png")));
						main.detailsPanel.start.setVisible(true);
					}


					if (BabySlamServer.recordPlayers == BabySlamServer.players) {
						System.out.println("Stop accepting players and begin the game...");
						main.clientPanel.clientLabel.setIcon(new ImageIcon(getClass().getResource("serverStart.png")));
						// Client.showClient(link);
						main.switchCard("Client");
						// main.setContentPane(main.clientPanel.clientLabel);
						new Server(socketList);
						// BattleField.battleFieldShow();
						///// We will have a thread in this part
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	

	public void run() {
		try {
			serverSocket= new ServerSocket(port);
			new Handler().start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				serverSocket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void changeIcon(){
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					System.out.println("CHANGE ICIIIIIIIIIIIIIIIIIIIIING");
					if (BabySlamServer.recordPlayers == 2 && BabySlamServer.players == 2) {
						main.detailsPanel.detailsLabel.setIcon(new ImageIcon(getClass().getResource("2players2.png")));
						main.detailsPanel.start.setVisible(true);
					}else if (BabySlamServer.recordPlayers == 1 && BabySlamServer.players == 2) {
						main.detailsPanel.detailsLabel.setIcon(new ImageIcon(getClass().getResource("2players.png")));
						main.repaint();
					}else if (BabySlamServer.recordPlayers == 1 && BabySlamServer.players == 3) {
						main.detailsPanel.detailsLabel.setIcon(new ImageIcon(getClass().getResource("3players.png")));
					}else if (BabySlamServer.recordPlayers == 2 && BabySlamServer.players == 3) {
						main.detailsPanel.detailsLabel.setIcon(new ImageIcon(getClass().getResource("3players2.png")));
					}else if (BabySlamServer.recordPlayers == 3 && BabySlamServer.players == 3) {
						main.detailsPanel.detailsLabel.setIcon(new ImageIcon(getClass().getResource("3players3.png")));
						main.detailsPanel.start.setVisible(true);
					}else if (BabySlamServer.recordPlayers == 1 && BabySlamServer.players == 4) {
						main.detailsPanel.detailsLabel.setIcon(new ImageIcon(getClass().getResource("4players.png")));
					}else if (BabySlamServer.recordPlayers == 2 && BabySlamServer.players == 4) {
						main.detailsPanel.detailsLabel.setIcon(new ImageIcon(getClass().getResource("4players2.png")));
					}else if (BabySlamServer.recordPlayers == 3 && BabySlamServer.players == 4) {
						main.detailsPanel.detailsLabel.setIcon(new ImageIcon(getClass().getResource("4players3.png")));
					}else if (BabySlamServer.recordPlayers == 4 && BabySlamServer.players == 4) {
						main.detailsPanel.detailsLabel.setIcon(new ImageIcon(getClass().getResource("4players4.png")));
						main.detailsPanel.start.setVisible(true);
					}
				}
			}
		);
	}
	
}
