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
		String testString = new String("\"sometext\"");
		String expected = "sometext";

		testString = InputScanner.removeGroupingSymbol(testString, Consts.QUOTES);

		assertTrue("testString should have the quotes stripped off and does not.  testString: " + testString
				+ " expected: " + expected, expected.equals(testString.toString()));
	}

	@Test
	public void nonMatchingGroupingSymbol() throws Exception
	{
		String testString = new String("sometext}");
		boolean exceptionThrown = false;
		try
		{
			InputScanner.removeGroupingSymbol(testString, Consts.BRACES);
		}
		catch (Exception e)
		{
			exceptionThrown = true;
		}
		assertTrue("Mismatched grouping symbol should throw an excpetion", exceptionThrown);
	}
	
	@Test
	public void testSeperateStringByCommasAndAddToList() throws Exception {
		
		List<String> actualList = new ArrayList<>();
		List<String> expectedList = new ArrayList<>();
		expectedList.add(EXPECTED_1);
		expectedList.add(EXPECTED_2);
		expectedList.add(EXPECTED_3);
		
		InputScanner.seperateStringByCommasAndAddToList(TEST_SEPERATE_BY_COMMAS_LIST, actualList);
		for (int i = 0; i < actualList.size(); i++){
			String expected = expectedList.get(i);
			String actual = actualList.get(i);
			assertTrue("Expected:" + expected.toString() + "End Actual:" + actual.toString() +"End", expected.equals(actual));
		}
	}
}
