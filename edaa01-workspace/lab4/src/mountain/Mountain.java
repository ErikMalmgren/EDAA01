package mountain;

import fractal.*;
import java.util.*;

public class Mountain extends Fractal {
	private Point a, b, c;
	private double dev;
	private Map<Side, Point> sideMap = new HashMap<Side, Point>();
	
	
	public Mountain(Point a, Point b, Point c, double dev) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.dev = dev;
	}

	@Override
	public String getTitle() {
		return "Bergsfraktal";
	}

	@Override
	public void draw(TurtleGraphics turtle) {
		fractalTriangle(turtle, order, a, b, c, dev);
		
	}
	
	private void fractalTriangle(TurtleGraphics turtle, int order, Point a, Point b, Point c, double dev) {
		if(order == 0) {
			turtle.moveTo(a.getX(), a.getY());
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
		} else {
			int random = (int) RandomUtilities.randFunc(dev);
			
			Point ab = newPoint(a, b, random);
			Point ac = newPoint(a, c, random);
			Point bc = newPoint(b, c, random);
			
			fractalTriangle(turtle, order - 1, a, ab, ac, dev/2);
			fractalTriangle(turtle, order - 1, bc, b, ab, dev/2);
			fractalTriangle(turtle, order - 1, ac, bc, c, dev/2);
			fractalTriangle(turtle, order - 1, ab, ac, bc, dev/2);
		}
	}
	
	private Point newPoint(Point a, Point b, int random) {
		Point newPoint;
		Side newSide = new Side(a, b);
		if(sideMap.containsKey(newSide)) {
			newPoint = sideMap.get(newSide);
			sideMap.remove(newSide);
		} else {
			int newX = (int) (a.getX() + .5 *(b.getX()-a.getX()));
			int newY = (int) (a.getY() + .5 *(b.getY()-a.getY())) + random;
			newPoint = new Point(newX, newY);
			sideMap.put(newSide, newPoint);
		}
		
		
		return newPoint;
	}

}
