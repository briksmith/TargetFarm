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

	public Rectangle(Point inLowerLeftPoint, Point inUpperRightPoint)
	{

		this.lowerLeftPoint = DefensiveCopyOfPoint(inLowerLeftPoint);
		this.upperRightPoint = DefensiveCopyOfPoint(inUpperRightPoint);
		
		this.upperLeftPoint = new Point(inLowerLeftPoint.getX(), inUpperRightPoint.getY());
		this.lowerRightPoint = new Point(inUpperRightPoint.getX(), inLowerLeftPoint.getY());
	}
	
	public Rectangle( int lowerLeftX, int lowerLeftY, int upperLeftX, int upperRightY){
	
		lowerLeftPoint = new Point(lowerLeftX, lowerLeftY);
		upperRightPoint = new Point(upperLeftX, upperRightY);
		
		this.upperLeftPoint = new Point(lowerLeftPoint.getX(), upperRightPoint.getY());
		this.lowerRightPoint = new Point(upperRightPoint.getX(), lowerLeftPoint.getY());
	}

	private Point DefensiveCopyOfPoint(Point inPoint)
	{
		int xValue = inPoint.getX();
		int yValue = inPoint.getY();
		return new Point(xValue, yValue);
	}

	public boolean Contains(Point inPoint)
	{

		if (checkIfInPointXValueIsWithinRectangle(inPoint) && checkIfInPointYValueIsWithinRectangle(inPoint))
		{
			return true;
		}
		return false;
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

	private boolean checkIfInPointXValueIsWithinRectangle(Point inPoint)
	{
		return inPoint.getX() >= lowerLeftPoint.getX() && inPoint.getX() <= upperRightPoint.getX();
	}

	private boolean checkIfInPointYValueIsWithinRectangle(Point inPoint)
	{
		return inPoint.getY() >= lowerLeftPoint.getY() && inPoint.getY() <= upperRightPoint.getY();
	}

	public Set<Point> getSetOfInfertilePoints(){
		
		Set<Point> infertilePoints = new HashSet<>();
		
		for( int i = lowerLeftPoint.getX(); i <= upperRightPoint.getX(); i++){
			for ( int j = lowerLeftPoint.getY(); j <= upperRightPoint.getY(); j++){
				Point pointToAdd = new Point(i,j);
				infertilePoints.add(pointToAdd);
			}
		}
		
		return infertilePoints;
	}
	
	public static Set<Point> getSetOfInfertilePointsForListOfRects(List<Rectangle> inRects){
		
		Set<Point> infertilePoints = new HashSet<>();
		
		for (Rectangle r : inRects){
			Set<Point> rectPoints = r.getSetOfInfertilePoints();
			infertilePoints.addAll(rectPoints);
		}
		return infertilePoints;
	}
	
	public Set<Point> getSetOfIntersectingPoints(Rectangle inRect){
		
		Set<Point> points = new HashSet<>();
		
		points.addAll(findIntersectionOfEdge(lowerLeftPoint, upperLeftPoint, inRect));
		points.addAll(findIntersectionOfEdge(upperLeftPoint, upperRightPoint, inRect));
		points.addAll(findIntersectionOfEdge(upperRightPoint, lowerRightPoint, inRect));
		points.addAll(findIntersectionOfEdge(lowerRightPoint, lowerLeftPoint, inRect));
		
		
		return points;
	}
	
	private List<Point> findIntersectionOfEdge(Point inPoint1, Point inPoint2, Rectangle inRect)
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

	private boolean shouldChangeXValue(Point inPoint1, Point inPoint2)
	{
		if ( inPoint1.getX() != inPoint2.getX()) {
			return true;
		}
		else{
			return false;
		}
	}

	private int getStepValue(Point inPoint1, Point inPoint2, boolean changeXValue)
	{
		int direction;
		direction = calculateDirection(inPoint1, inPoint2, changeXValue);
		return direction > 0 ? 1 : -1;
	}

	private int calculateDirection(Point inPoint1, Point inPoint2, boolean changeXValue)
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

	private int getCordinateToWalk(Point inPoint, boolean inChangeXValue)
	{
		if ( inChangeXValue){
			return inPoint.getX();
		}else{
			return inPoint.getY();
		}
	}

	private void incrementCordinateToWalk(boolean isX, int stepValue, Point pointToCheck)
	{
		int cordinate = getCordinateToWalk(pointToCheck, isX);
		cordinate += stepValue;
		if ( isX){
			pointToCheck.setX(cordinate);
		}else{
			pointToCheck.setY(cordinate);
		}
	}

	private List<Point> returnFirstAndLastValue(List<Point> points)
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

	public Point getLowerLeftPoint()
	{
		return lowerLeftPoint;
	}

	public void setLowerLeftPoint(Point lowerLeftPoint)
	{
		this.lowerLeftPoint = lowerLeftPoint;
	}

	public Point getUpperRightPoint()
	{
		return upperRightPoint;
	}

	public void setUpperRightPoint(Point upperRightPoint)
	{
		this.upperRightPoint = upperRightPoint;
	}
	
	public int calcPointsIncludeEdges(){
		int width = calcWidth();
		int length = calcLength();
		return width * length;
	}

	private int calcLength()
	{
		return ( upperRightPoint.getY() - lowerLeftPoint.getY()) + 1;
	}

	private int calcWidth()
	{
		return  ( upperRightPoint.getX() - lowerLeftPoint.getX() ) + 1;
	}
}
