package hellocucumber;

import hellocucumber.domain.Borrower;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
public class delayNotificationStepDefinitions {
    @Given("the system calendar")
    public void the_system_calendar() {
    }
    @Given("the university's email server")
    public void the_university_s_email_server() {
    }
    @Given("the trigger is every Monday at {int}:{int} a.m.")
    public void the_trigger_is_every_monday_at_a_m(Integer hour, Integer minute) {
    }
    @Given("{string}'s return date has passed")
    public void s_return_date_has_passed(String itemName) {
    }
    @Given("{borrower} had borrowed the item")
    public void george_red_had_borrowed_the_item(Borrower borrower) {
    }
    @When("the system searches for the item {string}")
    public void the_system_searches_for_the_item(String itemName) {
    }
    @When("the system identifies that George Red has borrowed the item")
    public void the_system_identifies_that_george_red_has_borrowed_the_item() {
    }
    @Then("the system sends an email to George Red with the delayed item and the number of delay days")
    public void the_system_sends_an_email_to_george_red_with_the_delayed_item_and_the_number_of_delay_days() {
    }

    @Given("George Red does not have an email address")
    public void george_red_does_not_have_an_email_address() {
    }

    @Then("the system ignores the borrower and George Red isn't notified")
    public void the_system_ignores_the_borrower_and_george_red_isn_t_notified() {
    }
    @When("the Email Server is not working")
    public void the_email_server_is_not_working() {
    }
    @Then("the use case ends")
    public void the_use_case_ends() {
    }

}
