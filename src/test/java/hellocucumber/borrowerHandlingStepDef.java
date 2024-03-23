package hellocucumber;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import hellocucumber.dao.BorrowerDAO;
import hellocucumber.domain.Borrower;
import hellocucumber.memorydao.BorrowerDAOMemory;
import org.mockito.Mock;

import static org.mockito.BDDMockito.*;

public class borrowerHandlingStepDef {
    @Mock
    private BorrowerDAO borrowerDAO;
    @Before
    public void setup(){
        borrowerDAO = new BorrowerDAOMemory();
    }
    @Given("{string} wants to register to become a borrower")
    public void wants_to_register_to_become_a_borrower(String string) {

        // na valw se metavlites ta stoixeia tou
    }

    @Given("{string} hasn't already registered")
    public void hasn_t_already_registered(String string) {
        //na psaxw an uparxei sto sistima me vash to onoma/kwdiko tou kai an iparxei na ton diagrapsw
        // H na skiparw auto to step
    }

    @When("{string} gets registered in the system with a unique borrowerΝο and his details")
    public void gets_registered_in_the_system_with_a_unique_borrowerΝο_and_his_details(String string) {
        //dimiourgw ton borrower kai ton vazw mesa sto sistima
    }

    @Then("the system stores the borrower's details successfully")
    public void the_system_stores_the_borrower_s_details_successfully() {
        //elegxw ean apothikeutike/mpike sto sustima
    }

    @Given("{string} has already registered")
    public void has_already_registered(String string) {
        //ton vazw mesa sto sustima na iparxei hdh (ean den iparxei)
    }

    @Then("the system informs that the user already exists")
    public void the_system_informs_that_the_user_already_exists() {
        //mock/stub se methodo gia na emfanistei ena minima sfalmatos otan paei na mpei mesa o xrhstis autos
    }

    @Given("{string} is a borrower")
    public void is_a_borrower(String string) {
        //ton vazw mesa sto sistima na iparxei/kanw mock an erwththei ean einai mesa na vgei true?

    }

    @Given("{string} wants to update his borrowing details")
    public void wants_to_update_his_borrowing_details(String string) {
        //apothikeuw se metavlites ta nea stoixeia tou
    }

    @When("the borrower's details are updated")
    public void the_borrower_s_details_are_updated() {
        //ta pernaw ta nea stoixeia tou
    }

    @Then("the system saves the changes")
    public void the_system_saves_the_changes() {
        //elegxw ean ta nea stoixeia mesa sto dao einai apothikeumena
    }

    @Given("{string} isn't a borrower")
    public void isn_t_a_borrower(String string) {
        //elegxw ean iparxei sto sustima, kai ean einai ton diagrafw, alliws tipota
    }

    @Then("the system displays an error message indicating that the borrower does not exist")
    public void the_system_displays_an_error_message_indicating_that_the_borrower_does_not_exist() {
        //kanw mock wste otan ton psaxw na bgalei false/minima sfalmatos
    }

    @Given("{string} wants to delete his account")
    public void wants_to_delete_his_account(String string) {

    }

    @When("the deletion of {string}'s account is attempted")
    public void the_deletion_of_s_account_is_attempted(String string) {
        //diagraphw ton xristi/ kanw mock tin diagrafi?
    }

    @Then("the system removes the borrower's details")
    public void the_system_removes_the_borrower_s_details() {
        //epivevaiwnw/kanw mock otan psaxw ton xristi na min iparxei
    }

    @Given("{string} has pending items")
    public void has_pending_items(String string) {
        //vazw pending items ston borrower
    }

    @Then("the system does not remove the borrower's details")
    public void the_system_doesnt_remove_borrowers_details(String string) {
    }

    @Then("the system informs about the non returned items")
    public void the_system_informs_about_the_non_returned_items() {
    }
}
