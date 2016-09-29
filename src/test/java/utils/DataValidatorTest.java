package utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DataValidatorTest
{
	@Test
    public void testDataValidationWhenCorrect(){
    	
    	assertTrue( DataValidator.ValidateSummation(Consts.TOTAL_FARM_AREA, 30, 239970));
    }
    
    @Test
    public void testDataValidationWhenInCorrect(){
    	
    	
    	assertFalse(DataValidator.ValidateSummation(Consts.TOTAL_FARM_AREA, 40, 239970));
    }
}
