package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import utils.DataValidator;

public class FarmTest
{

	private static final int SMALL_X_DIR = 40;
	private static final int SMALL_Y_DIR = 60;
	private static final int LARGE_X_DIR = 400;
	private static final int LARGE_Y_DIR = 600;

	@Mock
	FarmPoint farmPoint;

	Farm systemUnderTest;

	@Before 
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		systemUnderTest = new Farm(SMALL_X_DIR, SMALL_Y_DIR);
	}

	@Test
	public void testFarmFactory()
	{

		List<ArrayList<FarmPoint>> parcels = systemUnderTest.getParcels();

		int xValue = parcels.size();
		assertTrue("parcels should have " + SMALL_X_DIR + " list of points.  Was: " + xValue, xValue == SMALL_X_DIR);
		for (int i = 0; i < parcels.size(); i++)
		{
			List<FarmPoint> pointsFromParcel = parcels.get(i);
			verifyYDirection(pointsFromParcel);
			for (int j = 0; j < pointsFromParcel.size(); j++)
			{
				FarmPoint farmPoint = pointsFromParcel.get(j);
				verifyCordinates(i, j, farmPoint);
				verifyFertile(farmPoint);
			}
		}
	}

	private void verifyFertile(FarmPoint inFarmPoint)
	{
		boolean fertile = inFarmPoint.isFertile();
		assertTrue("each point should be initialized as fertils.  Was not.", fertile == true);
	}

	private void verifyCordinates(int i, int j, FarmPoint inFarmPoint)
	{
		Point farmPointPoint = inFarmPoint.getPoint();
		assertTrue("the point should have as an index " + i + " " + j + "Was: " + farmPointPoint.toString(),
				farmPointPoint.getX() == i && farmPointPoint.getY() == j);
	}

	private void verifyYDirection(List<FarmPoint> pointsFromParcel)
	{
		assertTrue("each list int the X direction should have " + SMALL_Y_DIR + " points.  Was: "
				+ pointsFromParcel.size(), pointsFromParcel.size() == SMALL_Y_DIR);
	}

	@Test
	public void testAddNewInfertileArea()
	{

		systemUnderTest = new Farm(15, 15);
		Rectangle rect1 = new Rectangle(2, 4, 10, 4);
		Rectangle rect2 = new Rectangle(8, 4, 10, 6);
		Rectangle rect3 = new Rectangle(2, 8, 10, 10);
		Rectangle rect4 = new Rectangle(2, 4, 4, 10);
		List<Rectangle> rects = new ArrayList<Rectangle>();
		rects.add(rect1);
		rects.add(rect2);
		rects.add(rect3);
		rects.add(rect4);

		systemUnderTest.setInfertileAreas(rects);
		List<ArrayList<FarmPoint>> parcels = systemUnderTest.getParcels();
		for (Rectangle r : rects)
		{
			verfiyPoints(r, parcels);
		}

	}

	@Test
	public void testAddNewInfertileAreaTargetTestCase2()
	{

		systemUnderTest = new Farm(400, 600);
		Rectangle rect1 = new Rectangle(48, 192, 351, 207);
		Rectangle rect2 = new Rectangle(48, 392, 351, 407);
		Rectangle rect3 = new Rectangle(120, 52, 135, 547);
		Rectangle rect4 = new Rectangle(260, 52, 275, 547);
		List<Rectangle> rects = new ArrayList<Rectangle>();
		rects.add(rect1);
		rects.add(rect2);
		rects.add(rect3);
		rects.add(rect4);

		systemUnderTest.setInfertileAreas(rects);
		List<ArrayList<FarmPoint>> parcels = systemUnderTest.getParcels();

		for (Rectangle r : rects)
		{
			verfiyPoints(r, parcels);
		}
		List<Point> points = new ArrayList<>();
		for (int i = 0; i < parcels.size(); i++)
		{
			for (int j = 0; j < parcels.get(i).size(); j++)
			{
				if (!parcels.get(i).get(j).isFertile())
				{
					Point p = new Point(i, j);
					points.add(p);
				}
			}
		}
	}

	private void verfiyPoints(Rectangle rect1, List<ArrayList<FarmPoint>> parcels)
	{
		Point lowerLeft = rect1.getLowerLeftPoint();
		Point upperRight = rect1.getUpperRightPoint();
		for (int i = lowerLeft.getX(); i <= upperRight.getX(); i++)
		{
			for (int j = lowerLeft.getY(); j <= upperRight.getY(); j++)
			{
				assertFalse(i + " " + j + " should have been false and was not.", parcels.get(i).get(j).isFertile());
			}
		}
	}

	@Test
	public void testTargetTestCaseOne()
	{
		systemUnderTest = new Farm(LARGE_X_DIR, LARGE_Y_DIR);
		Rectangle rect1 = new Rectangle(0, 292, 399, 307);
		List<Rectangle> rects = new ArrayList<>();
		rects.add(rect1);
		
		systemUnderTest.setInfertileAreas(rects);
		
		List<Integer> expectedList = new ArrayList<>();
		expectedList.add(116800);
		expectedList.add(116800);
		
		List<Integer> plots = systemUnderTest.getListOfFertilePlots();
		Collections.sort(plots);
		assertTrue("should have found 2 plots.  Found: " + plots.size(), plots.size() == expectedList.size());
		verifyPlots(expectedList, plots); 
	}
	
	@Test
	public void testTargetTestCaseTwo()
	{
		systemUnderTest = new Farm(LARGE_X_DIR, LARGE_Y_DIR);
		Rectangle rect1 = new Rectangle(48, 192, 351, 207);
		Rectangle rect2 = new Rectangle(48, 392, 351, 407);
		Rectangle rect3 = new Rectangle(120, 52, 135, 547);
		Rectangle rect4 = new Rectangle(260, 52, 275, 547);
		List<Rectangle> rects = new ArrayList<>();
		rects.add(rect1);
		rects.add(rect2);
		rects.add(rect3);
		rects.add(rect4);
		
		systemUnderTest.setInfertileAreas(rects);
		
		List<Integer> expectedList = new ArrayList<>();
		expectedList.add(22816);
		expectedList.add(192608);
		
		List<Integer> plots = systemUnderTest.getListOfFertilePlots();
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
		systemUnderTest = new Farm(11, 11);
		Rectangle rect1 = new Rectangle(2, 1, 3, 6);
		Rectangle rect2 = new Rectangle(2, 5, 7, 6);
		Rectangle rect3 = new Rectangle(6, 1, 7, 6);
		Rectangle rect4 = new Rectangle(2, 1, 7, 2);
		List<Rectangle> rects = new ArrayList<Rectangle>();
		rects.add(rect1);
		rects.add(rect2);
		rects.add(rect3);
		rects.add(rect4);
		List<Integer> expected = new ArrayList<>();
		expected.add(4);
		expected.add(85);

		systemUnderTest.setInfertileAreas(rects);
		Set<Point> infertilePoints = systemUnderTest.getInFertilePoints();
		int numInFertilePoints = infertilePoints.size();
		List<Integer> plots = systemUnderTest.getListOfFertilePlots();
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
		
		systemUnderTest = new Farm(20, 20);
		
		Rectangle rect1 = new Rectangle(2, 2, 3, 7);
		Rectangle rect2 = new Rectangle(4,6,8,7);
		Rectangle rect3 = new Rectangle(9,2,10,7);
		Rectangle rect4 = new Rectangle(4,2,8,3);
		List<Rectangle> rects = new ArrayList<>();
		rects.add(rect1);
		rects.add(rect2);
		rects.add(rect3);
		rects.add(rect4);
		List<Integer> expected = new ArrayList<>();
		expected.add(10);
		expected.add(346);
		
		systemUnderTest.setInfertileAreas(rects);
		Set<Point> infertilePoints = systemUnderTest.getInFertilePoints();
		List<Integer> plots = systemUnderTest.getListOfFertilePlots();
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
		
		systemUnderTest = new Farm(20, 20);
		
		Rectangle rect1 = new Rectangle(2, 2, 3, 7);
		Rectangle rect2 = new Rectangle(5,6,7,7);
		Rectangle rect3 = new Rectangle(9,2,10,7);
		Rectangle rect4 = new Rectangle(5,2,7,3);
		List<Rectangle> rects = new ArrayList<>();
		rects.add(rect1);
		rects.add(rect2);
		rects.add(rect3);
		rects.add(rect4);
		List<Integer> expected = new ArrayList<>();
		expected.add(364);
		
		systemUnderTest.setInfertileAreas(rects);
		Set<Point> infertilePoints = systemUnderTest.getInFertilePoints();
		List<Integer> plots = systemUnderTest.getListOfFertilePlots();
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
