package hellocucumber;

import hellocucumber.domain.Borrower;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
public class borrowerHandlingStepDefinitions {
    @Given("{borrower} is not registered as a borrower")
    public void george_red_is_not_registered_as_a_borrower(Borrower borrower) {
    }
    @When("George Red gets registered in the system with a unique borrower number and his details")
    public void george_red_gets_registered_in_the_system_with_a_unique_borrower_number_and_his_details() {
    }
    @Then("the system successfully stores the borrower's details")
    public void the_system_successfully_stores_the_borrower_s_details() {
    }
    @When("the system attempts to register George Red with a unique borrower number and his details")
    public void the_system_attempts_to_register_george_red_with_a_unique_borrower_number_and_his_details() {
    }
    @Then("the system informs that the user already exists")
    public void the_system_informs_that_the_user_already_exists() {
    }
    @Given("{borrower} is registered as a borrower")
    public void george_red_is_registered_as_a_borrower() {
    }
    @When("George Red updates his borrowing details")
    public void george_red_updates_his_borrowing_details() {
    }
    @Then("the system saves the changes")
    public void the_system_saves_the_changes() {
    }
    @When("George Red tries to update his borrowing details")
    public void george_red_tries_to_update_his_borrowing_details() {
    }
    @Then("the system displays an error message indicating that George Red does not exist")
    public void the_system_displays_an_error_message_indicating_that_george_red_does_not_exist() {
    }
    @When("the system deletes George Red's account")
    public void the_system_deletes_george_red_s_account() {
    }
    @Then("the system removes George Red's details")
    public void the_system_removes_george_red_s_details() {
    }
    @When("the system attempts to delete George Red's account")
    public void the_system_attempts_to_delete_george_red_s_account() {
    }
    @Given("George Red has pending items")
    public void george_red_has_pending_items() {
    }
    @Then("the system does not remove George Red's details")
    public void the_system_does_not_remove_george_red_s_details() {
    }
    @Then("the system informs about the pending items")
    public void the_system_informs_about_the_pending_items() {
    }

}
