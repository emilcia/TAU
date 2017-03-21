package pl.edu.pjatk.tau;

import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.easymock.TestSubject;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;

public class BudzikTest 
{
	@Rule
	public EasyMockRule rule = new EasyMockRule(this);
	
	@Mock
	Time time;
	
	@TestSubject
    private Budzik budzik = new BudzikImpl();
	
	@Test
	public void shouldRingTest(){
     
        budzik.addAlarmTime("09:00");
        replay(time);

		expect(time.getTime()).
		andReturn("09:00").times(4).
		andReturn("10:00");
		
        assertEquals(budzik.shouldRing(), true);
        assertEquals(budzik.shouldRing(), false);        
                
        verify(time);

	}
	
	
	@Test
	public void addAlarmTimeTest(){
	
        assertEquals(budzik.shouldRing(), false);       
		budzik.addAlarmTime("07:00");		
        assertEquals(budzik.shouldRing(), true);

	}
	
		
}
