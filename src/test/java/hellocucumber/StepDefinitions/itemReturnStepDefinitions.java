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
    Money fine;
    private LoanService loanService;
    private ReturnService returnService;
    private Borrower george_red;//The borrower persona that will be used for the whole feature
    private LoanDAO loanDao;
    private ItemDAO itemDao;
    private BorrowerDAO borrowerDao;
    private Book kingdom_book; //A book that will be used in this feature(title:"Animal Kingdom")
    private Item kingdom_item;//The item of the book we instantiated above
    private SimpleCalendar date;

    @Before
    public void setUp(){//Gets called before each scenario
        itemDao = new ItemDAOMemory();
        loanDao = new LoanDAOMemory();
        borrowerDao= new BorrowerDAOMemory();

        loanService = new LoanService();
        returnService = new ReturnService();
        clearData();
    }
    public void clearData() {//Gets called to delete all the data in the DAOs
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
        george_red = borrower;
        BorrowerCategory category = new BorrowerCategory();
        george_red.setCategory(category);
        george_red.getCategory().setMaxLendingItems(5);
        george_red.getCategory().setDailyFine(new Money(new BigDecimal(Integer.toString(5)),Currency.getInstance("EUR")));
        borrowerDao.save(george_red);


        kingdom_book = new Book();
        kingdom_book.setTitle("Animal Kingdom");

        kingdom_item = new Item();
        kingdom_item.setBook(kingdom_book);
        kingdom_book.addItem(kingdom_item);
        kingdom_item.available();
        kingdom_item.setItemNumber(1001114);
        itemDao.save(kingdom_item);

        if(loanService.findBorrower(george_red.getBorrowerNo())){
            LocalDate date = loanService.borrow(kingdom_item.getItemNumber());
            loanDao.findPending(kingdom_item.getItemNumber()).setLoanDate(LocalDate.now().minusDays(daysBefore));
        }

    }
    @Given("George Red has been assigned maximum lending days of {int}")
    public void georgeRedHasBeenAssignedMaximumLendingDaysOf(int maxLendingDays) {
//        BorrowerCategory category = new BorrowerCategory();//dummy number
        george_red.getCategory().setMaxLendingDays(maxLendingDays);//this is what we need
//        george_red.setCategory(category);
    }
    @When("the return of Animal Kingdom is processed")
    public void whenItemReturnIsProcessed() {
        fine = returnService.returnItem(kingdom_item.getItemNumber());
    }
    @Then("the system marks the state of Animal Kingdom as AVAILABLE")
    public void thenNewStateMarked() {
        Assertions.assertEquals(ItemState.AVAILABLE,kingdom_item.getState());
    }
    @Then("George Red has one less pending item")
    public void thenDecreasePendingItemsOfGeorgeRed() {
        Assertions.assertEquals(george_red.countPendingItems(),0);
    }
    @Then("George Red does not pay a fine")
    public void georgeRedDoesNotPayAFine() {
        Assertions.assertNull(fine);
    }
    @Then("the return date of the loan is set to today's date")
    public void thenNewReturnDateSet() {
        Assertions.assertEquals(LocalDate.now(),loanDao.findLoan(kingdom_item.getItemNumber()).getReturnDate());
    }
    @Then("George Red pays a fine based on the borrower category")
    public void thenFinePaymentIsDone() {
        Assertions.assertNotNull(fine);
    }
    @Given("George Red doesn't have enough money to pay the fine")
    public void givenGeorgeRedDoesNotHaveEnoughMoney() {
    }
    @Then("the system does not accept the return of the item")
    public void ThenSystemNotAcceptingTheReturn() {
    }
    @Then("George Red has the same number of pending items")
    public void thenSameNumberPendingItems() {
    }
    @Then("the return date of the loan is not created")
    public void thenReturnDateOfTheItemNotCreated() {
    }

}