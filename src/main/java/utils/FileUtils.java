package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Point;

public class FileUtils
{
	public static void writePointsToFile(List<Point> points) 
	{
		File f = new File("test points");
		FileWriter writer = null;
		Map<Integer, Integer> occurances = countOccurances(points);
		try{
		Collections.sort( points);
		if ( f.exists()){
			f.delete();
		}
		f.createNewFile();
		writer = new FileWriter(f);
		int index = 0;
		for (int i : occurances.keySet()){
			writer.write(index +": " + occurances.get(i) + "\n");
			index++;
		}
		for ( Point p : points){
			writer.write(p.toString() +"\n");
		}
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		finally{
			try
			{
				if ( writer != null ){
				writer.flush();
				}
			}
			catch (IOException e)
			{
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	private static Map<Integer, Integer> countOccurances(List<Point> points)
	{
		Map<Integer,Integer> occurances = new HashMap<>();
		for ( int i = 0 ; i < points.size(); i++ ){
			occurances.put(points.get(i).getX(),0);
		}
		for (Point p : points){
			int x = p.getX();
			occurances.put(x, occurances.get(x) + 1 );
		}
		return occurances;
	}
}
