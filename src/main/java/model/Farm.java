package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import controller.InfertilePlotsFinder;

public class Farm
{
	private List<ArrayList<FarmPoint>> parcels;

	private List<Rectangle> inFertileAreas;
	
	private Set<Point> inFertileAreaCornerPoints;

	private Set<Point> inFertilePoints;
	
	private InfertilePlotsFinder infertilePlotsFinder;

	int[] rowStep =
	{ 1, 1, 1, 0, -1, -1, -1, 0 };
	int[] colStep =
	{ -1, 0, 1, 1, 1, 0, -1, -1 };
	private final int rowCount;
	private final int colCount;

	public Farm(int x, int y)
	{

		rowCount = x;
		colCount = y;
		parcels = new ArrayList<ArrayList<FarmPoint>>();
		for (int i = 0; i < x; i++)
		{
			parcels.add(new ArrayList<>());
			List<FarmPoint> farmPoints = parcels.get(i);
			for (int j = 0; j < y; j++)
			{
				Point point = new Point(i, j);
				FarmPoint farmPoint = new FarmPoint(point);
				farmPoints.add(farmPoint);
			}
		}
	}

	public List<Integer> getListOfFertilePlots()
	{
		List<Integer> listOfFertilePlots = new ArrayList<>();
		for (int i = 0; i < parcels.size(); i++)
		{
			List<FarmPoint> farmPoints = parcels.get(i);
			for (int j = 0; j < farmPoints.size(); j++)
			{
				FarmPoint farmPoint = farmPoints.get(j);
				if (farmPoint.notCountedAndFertile())
				{
					int plotSize = calculateSizeOfFertilePlot(farmPoint);
					listOfFertilePlots.add(plotSize);
				}
			}
		}
		return listOfFertilePlots;
	}

	private int calculateSizeOfFertilePlot(FarmPoint farmPoint)
	{
		int plotSize = 0;
		List<Point> points = new ArrayList<>();
		ArrayDeque<FarmPoint> queue = new ArrayDeque<>();
		queue.add(farmPoint);
		while (!queue.isEmpty())
		{
			plotSize++;
			FarmPoint queuedFarmPoint = queue.pop();
			Point queuedPoint = queuedFarmPoint.getPoint();
			int row = queuedPoint.getX();
			int col = queuedPoint.getY();
			queuedFarmPoint.setVisited(true);
			for (int i = 0; i < rowStep.length; i++)
			{
				int rowNeighbor = getRowNeighbor(row, i);
				int colNeighbor = getColNeighbor(col, i);
				if (addNeighborToPlot(rowNeighbor, colNeighbor))
				{
					points.add(new Point(rowNeighbor, colNeighbor));
					FarmPoint farmPointToQueue = parcels.get(rowNeighbor).get(colNeighbor);
					farmPointToQueue.setVisited(true);
					queue.add(farmPointToQueue);
				}
			}
		}
		return plotSize;
	}

	private int getRowNeighbor(int inRow, int i)
	{
		return inRow + rowStep[i];
	}

	private int getColNeighbor(int inCol, int i)
	{
		return inCol + colStep[i];
	}

	private boolean addNeighborToPlot(int i, int j)
	{
		if (!checkIndexRanges(i, j))
		{
			return false;
		}
		FarmPoint point = parcels.get(i).get(j);
		return (point.notCountedAndFertile());
	}

	private boolean checkIndexRanges(int i, int j)
	{
		return checkRangeAbove(i, j) && checkRangeBelow(i, j);

	}

	private boolean checkRangeAbove(int i, int j)
	{
		return i < rowCount && j < colCount;
	}

	private boolean checkRangeBelow(int i, int j)
	{
		return i >= 0 && j >= 0;
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

	public void setInfertileAreas(List<Rectangle> newInfertileAreas)
	{
		this.inFertileAreas = newInfertileAreas;
		if (inFertilePoints == null)
		{
			inFertilePoints = new HashSet<>();
		}
		inFertilePoints = Rectangle.getSetOfInfertilePointsForListOfRects(newInfertileAreas);
		setParcelsToInfertile(inFertilePoints);
		setCornerSet(newInfertileAreas);

	}

	private void setParcelsToInfertile(Set<Point> InputinfertilePoints)
	{
		for ( List<FarmPoint> farmPoints : parcels){
			for ( FarmPoint farmPoint : farmPoints){
				Point point = farmPoint.getPoint();
				if ( InputinfertilePoints.contains(point) ){
					farmPoint.setFertile(false);
				}
			}
		}
	}
		
	private void setPointInParcelInfertile(Point p)
	{
		int x = p.getX();
		int y = p.getY();
		FarmPoint farmPoint = parcels.get(x).get(y);
		farmPoint.setFertile(false);
	}


	private void setCornerSet(List<Rectangle> newInfertileAreas)
	{
		inFertileAreaCornerPoints = new HashSet<>();
		for ( Rectangle r : newInfertileAreas){
			inFertileAreaCornerPoints.addAll(r.getRectangleCorners());
		}
		
	}

	public Set<Point> getInFertilePoints()
	{
		return inFertilePoints;
	}

	public int getRowCount()
	{
		return rowCount;
	}

	public int getColCount()
	{
		return colCount;
	}

	public Set<Point> getInFertileAreaCornerPoints()
	{
		return inFertileAreaCornerPoints;
	}

}
