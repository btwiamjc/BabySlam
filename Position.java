import java.io.Serializable;

@SuppressWarnings("serial")
public class Position implements Serializable{
	int x, y;
	int dx, dy;
	String babypos;
	int life;
	String name;
	String direction;
	
	public Position() {
		
	}
	
	public Position(int dx, int dy, String direction, int x, int y, String babypos, int life, String name) {
		this.x = x;
		this.y = y;
		this.babypos = babypos;
		this.life = life;
		this.name = name;
		this.direction = direction;
		this.dx = dx;
		this.dy = dy;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getDx() {
		return dx;
	}
	
	public int getDy() {
		return dy;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public String getBabypos() {
		return babypos;
	}
	
	public void setLife(int life) {
		this.life = life;
	}
	
	public int getLife() {
		return life;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDirection() {
		return direction;
	}
}
