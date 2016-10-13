package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

public class FarmPointTest
{

	FarmPoint farmPoint;
	
	
	@Before
	public void setUp(){
		farmPoint = new FarmPoint(new Point(0,0));
		initMocks(this);
	}
	
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
	
	@Test
	public void testNotCountedAndFertileFertileUnVisited() throws Exception
	{
		farmPoint.setFertile(true);
		farmPoint.setVisited(false);
		boolean result = farmPoint.notCountedAndFertile();

		assertTrue("In this case of fertile and not visited we should search the area.", result);
	}

	@Test
	public void testNotCountedAndFertileFertileAndVisited() throws Exception
	{

		farmPoint.setFertile(true);
		farmPoint.setVisited(true);
		boolean result = farmPoint.notCountedAndFertile();

		assertFalse("In this case of fertile and  visited we should not search the area.", result);
	}

	@Test
	public void testNotCountedAndFertileInfertileUnVisited() throws Exception
	{
		farmPoint.setFertile(false);
		farmPoint.setVisited(false);
		boolean result = farmPoint.notCountedAndFertile();

		assertFalse("In this case of inFertile and  unVisited we should not search the area.", result);
	}

	@Test
	public void testNotCountedAndInfertileInInfertileVisited() throws Exception
	{
		farmPoint.setFertile(false);
		farmPoint.setVisited(true);
		boolean result = farmPoint.notCountedAndFertile();

		assertFalse("In this case of inFertile and  visited we should not search the area.", result);
	}
}
