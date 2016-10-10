package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rectangle
{
	private Point lowerLeftPoint;
	private Point upperLeftPoint;
	private Point upperRightPoint;
	private Point lowerRightPoint;
	
	private List<Point> leftEdgeSet;
	private List<Point> rightEdgeSet;
	private List<Point> bottomEdgeSet;
	private List<Point> topEdgeSet;
	private List<List<Point>> edgeSetList = new ArrayList<List<Point>>();
	
	private Set<Point> infertilePoints;
	
	public Rectangle(Point inLowerLeftPoint, Point inUpperRightPoint)
	{

		this.lowerLeftPoint = DefensiveCopyOfPoint(inLowerLeftPoint);
		this.upperRightPoint = DefensiveCopyOfPoint(inUpperRightPoint);
		
		this.upperLeftPoint = new Point(inLowerLeftPoint.getX(), inUpperRightPoint.getY());
		this.lowerRightPoint = new Point(inUpperRightPoint.getX(), inLowerLeftPoint.getY());
		createEdgeSets();
		calculateInfertilePoints();
	}
	
	public Rectangle( int lowerLeftX, int lowerLeftY, int upperLeftX, int upperRightY){
	
		lowerLeftPoint = new Point(lowerLeftX, lowerLeftY);
		upperRightPoint = new Point(upperLeftX, upperRightY);
		
		this.upperLeftPoint = new Point(lowerLeftPoint.getX(), upperRightPoint.getY());
		this.lowerRightPoint = new Point(upperRightPoint.getX(), lowerLeftPoint.getY());
		createEdgeSets();
		calculateInfertilePoints();
	}

	private void createEdgeSets()
	{
		leftEdgeSet = createEdgeSet(lowerLeftPoint, upperLeftPoint);
		rightEdgeSet = createEdgeSet(lowerRightPoint, upperRightPoint);
		topEdgeSet = createEdgeSet(upperLeftPoint, upperRightPoint);
		bottomEdgeSet = createEdgeSet(lowerLeftPoint, lowerRightPoint);
		addEdgeSetsToList();
	
		
	}

	private void calculateInfertilePoints()
	{
		infertilePoints = new HashSet<>();
		
		for( int i = lowerLeftPoint.getX(); i <= upperRightPoint.getX(); i++){
			for ( int j = lowerLeftPoint.getY(); j <= upperRightPoint.getY(); j++){
				Point pointToAdd = new Point(i,j);
				infertilePoints.add(pointToAdd);
			}
		}
		
	}

	private void addEdgeSetsToList()
	{
		edgeSetList.add(leftEdgeSet);
		edgeSetList.add(rightEdgeSet);
		edgeSetList.add(bottomEdgeSet);
		edgeSetList.add(topEdgeSet);
	}

	private Point DefensiveCopyOfPoint(Point inPoint)
	{
		int xValue = inPoint.getX();
		int yValue = inPoint.getY();
		return new Point(xValue, yValue);
	}
	
	private static List<Point> createEdgeSet( Point inPoint1, Point inPoint2)
	{
		Point pointToIncrement = new Point(inPoint1);
		List<Point> edgeSet = new ArrayList<>();
		boolean changeXValue = shouldChangeXValue(inPoint1, inPoint2);
		int distance = calculateDistance(inPoint1, inPoint2, changeXValue);
		int sign = distance > 0 ? 1 : -1;
		
		for ( int i = 0; i <= distance; i++) {
				edgeSet.add(new Point(pointToIncrement));
				incrementCordinateToWalk(changeXValue, sign, pointToIncrement);
			}
			return edgeSet;
	}

	public static boolean shouldChangeXValue(Point inPoint1, Point inPoint2)
	{
		if ( inPoint1.getX() != inPoint2.getX()) {
			return true;
		}
		else{
			return false;
		}
	}

	public static int calculateDistance(Point inPoint1, Point inPoint2, boolean changeXValue)
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

	public boolean equals(Object o){ 
		
		if ( !( o instanceof Rectangle) ){
			return false;
		}
		
		Rectangle inRect = (Rectangle) o;
		
		if( checkLowerLeftPoint(inRect) && checkUpperRightPoint(inRect)){
			return true;
		}
		
		return false;
	}
	
	public String toString() {
		return lowerLeftPoint.toString() + " " + upperRightPoint.toString();
	}

	private boolean checkLowerLeftPoint(Rectangle inRect)
	{
		return this.lowerLeftPoint.equals(inRect.lowerLeftPoint);
	}

	private boolean checkUpperRightPoint(Rectangle inRect)
	{
		return this.upperRightPoint.equals(inRect.upperRightPoint);
	}

	public boolean Contains(Point inPoint)
	{
	
		if (checkIfInPointXValueIsWithinRectangle(inPoint) && checkIfInPointYValueIsWithinRectangle(inPoint))
		{
			return true;
		}
		return false;
	}

	private boolean checkIfInPointXValueIsWithinRectangle(Point inPoint)
	{
		return inPoint.getX() >= lowerLeftPoint.getX() && inPoint.getX() <= upperRightPoint.getX();
	}

	private boolean checkIfInPointYValueIsWithinRectangle(Point inPoint)
	{
		return inPoint.getY() >= lowerLeftPoint.getY() && inPoint.getY() <= upperRightPoint.getY();
	}

	public Set<Point> getInfertilePoints(){
		
		Set<Point> pointsToReturn = new HashSet<>(infertilePoints);
		return pointsToReturn;
	}
	
	public static Set<Point> getSetOfInfertilePointsForListOfRects(List<Rectangle> inRects){
		
		Set<Point> infertilePoints = new HashSet<>();
		
		for (Rectangle r : inRects){
			Set<Point> rectPoints = r.getInfertilePoints();
			infertilePoints.addAll(rectPoints);
		}
		return infertilePoints;
	}
	
	public Set<Point> getSetOfIntersectingPoints(Rectangle inRect){
		
		Set<Point> points = new HashSet<>();
		
		points.addAll(RectangleIntersectionFinder.findIntersectionOfEdge(lowerLeftPoint, upperLeftPoint, inRect));
		points.addAll(RectangleIntersectionFinder.findIntersectionOfEdge(upperLeftPoint, upperRightPoint, inRect));
		points.addAll(RectangleIntersectionFinder.findIntersectionOfEdge(upperRightPoint, lowerRightPoint, inRect));
		points.addAll(RectangleIntersectionFinder.findIntersectionOfEdge(lowerRightPoint, lowerLeftPoint, inRect));
		
		
		return points;
	}
	
	public Point getLowerLeftPoint()
	{
		return new Point(lowerLeftPoint);
	}

	public void setLowerLeftPoint(Point lowerLeftPoint)
	{
		this.lowerLeftPoint = lowerLeftPoint;
	}

	public Point getUpperRightPoint()
	{
		return new Point(upperRightPoint);
	}

	public void setUpperRightPoint(Point upperRightPoint)
	{
		this.upperRightPoint = upperRightPoint;
	}
	
	public int calcPointsIncludeEdges(){
		int width = calcWidth();
		int length = calcHeight();
		return width * length;
	}

	private int calcHeight()
	{
		return ( upperRightPoint.getY() - lowerLeftPoint.getY()) + 1;
	}

	private int calcWidth()
	{
		return  ( upperRightPoint.getX() - lowerLeftPoint.getX() ) + 1;
	}

	public Point getUpperLeftPoint()
	{
		return new Point(upperLeftPoint);
	}

	public Point getLowerRightPoint()
	{
		return new Point(lowerRightPoint);
	}

	public List<List<Point>> getEdgeSetList()
	{
		return edgeSetList;
	}

}
