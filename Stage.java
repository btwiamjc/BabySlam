
public class Stage {
	private int centerX;
	private int centerY;
	private double radius;
	
	public Stage(){
		
	}
	
	public Stage(int centerX, int centerY, double radius){
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
	}
	
	public double getRadius(){
		return radius;
	}
	
	public int getxRad(){
		return centerX;
	}
	
	public int getyRad(){
		return centerY;
	}
	
	public int getCenterX(){
		return centerX;
	}
	
	public int getCenterY(){
		return centerY;
	}
}
