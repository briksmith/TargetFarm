package model;

public class Point implements Comparable
{
	private int x;
	private int y;
	
	public Point(int inX, int inY)
	{
		this.x = inX;
		this.y = inY;
	}
	
	public Point(Point inPoint) {
		this.x = inPoint.getX();
		this.y = inPoint.getY();
	}

	public boolean equals(Object o){
	
		if( ! ( o instanceof Point ) ){
			return false;
		}
		Point inPoint = (Point) o;
		if ( this.x == inPoint.x && this.y == inPoint.y){
			return true;
		}
		
		return false;
	}
	
	public String toString(){
		return "X: " + x + ", Y: " + y;
	}
	
	public int hashCode() {
		return x * 17 + y * 43;
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

	@Override
	public int compareTo(Object o)
	{
		if ( o instanceof Point){
			Point toComp = (Point) o;
			if (this.equals(toComp) ) {
				return 0;
			}
			if ( this.x > toComp.x) {
				return 1;
			} 
			if ( this.x == toComp.x){
				if ( this.y > toComp.y){
					return 1;
				}
				else{
					return -1;
				}
			}
			return -1;
		}
		return 0;
	}
}
