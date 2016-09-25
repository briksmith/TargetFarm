package controller;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.Point;
import model.Rectangle;

public class RectangleListCreatorTest
{

	@Test
	public void testReadsFirstSample()
	{

		Point lowerLeft = new Point(0, 292);
		Point upperRight = new Point(399, 207);
		Rectangle expectedRectangle = new Rectangle(lowerLeft, upperRight);
		List<Rectangle> result = RectangleListCreator.createListOfRectangles("{\"0 292 399 207\"}");

		assertTrue("result should have had one entry in it.  Had: " + result.size(), result.size() == 1);
		Rectangle actualRectangle = result.get(0);
		assertTrue(
				"result should have had a rectangle equal to the test rectangle and did not.  Test rectangle: "
						+ expectedRectangle.toString() + " actual rectangle: " + actualRectangle.toString(),
				actualRectangle.equals(expectedRectangle));
	}

	@Test
	public void testReadsSecondSample()
	{

		List<Rectangle> expectedRectangles = new ArrayList<>();
		Point lowerLeft = new Point(48, 192);
		Point upperRight = new Point(351, 207);
		expectedRectangles.add(new Rectangle(lowerLeft, upperRight));

		lowerLeft = new Point(48, 392);
		upperRight = new Point(351, 407);
		expectedRectangles.add(new Rectangle(lowerLeft, upperRight));

		lowerLeft = new Point(120, 52);
		upperRight = new Point(135, 547);
		expectedRectangles.add(new Rectangle(lowerLeft, upperRight));

		lowerLeft = new Point(260, 52);
		upperRight = new Point(275, 547);
		expectedRectangles.add(new Rectangle(lowerLeft, upperRight));

		List<Rectangle> actualRectangles = new ArrayList<>();
		actualRectangles = RectangleListCreator.createListOfRectangles(
				"{\"48 192 351 207\", \"48 392 351 407\", \"120 52 135 547\", \"260 52 275 547\"}");
		assertTrue("actual should have had 4 entries in it and did not.  Had:  " + actualRectangles.size(),
				actualRectangles.size() == 4);

		for (int i = 0; i < actualRectangles.size(); i++)
		{
			Rectangle expected = expectedRectangles.get(i);
			Rectangle actual = actualRectangles.get(i);
			assertTrue("actual Rectangles should match expected rectangle.  Index " + i + " did not.  Expected: "
					+ expected + " Actual: " + actual, expected.equals(actual));
		}
	}

	@Test
	public void testErrorOnOneRectangleHaving3Points()
	{

	}

	@Test
	public void testErrorOnOneRectangleHaving5Points()
	{

	}

	@Test
	public void testErrorOnMissingComma()
	{

	}

	@Test
	public void testErrorOnMissingStartingQuote()
	{

	}

	@Test
	public void testErrorOnMissingEndQuote()
	{

	}

	@Test
	public void testErrorOnMissingStartBraces()
	{

	}

	@Test
	public void testErrorOnMissingEndBraces()
	{

	}

	
}
