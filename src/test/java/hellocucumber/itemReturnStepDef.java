package hellocucumber;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import hellocucumber.dao.BorrowerDAO;
import hellocucumber.memorydao.BorrowerDAOMemory;
import org.mockito.Mock;
import static org.mockito.BDDMockito.*;
public class itemReturnStepDef {
    @Mock
    private BorrowerDAO borrowerDAO;
    @Before
    public void setup(){
        borrowerDAO = new BorrowerDAOMemory();
    }
    @Given("{string} has borrowed the item {string}")
    public void has_borrowed_the_item(String string, String string2) {
        //
    }

    @When("the return of {string} by {string} is processed")
    public void the_return_of_by_is_processed(String string, String string2) {
        //
    }

    @Then("the system should mark the itemState of the item as {string}")
    public void the_system_should_mark_the_itemState_of_the_item_as(String string) {
        //
    }

    @Then("{string} has one less pending item")
    public void has_one_less_pending_item(String string) {
        //
    }

    @Then("the items count of the book should increase by one")
    public void the_items_count_of_the_book_should_increase_by_one() {
        //
    }

    @Given("{string} belongs in a borrower category")
    public void belongs_in_a_borrower_category(String string) {
        //
    }

    @Given("the due date for returning the item has passed")
    public void the_due_date_for_returning_the_item_has_passed() {
        //
    }

    @Then("the system should mark the itemState of {string} as {string}")
    public void the_system_should_mark_the_itemState_of_as(String string, String string2) {
        //
    }

    @Then("{string} incurs a fine based on the borrower category")
    public void incurs_a_fine_based_on_the_borrower_category(String string) {
        //
    }
}
