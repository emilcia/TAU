package pl.edu.pjatk.tau;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * To jest ta najważniejsza klasa do testów behawioralnych która implementuje opowieść (story)
 *
 * Zobacz, że ta jedna klasa może pasować do wielu opowieści.
 */


public class BudzikSteps {
    private Budzik budzik;
    Time time;
    
    @Given("nadchodzi godzina $czas")
    public void actualTime(String godzina){
        time = mock(Time.class);
        budzik = new BudzikImpl(time);
        given(time.getTime()).willReturn(godzina);
    }

    @When("na budziku byla ustawiona godzina $godzina")
    public void addAlarm(String czas) {
    	budzik.addAlarmTime(czas);
    }

    @Then("budzik powinien zadzwonic o godzinie $wynik")
    public void shouldRing(Boolean result) {
    	assertEquals(budzik.shouldRing(), result);
    }

}
