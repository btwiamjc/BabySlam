import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends JPanel{
	static BattleField bF;
	private static Main main;
	static ObjectInputStream inStream;
	//String mess;
	static Socket link;
	static int players;
	static String babyPos = "";
	int xpos, ypos;
	static int life = 3;
	static String name;
	public static JLabel clientLabel;
	ImageIcon icon = new ImageIcon(getClass().getResource("waitPlayers.png"));

	public Client(Main main){
		this.main = main;

		clientLabel = new JLabel(icon);
		clientLabel.setLocation(0,0);
		clientLabel.setSize(910,643);
	}	

	public static void showClient(Socket l) throws IOException, ClassNotFoundException{
		link = l;
		System.out.println("Sulod sa Client");

		// BabySlamServer.changeIcon();
		
		Message mess = new Message();
		inStream = new ObjectInputStream(link.getInputStream());
		mess = (Message) inStream.readObject();

		babyPos = mess.getBabypos();
		players = mess.getPlayers();
		name = Details.name;
		
		System.out.println("Babypos: " +babyPos + " Players: " + players);
		System.out.println(name);
		System.out.println("Client Must be printing...");

		ObjectOutputStream outStream = new ObjectOutputStream(link.getOutputStream());
		outStream.writeObject(new Message(babyPos, players, name));

		if (babyPos.equals("baby1") || babyPos.equals("baby2") || babyPos.equals("baby3") || babyPos.equals("baby4")) {
			System.out.println("HEEEEEEEEEEEEEEEEEEERE");
			toBattleField();
		}
	}

	public static void toBattleField(){
		bF = new BattleField(main);
		bF.setVisible(true);
		// main.setContentPane(bF);
		main.repaint();
		main.validate();
	}
}