package controller;

import java.util.List;
import java.util.Set;

import model.Farm;
import model.Point;
import model.RectangleIntersectionFinder;

public class ConnectDotsPlotFinder implements InfertilePlotsFinder
{

	@Override
	public List<Integer> getListOfFertilePlots(Farm inFarm)
	{
		Set<Point> pointsToConnectDotsFrom = RectangleIntersectionFinder.findAllIntersectionsWithAxisAndRectangles(inFarm);
		return null;
	}

}
