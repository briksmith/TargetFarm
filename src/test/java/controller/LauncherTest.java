package controller;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;


/**
 * Unit test for simple App.
 */
public class LauncherTest 
    
{
	
	Launcher systemUnderTest;
	
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
