package controller;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import model.Farm;
import model.FarmPoint;
import model.Point;
import model.Rectangle;
import utils.DataValidator;

public class ConnectDotsPlotFinderTest
{
	
	private static final int SMALL_X_DIR = 20;
	private static final int SMALL_Y_DIR = 20;
	private static final int LARGE_X_DIR = 400;
	private static final int LARGE_Y_DIR = 600;

	@Mock
	FarmPoint farmPoint;
	
	Farm farm;

	InfertilePlotsFinder systemUnderTest;

	@Before 
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		systemUnderTest = new ConnectDotsPlotFinder();
	}
	
	
	@Test
	public void testTargetTestCaseOne()
	{
		Rectangle rect1 = new Rectangle(0, 292, 399, 307);
		List<Rectangle> rects = new ArrayList<>();
		rects.add(rect1);
		farm = new Farm(LARGE_X_DIR, LARGE_Y_DIR);
		farm.setInfertileAreas(rects);
		
		List<Integer> expectedList = new ArrayList<>();
		expectedList.add(116800);
		expectedList.add(116800);
		
		List<Integer> plots = systemUnderTest.getListOfFertilePlots(farm);
		Collections.sort(plots);
		assertTrue("should have found 2 plots.  Found: " + plots.size(), plots.size() == expectedList.size());
		verifyPlots(expectedList, plots); 
	}
	
	@Test
	public void testTargetTestCaseTwo()
	{
		Rectangle rect1 = new Rectangle(48, 192, 351, 207);
		Rectangle rect2 = new Rectangle(48, 392, 351, 407);
		Rectangle rect3 = new Rectangle(120, 52, 135, 547);
		Rectangle rect4 = new Rectangle(260, 52, 275, 547);
		List<Rectangle> rects = new ArrayList<>();
		rects.add(rect1);
		rects.add(rect2);
		rects.add(rect3);
		rects.add(rect4);
		farm = new Farm(LARGE_X_DIR, LARGE_Y_DIR);
		farm.setInfertileAreas(rects);
		
		List<Integer> expectedList = new ArrayList<>();
		expectedList.add(22816);
		expectedList.add(192608);
		
		List<Integer> plots = systemUnderTest.getListOfFertilePlots(farm);
		Collections.sort(plots);
		assertTrue("should have found 2 plots.  Found: " + plots.size(), plots.size() == expectedList.size());
		verifyPlots(expectedList, plots); 
	}

	private void verifyPlots(List<Integer> expectedList, List<Integer> plots)
	{
		for( int i = 0; i < expectedList.size(); i++){
			Integer expected = expectedList.get(i);
			Integer actual = plots.get(i);
			assertTrue("Expected: " + expected + " Actual: " + plots.get(i), expected.equals(actual));
		}
	}

	@Test
	public void testForHole()
	{
		Rectangle rect1 = new Rectangle(2, 1, 3, 6);
		Rectangle rect2 = new Rectangle(2, 5, 7, 6);
		Rectangle rect3 = new Rectangle(6, 1, 7, 6);
		Rectangle rect4 = new Rectangle(2, 1, 7, 2);
		List<Rectangle> rects = new ArrayList<Rectangle>();
		rects.add(rect1);
		rects.add(rect2);
		rects.add(rect3);
		rects.add(rect4);
		farm = new Farm(11,11);
		farm.setInfertileAreas(rects);
		
		List<Integer> expected = new ArrayList<>();
		expected.add(4);
		expected.add(85);
		
		Set<Point> infertilePoints = Rectangle.getSetOfInfertilePointsForListOfRects(rects);
		int numInFertilePoints = infertilePoints.size();
		List<Integer> plots = systemUnderTest.getListOfFertilePlots(farm);
		Collections.sort(plots);
		int totalFertilePoints = 0;
		for (Integer i : plots)
		{
			totalFertilePoints += i;
		}
		assertTrue(
				"fertile plus inFertile should have equaled total and did not.  Fertile: " + totalFertilePoints
						+ " inFertilePoints: " + infertilePoints + " total: " + 121,
				DataValidator.ValidateSummation(121, numInFertilePoints, totalFertilePoints));
		verifyPlots(expected, plots);
	}
	
	@Test
	public void testRectanglesTouchDoNotIntersect(){
		
		Rectangle rect1 = new Rectangle(2, 2, 3, 7);
		Rectangle rect2 = new Rectangle(4,6,8,7);
		Rectangle rect3 = new Rectangle(9,2,10,7);
		Rectangle rect4 = new Rectangle(4,2,8,3);
		List<Rectangle> rects = new ArrayList<>();
		rects.add(rect1);
		rects.add(rect2);
		rects.add(rect3);
		rects.add(rect4);
		
		farm = new Farm(SMALL_X_DIR, SMALL_Y_DIR);
		farm.setInfertileAreas(rects);
		
		List<Integer> expected = new ArrayList<>();
		expected.add(10);
		expected.add(346);
		
		Set<Point> infertilePoints = Rectangle.getSetOfInfertilePointsForListOfRects(rects);
		List<Integer> plots = systemUnderTest.getListOfFertilePlots(farm);
		int totalFertilePoints = 0;
		for (Integer i : plots)
		{
			totalFertilePoints += i;
		}
		Collections.sort(plots);
		int numInFertilePoints = infertilePoints.size();
		assertTrue(
				"fertile plus inFertile should have equaled total and did not.  Fertile: " + totalFertilePoints
						+ " inFertilePoints: " + infertilePoints + " total: " + 400,
				DataValidator.ValidateSummation(400, numInFertilePoints, totalFertilePoints));
		assertTrue(plots.size() == 2);
		verifyPlots(expected, plots);
	}
	
	@Test
	public void testRectanglesDoNotTouchbyOne(){
		
		Rectangle rect1 = new Rectangle(2, 2, 3, 7);
		Rectangle rect2 = new Rectangle(5,6,7,7);
		Rectangle rect3 = new Rectangle(9,2,10,7);
		Rectangle rect4 = new Rectangle(5,2,7,3);
		List<Rectangle> rects = new ArrayList<>();
		rects.add(rect1);
		rects.add(rect2);
		rects.add(rect3);
		rects.add(rect4);
		
		farm = new Farm(SMALL_X_DIR, SMALL_Y_DIR);
		farm.setInfertileAreas(rects);
		
		List<Integer> expected = new ArrayList<>();
		expected.add(364);
		
		Set<Point> infertilePoints = Rectangle.getSetOfInfertilePointsForListOfRects(rects);
		List<Integer> plots = systemUnderTest.getListOfFertilePlots(farm);
		int totalFertilePoints = 0;
		for (Integer i : plots)
		{
			totalFertilePoints += i;
		}
		Collections.sort(plots);
		int numInFertilePoints = infertilePoints.size();
		assertTrue(
				"fertile plus inFertile should have equaled total and did not.  Fertile: " + totalFertilePoints
						+ " inFertilePoints: " + infertilePoints + " total: " + 400,
				DataValidator.ValidateSummation(400, numInFertilePoints, totalFertilePoints));
		assertTrue(plots.size() == expected.size());
		verifyPlots(expected, plots);
	}
}
