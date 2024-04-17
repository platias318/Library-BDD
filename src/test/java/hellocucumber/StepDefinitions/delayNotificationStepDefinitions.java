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
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.List;

public class delayNotificationStepDefinitions {
    // Instance variables
    private EmailProviderStub emailProvider;
    private NotificationService notificationService;
    private LoanService loanService;
    private Borrower georgeRed;
    private LoanDAO loanDao;
    private ItemDAO itemDao;
    private BorrowerDAO borrowerDao;
    private Item hPotterItem;
    @Before
    public void setUp(){
        itemDao = new ItemDAOMemory();
        loanDao = new LoanDAOMemory();
        borrowerDao = new BorrowerDAOMemory();
        loanService = new LoanService();
        notificationService= new NotificationService();
        emailProvider= new EmailProviderStub();
        clearData();
    }
    public void clearData(){
        List<Item> allItems = itemDao.findAll();
        for(Item item : allItems) {
            itemDao.delete(item);
        }
        List<Loan> allLoans = loanDao.findAll();
        for(Loan loan : allLoans) {
            loanDao.delete(loan);
        }
        List<Borrower> allBorrowers = borrowerDao.findAll();
        for(Borrower borrower : allBorrowers) {
            borrowerDao.delete(borrower);
        }
    }
    @Given("{borrower} has borrowed the item Harry Potter")
    public void givenBorrowerHasBorrowedTheItem(Borrower borrower) {
        georgeRed = borrower;
        georgeRed.setCategory(new BorrowerCategory());
        georgeRed.getCategory().setMaxLendingDays(2);//setting a dummy number for the scenario to pass
        georgeRed.getCategory().setMaxLendingItems(10);
        borrowerDao.save(georgeRed);

        //Create and save the item/book Harry Potter
        Book hPotterBook = new Book();
        hPotterBook.setTitle("Harry Potter");
        hPotterItem = new Item();
        hPotterItem.setBook(hPotterBook);
        hPotterBook.addItem(hPotterItem);
        hPotterItem.available();
        hPotterItem.setItemNumber(1001112);
        itemDao.save(hPotterItem);
    }
    @Given("Harry Potter's due date has passed")
    public void givenItemDueDatePassed() {
        if(loanService.findBorrower(georgeRed.getBorrowerNo())){
            loanService.borrow(hPotterItem.getItemNumber());
            loanDao.findPending(hPotterItem.getItemNumber()).setLoanDate(LocalDate.now().minusDays(10));
        }
    }


    @Given("George Red has an email address")
    public void georgeRedHasAnEmailAddress() {
        georgeRed.setEmail(new EmailAddress("georgeRed@gmail.com"));
    }
    @When("the system executes the delayed return notification process")
    public void theSystemExecutesTheDelayedReturnNotificationProcess() {
        notificationService.setProvider(emailProvider);
        notificationService.notifyBorrowers();
    }
    @Then("George Red receives an email notification for the return of the item")
    public void georgeRedReceivesAnEmailNotificationForTheReturnOfTheItem() {
        boolean answer = false;
        for(EmailMessage message:emailProvider.getAllEmails()){
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
        for(EmailMessage message:emailProvider.getAllEmails()){
            if(message.getTo().equals(georgeRed.getEmail())){
                answer= false;
            }
        }
        Assertions.assertTrue(answer);
    }
}
