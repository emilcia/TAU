package pl.edu.pjatk.tau;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;



public class BudzikTest 
{	
		@Test
	public void shouldRingTest(){

        Time test = Mockito.mock(Time.class);
        
        when(test.getTime()).thenReturn("09:00").thenReturn("12:00");
		Budzik budzik = new BudzikImpl(test);

        
        budzik.addAlarmTime("09:00");
        budzik.addAlarmTime("12:00");


        assertEquals(budzik.shouldRing(), true);
        assertEquals(budzik.shouldRing(), true);
        

	}
}
