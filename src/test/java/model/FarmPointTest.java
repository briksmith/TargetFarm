package model;

import org.junit.Test;

import utils.Consts;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FarmPointTest
{

	@Test
	public void testConstructor()
	{

		Point point = new Point(34, 98);
		FarmPoint farmPoint = new FarmPoint(point);

		Point farmPointsPoint = farmPoint.getPoint();
		int farmPointParcelIndex = farmPoint.getParcel();
		boolean farmPointFertile = farmPoint.isFertile();
		boolean farmPointVisited = farmPoint.isVisited();
		assertTrue("farmPoint should have point: " + point.toString() + " was: " + farmPointsPoint.toString(),
				farmPointsPoint.equals(point));
		assertTrue("farmPoint should have parcel index unassigned: " + Consts.UNASSIGNED_PARCEL_INDEX + " Was: " + farmPointParcelIndex,
					farmPointParcelIndex == Consts.UNASSIGNED_PARCEL_INDEX);
		assertTrue("farmPoint should be fertile.  Was false. ", farmPointFertile == true);
		assertFalse("farmPoint should not be visited.  Was visited. ", farmPointVisited = false);
	}
}
