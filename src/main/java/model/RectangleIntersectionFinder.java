package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RectangleIntersectionFinder
{
	private static Set<Point> leftEdgePoints = new HashSet<>();
	private static Set<Point> rightEdgePoints = new HashSet<>();
	private static Set<Point> topEdgePoints = new HashSet<>();
	private static Set<Point> bottomEdgePoints = new HashSet<>();
	private static List<List<Point>> listOfEdgePoints = new ArrayList<List<Point>>();
	
	public static Set<Point> findAllIntersectionsWithAxisAndRectangles(Farm inFarm){
		Set<Point> totalPoints = new HashSet<>();
		Set<Point> currentPoints = new HashSet<>();
		List<Rectangle> rects = inFarm.getInFertileAreas();
		for ( int i = 0; i < rects.size(); i++ ){
			Rectangle rect1 = rects.get(i);
			currentPoints = findIntersectionWithAxis(inFarm, rect1);
			totalPoints.addAll(currentPoints);
			for ( int j = i + 1; j < rects.size(); j++)
			{
				currentPoints = findIntersectionOfEdges(rect1, rects.get(j));
				totalPoints.addAll(currentPoints);
			}
			
		}
		
		return totalPoints;
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

	private static void flushEdgePointDataStructures()
	{
		leftEdgePoints.clear();
		rightEdgePoints.clear();
		topEdgePoints.clear();
		bottomEdgePoints.clear();
		listOfEdgePoints.clear();
		
	}

	private static void createEdgeSets(Rectangle rect2)
	{
		createEdgeSet(leftEdgePoints, rect2.getLowerLeftPoint(), rect2.getUpperLeftPoint());
	}

	private static void createEdgeSet(Set<Point> edgePoints, Point inPoint1, Point inPoint2)
	{
		Set<Point> edgeSet = new HashSet<>();
		boolean changeXValue = shouldChangeXValue(inPoint1, inPoint2);
		int distance = calculateDistance(inPoint1, inPoint2, changeXValue);
		int sign = distance > 0 ? 1 : -1;
		
		for ( int i = 0; i <= distance; i++) {
				edgeSet.add(new Point(inPoint1));
				incrementCordinateToWalk(changeXValue, sign, inPoint1);
			}
			
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

	private static int calculateDistance(Point inPoint1, Point inPoint2, boolean changeXValue)
	{
		int distance = 0;
		if ( changeXValue) {
			distance = ( inPoint2.getX() - inPoint1.getX() );
		}else
		{
			distance = ( inPoint2.getY() - inPoint1.getY() );
		}
		return distance;
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

