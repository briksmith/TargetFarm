package controller;

import java.io.Console;
import java.util.List;


import model.Rectangle;


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
		List<Rectangle> rects = RectangleListCreator.createListOfRectangles(input);
		for ( Rectangle r : rects){
			System.out.println(r.toString());
		}
	}

	private void printContiguousFertileArea()
	{
		// TODO Auto-generated method stub
		
	}
}
