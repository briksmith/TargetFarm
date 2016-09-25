package utils;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GroupingSymbolRemoverTest
{
	@Test
	public void removeGroupingSymbol() throws Exception
	{
		char[] groupingSymbols =
		{ '[', ']' };
		StringBuilder testString = new StringBuilder("[sometext]");
		String expected = "sometext";

		GroupingSymbolRemover.removeGroupingSymbol(testString, groupingSymbols);

		assertTrue("testString should have the square brackets stripped off and does not.  testString: " + testString
				+ " expected: " + expected, expected.equals(testString.toString()));
	}

	@Test
	public void nonMatchingGroupingSymbol() throws Exception
	{
		char[] groupingSymbols =
		{ '[', ']' };
		StringBuilder testString = new StringBuilder("sometext]");
		boolean exceptionThrown = false;
		try
		{
			GroupingSymbolRemover.removeGroupingSymbol(testString, groupingSymbols);
		}
		catch (Exception e)
		{
			exceptionThrown = true;
		}
		assertTrue("Mismatched grouping symbol should throw and excpetion", exceptionThrown);
	}
}
