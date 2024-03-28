package hellocucumber;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import hellocucumber.dao.BorrowerDAO;
import hellocucumber.memorydao.BorrowerDAOMemory;
import org.mockito.Mock;

import static org.mockito.BDDMockito.*;
public class itemLoaningStepDef {
    @Mock
    private BorrowerDAO borrowerDAO;
    @Before
    public void setup(){
        borrowerDAO = new BorrowerDAOMemory();
    }
    @Given("the library has the item {string} available")
    public void the_library_has_the_item_available(String string) {
        //
    }

    @Given("{string} has less pending items to return than the maximum lending limit he was assigned")
    public void has_less_pending_items_to_return_than_the_maximum_lending_limit_he_was_assigned(String string) {
        //
    }

    @When("{string} borrows the item {string}")
    public void borrows_the_item(String string, String string2) {
        //
    }

    @Then("the system successfully loans the item {string} to {string} with a return date set")
    public void the_system_successfully_loans_the_item_to_with_a_return_date_set(String string, String string2) {
        //
    }

    @Given("the library has the items {string} and {string} available")
    public void the_library_has_the_items_and_available(String string, String string2) {
        //
    }

    @When("{string} borrows the items {string} and {string}")
    public void borrows_the_items_and(String string, String string2, String string3) {
        //
    }

    @Then("the system successfully loans both items to {string} with return dates set for each")
    public void the_system_successfully_loans_both_items_to_with_return_dates_set_for_each(String string) {
        //
    }

    @Given("{string} has one pending item away from reaching the maximum lending limit he was assigned")
    public void has_one_pending_item_away_from_reaching_the_maximum_lending_limit_he_was_assigned(String string) {
        //
    }

    @When("{string} tries to borrow both items")
    public void tries_to_borrow_both_items(String string) {
        //
    }

    @Then("the system does not loan {string} to {string} due to the lending limit reached")
    public void the_system_does_not_loan_to_due_to_the_lending_limit_reached(String string, String string2) {
        //
    }

    @Given("the item {string} is in the library but marked as {string}")
    public void the_item_is_in_the_library_but_marked_as(String string, String string2) {
        //
    }

    @When("{string} tries to borrow the item {string}")
    public void tries_to_borrow_the_item(String string, String string2) {
        //
    }

    @Then("the system returns an error due to the item's status")
    public void the_system_returns_an_error_due_to_the_item_s_status() {
        //
    }

    @Then("the system withdraws the item {string}")
    public void the_system_withdraws_the_item(String string) {
        //
    }

    @Given("{string}'s pending items have reached the maximum lending limit he was assigned")
    public void s_pending_items_have_reached_the_maximum_lending_limit_he_was_assigned(String string) {
        //
    }

    @Then("the system doesn't allow the loan and displays an error message")
    public void the_system_doesn_t_allow_the_loan_and_displays_an_error_message() {
        //
    }

}
