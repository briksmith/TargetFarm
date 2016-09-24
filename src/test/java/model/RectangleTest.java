package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class RectangleTest
{

	@Before
	public void setUp()
	{

	}

	@Test
	public void testRectangleConstructor()
	{
		Point lowerLeft = new Point(1, 4);
		Point upperRight = new Point(2, 6);

		Rectangle rectangle = new Rectangle(lowerLeft, upperRight);
		assertTrue("Lower left point should have been: " + lowerLeft.toString() + ".  Was: "
				+ rectangle.getLowerLeftPoint(), rectangle.getLowerLeftPoint().equals(lowerLeft));
		assertTrue("Upper right point should have been: " + upperRight.toString() + " Was: "
				+ rectangle.getUpperRightPoint(), rectangle.getUpperRightPoint().equals(upperRight));

	}

	@Test
	public void testRectangleConstructorForDefensiveCopying()
	{
		Point lowerLeft = new Point(1, 4);
		Point upperRight = new Point(2, 6);

		Rectangle rectangle = new Rectangle(lowerLeft, upperRight);
		lowerLeft.setX(2);
		upperRight.setX(3);
		assertTrue("Lower left point should still be 1, 4.  Was: " + rectangle.getLowerLeftPoint().toString(),
				rectangle.getLowerLeftPoint().equals(new Point(1, 4)));
		assertTrue("Upper right point should still be 2, 6.  Was:  " + rectangle.getUpperRightPoint().toString(),
				rectangle.getUpperRightPoint().equals(new Point(2, 6)));
	}

	@Test
	public void testContainsPointInside()
	{
		Point testPointInside = new Point(3, 5);
		Point lowerLeft = new Point(0, 0);
		Point upperRight = new Point(4, 6);

		Rectangle rectangle = new Rectangle(lowerLeft, upperRight);

		assertTrue(pointShouldHaveBeenFoundErrorMessage(testPointInside, rectangle), rectangle.Contains(testPointInside));

	}

	private String pointShouldHaveBeenFoundErrorMessage(Point testPointInside, Rectangle rectangle)
	{
		return "Point: " + testPointInside.toString() + " should have been found in Rectangle: " + rectangle.toString()
				+ " and was not.";
	}
	
	@Test
	public void testContainsBottomEdgePoint()
	{
		Point testPointOnBottomEdge = new Point(2, 0);
		Point lowerLeft = new Point(0,0);
		Point upperRight = new Point(4, 6);
		
		Rectangle rectangle = new Rectangle(lowerLeft, upperRight);
		assertTrue(pointShouldHaveBeenFoundErrorMessage(testPointOnBottomEdge, rectangle), rectangle.Contains(testPointOnBottomEdge));
		
	}
	
	@Test
	public void testContainsRightEdgePoint()
	{
		Point testPointOnRightEdge = new Point(4, 2);
		Point lowerLeft = new Point(0,0);
		Point upperRight = new Point(4, 6);
		
		Rectangle rectangle = new Rectangle(lowerLeft, upperRight);
		assertTrue(pointShouldHaveBeenFoundErrorMessage(testPointOnRightEdge, rectangle), rectangle.Contains(testPointOnRightEdge));
		
	}
	
	@Test
	public void testContainsTopEdgePoint()
	{
		Point testPointOnTopEdge = new Point(2, 6);
		Point lowerLeft = new Point(0,0);
		Point upperRight = new Point(4, 6);
		
		Rectangle rectangle = new Rectangle(lowerLeft, upperRight);
		assertTrue(pointShouldHaveBeenFoundErrorMessage(testPointOnTopEdge, rectangle), rectangle.Contains(testPointOnTopEdge));
		
	}
	@Test
	public void testContainsLeftEdgePoint()
	{
		Point testPointOnLeftEdge = new Point(0, 3);
		Point lowerLeft = new Point(0,0);
		Point upperRight = new Point(4, 6);
		
		Rectangle rectangle = new Rectangle(lowerLeft, upperRight);
		assertTrue(pointShouldHaveBeenFoundErrorMessage(testPointOnLeftEdge, rectangle), rectangle.Contains(testPointOnLeftEdge));
		
	}

	@Test
	public void testOutsidePointShouldNotBeFound()
	{
		Point testPointOutside = new Point(5, 8);
		Point lowerLeft = new Point(0, 0);
		Point upperRight = new Point(4, 6);

		Rectangle rectangle = new Rectangle(lowerLeft, upperRight);

		assertFalse("Point: " + testPointOutside.toString() + " should have not been found in Rectangle: "
				+ rectangle.toString() + " and was.", rectangle.Contains(testPointOutside));

	}
}
