package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

class foo{
	
}

public class PointTest
{

	@Before
	public void setUp(){
		
	}
	
	@Test
	public void testConstructor(){
		
		Point p = new Point(12,5);
		
		assertTrue("Expected p.x to be 12.  Was: " + p.getX(), p.getX() == 12);
		assertTrue("Expected p.y to be 5.  Was: " + p.getY(), p.getY() == 5);
	}
	
	@Test
	public void testEqualsNonPointObject(){
		foo f = new foo();
		Point p = new Point(1,1);
		assertFalse("An object other than Point should always return false", p.equals(f));
	}
	
	@Test
	public void testNeitherValueMatches() {
		Point point1 = new Point(1,1);
		Point point2 = new Point(2,2);
		
		assertFalse("Point1: " + point1.toString() + " should not be equal to Point2: " + point2.toString(), point1.equals(point2));
	}
	
	@Test
	public void testOnlyXValuePointMatches() {
		Point point1 = new Point(2,1);
		Point point2 = new Point(2,2);
		
		assertFalse("Point1: " + point1.toString() + " should not be equal to Point2: " + point2.toString(), point1.equals(point2));
	}
	
	@Test
	public void testOnlyYValuePointMatches() {
		Point point1 = new Point(1,2);
		Point point2 = new Point(2,2);
		
		assertFalse("Point1: " + point1.toString() + " should not be equal to Point2: " + point2.toString(), point1.equals(point2));
	}
	
	@Test
	public void testBothValuesMatche() {
		Point point1 = new Point(2,2);
		Point point2 = new Point(2,2);
		
		assertTrue("Point1: " + point1.toString() + " should be equal to Point2: " + point2.toString(), point1.equals(point2));
	}
	
	@Test
	public void testToString() {
		Point point = new Point(1,1);
		assertTrue("Expected out put of: X: 1, Y: 1.  Was: " + point.toString(),  point.toString().equals("X: 1, Y: 1"));
	}
}
