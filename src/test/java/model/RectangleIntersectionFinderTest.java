package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class RectangleIntersectionFinderTest
{

	private static final int MOCK_ROW_COUNT = 30;
	private static final int MOCK_COL_COUNT = 20;
	
	@Mock
	Farm farm;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testRectanglesIntersectInLowerRight()
	{

		Rectangle rect1 = new Rectangle(1, 3, 4, 10);
		Rectangle rect2 = new Rectangle(4, 1, 10, 3);
		Point intersectPoint = new Point(4, 3);
		int expectedSize = 1;
		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect1, rect2);
		verifySize(expectedSize, points);
		verifyIntersectionPoint(intersectPoint, points);

	}

	@Test
	public void testRectanglesIntersectInUpperLeft()
	{

		Rectangle rect1 = new Rectangle(1, 3, 4, 10);
		Rectangle rect2 = new Rectangle(4, 2, 10, 3);
		Point intersectPoint = new Point(4, 3);
		int expectedSize = 1;

		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect2, rect1);

		verifySize(expectedSize, points);
		verifyIntersectionPoint(intersectPoint, points);

	}

	@Test
	public void testRectanglesIntersectInUpperRight()
	{

		Rectangle rect1 = new Rectangle(1, 3, 4, 10);
		Rectangle rect2 = new Rectangle(4, 10, 10, 15);
		Point intersectPoint = new Point(4, 10);
		int expectedSize = 1;

		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect1, rect2);

		verifySize(expectedSize, points);
		verifyIntersectionPoint(intersectPoint, points);

	}

	@Test
	public void testRectanglesIntersectInLowerLeft()
	{

		Rectangle rect1 = new Rectangle(1, 3, 4, 10);
		Rectangle rect2 = new Rectangle(4, 10, 10, 15);
		Point intersectPoint = new Point(4, 10);
		int expectedSize = 1;

		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect2, rect1);

		verifySize(expectedSize, points);
		verifyIntersectionPoint(intersectPoint, points);

	}

	private void verifySize(int expectedSize, Set<Point> points)
	{
		assertTrue("Should have had " + expectedSize + " points.  Had: " + points.size(),
				expectedSize == points.size());
	}

	private void verifyIntersectionPoint(Point intersectPoint, Set<Point> points)
	{
		assertTrue("Should have contained " + intersectPoint.toString() + " and did not.",
				points.contains(intersectPoint));
	}

	@Test
	public void testRectanglesIntersectInTwoPlacesTop()
	{

		Rectangle rect1 = new Rectangle(1, 1, 10, 5);
		Rectangle rect2 = new Rectangle(3, 3, 7, 10);
		Point intersectPoint1 = new Point(3, 5);
		Point intersectPoint2 = new Point(7, 5);

		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect1, rect2);
		verifyPoints(points, 2, intersectPoint1, intersectPoint2);
	}

	@Test
	public void testRectanglesIntersectInOnePlaceLeftAndOnePlaceRight()
	{

		Rectangle rect1 = new Rectangle(1, 1, 10, 5);
		Rectangle rect2 = new Rectangle(3, 3, 7, 10);
		Point intersectPoint1 = new Point(3, 5);
		Point intersectPoint2 = new Point(7, 5);

		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect2, rect1);
		verifyPoints(points, 2, intersectPoint1, intersectPoint2);
	}

	@Test
	public void testRectanglesIntersectInTwoPlacesBottomEdge()
	{

		Rectangle rect1 = new Rectangle(1, 3, 8, 10);
		Rectangle rect2 = new Rectangle(4, 1, 15, 3);
		Point intersectPoint1 = new Point(4, 3);
		Point intersectPoint2 = new Point(8, 3);

		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect1, rect2);
		verifyPoints(points, 2, intersectPoint1, intersectPoint2);
	}

	@Test
	public void testRectanglesIntersectInTwoPlacesTopEdge()
	{

		Rectangle rect1 = new Rectangle(1, 3, 8, 10);
		Rectangle rect2 = new Rectangle(4, 1, 15, 3);
		Point intersectPoint1 = new Point(4, 3);
		Point intersectPoint2 = new Point(8, 3);

		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect2, rect1);
		verifyPoints(points, 2, intersectPoint1, intersectPoint2);
	}
	
	@Test
	public void testRectanglesIntersectOneBottomOneLeft()
	{

		Rectangle rect1 = new Rectangle(3, 3, 7, 7);
		Rectangle rect2 = new Rectangle(1, 1, 5, 5);
		Point intersectPoint1 = new Point(5, 3);
		Point intersectPoint2 = new Point(3, 5);

		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect1, rect2);
		verifyPoints(points, 2, intersectPoint1, intersectPoint2);
	}
	
	@Test
	public void testRectanglesIntersectOneTopOneRight()
	{

		Rectangle rect1 = new Rectangle(3, 3, 7, 7);
		Rectangle rect2 = new Rectangle(1, 1, 5, 5);
		Point intersectPoint1 = new Point(5, 3);
		Point intersectPoint2 = new Point(3, 5);

		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect2, rect1);
		verifyPoints(points,  2, intersectPoint1, intersectPoint2);
	}
	
	@Test
	public void testRectanglesIntersectOneBottomOneRight()
	{

		Rectangle rect1 = new Rectangle(1, 3, 5, 7);
		Rectangle rect2 = new Rectangle(3, 2, 6, 5);
		Point intersectPoint1 = new Point(3, 3);
		Point intersectPoint2 = new Point(5, 5);

		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect1, rect2);
		verifyPoints(points,  2, intersectPoint1, intersectPoint2);
	}
	
	@Test
	public void testRectanglesIntersectOneTopOneLeft()
	{

		Rectangle rect1 = new Rectangle(1, 3, 5, 7);
		Rectangle rect2 = new Rectangle(3, 2, 6, 5);
		Point intersectPoint1 = new Point(3, 3);
		Point intersectPoint2 = new Point(5, 5);

		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect2, rect1);
		verifyPoints(points,  2, intersectPoint1, intersectPoint2);
	}
	
	@Test
	public void testBottom2()
	{
		Rectangle rect1 = new Rectangle(2,4,9,6);
		Rectangle rect2 = new Rectangle(4,2,7,5);
		Point intersectionPoint1 = new Point(4,4);
		Point intersectionPoint2 = new Point(7,4);
		
		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect1, rect2);
		verifyPoints(points,  2, intersectionPoint1, intersectionPoint2);
		
	}
	
	@Test
	public void testTopAndBottom(){
		Rectangle rect1 = new Rectangle(1,3,5,5);
		Rectangle rect2 = new Rectangle(4,2,10,6);
		Point intersectionPoint1 = new Point(4,3);
		Point intersectionPoint2 = new Point(4,5);
		
		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect1, rect2);
		verifyPoints(points,  2, intersectionPoint1, intersectionPoint2);
	}
	
	@Test
	public void testLeftEdgeRun(){
		Rectangle rect1 = new Rectangle(1,3,5,5);
		Rectangle rect2 = new Rectangle(4,2,10,6);
		Point intersectionPoint1 = new Point(4,3);
		Point intersectionPoint2 = new Point(4,5);
		
		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect2, rect1);
		verifyPoints(points,  2, intersectionPoint1, intersectionPoint2);
	}
	
	@Test
	public void testRightEdgeRun() {
		Rectangle rect1 = new Rectangle(4,2,8,6);
		Rectangle rect2 = new Rectangle(7,3,10,5);
		Point intersectionPoint1 = new Point(8,3);
		Point intersectionPoint2 = new Point(8,5);
		
		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect1, rect2);
		verifyPoints(points, 2, intersectionPoint1, intersectionPoint2);
	}

	private void verifyPoints(Set<Point> setOfPoints, int expectedSizeOfPoints, Point... pointsToCheck)
	{
		verifySize(expectedSizeOfPoints, setOfPoints);
		for ( Point p : pointsToCheck){
			verifyIntersectionPoint(p, setOfPoints);
		}
	}

	@Test
	public void testRectanglesDoNotIntersect()
	{
		Rectangle rect1 = new Rectangle(1, 3, 4, 10);
		Rectangle rect2 = new Rectangle(5, 1, 10, 3);
		Set<Point> points = RectangleIntersectionFinder.findIntersectionOfEdges(rect1, rect2);
		assertTrue(points.size() == 0);

		points = rect2.getSetOfIntersectingPoints(rect1);
		assertTrue(points.size() == 0);

	}

	@Test
	public void testCaseStudyIntersection()
	{
		Rectangle rect1 = new Rectangle(48, 192, 351, 207);
		Rectangle rect2 = new Rectangle(48, 392, 351, 407);
		Rectangle rect3 = new Rectangle(120, 52, 135, 547);
		Rectangle rect4 = new Rectangle(260, 52, 275, 547);

		List<Rectangle> rectangles = new ArrayList<>();
		rectangles.add(rect1);
		rectangles.add(rect2);
		rectangles.add(rect3);
		rectangles.add(rect4);
		Set<Point> points = new HashSet<>();
		for (int i = 0; i < rectangles.size() - 1; i++)
		{
			for (int j = i + 1; j < rectangles.size(); j++)
			{
				points.addAll(
						RectangleIntersectionFinder.findIntersectionOfEdges(rectangles.get(i), rectangles.get(j)));
			}
		}
		for (Point p : points)
		{
			System.out.println(p.toString());
		}
	}

	@Test
	public void getEmptySetWhenNoRects()
	{

		when(farm.getInFertileAreas()).thenReturn(Collections.emptyList());

		Set<Point> result = new HashSet<>();

		result = RectangleIntersectionFinder.findAllIntersectionsWithAxisAndRectangles(farm);

		assertTrue("This should have returned an empty set and did not. ", result.equals(Collections.EMPTY_SET));

	}

	@Test
	public void getEmptySetWhenRectsDoNotIntersectEachOtherOrAxis()
	{

		List<Rectangle> rects = new ArrayList<>();

		Rectangle rect1 = new Rectangle(1, 1, 4, 5);
		Rectangle rect2 = new Rectangle(7, 10, 9, 12);
		rects.add(rect1);
		rects.add(rect2);

		when(farm.getInFertileAreas()).thenReturn(rects);

		Set<Point> result = new HashSet<>();

		result = RectangleIntersectionFinder.findAllIntersectionsWithAxisAndRectangles(farm);

		assertTrue("This should have returned an empty set and did not. " + outPutSet(result),
				result.equals(Collections.EMPTY_SET));

	}

	private String outPutSet(Set<Point> result)
	{
		String outPut = "";
		for (Point p : result)
		{
			outPut = outPut + p.toString() + "\n";
		}
		return outPut;
	}

	@Test
	public void getRectWhenRectIntersectsYAxes()
	{

		List<Rectangle> rects = new ArrayList<>();
		Rectangle rect1 = new Rectangle(0, 4, 29, 8);
		rects.add(rect1);

		when(farm.getInFertileAreas()).thenReturn(rects);
		when(farm.getRowCount()).thenReturn(MOCK_ROW_COUNT);
		when(farm.getColCount()).thenReturn(MOCK_COL_COUNT);
		

		Set<Point> result = new HashSet<>();

		result = RectangleIntersectionFinder.findAllIntersectionsWithAxisAndRectangles(farm);
		assertTrue("rect1 should have been in set and was not.  Rect1: " + rect1.toString() + " result: "
				+ result.toString(), verifyRectangleVerticesInPointsPoints(rects, result));

	}
	
	@Test
	public void getRectWhenRectIntersectsXAxes()
	{

		List<Rectangle> rects = new ArrayList<>();
		Rectangle rect1 = new Rectangle(7, 0, 7, 19);
		rects.add(rect1);

		when(farm.getInFertileAreas()).thenReturn(rects);
		when(farm.getRowCount()).thenReturn(MOCK_ROW_COUNT);
		when(farm.getColCount()).thenReturn(MOCK_COL_COUNT);
		

		Set<Point> result = new HashSet<>();

		result = RectangleIntersectionFinder.findAllIntersectionsWithAxisAndRectangles(farm);
		assertTrue("rect1 should have been in set and was not.  Rect1: " + rect1.toString() + " result: "
				+ result.toString(), verifyRectangleVerticesInPointsPoints(rects, result));

	}
	
	@Test
	public void rectangleThatIntersectsOnlyLeftYAxisShouldNotBeFound(){
		
		List<Rectangle> rects = new ArrayList<>();
		Rectangle rect1 = new Rectangle(0, 3, 0, 8);
		rects.add(rect1);
		
		when(farm.getInFertileAreas()).thenReturn(rects);
		when(farm.getRowCount()).thenReturn(MOCK_ROW_COUNT);
		when(farm.getRowCount()).thenReturn(MOCK_COL_COUNT);
		
		Set<Point> result = new HashSet<>();

		result = RectangleIntersectionFinder.findAllIntersectionsWithAxisAndRectangles(farm);
		assertFalse("rect1 should have been in set and was not.  Rect1: " + rect1.toString() + " result: "
				+ result.toString(), verifyRectangleVerticesInPointsPoints(rects, result));
		
	}
	
	@Test
	public void rectangleThatIntersectsOnlyRightYAxisShouldNotBeFound(){
		
		List<Rectangle> rects = new ArrayList<>();
		Rectangle rect1 = new Rectangle(MOCK_ROW_COUNT - 1, 3, MOCK_ROW_COUNT - 1, 8);
		rects.add(rect1);
		
		when(farm.getInFertileAreas()).thenReturn(rects);
		when(farm.getRowCount()).thenReturn(MOCK_ROW_COUNT);
		when(farm.getRowCount()).thenReturn(MOCK_COL_COUNT);
		
		Set<Point> result = new HashSet<>();

		result = RectangleIntersectionFinder.findAllIntersectionsWithAxisAndRectangles(farm);
		assertFalse("rect1 should have been in set and was not.  Rect1: " + rect1.toString() + " result: "
				+ result.toString(), verifyRectangleVerticesInPointsPoints(rects, result));
		
	}
	
	@Test
	public void rectangleThatIntersectsOnlyBottomXAxisShouldNotBeFound(){
		
		List<Rectangle> rects = new ArrayList<>();
		Rectangle rect1 = new Rectangle(3, 0, 5, 4);
		rects.add(rect1);
		
		when(farm.getInFertileAreas()).thenReturn(rects);
		when(farm.getRowCount()).thenReturn(MOCK_ROW_COUNT);
		when(farm.getRowCount()).thenReturn(MOCK_COL_COUNT);
		
		Set<Point> result = new HashSet<>();

		result = RectangleIntersectionFinder.findAllIntersectionsWithAxisAndRectangles(farm);
		assertFalse("rect1 should have been in set and was not.  Rect1: " + rect1.toString() + " result: "
				+ result.toString(), verifyRectangleVerticesInPointsPoints(rects, result));
		
	}

	@Test
	public void rectangleThatIntersectsOnlyTopXAxisShouldNotBeFound(){
		
		List<Rectangle> rects = new ArrayList<>();
		Rectangle rect1 = new Rectangle(3, MOCK_COL_COUNT - 1,5, MOCK_COL_COUNT - 1);
		rects.add(rect1);
		
		when(farm.getInFertileAreas()).thenReturn(rects);
		when(farm.getRowCount()).thenReturn(MOCK_ROW_COUNT);
		when(farm.getRowCount()).thenReturn(MOCK_COL_COUNT);
		
		Set<Point> result = new HashSet<>();

		result = RectangleIntersectionFinder.findAllIntersectionsWithAxisAndRectangles(farm);
		assertFalse("rect1 should have been in set and was not.  Rect1: " + rect1.toString() + " result: "
				+ result.toString(), verifyRectangleVerticesInPointsPoints(rects, result));
		
	}
	
	private boolean verifyRectangleVerticesInPointsPoints(List<Rectangle> rects, Set<Point> result)
	{
		if (rects.isEmpty())
		{
			return false;
		}
		for (Rectangle r : rects)
		{
			if (!verifyRectangleInSet(r, result))
			{
				return false;
			}
		}
		return true;
	}

	private boolean verifyRectangleInSet(Rectangle r, Set<Point> result)
	{
		return (result.contains(r.getLowerLeftPoint()) && result.contains(r.getLowerRightPoint())
				&& result.contains(r.getUpperLeftPoint()) && result.contains(r.getUpperRightPoint()));
	}

	@Test
	public void getAllRectsWhenOneRectIntersectsAxis()
	{

	}
}
