package hellocucumber;

import hellocucumber.domain.Borrower;
import hellocucumber.util.SimpleCalendar;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class itemReturnStepDefinitions {
    @Given("{borrower} has borrowed the item Animal Kingdom with a due date of {simpleCalendar}")
    public void georgeRed_has_borrowed_the_item_with_a_return_date_of(Borrower borrower,SimpleCalendar date) {
    }
    @Given("today's date is {simpleCalendar}")
    public void today_date_is(SimpleCalendar date) {
    }
    @When("the return of Animal Kingdom is processed")
    public void the_return_is_processed() {
    }
    @Then("the system marks the state of Animal Kingdom as {string}")
    public void system_marks_the_state_of_as(String newState) {
    }
    @Then("George Red has one less pending item")
    public void georgeRed_has_one_less_pending_item() {
    }
    @Then("the items count of the book increases by one")
    public void items_count_of_the_book_increases_by_one() {
    }
    @Then("George Red pays a fine based on the borrower category")
    public void george_red_pays_a_fine_based_on_the_borrower_category() {
    }
    @Given("George Red doesn't have enough money to pay the fine")
    public void george_red_doesn_t_have_enough_money_to_pay_the_fine() {
    }
    @Then("the system does not accept the return")
    public void the_system_does_not_accept_the_return() {
    }
    @Then("George Red has the same number of pending items")
    public void george_red_has_the_same_number_of_pending_items() {
    }
    @Then("the return date of the item is set to {int}{int}{int}")
    public void theReturnDateOfTheItemIsSetTo(int arg0, int arg1, int arg2) {
    }
    @Then("the return date of the item is not created")
    public void theReturnDateOfTheItemIsNotCreated() {
    }

}