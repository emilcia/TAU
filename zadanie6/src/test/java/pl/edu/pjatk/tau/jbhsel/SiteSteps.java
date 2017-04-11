package pl.edu.pjatk.tau.jbhsel;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class SiteSteps {

    private final Pages pages;

    public SiteSteps(Pages pages) {
        this.pages = pages;
    }

    @Given("user is on main page")
    public void userOnHelpdeskPage(){
        pages.helpdesk().open();
    }

    @When("user clicks the login tab with wrong attributes")
    public void clicksTabLink() {
        pages.helpdesk().loginFalse();
    }
    @When("user clicks the login tab")
    public void userClicksTabLink() {
        pages.helpdesk().loginTrue();

    }

    @Then("login failed")
    public void loginFailed() {
    	assertEquals(pages.helpdesk().loginFailed(), true);
    }
    @Then("login success")
    public void loginSuccess() {
    	assertEquals(pages.helpdesk().loginSuccess(), true);
    }
    @Then("reload page to check session")
    public void reloadPage() {
        pages.helpdesk().open();
    	assertEquals(pages.helpdesk().loginSuccess(), true);
    }

}
