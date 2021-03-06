package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;



public class RectangleTest
{

	private final Point rect1LowerLeft = new Point(2, 4);
	private final Point rect1UpperRight = new Point(5, 10);
	private final Rectangle testRectangle = new Rectangle(rect1LowerLeft, rect1UpperRight);
	
	private final Point overLapRectLowerLeft = new Point(3, 6);
	private final Point overLapRectUpperRight = new Point(4, 12);
	private final Rectangle overLapRectangle = new Rectangle(overLapRectLowerLeft, overLapRectUpperRight);
	
	private class PointInfo {
		
		int lowerLeftX;
		int lowerLeftY;
		int upperRightX;
		int upperRightY;
		
		PointInfo(Point lowerLeft, Point upperRight){
			lowerLeftX = lowerLeft.getX();
			lowerLeftY = lowerLeft.getY();
			upperRightX = upperRight.getX();
			upperRightY = upperRight.getY();
		}
		
	}
	
	@Before
	public void setUp()
	{

	}

	@Test
	public void testRectangleConstructor()
	{
		Point lowerLeft = new Point(1, 4);
		Point upperRight = new Point(4, 8);

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

		assertTrue(pointShouldHaveBeenFoundErrorMessage(testPointInside, rectangle),
				rectangle.Contains(testPointInside));

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
		Point lowerLeft = new Point(0, 0);
		Point upperRight = new Point(4, 6);

		Rectangle rectangle = new Rectangle(lowerLeft, upperRight);
		assertTrue(pointShouldHaveBeenFoundErrorMessage(testPointOnBottomEdge, rectangle),
				rectangle.Contains(testPointOnBottomEdge));

	}

	@Test
	public void testContainsRightEdgePoint()
	{
		Point testPointOnRightEdge = new Point(4, 2);
		Point lowerLeft = new Point(0, 0);
		Point upperRight = new Point(4, 6);

		Rectangle rectangle = new Rectangle(lowerLeft, upperRight);
		assertTrue(pointShouldHaveBeenFoundErrorMessage(testPointOnRightEdge, rectangle),
				rectangle.Contains(testPointOnRightEdge));

	}

	@Test
	public void testContainsTopEdgePoint()
	{
		Point testPointOnTopEdge = new Point(2, 6);
		Point lowerLeft = new Point(0, 0);
		Point upperRight = new Point(4, 6);

		Rectangle rectangle = new Rectangle(lowerLeft, upperRight);
		assertTrue(pointShouldHaveBeenFoundErrorMessage(testPointOnTopEdge, rectangle),
				rectangle.Contains(testPointOnTopEdge));

	}

	@Test
	public void testContainsLeftEdgePoint()
	{
		Point testPointOnLeftEdge = new Point(0, 3);
		Point lowerLeft = new Point(0, 0);
		Point upperRight = new Point(4, 6);

		Rectangle rectangle = new Rectangle(lowerLeft, upperRight);
		assertTrue(pointShouldHaveBeenFoundErrorMessage(testPointOnLeftEdge, rectangle),
				rectangle.Contains(testPointOnLeftEdge));

	}

	@Test
	public void testPointShouldNotBeFoundXValueOutBy1()
	{
		Point testPointOutside = new Point(5, 8);
		Point lowerLeft = new Point(0, 0);
		Point upperRight = new Point(4, 6);

		Rectangle rectangle = new Rectangle(lowerLeft, upperRight);

		assertFalse("Point: " + testPointOutside.toString() + " should have not been found in Rectangle: "
				+ rectangle.toString() + " and was.", rectangle.Contains(testPointOutside));

	}
	
	@Test
	public void testOutsidePointShouldNotBeFoundYValueOutBy1()
	{
		Point testPointOutside = new Point(4, 7);
		Point lowerLeft = new Point(0, 0);
		Point upperRight = new Point(4, 6);

		Rectangle rectangle = new Rectangle(lowerLeft, upperRight);

		assertFalse("Point: " + testPointOutside.toString() + " should have not been found in Rectangle: "
				+ rectangle.toString() + " and was.", rectangle.Contains(testPointOutside));

	}

	@Test
	public void testEqualsNeitherPointEqual()
	{

		Point rect2LowerLeft = new Point(10, 8);
		Point rect2UpperRight = new Point(12, 10);

		Rectangle rect2 = new Rectangle(rect2LowerLeft, rect2UpperRight);

		assertFalse("These rectangles should not be found to be equal and were.  Rect1: " + testRectangle.toString()
				+ " Rect2: " + rect2.toString(), testRectangle.equals(rect2));

	}

	@Test
	public void testEqualsLowerLeftPointEqual()
	{

		Point rect2LowerLeft = new Point(2, 4);
		Point rect2UpperRight = new Point(12, 10);

		Rectangle rect2 = new Rectangle(rect2LowerLeft, rect2UpperRight);

		assertFalse("These rectangles should not be found to be equal and were.  Rect1: " + testRectangle.toString()
				+ " Rect2: " + rect2.toString(), testRectangle.equals(rect2));

	}

