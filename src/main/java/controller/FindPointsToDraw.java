package controller;

import java.util.Set;
import java.util.TreeSet;

import model.Farm;
import model.Point;

public class FindPointsToDraw
{

	private FindPointsToDraw()
	{
	}

	private static final Point errorPoint = new Point(-1, -1);

	public static Point findHigherPoint(Point inPoint, TreeSet<Point> inPoints, Farm inFarm)
	{
		if (nextPointUpIsInfertile(inPoint, inFarm))
		{
			return walkEdgeUntilCornerFound(inPoint, inFarm);
		}
		else
		{
			inPoint = findNextCornerOrIntersectingEdge(inPoint, inFarm);
		}
		return inPoint;
	}

	private static boolean nextPointUpIsInfertile(Point inPoint, Farm inFarm)
	{
		Point nextPoint = getNextYPoint(inPoint, inFarm);
		Set<Point> inFertilePoints = inFarm.getInFertilePoints();
		return inFertilePoints.contains(nextPoint);
	}

	private static Point getNextYPoint(Point inPoint, Farm inFarm)
	{
		int curY = inPoint.getY();
		if (checkYBound(curY, inFarm))
		{
			return new Point(inPoint.getX(), curY + 1);
		}
		else
		{
			return errorPoint;
		}
	}

	private static boolean checkYBound(int curY, Farm inFarm)
	{
		return curY < inFarm.getColCount();

	}

	private static Point walkEdgeUntilCornerFound(Point inPoint, Farm inFarm)
	{
		Set<Point> rectangleCorners = inFarm.getInFertileAreaCornerPoints();
		Point nextPoint = getNextYPoint(inPoint, inFarm);
		while (!rectangleCorners.contains(nextPoint) && !nextPoint.equals(errorPoint))
		{
			nextPoint = getNextYPoint(nextPoint, inFarm);
		}
		return nextPoint;
	}

	private static Point findNextCornerOrIntersectingEdge(Point inPoint, Farm inFarm)
	{
		Set<Point> rectangleCorners = inFarm.getInFertileAreaCornerPoints();
		Point nextPoint = new Point(inPoint);
		Point pointToReturn = errorPoint;
		do{
			pointToReturn = walkTowardsOriginPoint(nextPoint, inFarm);
			if( rectangleCorners.contains(pointToReturn)){
				inPoint = pointToReturn;
			}
			pointToReturn = nextPoint;
			pointToReturn = walkToFarRightLookingForCorner(nextPoint, inFarm);
			if ( rectangleCorners.contains(pointToReturn)){
				inPoint = pointToReturn;
			}
			inPoint = getNextYPoint(inPoint, inFarm);
			if ( inFarm.getInFertilePoints().contains(inPoint)){
				pointToReturn = inPoint;
			}
			
		}while(!rectangleCorners.contains(inPoint) && !inPoint.equals(errorPoint));
		return pointToReturn;
	}

	

	private static Point walkTowardsOriginPoint(Point inPoint, Farm inFarm)
	{
		int curX = inPoint.getX();
		if ( curX > 0){
			return new Point(curX -1, inPoint.getY());
		}else{
			return errorPoint;
		}
	}

	private static Point walkToFarRightLookingForCorner(Point inPoint, Farm inFarm)
	{
		int curX = inPoint.getX();
		if ( curX < inFarm.getRowCount() ){
			return new Point(curX + 1, inPoint.getY());
		}else{
			return errorPoint;
		}
		
	}

	public static Point findRightPoint(Point inPoint, TreeSet<Point> inPoints)
	{
		return new Point(0, 0);
	}

	public static Point findDownPoint(Point inPoint, TreeSet<Point> inPoints)
	{
		return new Point(0, 0);
	}

	public static Point findLeftPoint(Point inPoint, TreeSet<Point> inPoints)
	{
		return new Point(0, 0);
	}

	private static void addPointToSetIfNotInSet(Point inPoint, TreeSet<Point> inPoints)
	{

	}
}
