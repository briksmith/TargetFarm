package utils;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputScanner
{
	private static final char[] QUOTES = { '"', '"' };
	 
	public static void removeGroupingSymbol(StringBuilder inStringBuilder, char[] symbols) throws Exception
	{
		if (stringNotEnclosedInEndingSymbols(inStringBuilder, symbols))
		{
			throw new Exception(
					"String " + inStringBuilder.toString() + " must be enclosed in " + symbols[0] + " " + symbols[1]);
		}
		inStringBuilder.delete(0, 1);
		inStringBuilder.delete(inStringBuilder.length() - 1, inStringBuilder.length());
	}

	private static boolean stringNotEnclosedInEndingSymbols(StringBuilder inStringBuilder, char[] symbol)
	{
		inStringBuilder = removeWhiteSpace(inStringBuilder);
		if (inStringBuilder.charAt(0) != symbol[0] || inStringBuilder.charAt(inStringBuilder.length() - 1) != symbol[1])
		{
			return true;
		}
		return false;
	}
	
	private static StringBuilder removeWhiteSpace(StringBuilder inStringBuilder)
	{
		String s = inStringBuilder.toString();
		return new StringBuilder(s.trim());
		
	}
	
	public static void seperateStringByCommasAndAddToList(StringBuilder stringToParse, List<StringBuilder> rawStrings) throws Exception
	{
	
		String stringFromBuilder = stringToParse.toString();
		String[] numbersInQuotes = stringFromBuilder.split(",");
		StringBuilder builder = new StringBuilder();
		String delimiterPattern = "\\d";
		
		Scanner scanNumbers = null;
		for (String number : numbersInQuotes)
		{
			number = number.replace("\"", "");
			number = number.trim();
			System.out.println(number+"end");
			builder.append(number);
			scanNumbers = new Scanner(number);
			while ( scanNumbers.hasNext()){
				System.out.println(scanNumbers.next()+"End");
			}
			System.out.println(number+"end");
			
			rawStrings.add(builder);
			builder = new StringBuilder();
		}
		closeScanner(scanNumbers);
	
	}

	private static void closeScanner(Scanner scanNumbers)
	{
		if( scanNumbers != null){
			scanNumbers.close();
		}
	}
}
