package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Point;
import model.Rectangle;
import utils.Consts;
import utils.InputScanner;

public class RectangleListCreator
{
	private static final int NUM_COORDINATES = 4;

	public static List<Rectangle> createListOfRectangles(String inString)
	{

		List<String> rawStrings = new ArrayList<>();
		List<List<Integer>> rawNumbers = new ArrayList<>();
		List<Rectangle> rectangles = new ArrayList<>();
		try
		{
			inString = InputScanner.removeGroupingSymbol(inString, Consts.BRACES);
			inString = InputScanner.removeGroupingSymbol(inString, Consts.QUOTES);
			InputScanner.seperateStringByCommasAndAddToList(inString, rawStrings);
			translateRawStringNumbersToListOfRawIntegerLists(rawStrings, rawNumbers);
			translateRawIntegersToRectangles(rawNumbers, rectangles);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return Collections.emptyList();
		}
		return rectangles;
	}

	private static void translateRawStringNumbersToListOfRawIntegerLists(List<String> rawStrings,
			List<List<Integer>> rawNumbers) throws Exception
	{
		List<Integer> list = new ArrayList<>();
		for (String b : rawStrings)
		{
			list = parseStringToRawPoints(b);
			rawNumbers.add(list);
			list = new ArrayList<>();
			;
		}
	}

	private static List<Integer> parseStringToRawPoints(String b)
	{
		String[] stringNumbers = parseToStringNumbers(b);
		List<Integer> intNumbers = new ArrayList<>();
		translateStringNumbersToIntegers(stringNumbers, intNumbers);
		return intNumbers;
	}

	private static String[] parseToStringNumbers(String inString)
	{
		String[] numbers = inString.split(" ");
		return numbers;
	}

	private static void translateStringNumbersToIntegers(String[] stringNumbers, List<Integer> intNumbers)
	{
		for (String numberString : stringNumbers)
		{
			int i = Integer.parseInt(numberString);
			intNumbers.add(i);
		}
	}

	private static void translateRawIntegersToRectangles(List<List<Integer>> rawNumbers, List<Rectangle> rectangles) throws Exception
	{
		for ( List<Integer> ints : rawNumbers){
			if ( invalidSize(ints) ) {
				String nums = generateStringOfInputNumbers(ints);
				throw new Exception("Each element in list must be " + NUM_COORDINATES + ".  Was: " + ints.size() + " actual numbers: "
					+	nums);
			}
			Point lowerLeft = new Point(ints.get(0), ints.get(1));
			Point upperRight = new Point(ints.get(2), ints.get(3));
			Rectangle rect = new Rectangle(lowerLeft, upperRight);
			rectangles.add(rect);
		}
	}

	private static String generateStringOfInputNumbers(List<Integer> rawNumbers)
	{
		String toReturn = "";
		for (Integer i : rawNumbers)
		{
			toReturn = toReturn.concat(" " + i.toString());
		}
		return toReturn;
	}

	private static boolean invalidSize(List<Integer> ints)
	{
		return ints.size() != NUM_COORDINATES;
	}
}
