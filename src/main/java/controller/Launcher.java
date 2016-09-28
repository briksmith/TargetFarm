package controller;

import java.io.Console;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import model.Farm;
import model.Point;
import model.Rectangle;
import utils.Consts;
import utils.DataValidator;

public class Launcher
{
	private Farm farm;

	public static void main(String[] args)
	{
		Launcher l = new Launcher();
		printInstructions();
		l.findFertileArea();
	}

	private static void printInstructions()
	{
		System.out.println("Input strings in following format:");
		System.out.println("A list in braces, each element in quotes, each element sperated by a comma.");
		System.out.println(
				"Each element is two ordered pairs of coordinates consisting of the x,y values of the lower left");
		System.out.println("And the upper right.");
		System.out.println("Enter Q\\q to quit.");
		System.out.println("Example:  {\"12 23 45 30\" , \"1 1 34 44\"}");

	}

	private void findFertileArea()
	{
		do
		{
			if (readBarrenLandCordinates())
			{
				printContiguousFertileArea();
			}
			else
			{
				break;
			}
		}
		while (true);

	}

	private boolean readBarrenLandCordinates()
	{
		Console console = System.console();
		String input = console.readLine();
		if (checkIfUserQuit(input))
		{
			return false;
		}
		else
		{
			processUserInput(input);
		}
		return true;
	}

	private void processUserInput(String input)
	{
		List<Rectangle> rects = RectangleListCreator.createListOfRectangles(input);
		BuildFarm(rects);
	}

	private boolean checkIfUserQuit(String input)
	{
		if (input.equals("Q") || input.equals("q"))
		{
			return true;
		}
		return false;
	}

	private void BuildFarm(List<Rectangle> rects)
	{
		farm = new Farm(Consts.X_DIR, Consts.Y_DIR);
		farm.setInFertileAreas(rects);
	}

	private void printContiguousFertileArea()
	{
		List<Integer> fertileArea = farm.getListOfFertilePlots();
		checkAnswer(fertileArea);
		SortInfertileArea(fertileArea);
		printSortedAreas(fertileArea);
	}

	private void checkAnswer(List<Integer> fertileArea)
	{
		int numberOfInfertilePoints = getTotalOfInfertilePoints();
		int totalFertileArea = totalFertileAreas(fertileArea);
		if ( !DataValidator.ValidateSummation(Consts.TOTAL_FARM_AREA, numberOfInfertilePoints, totalFertileArea))
		{
			System.out.println("Warning:  Infertile points + fertile points does not equal total area.");
			System.out.println(Consts.TOTAL_FARM_AREA);
			System.out.println("numberOfInfertilePoints " + numberOfInfertilePoints + " totalFertileArea " + totalFertileArea);
		}

	}

	private int getTotalOfInfertilePoints()
	{
		Set<Point> setOfInfertilePoints = farm.getInFertilePoints();
		int numberOfInfertilePoints = setOfInfertilePoints.size();
		return numberOfInfertilePoints;
	}

	public int totalFertileAreas(List<Integer> fertileArea)
	{
		int totalFertileArea = 0;
		for (Integer i : fertileArea)
		{
			totalFertileArea += i.intValue();
		}
		return totalFertileArea;
	}

	private void SortInfertileArea(List<Integer> infertileArea)
	{
		Collections.sort(infertileArea);
	}

	private void printSortedAreas(List<Integer> infertileArea)
	{
		for (Integer i : infertileArea)
		{
			System.out.println(i);
		}
	}
}
