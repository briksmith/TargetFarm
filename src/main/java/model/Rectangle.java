package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Rectangle
{
	private Point lowerLeftPoint;
	private Point upperRightPoint;

	public Rectangle(Point inLowerLeftPoint, Point inUpperRightPoint)
	{

		this.lowerLeftPoint = DefensiveCopyOfPoint(inLowerLeftPoint);
		this.upperRightPoint = DefensiveCopyOfPoint(inUpperRightPoint);
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
		
		return null;
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
}
