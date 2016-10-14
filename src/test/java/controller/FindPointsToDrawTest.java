package controller;

import static org.junit.Assert.assertTrue;

import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

import model.Farm;
import model.Point;

public class FindPointsToDrawTest
{
	
	private TreeSet<Point> points;
	
	private static final int X = 20;
	private static final int Y = 20;
	
	@Mock
	Farm farm;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		
		points = new TreeSet<>();
		points.add(new Point(0,0));
		points.add(new Point(4,0));
		points.add(new Point(6,4));
		points.add(new Point(11,4));
		points.add(new Point(11,7));
		points.add(new Point(6,7));
		points.add(new Point(6,9));
		points.add(new Point(9,9));
		points.add(new Point(7,12));
		points.add(new Point(9,12));
		points.add(new Point(12,12));
		points.add(new Point(4,9));
		points.add(new Point(0,9));
		points.add(new Point(0,13));
		points.add(new Point(4,13));
		points.add(new Point(4,19));
		points.add(new Point(7,12));
		points.add(new Point(7,15));
		points.add(new Point(9,13));
		points.add(new Point(12,15));
		points.add(new Point(12,0));
		points.add(new Point(19,12));
		points.add(new Point(19,15));
		points.add(new Point(12,19));
		
		points.add(new Point(13,8));
		points.add(new Point(13,11));
		points.add(new Point(16,11));
		points.add(new Point(16,8));
		
		points.add(new Point(15,6));
		points.add(new Point(15,9));
		points.add(new Point(18,9));
		points.add(new Point(18,6));
		
		points.add(new Point(15,8));
		points.add(new Point(16,9));
		
	}
	
	@Test
	public void testFindNextUpPointAbove(){
		Point start = new Point(6,4);
		Point expected = new Point(6,7);
		Point actual;
		actual = FindPointsToDraw.findHigherPoint(start, points, farm);
		assertTrue(pointFindingErrorMessage(expected, actual), expected.equals(actual));
	}

	private String pointFindingErrorMessage(Point expected, Point actual)
	{
		return "Should have found: " + expected.toString() + " Was: " + actual.toString();
	}
	
	@Test
	public void testFindNextUpPointAboveAndRight() {
		Point start = new Point(4,13);
		Point expected = new Point(7,15);
		Point actual = FindPointsToDraw.findHigherPoint(start, points, farm);
		assertTrue(pointFindingErrorMessage(expected, actual), expected.equals(actual));
	}
	
	@Test
	public void testFindNextUpPointAboveRightAndOtherPointDirectlyAbove() {
		Point start = new Point(4,9);
		Point expected = new Point(7,12);
		Point actual = FindPointsToDraw.findHigherPoint(start,points, farm);
		assertTrue(pointFindingErrorMessage(expected,actual), expected.equals(actual));
	}
	
	@Test
	public void testFindNextUpPointAboveAndLeft() {
		Point start = new Point(18,6);
		Point expected = new Point(18,8);
		Point actual = FindPointsToDraw.findHigherPoint(start, points, farm);
		assertTrue(pointFindingErrorMessage(expected,actual), expected.equals(actual));
	}
	
	@Test
	public void testFindNextUpAxis() {
		Point start = new Point(7,15);
		Point expected = new Point(7, X);
		when(farm.getColCount()).thenReturn(Y);
		Point actual = FindPointsToDraw.findHigherPoint(start, points, farm);
		assertTrue(pointFindingErrorMessage(expected, actual), expected.equals(actual));
	}
	
	@Test
	public void testFindNextUpAboveInBetween() {
		Point start = new Point(6,7);
		Point expected = new Point(7,9);
		Point actual = FindPointsToDraw.findHigherPoint(start, points, farm);
		assertTrue(pointFindingErrorMessage(expected,actual), expected.equals(actual));
	}
	
}
