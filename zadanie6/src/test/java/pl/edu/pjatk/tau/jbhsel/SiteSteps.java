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

    @Given("user is on helpdesk page")
    public void userOnHelpdeskPage(){
        pages.helpdesk().open();
    }

    @When("user clicks the login tab")
    public void userClicksTabLink() {
        pages.helpdesk().loginTrue();

    }
    @When("user clicks the login tab with wrong attributes")
    public void clicksTabLink() {
        pages.helpdesk().loginFalse();
    }

    @Then("reload page to check session")
    public void reloadPage() {
        pages.helpdesk().open();
    	assertEquals(pages.helpdesk().session(), true);
    }
    @Then("login failed")
    public void loginFailed() {
        pages.helpdesk().open();
    	assertEquals(pages.helpdesk().loginFailed(), true);
    }

}
