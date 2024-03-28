package hellocucumber;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import hellocucumber.contacts.Address;
import hellocucumber.contacts.EmailAddress;
import hellocucumber.contacts.TelephoneNumber;
import hellocucumber.dao.BorrowerDAO;
import hellocucumber.domain.Borrower;
import hellocucumber.memorydao.BorrowerDAOMemory;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;

import static org.mockito.BDDMockito.*;

public class borrowerHandlingStepDef {
    private BorrowerDAO borrowerDAO=new BorrowerDAOMemory();
    private int borrowerNO;
    private String firstName;
    private String lastName;
    private EmailAddress email;
    private Address address;
    private TelephoneNumber phoneNumber;
    private Borrower borrower;
    @Before
    public void setup(){}
    @Given("{string} is not registered as a borrower")
    public void is_not_registered_as_a_borrower(String name) {
        String[] parts = name.split(" ");
        firstName = parts[0];
        lastName = parts[1];
        Borrower dummy_user= borrowerDAO.find(firstName, lastName);
        if(dummy_user!=null){
            borrowerDAO.delete(dummy_user); // we ensure that the borrower is not already in the system by deleting him if his is not null
        }

    }

    @When("{string} gets registered in the system with a unique borrowerΝο and his details")
    public void gets_registered_in_the_system_with_a_unique_borrowerΝο_and_his_details(String string) {
        borrowerNO=5;
        address = new Address();
        email = new EmailAddress("dummy@gmail.com");
        phoneNumber = new TelephoneNumber("6987654321");
        borrower = new Borrower(borrowerNO,firstName, lastName,address, email, phoneNumber);
        borrowerDAO.save(borrower);
    }

    @Then("the system successfully stores the borrower's details")
    public void the_system_successfully_stores_the_borrower_s_details() {
        Assertions.assertEquals(borrowerDAO.find(borrower.getBorrowerNo()),borrower);
    }

    @Given("{string} is already registered as a borrower")
    public void is_already_registered_as_a_borrower(String name) {
        String[] parts = name.split(" ");
        firstName = parts[0];
        lastName = parts[1];
        Borrower user= borrowerDAO.find(firstName, lastName); // the user will exist because I inserted him in an earlier scenario, and that is what I want to test his behaviour, but if he doesn't, we create him now
        if(user==null){
            user = new Borrower(borrowerNO,firstName, lastName,address, email, phoneNumber);
            borrowerDAO.save(user); // we ensure that the borrower is not already in the system by deleting him if his is not null
        }
    }

    @When("the system attempts to register {string} with a unique borrowerΝο and his details")
    public void the_system_attempts_to_register_with_a_unique_borrowerΝο_and_his_details(String string) {
        //prospathw na valw ton xristi pou dimiourgisa panw iq kai stoixeia, ton vazw sto sustima
        //na valw isws na iparxei ena message pou elegxw meta sto then ean einai auto poy thelw
    }

    @Then("the system informs that the user already exists")
    public void the_system_informs_that_the_user_already_exists() {
        //elegxw to minima to proigoymeno pou itan sto vima oti einai kapoio lathous klp
    }

    @Given("{string} is a registered borrower")
    public void is_a_registered_borrower(String string) {
        //dimiourgw ton borrower kai ton vazw sto sustima(mesw mock? h mesw tou dao memory apla)
    }

    @When("{string} updates his borrowing details")
    public void updates_his_borrowing_details(String string) {
        //na apothikeusw ta nea stoixeia se metavlites
        //isws na valw se nees metavlites kai na allaksw sto sistima ta stoixeia tou xristi
    }

    @Then("the system saves the changes")
    public void the_system_saves_the_changes() {
        //na elegjw ean to stoixeio pou allaksa einai auto pou apothikeusa epanw se metavliti
    }

    @Given("{string} is not a registered borrower")
    public void is_not_a_registered_borrower(String string) {
        //na kanw borrower me to onoma tou kai na dw an einai mesa? kai an den einai tote X
        //na dw an den uparxei sto dao, na klanw mock ne petaei false otan einai mesa???
    }

    @When("{string} tries to update his borrowing details")
    public void tries_to_update_his_borrowing_details(String string) {
        //na apothikeusw ta nea stoixeia
        //isws na valw se nees metavlites kai na allaksw sto sistima ta stoixeia tou xristi,
        //kai na epistrefw minima msg poy a einai koble na to tsekarw meta alliws oxi
    }

    @Then("the system displays an error message indicating that {string} does not exist")
    public void the_system_displays_an_error_message_indicating_that_does_not_exist(String string) {
        //to parapanw minima elegxw ti girise klp
    }

    @When("the system deletes {string}'s account")
    public void the_system_deletes_s_account(String string) {
        //diagraphw to account tou xristi mesw to dao? h kati me mock isws
    }

    @Then("the system removes {string}'s details")
    public void the_system_removes_s_details(String string) {
        //elegxw me insertion/mock oti den einai mesa sto sistima o xristis
    }

    @When("the system attempts to delete {string}'s account \\( edw mporw na vgalw to attempts, na mpei idio me epanw , kai na valw na apothikeuei se string ena minima poy sto epomeno vima elegxw ti einai ean einai dld success h oxi)")
    public void the_system_attempts_to_delete_s_account_edw_mporw_na_vgalw_to_attempts_na_mpei_idio_me_epanw_kai_na_valw_na_apothikeuei_se_string_ena_minima_poy_sto_epomeno_vima_elegxw_ti_einai_ean_einai_dld_success_h_oxi(String string) {
        //
    }

    @Given("{string} has pending items")
    public void has_pending_items(String string) {
        // na valw kapoia adeia items ston xristi
    }

    @When("the system attempts to delete {string}'s account")
    public void the_system_attempts_to_delete_s_account(String string) {
        //me kapoio minima i kati dokimazw na diagrapsw enan xristi kai elegxw me to minima auto
        //kalw tin methodo pou exei mesa sto sistima kai diagraphei ton xristi kai ekei elegxei ean tha diagrapsei
        //h oxi ton xrhsth
    }

    @Then("the system does not remove {string}'s details")
    public void the_system_does_not_remove_s_details(String string) {
        //elegxw oti o xristis einai akomi mesa sto sistima
    }

    @Then("the system informs about the pending items")
    public void the_system_informs_about_the_pending_items() {
        //epistrefaike kapoio minima prin poy leei poia antikeimena/ oti prepei na gurisei kati?
    }
}
