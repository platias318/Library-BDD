package hellocucumber;

import hellocucumber.domain.Borrower;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
public class ιtemLoaningStepDefinitions {
    @Given("the library has the item {string} available")
    public void the_library_has_the_item_available(String itemName) {
    }
    @Given("{borrower} is a registered borrower")
    public void george_red_is_a_registered_borrower(Borrower borrower) {
    }
    @Given("George Red has {int} pending items to be returned")
    public void george_red_has_pending_items_to_be_returned(Integer pendingItems) {
    }
    @Given("George Red has been assigned a maximum lending limit of {int}")
    public void george_red_has_been_assigned_a_maximum_lending_limit_of(Integer maxLendingLimit) {
    }
    @When("George Red borrows the item {string}")
    public void george_red_borrows_the_item(String itemName) {
    }
    @Then("the system successfully loans the item {string} to George Red with a return date set")
    public void the_system_successfully_loans_the_item_to_george_red_with_a_return_date_set(String itemName) {
    }
    @Then("George Red's pending items increase to {int}")
    public void george_red_s_pending_items_increase_to(Integer pendingItems) {
    }
    @Given("the library has the items {string} and {string} available")
    public void the_library_has_the_items_and_available(String itemName1, String itemName2) {
    }

    @When("George Red tries to borrow both items")
    public void george_red_tries_to_borrow_both_items() {
    }

    @Then("the system does not loan {string} to George Red due to the lending limit reached")
    public void the_system_does_not_loan_to_due_to_the_lending_limit_reached(String string) {
    }

    @Given("the item {string} is in the library but marked as {string}")
    public void the_item_is_in_the_library_but_marked_as(String itemName, String itemState) {
    }

    @When("George Red tries to borrow the item {string}")
    public void george_red_tries_to_borrow_the_item(String string) {
    }
    @Then("the system returns an error due to the item's status")
    public void the_system_returns_an_error_due_to_the_item_s_status() {
    }
    @Then("the system withdraws the item {string}")
    public void the_system_withdraws_the_item(String itemName) {
    }

    @Then("the system doesn't allow the loan")
    public void the_system_doesn_t_allow_the_loan() {
    }
    @Then("George Red's pending items remain {int}")
    public void george_red_s_pending_items_remain(Integer pendingItems) {
    }


}
