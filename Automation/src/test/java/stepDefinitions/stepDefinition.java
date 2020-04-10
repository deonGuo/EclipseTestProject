package stepDefinitions;

import org.junit.runner.RunWith;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
public class stepDefinition {

    @Given("^User is on NetBanking landing page$")
    public void user_is_on_netbanking_landing_page() throws Throwable {
        System.out.println("Given is executed");
    }
    
    @When("the user does something")
    public void the_user_does_something() {
        // Write code here that turns the phrase above into concrete actions
    	System.out.println("When is executed");
    }

    @Then("the something happens")
    public void the_something_happens() {
        // Write code here that turns the phrase above into concrete actions
    	System.out.println("Then is executed");
    }

}