	@Test
	public void testEqualsUpperRightPointEqual()
	{

		Point rect2LowerLeft = new Point(10, 8);
		Point rect2UpperRight = new Point(5, 10);

		Rectangle rect2 = new Rectangle(rect2LowerLeft, rect2UpperRight);

		assertFalse("These rectangles should not be found to be equal and were.  Rect1: " + testRectangle.toString()
				+ " Rect2: " + rect2.toString(), testRectangle.equals(rect2));

	}

	@Test
	public void testEqualsBothPointsEqual()
	{

		Point rect2LowerLeft = new Point(2, 4);
		Point rect2UpperRight = new Point(5, 10);

		Rectangle rect2 = new Rectangle(rect2LowerLeft, rect2UpperRight);

		assertTrue("These rectangles should  be found to be equal and were not.  Rect1: " + testRectangle.toString()
				+ " Rect2: " + rect2.toString(), testRectangle.equals(rect2));

	}
	
	@Test
	public void testEqualsReturnsFalseOnNonRectangleObject()
	{
		Object o = new Object();
		
		assertFalse("Rectangle and non rectangle should return false.", testRectangle.equals(o));
	}
	
	@Test
	public void testGetInfertilePoints()
	{
		Set<Point> result = testRectangle.getInfertilePoints();
		Point lowerLeft = testRectangle.getLowerLeftPoint();
		Point upperRight = testRectangle.getUpperRightPoint();
		PointInfo pointInfo = new PointInfo(lowerLeft, upperRight);
		verifyTotalFoundPoints(pointInfo, result);
		verifyPointsInSet(pointInfo, result);
	}
	
	private void verifyTotalFoundPoints(PointInfo inPointInfo, Set<Point> result)
	{
		final int length = inPointInfo.upperRightX - inPointInfo.lowerLeftX + 1;
		final int width = inPointInfo.upperRightY - inPointInfo.lowerLeftY + 1;
		final int totalPoints = length * width;
		
		
		assertTrue("There should be " + totalPoints + " points as that is the size of the rectangle.  Was: " + result.size(),
				result.size() == length * width );
		
	}

	private void verifyPointsInSet(PointInfo inPointInfo, Set<Point> result)
	{
		for ( int i = inPointInfo.lowerLeftX; i <= inPointInfo.upperRightX; i++ )
			for ( int j = inPointInfo.lowerLeftY; j <= inPointInfo.upperRightY; j++){
				Point testPoint = new Point(i,j);
				assertTrue("The point " + i + " " + j + " should have been found and wasn't",  result.contains(testPoint) );
			}
		
	}

	@Test
	public void testGetSetOfInfertilePointsFromList() {
		
		List<Rectangle> rects = new ArrayList<>();
		rects.add(testRectangle);
		rects.add(overLapRectangle);
		Set<Point> infertilePoints = Rectangle.getSetOfInfertilePointsForListOfRects(rects);
		PointInfo firstRectanglePointInfo = new PointInfo(testRectangle.getLowerLeftPoint(), testRectangle.getUpperRightPoint());
		PointInfo overlapAreaPointInfo = new PointInfo(overLapRectangle.getLowerLeftPoint(), overLapRectangle.getUpperRightPoint());
		
		
		verifyPointsInSet(firstRectanglePointInfo, infertilePoints);
		verifyPointsInSet(overlapAreaPointInfo, infertilePoints);
	}
	
	@Test
	public void testRectangleIntegersConstructor(){
		int lowerLeftX = 1;
		int lowerLeftY = 3;
		int upperRightX = 4;
		int upperRightY = 10;
		Rectangle rectangle = new Rectangle(lowerLeftX, lowerLeftY, upperRightX, upperRightY);
		Point lowerLeft = rectangle.getLowerLeftPoint();
		Point upperRight = rectangle.getUpperRightPoint();
		assertTrue(lowerLeft.getX() == lowerLeftX);
		assertTrue(lowerLeft.getY() == lowerLeftY);
		assertTrue(upperRight.getX() == upperRightX);
		assertTrue(upperRight.getY() == upperRightY);
	}
	
	@Test
	public void testDefensiveCopyOfInfertilePoints(){
		
		Point testPoint = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
		Set<Point> infertilePoints = testRectangle.getInfertilePoints();
		
		infertilePoints.add(testPoint);
		
		Set<Point> defensiveCopyCheck = testRectangle.getInfertilePoints();
		assertFalse("You should not be able to add a point to the set of inferilepoints.  We were.", defensiveCopyCheck.contains(testPoint));
	}
	
	@Test
	public void testGetRectangleCorners() {
		
		Point lowerLeft = testRectangle.getLowerLeftPoint();
		Point lowerRight = testRectangle.getLowerRightPoint();
		Point upperLeft = testRectangle.getUpperLeftPoint();
		Point upperRight = testRectangle.getUpperRightPoint();
		
		
		Set<Point> points = testRectangle.getRectangleCorners();
		assertTrue(points.contains(lowerLeft));
		assertTrue(points.contains(lowerRight));
		assertTrue(points.contains(upperLeft));
		assertTrue(points.contains(upperRight));
	}
	
}
