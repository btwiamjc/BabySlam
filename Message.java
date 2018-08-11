import java.io.Serializable;

@SuppressWarnings("serial")
public class Message implements Serializable {
	int players;
	String babypos;
	int life;
	String name;
	
	public Message() {
		
	}
	
	public Message(String babypos, int players) {
		this.babypos = babypos;
		this.players = players;
	}
	
	public Message(String babypos, int players, String name) {
		this.babypos = babypos;
		this.players = players;
		this.name = name;
	}
	
	public int getPlayers() {
		return players;
	}
	
	public String getBabypos() {
		return babypos;
	}
	
	public String getName() {
		return name;
	}
}
