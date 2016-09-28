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
		boolean farmPointFertile = farmPoint.isFertile();
		boolean farmPointVisited = farmPoint.isVisited();
		assertTrue("farmPoint should have point: " + point.toString() + " was: " + farmPointsPoint.toString(),
				farmPointsPoint.equals(point));
		assertTrue("farmPoint should be fertile.  Was false. ", farmPointFertile == true);
		assertTrue("farmPoint should not be visited.  Was visited. ", farmPointVisited == false);
	}
}
