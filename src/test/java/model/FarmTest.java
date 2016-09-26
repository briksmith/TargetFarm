package model;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import utils.Consts;

public class FarmTest
{

	private static final int Y_DIR = 600;
	private static final int X_DIR = 400;

	@Test
	public void testFarmFactory()
	{

		Farm farm = new Farm(X_DIR, Y_DIR);

		List<ArrayList<FarmPoint>> parcels = farm.getParcels();

		int xValue = parcels.size();
		assertTrue("parcels should have " + X_DIR + " list of points.  Was: " + xValue, xValue == X_DIR);
		for (int i = 0; i < parcels.size(); i++){
			List<FarmPoint> pointsFromParcel = parcels.get(i);
			verifyXDirection(pointsFromParcel);
			for ( int j = 0; j < pointsFromParcel.size(); j++){
				FarmPoint farmPoint = pointsFromParcel.get(j);
				verifyCordinates(i, j, farmPoint);
				verifyFertile(farmPoint);
				verifyParcelIndex(farmPoint);
			}
	}
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

	private void verifyXDirection(List<FarmPoint> pointsFromParcel)
	{
		assertTrue("each list int the Y direction should have " + Y_DIR + " points.  Was: " + pointsFromParcel.size(),
				pointsFromParcel.size() == Y_DIR);
	}
	
	@Test
	public void testAddNewInfertileArea(){
		
		Farm farm = new Farm(X_DIR, Y_DIR);
		
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
	
}
