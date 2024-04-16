package hellocucumber.StepDefinitions;

import hellocucumber.contacts.EmailAddress;
import hellocucumber.dao.BorrowerDAO;
import hellocucumber.dao.ItemDAO;
import hellocucumber.dao.LoanDAO;
import hellocucumber.domain.*;
import hellocucumber.memorydao.BorrowerDAOMemory;
import hellocucumber.memorydao.ItemDAOMemory;
import hellocucumber.memorydao.LoanDAOMemory;
import hellocucumber.service.LoanService;
import hellocucumber.service.NotificationService;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.List;

public class delayNotificationStepDefinitions {
    // Instance variables
    private NotificationService notificationService;
    private LoanService loanService; //An instance of the loanService class to help with the loaning of the copies
    private Borrower george_red; //The borrower persona that will be used for the whole feature
    private LoanDAO loanDao; //The DAO where all the loans will be saved
    private ItemDAO itemDao; //The DAO where all the items will be saved
    private BorrowerDAO borrowerDao; //The DAO where all the borrowers will be saved
    private Book hPotter_book; //A book that will be used in this feature(title:"Harry Potter")
    private Item hPotter_item; //The item of the book we instantiated above
    // Method to clear data in DAOs
    @Before
    public void setUp(){
        itemDao = new ItemDAOMemory();
        loanDao = new LoanDAOMemory();
        borrowerDao = new BorrowerDAOMemory();
        loanService = new LoanService();
        notificationService= new NotificationService();
        clearData();
    }
    public void clearData(){
        // Delete all items
        List<Item> allItems = itemDao.findAll();
        for(Item item : allItems) {
            itemDao.delete(item);
        }
        // Delete all loans
        List<Loan> allLoans = loanDao.findAll();
        for(Loan loan : allLoans) {
            loanDao.delete(loan);
        }
        // Delete all borrowers
        List<Borrower> allBorrowers = borrowerDao.findAll();
        for(Borrower borrower : allBorrowers) {
            borrowerDao.delete(borrower);
        }
    }
    //Creating the borrower and the item/book that will be used in the scenario
    @Given("{borrower} has borrowed the item Harry Potter")
    public void givenBorrowerHasBorrowedTheItem(Borrower borrower) {
        //Save the borrower
        george_red = borrower;
        george_red.setCategory(new BorrowerCategory());
        george_red.getCategory().setMaxLendingDays(2);//setting a dummy number for the scenario to pass
        george_red.getCategory().setMaxLendingItems(10);
        borrowerDao.save(george_red);

        //Create and save the item/book Harry Potter
        hPotter_book = new Book();
        hPotter_book.setTitle("Harry Potter");
        hPotter_item = new Item();
        hPotter_item.setBook(hPotter_book);
        hPotter_book.addItem(hPotter_item);
        hPotter_item.available();
        hPotter_item.setItemNumber(1001112);
        itemDao.save(hPotter_item);
    }
    @Given("Harry Potter's due date has passed")
    public void givenItemDueDatePassed() {
        //make the due date of the item passed
        if(loanService.findBorrower(george_red.getBorrowerNo())){
            loanService.borrow(hPotter_item.getItemNumber());
            loanDao.findPending(hPotter_item.getItemNumber()).setLoanDate(LocalDate.now().minusDays(10));
        }
    }


    @Given("George Red has an email address")
    public void georgeRedHasAnEmailAddress() {
        //Adding an email address to the borrower for the scenario to pass
        george_red.setEmail(new EmailAddress("georgeRed@gmail.com"));
    }
    @When("the system executes the delayed return notification process")
    public void theSystemExecutesTheDelayedReturnNotificationProcess() {

    }
    @Then("George Red receives an email notification for the return of the item")
    public void georgeRedReceivesAnEmailNotificationForTheReturnOfTheItem() {
    }

    @Given("George Red does not have an email address")
    public void givenGeorgeRedDoesNotHaveEmailAddress() {
        //Making sure that if the borrower has an email, we set it to null, for the scenario to pass
        if(george_red.getEmail()!=null){
            george_red.setEmail(null);
        }
    }
    @Then("George Red does not receive an email notification for the return of the item")
    public void georgeRedDoesNotReceiveAnEmailNotificationForTheReturnOfTheItem() {
    }
}
