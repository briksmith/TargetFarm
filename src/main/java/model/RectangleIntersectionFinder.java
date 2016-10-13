package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class RectangleIntersectionFinder
{
	public static Set<Point> findAllIntersectionsWithAxisAndRectangles(Farm inFarm){
		Set<Point> totalPoints = new HashSet<>();
		List<Rectangle> rects = inFarm.getInFertileAreas();
		int i = 0;
		for ( Rectangle rect1 : rects){
			addIntersectionWithAxes(inFarm, totalPoints, rect1);
			for ( int j = i + 1; j < rects.size(); j++)
			{
				addIntersectionWithEdges(totalPoints, rects, rect1, j);
			}
			i++;
		}
		if ( totalPoints.isEmpty()){
			return totalPoints;
		}
		addOrigin(totalPoints);
		addPointsOnAxes(inFarm, totalPoints, rects);
		addAllRectanglePointsToCurrentPoints(totalPoints, rects);
		return totalPoints;
	}


	private static void addIntersectionWithAxes(Farm inFarm, Set<Point> totalPoints, Rectangle rect1)
	{
		Set<Point> currentPoints;
		currentPoints = findIntersectionWithAxis(inFarm, rect1);
		totalPoints.addAll(currentPoints);
	}


	private static Set<Point> findIntersectionWithAxis(Farm inFarm, Rectangle rect1)
	{
		Set<Point> points = new HashSet<>();
		if ( intersectsYAxis(inFarm, rect1) || intersectsXAxis(inFarm, rect1))
		{
			points = rect1.getRectangleCorners();
		}
		return points;
	}


	private static boolean intersectsYAxis(Farm inFarm, Rectangle rect1)
	{
		Point lowerLeft = rect1.getLowerLeftPoint();
		Point lowerRight = rect1.getLowerRightPoint();
		if ( lowerLeft.getX() == 0 && lowerRight.getX() == inFarm.getRowCount() - 1){
			return true;
		}
		return false;
	}


	private static boolean intersectsXAxis(Farm inFarm, Rectangle rect1)
	{
		Point lowerLeft = rect1.getLowerLeftPoint();
		Point upperLeft = rect1.getUpperLeftPoint();
		if ( lowerLeft.getY() == 0 && upperLeft.getY() == inFarm.getColCount() - 1) {
			return true;
		}
		return false;
	}


	private static void addIntersectionWithEdges(Set<Point> totalPoints, List<Rectangle> rects, Rectangle rect1, int j)
	{
		Set<Point> currentPoints;
		currentPoints = findIntersectionOfEdges(rect1, rects.get(j));
		totalPoints.addAll(currentPoints);
	}


	private static void addOrigin(Set<Point> totalPoints)
	{
		totalPoints.add(new Point(0,0));
	}


	private static void addPointsOnAxes(Farm inFarm, Set<Point> totalPoints, List<Rectangle> rects)
	{
		Set<Point> currentPoints;
		currentPoints = findPointsOnAxes(rects, inFarm);
		totalPoints.addAll(currentPoints);
	}


	private static Set<Point> findPointsOnAxes(List<Rectangle> rects, Farm inFarm)
	{
		TreeSet<Point> sortedPoints = new TreeSet<>();
		putCornerPointsIntoSortedSet(rects, sortedPoints);
		
		Set<Point> curPoints = new HashSet<>();
		Object[] arrayOfPoints =  sortedPoints.toArray();
		Point lowerLeft = (Point) arrayOfPoints[0];
		Point upperLeft = (Point) arrayOfPoints[1];
		Point lowerRight = (Point) arrayOfPoints[arrayOfPoints.length - 2];
		Point upperRight = (Point) arrayOfPoints[arrayOfPoints.length - 1];
		addPointsForLowerLeft(curPoints, lowerLeft);
		addPointsForUpperLeft(inFarm, curPoints, upperLeft);
		addPointsForLowerRight(inFarm, curPoints, lowerRight);
		addPointsForUpperRight(inFarm, curPoints, upperRight);
		return curPoints;
	}


	private static void putCornerPointsIntoSortedSet(List<Rectangle> rects, TreeSet<Point> sortedPoints)
	{
		Set<Point> points;
		for ( Rectangle r : rects){
			points = r.getRectangleCorners();
			sortedPoints.addAll(points);
		}
	}


	private static void addPointsForLowerLeft(Set<Point> curPoints, Point lowerLeft)
	{
		curPoints.add( new Point(lowerLeft.getX(),0));
		curPoints.add( new Point(0, lowerLeft.getY()));
	}


	private static void addPointsForUpperLeft(Farm inFarm, Set<Point> curPoints, Point upperLeft)
	{
		curPoints.add( new Point(upperLeft.getX(), inFarm.getColCount() -1 ));
		curPoints.add( new Point(0, upperLeft.getY()));
	}


	private static void addPointsForLowerRight(Farm inFarm, Set<Point> curPoints, Point lowerRight)
	{
		curPoints.add( new Point(lowerRight.getX(), 0));
		curPoints.add( new Point(inFarm.getRowCount() - 1, lowerRight.getY()));
	}


	private static void addPointsForUpperRight(Farm inFarm, Set<Point> curPoints, Point upperRight)
	{
		curPoints.add( new Point(upperRight.getX(), inFarm.getColCount() - 1));
		curPoints.add( new Point(inFarm.getRowCount() - 1, upperRight.getY()));
	}


	private static void addAllRectanglePointsToCurrentPoints(Set<Point> currentPoints, List<Rectangle> rects)
	{
		for ( Rectangle r :rects){
			Set<Point> rectPoints = r.getRectangleCorners();
			currentPoints.addAll(rectPoints);
		}
	}


	public static Set<Point> findIntersectionOfEdges(Rectangle rectangleIntersecting, Rectangle rectangleToIntersectWith){
		
		List<List<Point>> intersectingEdges = rectangleIntersecting.getEdgeSetList();
		
		Set<Point> points = new HashSet<Point>();
		for ( List<Point> intersectingEdge : intersectingEdges){
			Set<Point> intersectionPointsWithRectangle = findPointsIntersectedWithRectangle(intersectingEdge, rectangleToIntersectWith);
			points.addAll(intersectionPointsWithRectangle);
			
		}
		return points;
	}

	private static Set<Point> findPointsIntersectedWithRectangle(List<Point> intersectingEdge,
			Rectangle rectangleToIntersectWith)
	{
		List<List<Point>> edgesToIntersectWith = rectangleToIntersectWith.getEdgeSetList();
		List<Point> intersectionPointsFound = new ArrayList<>();
		for ( List<Point> edgeToIntersectWith : edgesToIntersectWith){
			
			for ( Point s : intersectingEdge){
				if( edgeToIntersectWith.contains(s)){
					intersectionPointsFound.add(s);
				}
		}
		}
		List<Point> intersections = returnFirstAndLastValue(intersectionPointsFound);
		Set<Point> points = new HashSet<Point>();
		points.addAll(intersections);
		return points;
	}

	private static boolean shouldChangeXValue(Point inPoint1, Point inPoint2)
	{
		if ( inPoint1.getX() != inPoint2.getX()) {
			return true;
		}
		else{
			return false;
		}
	}

	
	private static int getCordinateToWalk(Point inPoint, boolean inChangeXValue)
	{
		if ( inChangeXValue){
			return inPoint.getX();
		}else{
			return inPoint.getY();
		}
	}
	
	private static void incrementCordinateToWalk(boolean isX, int stepValue, Point pointToCheck)
	{
		int cordinate = getCordinateToWalk(pointToCheck, isX);
		cordinate += stepValue;
		if ( isX){
			pointToCheck.setX(cordinate);
		}else{
			pointToCheck.setY(cordinate);
		}
	}

	private static List<Point> returnFirstAndLastValue(List<Point> points)
	{
		List<Point> pointsToReturn = new ArrayList<>();
		if ( points.size() > 0){
		pointsToReturn.add(points.get(0));
		}
		if (points.size() > 1){
		pointsToReturn.add(points.get(points.size() - 1));
		}
		
		return pointsToReturn;
	}
	
	public static List<Point> findIntersectionOfEdge(Point inPoint1, Point inPoint2, Rectangle inRect)
	{
		List<Point> points = new ArrayList<>();
		boolean changeXValue = shouldChangeXValue(inPoint1, inPoint2);
		int stepValue = getStepValue(inPoint1, inPoint2, changeXValue);
		int startPoint = getCordinateToWalk(inPoint1, changeXValue);
		int endPoint = getCordinateToWalk(inPoint2, changeXValue);
		Point pointToCheck = new Point(inPoint1);
		
		if ( stepValue > 0)
		{
			for ( int i = startPoint; i <= endPoint; i++){
				
				if ( inRect.Contains(pointToCheck)){
					points.add( new Point(pointToCheck));
				}
				incrementCordinateToWalk(changeXValue, stepValue, pointToCheck);
			}
		}
		else{
			for ( int i = startPoint; i >= endPoint; i--){
				if ( inRect.Contains(pointToCheck)){
					points.add( new Point(pointToCheck));
				}
				incrementCordinateToWalk(changeXValue, stepValue, pointToCheck);
			}
		}
		return returnFirstAndLastValue(points);
	}
	
	private static int getStepValue(Point inPoint1, Point inPoint2, boolean changeXValue)
	{
		int direction;
		direction = calculateDirection(inPoint1, inPoint2, changeXValue);
		return direction > 0 ? 1 : -1;
	}
	
	private static int calculateDirection(Point inPoint1, Point inPoint2, boolean changeXValue)
	{
		int direction;
		if ( changeXValue) {
			direction = ( inPoint2.getX() - inPoint1.getX() );
		}else
		{
			direction = ( inPoint2.getY() - inPoint1.getY() );
		}
		return direction;
	}
	
}

