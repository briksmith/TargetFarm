package utils;

public class DataValidator
{
	public static boolean ValidateSummation(int inTotal, int...subTotals){
		int checkTotal = 0;
		for ( int i : subTotals){
			checkTotal += i;
		}
		return checkTotal == inTotal;
	}
}
