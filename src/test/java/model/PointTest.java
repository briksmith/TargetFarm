package model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

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
	
}
