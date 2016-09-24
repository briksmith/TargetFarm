package controller;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import model.Point;
import model.Rectangle;

public class RectangleListCreatorTest
{

	@Test
	public void testReadsFirstSample(){
		
		Point lowerLeft = new Point(0, 292);
		Point upperRight = new Point(399, 207);
		Rectangle expectedRectangle = new Rectangle(lowerLeft, upperRight);
		List<Rectangle> result = RectangleListCreator.createListOfRectangles("{\"0 292 399 307\"}");
		
		assertTrue("result should have had one entry in it.  Had: " + result.size(), result.size() == 1);
	}
	
	@Test
	public void testReadsSecondSample(){
		
	}
	
	@Test
	public void testErrorOnOneRectangleHaving3Points(){
		
	}
	

	@Test
	public void testErrorOnOneRectangleHaving5Points(){
		
	}
	
	@Test
	public void testErrorOnMissingComma(){
		
	}
	
	@Test
	public void testErrorOnMissingStartingQuote(){
		
	}
	
	@Test
	public void testErrorOnMissingEndQuote(){
		
	}
	
	@Test
	public void testErrorOnMissingStartBraces(){
		
	}
	@Test
	public void testErrorOnMissingEndBraces(){
		
	}
}
