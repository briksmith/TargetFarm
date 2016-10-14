package controller;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import model.Farm;
import model.Point;
import model.RectangleIntersectionFinder;

public class ConnectDotsPlotFinder implements InfertilePlotsFinder
{

	@Override
	public List<Integer> getListOfFertilePlots(Farm inFarm)
	{
		TreeSet<Point> pointsToConnectDotsFrom = RectangleIntersectionFinder.findAllIntersectionsWithAxisAndRectangles(inFarm);
		Set<Point> rectangleToDraw;
		for( Point p : pointsToConnectDotsFrom){
			
		}
		return null;
	}

}
