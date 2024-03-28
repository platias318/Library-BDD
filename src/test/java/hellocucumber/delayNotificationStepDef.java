package hellocucumber;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import hellocucumber.dao.BorrowerDAO;
import hellocucumber.memorydao.BorrowerDAOMemory;
import org.mockito.Mock;
import static org.mockito.BDDMockito.*;
public class delayNotificationStepDef {
    @Mock
    private BorrowerDAO borrowerDAO;
    @Before
    public void setup(){
        borrowerDAO = new BorrowerDAOMemory();
    }
    @Given("the system calendar")
    public void the_system_calendar() {
        //dimiourgw ena antikeimeno calendar
    }

    @Given("the university's email server")
    public void the_university_s_email_server() {
        //dimiourgw ena antikeimeno email server
    }

    @Given("the trigger is every Monday at {int}:{int} a.m.")
    public void the_trigger_is_every_Monday_at_a_m(Integer int1, Integer int2) {
        // kanw me kapoio mock /stub na ginei deutera to trigger?
    }

    @Given("{string}'s return date has passed")
    public void s_return_date_has_passed(String string) {
        //na dimiourgisw ena vivlio  pou h hmeromhnia epistrofis exei perasei
    }

    @Given("{string} had borrowed the item")
    public void had_borrowed_the_item(String string) {
        //na valw enan xristi na exei daneistei to parapanw vivlio pou anaferthike
    }

    @When("the system searches for {string}")
    public void the_system_searches_for(String string) {
        //
    }

    @When("the system identifies that {string} has borrowed the item")
    public void the_system_identifies_that_has_borrowed_the_item(String string) {
        //
    }

    @Then("the system sends an email to {string} with the delayed item and the number of delay days")
    public void the_system_sends_an_email_to_with_the_delayed_item_and_the_number_of_delay_days(String string) {
        //
    }

    @Given("{string} does not have an email address")
    public void does_not_have_an_email_address(String string) {
        //
    }

    @Then("the system ignores the borrower and {string} isn't notified")
    public void the_system_ignores_the_borrower_and_isn_t_notified(String string) {
        //
    }

    @When("the Email Server is not working")
    public void the_Email_Server_is_not_working() {
        //
    }

    @Then("the use case ends")
    public void the_use_case_ends() {
        //
    }
}
