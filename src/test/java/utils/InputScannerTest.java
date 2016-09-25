package utils;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class InputScannerTest
{
	private static final String TEST_SEPERATE_BY_COMMAS_LIST = "   \"45 98 230 100\", \"43 98 50 100\", \"99 2 111 12\"";
	private static final String EXPECTED_1= "45 98 230 100";
	private static final String EXPECTED_2 = "43 98 50 100";
	private static final String EXPECTED_3 = "99 2 111 12";

	@Test
	public void removeGroupingSymbol() throws Exception
	{
		char[] groupingSymbols =
		{ '[', ']' };
		StringBuilder testString = new StringBuilder("[sometext]");
		String expected = "sometext";

		InputScanner.removeGroupingSymbol(testString, groupingSymbols);

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
			InputScanner.removeGroupingSymbol(testString, groupingSymbols);
		}
		catch (Exception e)
		{
			exceptionThrown = true;
		}
		assertTrue("Mismatched grouping symbol should throw an excpetion", exceptionThrown);
	}
	
	@Test
	public void testSeperateStringByCommasAndAddToList() throws Exception {
		
		List<StringBuilder> actualList = new ArrayList<>();
		List<StringBuilder> expectedList = new ArrayList<>();
		expectedList.add(new StringBuilder(EXPECTED_1));
		expectedList.add(new StringBuilder(EXPECTED_2));
		expectedList.add(new StringBuilder(EXPECTED_3));
		StringBuilder input = new StringBuilder(TEST_SEPERATE_BY_COMMAS_LIST);
		
		InputScanner.seperateStringByCommasAndAddToList(input, actualList);
		for (int i = 0; i < actualList.size(); i++){
			StringBuilder expected = expectedList.get(i);
			String expectedString = expected.toString();
			StringBuilder actual = actualList.get(i);
			String actualString = actual.toString();
			assertTrue("Expected:" + expected.toString() + "End Actual:" + actual.toString() +"End", expectedString.equals(actualString));
		}
		
	}
}
