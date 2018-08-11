import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	ObjectOutputStream outStream;
	ObjectInputStream inStream;
	ArrayList<String> places = new ArrayList<String>();
	ArrayList<String> nameOfPlayers = new ArrayList<String>();
	
	static int positionNum = 0;
	
	public Server(ArrayList<Socket> socketList) throws ClassNotFoundException {
		
		for(Socket socket : socketList) {
			try {
				outStream = new ObjectOutputStream(socket.getOutputStream());
				outStream.writeObject(new Message("baby" + Integer.toString(++positionNum), BabySlamServer.players));
				
//				outStream = new ObjectOutputStream(socket.getOutputStream());
//				outStream.writeObject(new Message(Integer.toString(BabySlamServer.players)));
				
				Message message = new Message();
				inStream = new ObjectInputStream(socket.getInputStream());
				message = (Message) inStream.readObject();
				nameOfPlayers.add(message.getName());
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println(nameOfPlayers);
		
		try {
			int xpos, ypos, life, dx, dy;
			String babypos, name, direction;
			
			while(true) {
				for(Socket socket : socketList) {
					if (true) {
						//You must also send what baby is the sender
						//Use the Message to receive the position and the baby name 
						//The message that will be received is the current position of the sender
						Position pos = new Position();
						inStream = new ObjectInputStream(socket.getInputStream());
						//System.out.println("RECEIVED...");
						pos = (Position) inStream.readObject();
						xpos = pos.getX();
						ypos = pos.getY();
						babypos = pos.getBabypos();
						life = pos.getLife();
						name = pos.getName();
						direction = pos.getDirection();
						dx = pos.getDx();
						dy = pos.getDy();
						
						//****** There is a problem in adding the winner ***//
						
						if (life == 0 && !places.contains(name)) {
							places.add(name);
							System.out.println("Socket added...");
							
							if (places.size() == BabySlamServer.players-1) {
								System.out.println("Game Over...");
								
								for(Socket socket2 : socketList) {
									outStream = new ObjectOutputStream(socket2.getOutputStream());
									outStream.writeObject(new Position(dx, dy, direction, xpos, ypos, babypos, life, "GAMEOVER"));
								}
								new GameOver(socketList, places, nameOfPlayers);
								break;
							}
							
							else {
								for(Socket socket2 : socketList) {
									outStream = new ObjectOutputStream(socket2.getOutputStream());
									outStream.writeObject(new Position(dx, dy, direction, xpos, ypos, babypos, life, name));
								}									
							}
						}
						
						else {
							for(Socket socket2 : socketList) {
								//Send to all clients the position and baby name
								outStream = new ObjectOutputStream(socket2.getOutputStream());
								outStream.writeObject(new Position(dx, dy, direction, xpos, ypos, babypos, life, name));
//								System.out.println("x: " + xpos + "y: " + ypos);
							}
							
						}
						
						
					}
				}
				
			}
			
		}
		
		catch(IOException ex) {
			ex.printStackTrace();
		}
		
	}
}
