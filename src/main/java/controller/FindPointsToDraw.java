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
		while (isNotACornerPoint(rectangleCorners, nextPoint) && isNotAnErrorPoint(nextPoint))
		{
			nextPoint = getNextYPoint(nextPoint, inFarm);
		}
		return nextPoint;
	}

	private static boolean isNotACornerPoint(Set<Point> rectangleCorners, Point nextPoint)
	{
		return !rectangleCorners.contains(nextPoint);
	}

	private static boolean isNotAnErrorPoint(Point nextPoint)
	{
		return !nextPoint.equals(errorPoint);
	}

	private static Point findNextCornerOrIntersectingEdge(Point inPoint, Farm inFarm)
	{
		Point pointToReturn = new Point (inPoint);
		Point pointToWalk = errorPoint;
		do{
			pointToReturn = getNextYPoint(pointToReturn, inFarm);
			pointToWalk = getCornerOrEdgeBeforeTheYAxis(pointToReturn, inFarm);
			if (!pointToWalk.equals(errorPoint)){
				break;
			}
			pointToWalk = pointToReturn;
			pointToWalk = walkToFarRight(pointToReturn, inFarm);
			pointToWalk = getCornerOrEdgeBeforeFarRight(pointToReturn, inFarm);
			if ( !pointToWalk.equals(errorPoint)){
				break;
			}
			if ( inFarm.getInFertilePoints().contains(pointToReturn)){
				pointToWalk = pointToReturn;
				break;
			}
			pointToWalk = pointToReturn;
			if ( pointToWalk.getY() >= inFarm.getColCount()){
				break;
			}
			
		}while(true);
		return pointToReturn;
	}

	

	private static Point getCornerOrEdgeBeforeTheYAxis(Point nextPoint, Farm inFarm)
	{
		Point pointToReturn = new Point(nextPoint);
		Set<Point> rectangleCorners = inFarm.getInFertileAreaCornerPoints();
		
		while (  pointToReturn.getX() >= 0 ) {
			if ( rectangleCorners.contains(pointToReturn)){
				return pointToReturn;
			}
			pointToReturn = walkTowardsYAxis(pointToReturn, inFarm);
		}
		return errorPoint;
	}

	private static Point walkTowardsYAxis(Point inPoint, Farm inFarm)
	{
		int curX = inPoint.getX();
		if ( curX > 0){
			return new Point(curX -1, inPoint.getY());
		}else{
			return errorPoint;
		}
	}

	private static Point getCornerOrEdgeBeforeFarRight(Point nextPoint, Farm inFarm){
		Point pointToReturn = new Point(nextPoint);
		Set<Point> rectangleCorners = inFarm.getInFertileAreaCornerPoints();
		
		while ( pointToReturn.getX() < inFarm.getColCount()){
			if ( rectangleCorners.contains(pointToReturn)) {
				return pointToReturn;
			}
			pointToReturn = walkToFarRight(pointToReturn, inFarm);
		}
		return errorPoint;
	}
	
	private static Point walkToFarRight(Point inPoint, Farm inFarm)
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
