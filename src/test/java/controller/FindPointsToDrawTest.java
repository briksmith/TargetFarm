package controller;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import junitparams.*;

import model.Farm;
import model.Point;
import model.Rectangle;

@RunWith(JUnitParamsRunner.class)
public class FindPointsToDrawTest
{
	
	private static final int ROW_COUNT = 20;

	private static final int COL_COUNT = 20;

	private TreeSet<Point> points;
	
	private static final int X = 20;
	private static final int Y = 20;
	
	private final Rectangle SixFour11_7 = new Rectangle(6,4,11,7);
	private final Rectangle Four9_9_13 = new Rectangle(4,9,9,13);
	private final Rectangle Seven_12_12_15 = new Rectangle(7,12,12,15);
	private final Rectangle Thirteen_8_16_11 = new Rectangle(13,8,16,11);
	private final Rectangle Fifteen6_18_9 = new Rectangle(15,6,18,9);
	private final Rectangle SevenTeen17_19_19 = new Rectangle(17,17,19,19); 
	
	private List<Rectangle> rects;
	private Set<Point> rectangleCorners;
	
	@Mock
	Farm farm;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		
		points = new TreeSet<>();
		rects = new ArrayList<>();
		
		rects.add(SixFour11_7);
		rects.add(Four9_9_13);
		rects.add(Seven_12_12_15);
		rects.add(Thirteen_8_16_11);
		rects.add(Fifteen6_18_9);
		rects.add(SevenTeen17_19_19);
		
		addRectsVerticesToSet(points, rects);
		addExpectedAxisIntersectionPoints(points);
		addExpectedIntersectionOfRectanglesPoints(points);
		createRectangleCornerSet(rects);
		
	}
	
	private void createRectangleCornerSet(List<Rectangle> rects2)
	{
		rectangleCorners = new HashSet<>();
		for ( Rectangle r : rects){
			rectangleCorners.addAll(r.getRectangleCorners());
		}
		
	}

	private void addRectsVerticesToSet(TreeSet<Point> points2, List<Rectangle> rects2)
	{
		for ( Rectangle r : rects2){
			Set<Point> corners = r.getRectangleCorners();
			points2.addAll(corners);
		}
	}

	private void addExpectedAxisIntersectionPoints(TreeSet<Point> inPoints)
	{
		inPoints.add(new Point(0,0));
		inPoints.add(new Point(6,4));
		inPoints.add(new Point(0,4));
		inPoints.add(new Point(0,13));
		inPoints.add(new Point(4,13));
		inPoints.add(new Point(4,19));
		inPoints.add(new Point(19,6));
		inPoints.add(new Point(19,9));
		
	}

	private void addExpectedIntersectionOfRectanglesPoints(TreeSet<Point> inPoints)
	{
		 inPoints.add(new Point(7,12));
		 inPoints.add(new Point(9,13));
		 inPoints.add(new Point(15,8));
		 inPoints.add(new Point(16,9));
	}

	@Test
	public void testFindNextUpPointAbove(){
		Point start = new Point(6,4);
		Point expected = new Point(6,7);
		setInfertileAreasAndPoints();
		when(farm.getColCount()).thenReturn(COL_COUNT);
		when(farm.getRowCount()).thenReturn(ROW_COUNT);
		Point actual = FindPointsToDraw.findHigherPoint(start, points, farm);
		assertTrue(pointFindingErrorMessage(expected, actual), expected.equals(actual));
	}

	private void setInfertileAreasAndPoints()
	{
		Set<Point> infertilePoints = Rectangle.getSetOfInfertilePointsForListOfRects(rects);
		farm.setInfertileAreas(rects);
		when(farm.getInFertilePoints()).thenReturn(infertilePoints);
		when(farm.getInFertileAreaCornerPoints()).thenReturn(rectangleCorners);
	}

	private String pointFindingErrorMessage(Point expected, Point actual)
	{
		return "Should have found: " + expected.toString() + " Was: " + actual.toString();
	}
	
	@Test
	public void testFindNextUpPointAboveAndRight() {
		Point start = new Point(4,13);
		Point expected = new Point(7,15);
		setInfertileAreasAndPoints();
		Point actual = FindPointsToDraw.findHigherPoint(start, points, farm);
		assertTrue(pointFindingErrorMessage(expected, actual), expected.equals(actual));
	}
	
	@Test
	public void testFindNextUpPointAboveRightAndOtherPointDirectlyAbove() {
		Point start = new Point(4,9);
		Point expected = new Point(7,12);
		setInfertileAreasAndPoints();
		Point actual = FindPointsToDraw.findHigherPoint(start,points, farm);
		assertTrue(pointFindingErrorMessage(expected,actual), expected.equals(actual));
	}
	
	@Test
	public void testFindNextUpPointAboveAndLeft() {
		Point start = new Point(18,6);
		Point expected = new Point(18,8);
		setInfertileAreasAndPoints();
		Point actual = FindPointsToDraw.findHigherPoint(start, points, farm);
		assertTrue(pointFindingErrorMessage(expected,actual), expected.equals(actual));
	}
	
	@Test
	public void testFindNextUpAxis() {
		Point start = new Point(7,15);
		Point expected = new Point(7, X);
		setInfertileAreasAndPoints();
		when(farm.getColCount()).thenReturn(Y);
		Point actual = FindPointsToDraw.findHigherPoint(start, points, farm);
		assertTrue(pointFindingErrorMessage(expected, actual), expected.equals(actual));
	}
	
	@Test
	public void testFindNextUpAboveInBetween() {
		Point start = new Point(6,7);
		Point expected = new Point(7,9);
		setInfertileAreasAndPoints();
		Point actual = FindPointsToDraw.findHigherPoint(start, points, farm);
		assertTrue(pointFindingErrorMessage(expected,actual), expected.equals(actual));
	}
	
	private static final Object[] getPointsForTestUp() {
		return new Object[] {
			new Object[] { new Point(17,17),
							new Point(17,19)
			}
		};
	}
	
	@Test
	@Parameters(method="getPointsForTestUp")
	public void testFindHigherPoint(Point start, Point expected) {
		
		setInfertileAreasAndPoints();
		when(farm.getColCount()).thenReturn(Y);
		Point actual = FindPointsToDraw.findHigherPoint(start, points, farm);
		assertTrue(pointFindingErrorMessage(expected, actual), expected.equals(actual));
		
	}
}
