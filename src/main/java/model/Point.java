package model;

public class Point
{
	private int x;
	private int y;
	
	public Point(int inX, int inY)
	{
		this.x = inX;
		this.y = inY;
	}

	@Override
	public boolean equals(Object o){
	
		return false;
	}
	
	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}
}