package controller;

import java.util.TreeSet;

import model.Farm;
import model.Point;

public class FindPointsToDraw
{
	
	private FindPointsToDraw(){}
	
	public static Point findHigherPoint(Point inPoint,  TreeSet<Point> inPoints, Farm inFarm){
		return new Point(0,0);
	}
	
	public static Point findRightPoint(Point inPoint, TreeSet<Point> inPoints) {
		return null;
	}
	
	public static Point findDownPoint(Point inPoint, TreeSet<Point> inPoints) {
		return null;
	}
	
	public static Point findLeftPoint(Point inPoint, TreeSet<Point> inPoints){
		return null;
	}
	
	private static void addPointToSetIfNotInSet(Point inPoint, TreeSet<Point> inPoints){
		
	}
}
