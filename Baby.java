import java.awt.event.KeyEvent;
import java.util.Random;

public class Baby extends Sprite{

	private int dx;
	private int dy;
	private int speedX;
	private int speedY;
	private int speedIterator = 0;
	public boolean isKeyPressed = false;
	private boolean isBounce = false;
	private String position = "s";

	public Baby(int x, int y) {
		super(x, y);
		
		loadImage("babyDown.png");
		getImageDimensions();
		// TODO Auto-generated constructor stub
	}
	
	public void move() {
		if(x + dx < 720 && x + dx > 1)
    		x += dx;
        if(y + dy < 540 && y + dy > 1)
        	y += dy;
	}
	
	public void deccelerate() {
		speedIterator++;
    	
		if(dx > 0)
	        if (speedIterator%7 == 0) {
	        	speedX--;
	        }
		if(dx < 0)
	        if (speedIterator%7 == 0) {
	        	speedX++;
	        }
		if(dy > 0)
	        if (speedIterator%7 == 0) {
	        	speedY--;
	        }
		if(dy < 0)
	        if (speedIterator%7 == 0) {
	        	speedY++;
	        }
		
  
        if(dx > 0 || dx < 0) {
        	dx = speedX;
        }
        if(dy > 0 || dy < 0) {
        	dy = speedY;
        }
        
		if(x + dx < 720 && x + dx > 1)
    		x += dx;
        if(y + dy < 540 && y + dy > 1)
        	y += dy;
	}
	
	public void bounce(int enemyDx, int enemyDy) {
		x += enemyDx * 5;
		y += enemyDy * 5;
		
		dx = -enemyDx;
		dy = -enemyDy;
//		x += -dx*5;
//		y += -dy*5;
//		
//		speedX = -speedX;
//		speedY = -speedY;
	}
	
	public void keyPressed(KeyEvent e) {
		
		isKeyPressed = true;
		speedIterator++;
		int key = e.getKeyCode();
		
		
		if(key == KeyEvent.VK_A) {
			position = "a";
			dx = speedX;
        	
            if (speedIterator%5 == 0)
            	speedX--; 
			
			Random rand = new Random();
			int ds = rand.nextInt(10);
            if(ds % 2 == 0)
            	loadImage("babyLeft.png");
			else
				loadImage("babyLeft1.png");
		} 
		
		if(key == KeyEvent.VK_D) {
			position = "d";
			dx = speedX;
        	
            if (speedIterator%5 == 0)
            	speedX++;
			
			Random rand = new Random();
			int ds = rand.nextInt(10);
            if(ds % 2 == 0)
            	loadImage("babyRight.png");
			else
				loadImage("babyRight1.png");
		} 
		
		if (key == KeyEvent.VK_W) {
			position = "w";
			dy = speedY;
        	
            if (speedIterator%5 == 0)
            	speedY--;
            
            Random rand = new Random();
			int ds = rand.nextInt(10);
            if(ds % 2 == 0)
            	loadImage("babyUp.png");
			else
				loadImage("babyUp1.png");
        }
		
		if (key == KeyEvent.VK_S) {
			position = "s";
			dy = speedY;
        	
            if (speedIterator%5 == 0)
            	speedY++;
            
            Random rand = new Random();
			int ds = rand.nextInt(10);
            if(ds % 2 == 0)
            	loadImage("babyDown.png");
			else
				loadImage("babyDown1.png");
        }
        
	}
	
	public void keyReleased(KeyEvent e) {

		isKeyPressed = false;
		speedIterator = 0;
		int key = e.getKeyCode();

        if (key == KeyEvent.VK_S) {
            //speedY = 0;
        }

        if (key == KeyEvent.VK_W) {
            //speedY = 0;
        }

        if (key == KeyEvent.VK_A) {
            //speedX = 0;
        }

        if (key == KeyEvent.VK_D) {
            //speedX = 0;
        }
    }
	
	public void setDx(int dx) {
		this.dx = dx;
	}
	
	public void setDy(int dy) {
		this.dy = dy;
	}
	
	public int getDx() {
		return dx;
	}
	
	public int getDy() {
		return dy;
	}
	
	public void setBounce(boolean isBounce) {
		this.isBounce = isBounce;
	}
	
	public boolean getBounce() {
		return isBounce;
	}
	
	public void setDirection(String pos) {
		this.position = pos;
	}
	
	public String getDirection() {
		return position;
	}
}
