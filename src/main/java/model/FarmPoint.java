package model;

public class FarmPoint
{
	private Point point;
	
	private boolean fertile;
	
	private boolean visited;
	
	public FarmPoint(Point inPoint){
		this.point = inPoint;
		fertile = true;
		visited = false;
	}

	public boolean notCountedAndFertile()
	{
		return isFertile() && !isVisited();
	}
	
	public Point getPoint()
	{
		return point;
	}

	public void setPoint(Point point)
	{
		this.point = point;
	}

	public boolean isFertile()
	{
		return fertile;
	}

	public void setFertile(boolean fertile)
	{
		this.fertile = fertile;
	}

	public boolean isVisited()
	{
		return visited;
	}

	public void setVisited(boolean visited)
	{
		this.visited = visited;
	}
}
