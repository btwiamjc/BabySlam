import java.io.IOException;
import java.io.ObjectInputStream;

public class Receive extends Thread{
	ObjectInputStream inStream;
	int xpos, ypos;
	
	public void run() {
		for (int i = 0; i<Client.players; i++){
//		while(true) {
			try {
				
				Position pos = new Position();
				inStream = new ObjectInputStream(Details.link.getInputStream());
				pos = (Position) inStream.readObject();
				xpos = pos.getX();
				ypos = pos.getY();
				System.out.println("x: " + xpos + "y: " + ypos);
				
			} catch (IOException | ClassNotFoundException | NullPointerException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			

		}
	}
}
