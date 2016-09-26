package model;

import java.util.ArrayList;
import java.util.List;

public class Farm
{
	private List<ArrayList<FarmPoint>> parcels;
	
	private List<Rectangle> inFertileAreas;
	
	public  Farm(int x, int y) {
		
		parcels = new ArrayList<ArrayList<FarmPoint>>();
		for ( int i = 0; i < x; i++){
			parcels.add(new ArrayList<>());
			List<FarmPoint> pointsToInitalize = parcels.get(i);
			for ( int j = 0; j < y; j++){
				Point point = new Point(i, j);
				FarmPoint farmPoint = new FarmPoint(point);
				pointsToInitalize.add(farmPoint);
			}
		}
	}
	
	public List<Integer> calculateFertileAreas(){
		
		return null;
	}

	public List<ArrayList<FarmPoint>> getParcels()
	{
		return parcels;
	}

	public void setParcels(List<ArrayList<FarmPoint>> inParcels)
	{
		this.parcels = inParcels;
	}

	public List<Rectangle> getInFertileAreas()
	{
		return inFertileAreas;
	}

	public void setInFertileAreas(List<Rectangle> inFertileAreas)
	{
		this.inFertileAreas = inFertileAreas;
	}
}
