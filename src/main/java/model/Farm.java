package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Farm
{
	private List<ArrayList<FarmPoint>> parcels;

	private List<Rectangle> inFertileAreas;

	private Set<Point> inFertilePoints;

	int[] rowStep =
	{ 1, 1, 1, 0, -1, -1, -1,  0 };
	int[] colStep =
	{-1, 0, 1, 1,  1,  0, -1, -1 };
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
				if (notCountedAndFertile(farmPoint))
				{
					int plotSize = calculateSizeOfFertilePlot(farmPoint);
					listOfFertilePlots.add(plotSize);
				}
			}
		}
		return listOfFertilePlots;
	}

	private boolean notCountedAndFertile(FarmPoint farmPoint)
	{
		return farmPoint.isFertile() && !farmPoint.isVisited();
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
		return (notCountedAndFertile(point));
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

	public void setInFertileAreas(List<Rectangle> newInfertileAreas)
	{
		this.inFertileAreas = newInfertileAreas;
		if (inFertilePoints == null)
		{
			inFertilePoints = new HashSet<>();
			inFertilePoints = Rectangle.getSetOfInfertilePointsForListOfRects(newInfertileAreas);
			setParcelsToInfertile(inFertilePoints);
		}
		else
		{
			Set<Point> newFertilePoints = getPointsInOldNotInNew(newInfertileAreas);
			setParcelsToFertile(newFertilePoints);
			Set<Point> newInfertilePoints = getPointsInNewNotInOld(newInfertileAreas);
			setParcelsToInfertile(newInfertilePoints);
		}

	}

	private void setParcelsToInfertile(Set<Point> InputinfertilePoints)
	{
		for (Point p : InputinfertilePoints)
		{
			setPointInParcelInfertile(p);
		}

	}

	private void setPointInParcelInfertile(Point p)
	{
		int x = p.getX();
		int y = p.getY();
		FarmPoint farmPoint = parcels.get(x).get(y);
		farmPoint.setFertile(false);
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

	public Set<Point> getInFertilePoints()
	{
		return inFertilePoints;
	}

}
