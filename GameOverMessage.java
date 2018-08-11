import java.io.Serializable;

@SuppressWarnings("serial")
public class GameOverMessage implements Serializable{
	String name;
	int place;
	
	public GameOverMessage(String name, int place) {
		this.name = name;
		this.place = place;
	}
	
	public GameOverMessage() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public int getPlace() {
		return place;
	}
}
