package controller;

import java.io.Console;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import model.Farm;
import model.Rectangle;
import utils.Consts;


public class Launcher 
{
	private Farm farm;
	
    public static void main( String[] args )
    {
        Launcher l = new Launcher();
        l.findFertileArea();
    }

	private void findFertileArea()
	{
		readBarrenLandCordinates();
		printContiguousFertileArea();
		
	}

	private void readBarrenLandCordinates()
	{
		Console console = System.console();
		String input = console.readLine();
		List<Rectangle> rects = RectangleListCreator.createListOfRectangles(input);
		BuildFarm(rects);
	}

	private void BuildFarm(List<Rectangle> rects)
	{
		farm = new Farm(Consts.X_DIR, Consts.Y_DIR);
		farm.setInFertileAreas(rects);
		
	}

	private void printContiguousFertileArea()
	{
		List<Integer> infertileArea = farm.calculateFertileAreas();
		SortInfertileArea(infertileArea);
		printSortedAreas(infertileArea);
		
	}

	private void printSortedAreas(List<Integer> infertileArea)
	{
		for ( Integer i : infertileArea){
			System.out.println(i);
		}
		
	}

	private void SortInfertileArea(List<Integer> infertileArea)
	{
		Collections.sort(infertileArea);
	}
}
