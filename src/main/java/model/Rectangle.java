package model;

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

	private boolean checkIfInPointXValueIsWithinRectangle(Point inPoint)
	{
		return inPoint.getX() >= lowerLeftPoint.getX() && inPoint.getX() <= upperRightPoint.getX();
	}

	private boolean checkIfInPointYValueIsWithinRectangle(Point inPoint)
	{
		return inPoint.getY() >= lowerLeftPoint.getY() && inPoint.getY() <= upperRightPoint.getY();
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
