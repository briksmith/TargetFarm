package controller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import model.Farm;
import model.FarmPoint;
import model.Point;

public class WalkInfertilePlots implements InfertilePlotsFinder
{

	private List<ArrayList<FarmPoint>> parcels;
	
	private final int[] rowStep =
		{ 1, 1, 1, 0, -1, -1, -1, 0 };
	private final int[] colStep =
		{ -1, 0, 1, 1, 1, 0, -1, -1 };
	
	private int rowCount;
	private int colCount;
	
	@Override
	public List<Integer> getListOfFertilePlots(Farm inFarm)
	{
		initMembers(inFarm);
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

	private void initMembers(Farm inFarm)
	{
		parcels = inFarm.getParcels();
		rowCount = inFarm.getRowCount();
		colCount = inFarm.getColCount();
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
	
}
