
public class BoundsChecker {
	public boolean isBabyColliding(Baby baby1, Baby baby2) {
		
		if(baby1.getBounds().intersects(baby2.getBounds())) {
			return true;
		}
		
		return false;
	}
	
//	public boolean isBabyColliding(Baby baby1, Baby baby2, Baby baby3) {
//		if(baby1.getBounds().intersects(baby2.getBounds())) {
//			return true;
//		}
//		
//		return false;
//	}
	
	public boolean isOutofBounds(Baby baby) {
		
    	double distance = Math.sqrt( (( 430 - baby.getX() ) * ( 430 - baby.getX() )) + ((280 - baby.getY() ) * (280 - baby.getY() )));
    	
        if (distance > 230){	
        	return false;
        }
    	
        else {
        	return true;
        }
	}
}
