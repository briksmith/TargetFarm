package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FarmTest
{

	private static final int SMALL_X_DIR = 40;
	private static final int SMALL_Y_DIR = 60;

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

}
