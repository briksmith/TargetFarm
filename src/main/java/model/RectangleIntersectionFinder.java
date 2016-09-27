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
}
