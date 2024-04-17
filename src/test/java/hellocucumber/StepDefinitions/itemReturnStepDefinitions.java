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
import io.cucumber.java.Before;
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
    private Money fine;
    private LoanService loanService;
    private ReturnService returnService;
    private Borrower georgeRed;
    private LoanDAO loanDao;
    private ItemDAO itemDao;
    private BorrowerDAO borrowerDao;
    private Item kingdomItem;

    @Before
    public void setUp(){
        itemDao = new ItemDAOMemory();
        loanDao = new LoanDAOMemory();
        borrowerDao= new BorrowerDAOMemory();
        loanService = new LoanService();
        returnService = new ReturnService();
        clearData();
    }
    public void clearData() {
        List<Item> allItems = itemDao.findAll();
        for (Item item : allItems) {
            itemDao.delete(item);
        }
        List<Loan> allLoans = loanDao.findAll();
        for (Loan loan : allLoans) {
            loanDao.delete(loan);
        }
        List<Borrower> allBorrowers = borrowerDao.findAll();
        for(Borrower borrower : allBorrowers) {
            borrowerDao.delete(borrower);
        }
    }
    @Given("{borrower} borrowed the item Animal Kingdom {int} days prior to today's date")
    public void givenBorrowerBorrowedItemWithReturnDate(Borrower borrower,Integer daysBefore) {
        georgeRed = borrower;
        georgeRed.setCategory(new BorrowerCategory());
        georgeRed.getCategory().setMaxLendingItems(5);
        georgeRed.getCategory().setDailyFine(new Money(new BigDecimal(Integer.toString(5)),Currency.getInstance("EUR")));
        borrowerDao.save(georgeRed);

        // Create and save the item/book Animal Kingdom
        Book kingdomBook = new Book();
        kingdomBook.setTitle("Animal Kingdom");
        kingdomItem = new Item();
        kingdomItem.setBook(kingdomBook);
        kingdomBook.addItem(kingdomItem);
        kingdomItem.available();
        kingdomItem.setItemNumber(1001114);
        itemDao.save(kingdomItem);
        if(loanService.findBorrower(georgeRed.getBorrowerNo())){
            LocalDate date = loanService.borrow(kingdomItem.getItemNumber());
            //changing to a custom loan date than the one created, for the scenario to be right
            loanDao.findPending(kingdomItem.getItemNumber()).setLoanDate(LocalDate.now().minusDays(daysBefore));
        }

    }
    @Given("George Red has been assigned maximum lending days of {int}")
    public void georgeRedHasBeenAssignedMaximumLendingDaysOf(int maxLendingDays) {
        georgeRed.getCategory().setMaxLendingDays(maxLendingDays);
    }
    @When("the return of Animal Kingdom is processed")
    public void whenItemReturnIsProcessed() {
        fine = returnService.returnItem(kingdomItem.getItemNumber());
    }
    @Then("the system marks the state of Animal Kingdom as AVAILABLE")
    public void thenNewStateMarked() {
        Assertions.assertEquals(ItemState.AVAILABLE, kingdomItem.getState());
    }
    @Then("George Red has one less pending item")
    public void thenDecreasePendingItemsOfGeorgeRed() {
        // Assert that the borrower has no pending items(he had 1 before)
        Assertions.assertEquals(georgeRed.countPendingItems(),0);
    }
    @Then("George Red does not pay a fine")
    public void georgeRedDoesNotPayAFine() {
        Assertions.assertNull(fine);
    }
    @Then("the return date of the loan is set to today's date")
    public void thenNewReturnDateSet() {
        Assertions.assertEquals(LocalDate.now(),loanDao.findLoan(kingdomItem.getItemNumber()).getReturnDate());
    }
    @Then("George Red pays a fine based on the borrower category")
    public void thenFinePaymentIsDone() {
        Assertions.assertNotNull(fine);
    }

}