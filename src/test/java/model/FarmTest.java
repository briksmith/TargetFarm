package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import utils.Consts;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Farm.class)
public class FarmTest
{

	private static final int LARGE_Y_DIR = 600;
	private static final int LARGE_X_DIR = 400;
	private static final int SMALL_X_DIR = 60;
	private static final int SMALL_Y_DIR = 40;
	
	@Mock
	FarmPoint farmPoint;
	
	Farm systemUnderTest;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		systemUnderTest = new Farm(SMALL_X_DIR, SMALL_Y_DIR);
	}

	@Test
	public void testFarmFactory()
	{


		List<ArrayList<FarmPoint>> parcels = systemUnderTest.getParcels();

		int xValue = parcels.size();
		assertTrue("parcels should have " + SMALL_X_DIR + " list of points.  Was: " + xValue, xValue == SMALL_X_DIR);
		for (int i = 0; i < parcels.size(); i++){
			List<FarmPoint> pointsFromParcel = parcels.get(i);
			verifyYDirection(pointsFromParcel);
			System.out.println(i);
			for ( int j = 0; j < pointsFromParcel.size(); j++){
				FarmPoint farmPoint = pointsFromParcel.get(j);
				verifyCordinates(i, j, farmPoint);
				verifyFertile(farmPoint);
				verifyParcelIndex(farmPoint);
			}
	}
		System.out.println("veified farm");
	}

	private void verifyParcelIndex(FarmPoint inFarmPoint)
	{
		int parcel = inFarmPoint.getParcel();
		assertTrue("each point should be initialized to unassigned parcel index.  Was: " + parcel, parcel == Consts.UNASSIGNED_PARCEL_INDEX);
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
		assertTrue("each list int the X direction should have " + SMALL_Y_DIR + " points.  Was: " + pointsFromParcel.size(),
				pointsFromParcel.size() == SMALL_Y_DIR);
	}
	
	@Test
	public void testAddNewInfertileArea(){
		
		
	}
	
	@Test
	public void testNotCountedAndFertileFertileUnVisited() throws Exception{
		when(farmPoint.isFertile()).thenReturn(true);
		when(farmPoint.isVisited()).thenReturn(false);
		boolean result = Whitebox.invokeMethod(systemUnderTest, "notCountedAndFertile", farmPoint);
		
		assertTrue("In this case of fertile and not visited we should search the area.", result);
	}
	
	@Test
	public void testNotCountedAndFertileFertileAndVisited() throws Exception{
		
		when(farmPoint.isFertile()).thenReturn(true);
		when(farmPoint.isVisited()).thenReturn(true);
		boolean result = Whitebox.invokeMethod(systemUnderTest, "notCountedAndFertile", farmPoint);
		
		assertFalse("In this case of fertile and  visited we should not search the area.", result);
	}
	
	@Test
	public void testNotCountedAndFertileInfertileUnVisited() throws Exception{
		when(farmPoint.isFertile()).thenReturn(false);
		when(farmPoint.isVisited()).thenReturn(false);
		boolean result = Whitebox.invokeMethod(systemUnderTest, "notCountedAndFertile", farmPoint);
		
		assertFalse("In this case of inFertile and  unVisited we should not search the area.", result);
	}
	
	@Test
	public void testNotCountedAndInfertileInInfertileVisited() throws Exception{
		when(farmPoint.isFertile()).thenReturn(false);
		when(farmPoint.isVisited()).thenReturn(true);
		boolean result = Whitebox.invokeMethod(systemUnderTest, "notCountedAndFertile", farmPoint);
		
		assertFalse("In this case of inFertile and  visited we should not search the area.", result);
	}
	
	@Test
	public void testAddNewInfertileAreaToExistingInfertileArea(){
		
	}
	
	@Test
	public void testAddNewInfertileAreasOverLapping(){
		
	}
	
	@Test
	public void testAddNewInfertileAreasOverLappingToExistingOverlappingInfertileAreas(){
		
	}
	
	
	@Test
	public void testTargetTestCaseOne()
	{
	/*	systemUnderTest = new Farm(LARGE_X_DIR, LARGE_Y_DIR);
		List<Integer> expectedList = new ArrayList<>();
		expectedList.add(116800);
		expectedList.add(116800);
		
	//	List<Integer> plots = systemUnderTest.getListOfFertilePlots();
		assertTrue("should have found 2 plots.  Found: " + plots.size(), plots.size() == 1);
		for( int i = 0; i < expectedList.size(); i++){
			Integer expected = expectedList.get(i);
			Integer actual = plots.get(i);
			assertTrue("Expected: " + expected + " Actual: " + plots.get(i), expected.equals(actual));
		} */
	}
}
