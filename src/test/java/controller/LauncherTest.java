package controller;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import model.Farm;
import model.Point;


/**
 * Unit test for simple App.
 */
public class LauncherTest 
    
{
	
	Launcher systemUnderTest;
	@Mock
	Farm farm;
	
	@Mock
	Set<Point> mockSet;
	
	@Before
	public void setUp() {
		systemUnderTest = new Launcher();
		MockitoAnnotations.initMocks(this);
	}
	
    @Test
    public void testCalcTotalFertileAreas(){
    	
    	List<Integer> ints = new ArrayList<>();
    	
    	ints.add(12);
    	ints.add(18);
    	
    	assertTrue("SHould have gotten 30.", systemUnderTest.totalFertileAreas(ints) ==30);
    }
    
    
}
