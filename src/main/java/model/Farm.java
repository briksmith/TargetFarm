package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Farm
{
	private List<ArrayList<FarmPoint>> parcels;
	
	private List<Rectangle> inFertileAreas;
	
	private Set<Point> inFertilePoints;
	
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

	public void setInFertileAreas(List<Rectangle> newInfertileAreas)
	{
		this.inFertileAreas = newInfertileAreas;
		if ( inFertilePoints == null ){
			inFertilePoints = new HashSet<>();
			Set<Point> infertilePoints =  Rectangle.getSetOfInfertilePointsForListOfRects(newInfertileAreas);
			setParcelsToInfertile(infertilePoints);
		}
		else {
			Set<Point> newFertilePoints = getPointsInOldNotInNew(newInfertileAreas);
			setParcelsToFertile(newFertilePoints);
			Set<Point> newInfertilePoints = getPointsInNewNotInOld(newInfertileAreas);
			setParcelsToInfertile(newInfertilePoints);
		}
		
	}

	private void setParcelsToInfertile(Set<Point> InputinfertilePoints)
	{
		// TODO Auto-generated method stub
		
	}

	private void setParcelsToFertile(Set<Point> newFertilePoints)
	{
		// TODO Auto-generated method stub
		
	}

	private Set<Point> getPointsInOldNotInNew(List<Rectangle> newInfertileAreas)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private Set<Point> getPointsInNewNotInOld(List<Rectangle> newInfertileAreas)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
