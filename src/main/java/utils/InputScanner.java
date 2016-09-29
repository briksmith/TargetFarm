package utils;

import java.util.List;

public class InputScanner
{
	private static final char[] QUOTES = { '"', '"' };
	 
	public static String removeGroupingSymbol(String inString, char[] symbols) throws Exception
	{
		if (stringNotEnclosedInEndingSymbols(inString, symbols))
		{
			throw new Exception(
					"String " + inString.toString() + " must be enclosed in " + symbols[0] + " " + symbols[1]);
		}
		for ( char c : symbols){
			inString = inString.replace(c, ' ');
		}
		return inString.trim();
	}

	private static boolean stringNotEnclosedInEndingSymbols(String inString, char[] symbol)
	{
		inString = removeWhiteSpace(inString);
		if (inString.charAt(0) != symbol[0] || inString.charAt(inString.length() - 1) != symbol[1])
		{
			return true;
		}
		return false;
	}
	
	private static String removeWhiteSpace(String inString)
	{
		return inString.trim();
		
	}
	
	public static void seperateStringByCommasAndAddToList(String stringToParse, List<String> rawStrings) throws Exception
	{
	
		removeGroupingSymbol(stringToParse, QUOTES);
		String[] numbersInQuotes = stringToParse.split(",");
		StringBuilder builder = new StringBuilder();
		for (String number : numbersInQuotes)
		{
			number = number.replace("\"", "");
			number = number.trim();
			builder.append(number);
			rawStrings.add(builder.toString());
			builder = new StringBuilder();
		}
	
	}
}
