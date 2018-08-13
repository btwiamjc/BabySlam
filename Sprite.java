import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Sprite {
	
	protected int x;
	protected int y;
	private boolean vis;
	protected String imageName;
	private Image image;
	private int width;
	private int height;
	
	public Sprite(int x, int y) {
		this.x = x;
		this.y =y;
		vis = true;
	}
	
	protected void loadImage(String imageName) {
		this.imageName = imageName;
		
		ImageIcon ii = new ImageIcon(imageName);
		image = ii.getImage();
	}
	
	public Image getImage() {
		return image;
	}
	
	protected void getImageDimensions() {
        width = image.getWidth(null);
        height = image.getHeight(null);
	}
	
	public String getImageName() {
		return imageName;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x =x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	public boolean isVisible() {
		return vis;
	}
	
	public void setVisible(boolean visible) {
		this.vis = visible;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 40, 30);
	}
}
