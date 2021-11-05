package mountain;

public class Side {
	private Point p1, p2;
	
	public Side(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Side) {
			Side temp = (Side) o;
			return (p1 == temp.p1 && p2 == temp.p2) || (p1 == temp.p2 && p2 == temp.p1);
		}
		
		return false;
	}
	
	
	@Override
	public int hashCode() {
		return p1.hashCode() + p2.hashCode();
	}
	

}
