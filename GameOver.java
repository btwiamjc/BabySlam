
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class GameOver {
	int place;
	String name;
	
	///**Winner not yet included *////
	public GameOver (ArrayList<Socket> socketList, ArrayList<String> names, ArrayList<String> nameOfPlayers) throws IOException {
		place = names.size() + 1;
		
		////// Compare nameOfPlayers to names
		///// If not in names, add
		System.out.println(names);
		System.out.println(nameOfPlayers);
		
		for(int i=0;i<nameOfPlayers.size();i++){
			if(!names.contains(nameOfPlayers.get(i))){
				names.add(nameOfPlayers.get(i));
			}
		}
		
		for(int i=0; i<names.size(); i++) {
			name = names.get(i);
			
			for(Socket socket2 : socketList) {
				ObjectOutputStream outStream = new ObjectOutputStream(socket2.getOutputStream());
				outStream.writeObject(new GameOverMessage(name, place));
			}
			place--;
		}
	}
}
