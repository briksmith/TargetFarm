package model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class RectangleTest
{

	private static final int _1 = 1;

	@Before
	public void setUp(){
		
	}
	
	@Test 
	public void testRectangleConstructor(){
		Point lowerLeft = new Point(1, 4);
		Point upperRight = new Point(2, 6);
		
		Rectangle rectangle = new Rectangle(lowerLeft, upperRight);
		assertTrue("Lower left point should have been: " + lowerLeft.toString() + ".  Was: " + rectangle.getLowerLeftPoint(),
				rectangle.getLowerLeftPoint().equals(lowerLeft));
		assertTrue("Upper right point should have been: " + upperRight.toString() + " Was: " + rectangle.getUpperRightPoint(),
				rectangle.getUpperRightPoint().equals(upperRight));
		
		
	}
	
	@Test
	public void testRectangleForDefensiveCopying() {
		Point lowerLeft = new Point(1,4);
		Point upperRight = new Point(2,6);
		
		Rectangle rectangle = new Rectangle(lowerLeft, upperRight);
		lowerLeft.setX(2);
		upperRight.setX(3);
		assertTrue("Lower left point should still be 1, 4.  Was: " + rectangle.getLowerLeftPoint().toString(), rectangle.getLowerLeftPoint().equals(new Point(1,4)));
		assertTrue("Upper right point should still be 2, 6.  Was:  " + rectangle.getUpperRightPoint().toString(), rectangle.getUpperRightPoint().equals(new Point(2, 6)));
	}
}
