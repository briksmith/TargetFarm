package model;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class RectangleIntersectionFinderTest
{
	
	@Test
	public void testRectanglesIntersectInLowerRight() {
		
		Rectangle rect1 = new Rectangle(1, 3, 4, 10);
		Rectangle rect2 = new Rectangle(4,1,10,3);
		Point intersectPoint = new Point(4,3);
		int expectedSize = 1;
		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect1, rect2);
		verifySize(expectedSize, points);
		verifyIntersectionPoint(intersectPoint, points);
		
		
	}
	
	@Test
	public void testRectanglesIntersectInUpperLeft() {
		
		Rectangle rect1 = new Rectangle(1, 3, 4, 10);
		Rectangle rect2 = new Rectangle(4,3,10,3);
		Point intersectPoint = new Point(4,3);
		int expectedSize = 1;
		
		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect1, rect2);
		 
		verifySize(expectedSize, points);
		verifyIntersectionPoint(intersectPoint, points);
		
	}
	
	@Test
	public void testRectanglesIntersectInUpperRight() {
		
		Rectangle rect1 = new Rectangle(1, 3, 4, 10);
		Rectangle rect2 = new Rectangle(4,10,10,15);
		Point intersectPoint = new Point(4,10);
		int expectedSize = 1;
		
		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect1, rect2);
		 
		verifySize(expectedSize, points);
		verifyIntersectionPoint(intersectPoint, points);
		
	}
	
	@Test
	public void testRectanglesIntersectInLowerLeft() {
		
		Rectangle rect1 = new Rectangle(1, 3, 4, 10);
		Rectangle rect2 = new Rectangle(4,10,10,15);
		Point intersectPoint = new Point(4,10);
		int expectedSize = 1;
		
		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect2, rect1);
		 
		verifySize(expectedSize, points);
		verifyIntersectionPoint(intersectPoint, points);
		
	}

	private void verifySize(int expectedSize, Set<Point> points)
	{
		assertTrue("Should have had " + expectedSize + " points.  Had: " + points.size(), expectedSize == points.size());
	}

	private void verifyIntersectionPoint(Point intersectPoint, Set<Point> points)
	{
		assertTrue("Should have contained " + intersectPoint.toString() + " and did not.", points.contains(intersectPoint));
	}
	
	@Test
	public void testRectanglesIntersectInTwoPlacesTop() {
		
		Rectangle rect1 = new Rectangle(1, 1, 10, 5);
		Rectangle rect2 = new Rectangle(3, 3, 7, 10);
		Point intersectPoint1 = new Point(3, 5);
		Point intersectPoint2= new Point(7, 5);
		
		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect1, rect2);
		verifySize(2, points);
		verifyIntersectionPoint(intersectPoint1, points);
		verifyIntersectionPoint(intersectPoint2, points);
	}
	
	@Test
	public void testRectanglesIntersectInOnePlaceLeftAndOnePlaceRight() {
		
		Rectangle rect1 = new Rectangle(1, 1, 10, 5);
		Rectangle rect2 = new Rectangle(3, 3, 7, 10);
		Point intersectPoint1 = new Point(3, 5);
		Point intersectPoint2= new Point(7, 5);
		
		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect1, rect2);
		verifySize(2, points);
		verifyIntersectionPoint(intersectPoint1, points);
		verifyIntersectionPoint(intersectPoint2, points);
	}
	
	@Test
	public void testRectanglesIntersectInTwoPlacesBottomEdge() {
		
		Rectangle rect1 = new Rectangle(1, 3, 8, 10);
		Rectangle rect2 = new Rectangle(4,1,15,3);
		Point intersectPoint1 = new Point(4,3);
		Point intersectPoint2= new Point(8,3);
		
		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect1, rect2);
		verifySize(2, points);
		verifyIntersectionPoint(intersectPoint1, points);
		verifyIntersectionPoint(intersectPoint2, points);
	}
	
	@Test
	public void testRectanglesIntersectOneBottom() {
		
		Rectangle rect1 = new Rectangle(3,3,7,7);
		Rectangle rect2 = new Rectangle(1,1,5,5);
		Point intersectPoint1 = new Point(5,3);
		Point intersectPoint2 = new Point(3,5);
		
		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect1, rect2);
		verifySize(2, points);
		verifyIntersectionPoint(intersectPoint1, points);
		verifyIntersectionPoint(intersectPoint2, points);
	}
	
	@Test
	public void testRectanglesDoNotIntersect() {
		Rectangle rect1 = new Rectangle(1, 3, 4, 10);
		Rectangle rect2 = new Rectangle(5,1,10,3);
		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect1, rect2);
		assertTrue(points.size() == 0);
		
		points = rect2.getSetOfIntersectingPoints(rect1);
		assertTrue(points.size() == 0);
		
	}
	
	@Test
	public void testCaseStudyIntersection() {
		Rectangle rect1 = new Rectangle(48,192,351,207);
		Rectangle rect2 = new Rectangle(48, 392, 351, 407);
		Rectangle rect3 = new Rectangle(120, 52, 135, 547);
		Rectangle rect4 = new Rectangle(260, 52, 275, 547);
		
		List<Rectangle> rectangles = new ArrayList<>();
		rectangles.add(rect1);
		rectangles.add(rect2);
		rectangles.add(rect3);
		rectangles.add(rect4);
		Set<Point> points = new HashSet<>();
		for ( int i = 0; i < rectangles.size() - 1; i++){
			for( int j = i + 1; j < rectangles.size(); j++){
				points.addAll(RectangleIntersectionFinder.findIntersectionOfEdges(rectangles.get(i), rectangles.get(j)));
			}
		}
		for ( Point p : points){
		System.out.println(p.toString());
		}
	}
}