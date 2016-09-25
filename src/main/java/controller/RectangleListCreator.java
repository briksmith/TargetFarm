package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import model.Point;
import model.Rectangle;
import utils.GroupingSymbolRemover;

public class RectangleListCreator
{
	private static final int NUM_COORDINATES = 4;
	private static final char[] BRACES =
	{ '{', '}' };
	private static final char[] QUOTES =
	{ '"', '"' };

	public static List<Rectangle> createListOfRectangles(String inString)
	{

		List<StringBuilder> rawStrings = new ArrayList<>();
		List<List<Integer>> rawNumbers = new ArrayList<>();
		List<Rectangle> rectangles = new ArrayList<>();
		System.out.println(inString);
		try
		{
			StringBuilder stringToParse = new StringBuilder(inString);
			GroupingSymbolRemover.removeGroupingSymbol(stringToParse, BRACES);
			seperateStringByCommasAndAddToList(stringToParse, rawStrings);
			translateRawStringNumbersToListOfRawIntegerLists(rawStrings, rawNumbers);
			translateRawIntegersToRectangles(rawNumbers, rectangles);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
			return Collections.emptyList();
		}
		return rectangles;
	}

	
	private static void seperateStringByCommasAndAddToList(StringBuilder stringToParse, List<StringBuilder> rawStrings) throws Exception
	{
	
		String stringFromBuilder = stringToParse.toString();
		String[] numbersInQuotes = stringFromBuilder.split(",");
		StringBuilder builder = new StringBuilder();
		for (String number : numbersInQuotes)
		{
			builder.append(number);
			GroupingSymbolRemover.removeGroupingSymbol(builder, QUOTES);
			StringBuilder b = new StringBuilder(builder.toString());
			rawStrings.add(b);
			builder.delete(0, builder.length());
		}
	
	}

	private static void translateRawStringNumbersToListOfRawIntegerLists(List<StringBuilder> rawStrings,
			List<List<Integer>> rawNumbers) throws Exception
	{
		List<Integer> list = new ArrayList<>();
		for (StringBuilder b : rawStrings)
		{
			list = parseStringToRawPoints(b);
			rawNumbers.add(list);
			list.clear();
		}
	}

	private static List<Integer> parseStringToRawPoints(StringBuilder b)
	{
		String[] stringNumbers = parseToStringNumbers(b);
		List<Integer> intNumbers = new ArrayList<>();
		translateStringNumbersToIntegers(stringNumbers, intNumbers);
		return intNumbers;
	}

	private static String[] parseToStringNumbers(StringBuilder b)
	{
		String s = b.toString();
		String[] numbers = s.split(" ");
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

	private static void translateRawIntegersToRectangles(List<List<Integer>> rawNumbers, List<Rectangle> rectangles)
	{
		for ( List<Integer> ints : rawNumbers){
			if ( invalidSize(ints) ) {
				continue;
			}
			Point lowerLeft = new Point(ints.get(0), ints.get(1));
			Point upperRight = new Point(ints.get(2), ints.get(3));
			Rectangle rect = new Rectangle(lowerLeft, upperRight);
			rectangles.add(rect);
		}
			
		
	}

	private static boolean invalidSize(List<Integer> ints)
	{
		return ints.size() != NUM_COORDINATES;
	}
}
