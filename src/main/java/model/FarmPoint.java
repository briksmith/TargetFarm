package model;

import utils.Consts;

public class FarmPoint
{
	private Point point;
	
	private boolean fertile;
	
	private int parcel;
	
	public FarmPoint(Point inPoint){
		this.point = inPoint;
		fertile = true;
		parcel = Consts.UNASSIGNED_PARCEL_INDEX;
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

	public int getParcel()
	{
		return parcel;
	}

	public void setParcel(int parcel)
	{
		this.parcel = parcel;
	}
}
