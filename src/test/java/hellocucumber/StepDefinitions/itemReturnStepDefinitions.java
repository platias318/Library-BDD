package hellocucumber.StepDefinitions;

import hellocucumber.dao.BorrowerDAO;
import hellocucumber.dao.ItemDAO;
import hellocucumber.dao.LoanDAO;
import hellocucumber.domain.*;
import hellocucumber.memorydao.BorrowerDAOMemory;
import hellocucumber.memorydao.ItemDAOMemory;
import hellocucumber.memorydao.LoanDAOMemory;
import hellocucumber.service.LoanService;
import hellocucumber.service.ReturnService;
import hellocucumber.util.Money;
import hellocucumber.util.SimpleCalendar;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

public class itemReturnStepDefinitions {
    // Instance variables
    private Money fine; //An object that will be used to save money objects
    private LoanService loanService; //An instance of the loanService class to help with the loaning of the copies
    private ReturnService returnService; //An instance of the returnService class to help with the returning of the copies
    private Borrower george_red;//The borrower persona that will be used for the whole feature
    private LoanDAO loanDao; //The DAO where all the loans will be saved
    private ItemDAO itemDao; //The DAO where all the items will be saved
    private BorrowerDAO borrowerDao; //The DAO where all the borrowers will be saved
    private Book kingdom_book; //A book that will be used in this feature(title:"Animal Kingdom")
    private Item kingdom_item;//The item of the book we instantiated above

    // Method to initialize DAOs and service before each scenario
    @Before
    public void setUp(){
        itemDao = new ItemDAOMemory();
        loanDao = new LoanDAOMemory();
        borrowerDao= new BorrowerDAOMemory();
        loanService = new LoanService();
        returnService = new ReturnService();
        clearData();
    }
    // Method to clear data in DAOs
    public void clearData() {
        // Delete all items
        List<Item> allItems = itemDao.findAll();
        for (Item item : allItems) {
            itemDao.delete(item);
        }
        // Delete all loans
        List<Loan> allLoans = loanDao.findAll();
        for (Loan loan : allLoans) {
            loanDao.delete(loan);
        }
        // Delete all borrowers
        List<Borrower> allBorrowers = borrowerDao.findAll();
        for(Borrower borrower : allBorrowers) {
            borrowerDao.delete(borrower);
        }
    }
    @Given("{borrower} borrowed the item Animal Kingdom {int} days prior to today's date")
    public void givenBorrowerBorrowedItemWithReturnDate(Borrower borrower,Integer daysBefore) {
        //creating,saving the borrower and setting up the borrower category
        george_red = borrower;
        BorrowerCategory category = new BorrowerCategory();
        george_red.setCategory(category);
        george_red.getCategory().setMaxLendingItems(5);
        george_red.getCategory().setDailyFine(new Money(new BigDecimal(Integer.toString(5)),Currency.getInstance("EUR")));
        borrowerDao.save(george_red);

        // Create and save the item/book Animal Kingdom
        kingdom_book = new Book();
        kingdom_book.setTitle("Animal Kingdom");
        kingdom_item = new Item();
        kingdom_item.setBook(kingdom_book);
        kingdom_book.addItem(kingdom_item);
        kingdom_item.available();
        kingdom_item.setItemNumber(1001114);
        itemDao.save(kingdom_item);
        // Attempt to borrow the item and get due date if successful
        if(loanService.findBorrower(george_red.getBorrowerNo())){
            LocalDate date = loanService.borrow(kingdom_item.getItemNumber());
            //changing to a custom loan date than the one created, for the scenario to be right
            loanDao.findPending(kingdom_item.getItemNumber()).setLoanDate(LocalDate.now().minusDays(daysBefore));
        }

    }
    @Given("George Red has been assigned maximum lending days of {int}")
    public void georgeRedHasBeenAssignedMaximumLendingDaysOf(int maxLendingDays) {
        //Setting the max lending days to the borrower's borrower category
        george_red.getCategory().setMaxLendingDays(maxLendingDays);
    }
    @When("the return of Animal Kingdom is processed")
    public void whenItemReturnIsProcessed() {
        //Attempt to create a fine based on the return service
        fine = returnService.returnItem(kingdom_item.getItemNumber());
    }
    @Then("the system marks the state of Animal Kingdom as AVAILABLE")
    public void thenNewStateMarked() {
        // Assert that the loan's state has been changed to available
        Assertions.assertEquals(ItemState.AVAILABLE,kingdom_item.getState());
    }
    @Then("George Red has one less pending item")
    public void thenDecreasePendingItemsOfGeorgeRed() {
        // Assert that the borrower has no pending items(he had 1 before)
        Assertions.assertEquals(george_red.countPendingItems(),0);
    }
    @Then("George Red does not pay a fine")
    public void georgeRedDoesNotPayAFine() {
        //Assert that the fine that was returned is null (that means he returned it on time)
        Assertions.assertNull(fine);
    }
    @Then("the return date of the loan is set to today's date")
    public void thenNewReturnDateSet() {
        // Assert that the date of the loan equals today's date(because today is the day it was returned)
        Assertions.assertEquals(LocalDate.now(),loanDao.findLoan(kingdom_item.getItemNumber()).getReturnDate());
    }
    @Then("George Red pays a fine based on the borrower category")
    public void thenFinePaymentIsDone() {
        // Assert that a fine was created because the borrower was late to return the item
        Assertions.assertNotNull(fine);
    }
//    @Given("George Red doesn't have enough money to pay the fine")
//    public void givenGeorgeRedDoesNotHaveEnoughMoney() {
//    }
//    @Then("the system does not accept the return of the item")
//    public void ThenSystemNotAcceptingTheReturn() {
//    }
//    @Then("George Red has the same number of pending items")
//    public void thenSameNumberPendingItems() {
//    }
//    @Then("the return date of the loan is not created")
//    public void thenReturnDateOfTheItemNotCreated() {
//    }

}