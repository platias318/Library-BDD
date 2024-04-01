package hellocucumber;

import hellocucumber.domain.Borrower;
import hellocucumber.util.SimpleCalendar;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class itemReturnStepDefinitions {
    @Given("{borrower} has borrowed the item {string} with a return date of {simpleCalendar}")
    public void georgeRed_has_borrowed_the_item_with_a_return_date_of(Borrower borrower, String itemTitle, SimpleCalendar date) {
    }
    @Given("today's date is {simpleCalendar}")
    public void today_date_is(SimpleCalendar date) {
    }
    @When("the return of {string} is processed")
    public void the_return_is_processed(String itemTitle) {
    }
    @Then("the system marks the state of {string} as {string}")
    public void system_marks_the_state_of_as(String itemName, String newState) {
    }
    @Then("George Red has one less pending item")
    public void georgeRed_has_one_less_pending_item(Borrower borrower) {
    }
    @Then("the items count of the book increases by one")
    public void items_count_of_the_book_increases_by_one() {
    }
    @Then("George Red pays a fine based on the borrower category")
    public void george_red_pays_a_fine_based_on_the_borrower_category(Borrower borrower) {
    }
    @Given("George Red doesn't have enough money to pay the fine")
    public void george_red_doesn_t_have_enough_money_to_pay_the_fine(Borrower borrower) {
    }
    @Then("the system does not accept the return")
    public void the_system_does_not_accept_the_return() {
    }
    @Then("George Red has the same number of pending items")
    public void george_red_has_the_same_number_of_pending_items(Borrower borrower) {
    }

}