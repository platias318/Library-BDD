package hellocucumber.StepDefinitions;

import hellocucumber.contacts.EmailAddress;
import hellocucumber.contacts.EmailMessage;
import hellocucumber.dao.BorrowerDAO;
import hellocucumber.dao.ItemDAO;
import hellocucumber.dao.LoanDAO;
import hellocucumber.domain.*;
import hellocucumber.memorydao.BorrowerDAOMemory;
import hellocucumber.memorydao.ItemDAOMemory;
import hellocucumber.memorydao.LoanDAOMemory;
import hellocucumber.service.EmailProviderStub;
import hellocucumber.service.LoanService;
import hellocucumber.service.NotificationService;
import hellocucumber.support.LibraryWorld;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.List;

public class delayNotificationStepDefinitions {
    // Instance variables
    private Borrower georgeRed;
    private Item hPotterItem;
    private final LibraryWorld world;
    public delayNotificationStepDefinitions(LibraryWorld world){
        this.world=world;
    }
    @Before
    public void setUp(){
        world.clearBorrowers();
        world.clearLoans();
        world.clearItems();
    }
    @Given("{borrower} has borrowed the item Harry Potter")
    public void givenBorrowerHasBorrowedTheItem(Borrower borrower) {
        georgeRed = borrower;
        georgeRed.setCategory(new BorrowerCategory());
        georgeRed.getCategory().setMaxLendingDays(2);//setting a dummy number for the scenario to pass
        georgeRed.getCategory().setMaxLendingItems(10);
        //Create and save the item/book Harry Potter
        hPotterItem=world.createItem("Harry Potter", 1001112);
    }
    @Given("Harry Potter's due date has passed")
    public void givenItemDueDatePassed() {
        if(world.loanService.findBorrower(georgeRed.getBorrowerNo())){
            world.loanService.borrow(hPotterItem.getItemNumber());
            world.loanDao.findPending(hPotterItem.getItemNumber()).setLoanDate(LocalDate.now().minusDays(10));
        }
    }


    @Given("George Red has an email address")
    public void georgeRedHasAnEmailAddress() {
        georgeRed.setEmail(new EmailAddress("georgeRed@gmail.com"));
    }
    @When("the system executes the delayed return notification process")
    public void theSystemExecutesTheDelayedReturnNotificationProcess() {
        world.notificationService.setProvider(world.emailProvider);
        world.notificationService.notifyBorrowers();
    }
    @Then("George Red receives an email notification for the return of the item")
    public void georgeRedReceivesAnEmailNotificationForTheReturnOfTheItem() {
        boolean answer = false;
        for(EmailMessage message:world.emailProvider.getAllEmails()){
            if(message.getTo().equals(georgeRed.getEmail())){
                answer= true;
            }
        }
        Assertions.assertTrue(answer);
    }

    @Given("George Red does not have an email address")
    public void givenGeorgeRedDoesNotHaveEmailAddress() {
        if(georgeRed.getEmail()!=null){
            georgeRed.setEmail(null);
        }
    }
    @Then("George Red does not receive an email notification for the return of the item")
    public void georgeRedDoesNotReceiveAnEmailNotificationForTheReturnOfTheItem() {
        boolean answer= true;
        for(EmailMessage message:world.emailProvider.getAllEmails()){
            if(message.getTo().equals(georgeRed.getEmail())){
                answer= false;
            }
        }
        Assertions.assertTrue(answer);
    }
}
