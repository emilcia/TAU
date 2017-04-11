package pl.edu.pjatk.tau.jbhsel.pages;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * Created by tp on 03.04.17.
 */
public class HelpDesk extends WebDriverPage {

    public HelpDesk(WebDriverProvider driverProvider) {
        super(driverProvider);
    }

    public void open() {
        get("http://szuflandia.pjwstk.edu.pl/~s12594/logowanie/logowanie.html");
        manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    }

    public void loginTrue() {
    	WebElement e;
		e = findElement(By.id("login"));
		e.sendKeys("eminka");
		e = findElement(By.id("pswrd"));
		e.sendKeys("admin");
        e = findElement(By.className("login"));
        e.click();
    }
    public void loginFalse() {
    	WebElement e;
		e = findElement(By.id("login"));
		e.sendKeys("cos");
		e = findElement(By.id("pswrd"));
		e.sendKeys("tam");
        e = findElement(By.className("login"));
        e.click();
    }
    public boolean loginSuccess(){
    	WebElement e = findElement(By.className("logout"));
    		if(!e.getAttribute("style").contains("display: none")){
    	        e = findElement(By.className("logout"));
    	    	e.click();
    			return true;
    		}
    		else
    			return false;
    }
    public boolean loginFailed(){
    	WebElement e = findElement(By.className("login"));
    	if(!e.getAttribute("style").contains("display: none")){
    		return true;
    	}
    	else
    		return false;
    }

}
