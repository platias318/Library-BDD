package hellocucumber;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import static org.mockito.BDDMockito.*;

public class borrowerHandlingStepDef {

    @Before
    public void setup(){

    }
    @Given("{string} wants to register to become a borrower")
    public void wants_to_register_to_become_a_borrower(String string) {
        String[] fullname = string.split(" ");
    }

    @Given("{string} hasn't already registered")
    public void hasn_t_already_registered(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("{string} gets registered in the system with a unique borrowerΝο and his details")
    public void gets_registered_in_the_system_with_a_unique_borrowerΝο_and_his_details(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the system stores the borrower's details successfully")
    public void the_system_stores_the_borrower_s_details_successfully() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Given("{string} has already registered")
    public void has_already_registered(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the system informs that the user already exists")
    public void the_system_informs_that_the_user_already_exists() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Given("{string} is a borrower")
    public void is_a_borrower(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Given("{string} wants to update his borrowing details")
    public void wants_to_update_his_borrowing_details(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("the borrower's details are updated")
    public void the_borrower_s_details_are_updated() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the system saves the changes")
    public void the_system_saves_the_changes() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Given("{string} isn't a borrower")
    public void isn_t_a_borrower(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the system displays an error message indicating that the borrower does not exist")
    public void the_system_displays_an_error_message_indicating_that_the_borrower_does_not_exist() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Given("{string} wants to delete his account")
    public void wants_to_delete_his_account(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("the deletion of {string}'s account is attempted")
    public void the_deletion_of_s_account_is_attempted(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the system removes the borrower's details")
    public void the_system_removes_the_borrower_s_details() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Given("{string} has pending items")
    public void has_pending_items(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the system doesn{string}s details")
    public void the_system_doesn_s_details(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the system informs about the non returned items")
    public void the_system_informs_about_the_non_returned_items() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }
}
