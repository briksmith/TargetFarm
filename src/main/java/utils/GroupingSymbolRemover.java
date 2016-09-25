package utils;

public class GroupingSymbolRemover
{
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
}
