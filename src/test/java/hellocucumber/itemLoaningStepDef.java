package hellocucumber;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import hellocucumber.domain.*;

public class itemLoaningStepDef {
    private Item item;
    private Borrower borrower;
    private Book book;
    @Given("the library has the item {string}")
    public void the_library_has_the_item(String title) {
        book = new Book();
        book.setTitle(title);
        item = new Item();
        item.setBook(book);
        item.available();
        book.addItem(item);
    }

    @Given("{string} is a borrower")
    public void is_a_borrower(String name){
        String[] fullname = name.split(" ");
        String firstName=fullname[0];
        String lastName=fullname[1];
        borrower = new Borrower();
        borrower.setFirstName(firstName);
        borrower.setLastName(lastName);
    }
    @Given("he/she has less pending items to return than the maximum lending limit he was assigned")
    public void has_less_pending_items_to_return_than_the_maximum_lending_limit_he_was_assigned(String name) {
        borrower

    }

    @When("{string} wants to borrow the item {string}")
    public void wants_to_borrow_the_item(String string, String string2) {

    }

    @Then("the system successfully loans the item {string}")
    public void the_system_successfully_loans_the_item(String string) {

    }

    @Then("the system creates a returnDate for the return of the item {string}")
    public void the_system_creates_a_returnDate_for_the_return_of_the_item(String string) {

    }

    @Given("{string} has one pending item away from reaching the maximum lending limit he was assigned.")
    public void has_one_pending_item_away_from_reaching_the_maximum_lending_limit_he_was_assigned(String string) {

    }

    @Then("the system unsuccessfully loans the item {string}")
    public void the_system_unsuccessfully_loans_the_item(String string) {

    }

    @Then("the system creates a returnDate for the return of {string}")
    public void the_system_creates_a_returnDate_for_the_return_of(String string) {

    }

    @Given("the item is in the itemState {string}")
    public void the_item_is_in_the_itemState(String string) {

    }

    @Then("the system returns an error")
    public void the_system_returns_an_error() {

    }

    @Then("the system withdraws the item {string}")
    public void the_system_withdraws_the_item(String string) {

    }

    @Given("{string}'s pending items have reached the maximum lending limit he was assigned")
    public void s_pending_items_have_reached_the_maximum_lending_limit_he_was_assigned(String string) {

    }

    @Then("the system doesn't allow the loan and displays an error message")
    public void the_system_doesn_t_allow_the_loan_and_displays_an_error_message() {

    }

}
