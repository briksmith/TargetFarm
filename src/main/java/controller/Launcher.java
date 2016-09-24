package controller;

import java.io.Console;

public class Launcher 
{
    public static void main( String[] args )
    {
        Launcher l = new Launcher();
        l.findFertileArea();
    }

	private void findFertileArea()
	{
		readBarrenLandCordinates();
		printContiguousFertileArea();
		
	}

	private void readBarrenLandCordinates()
	{
		Console console = System.console();
		String input = console.readLine();
		
	}

	private void printContiguousFertileArea()
	{
		// TODO Auto-generated method stub
		
	}
}
