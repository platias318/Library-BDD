package hellocucumber.StepDefinitions;

import hellocucumber.dao.*;
import hellocucumber.domain.*;
import hellocucumber.memorydao.BorrowerDAOMemory;
import hellocucumber.memorydao.ItemDAOMemory;
import hellocucumber.memorydao.LoanDAOMemory;
import hellocucumber.service.LoanService;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.List;

public class itemLoaningStepDefinitions {
    private LoanService loanService;
    private LocalDate dueDate1;
    private LocalDate dueDate2;
    private Borrower george_red;//The borrower persona that will be used for the whole feature
    private LoanDAO loanDao;
    private ItemDAO itemDao;
    private BorrowerDAO borrowerDao;
    private Book hPotter_book; //A book that will be used in this feature(title:"Harry Potter")
    private Book mDick_book; //A book that will be used in this feature(title:"Moby Dick")
    private Item hPotter_item;//The item of the book we instantiated above
    private Item mDick_item;//The item of the book we instantiated above
    private Loan hPotter_loan;//The loan that will be used for the book "Harry Potter"
    private Loan mDick_loan;//The loan that will be used for the book "Moby Dick"
    @Before
    public void setUp(){ //Gets called before each scenario
        itemDao = new ItemDAOMemory();
        loanDao = new LoanDAOMemory();
        borrowerDao = new BorrowerDAOMemory();
        loanService = new LoanService();
        clearData();
    }
    public void clearData(){//Gets called to delete all the data in the DAOs
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
    @Given("the library has the item Harry Potter available")
    public void givenItemHarryPotterAvailable() { //We create the books and the copies needed for the scenario to be executed
        hPotter_book = new Book();
        hPotter_book.setTitle("Harry Potter");
        hPotter_item = new Item();
        hPotter_item.setBook(hPotter_book);
        hPotter_book.addItem(hPotter_item);
        hPotter_item.available();
        hPotter_item.setItemNumber(1001112);
        itemDao.save(hPotter_item);
    }
    @Given("{borrower} is a registered borrower")//We create the borrower that will be used in the scenario
    public void givenBorrowerRegistered(Borrower borrower) {
        george_red = borrower;
        borrowerDao.save(george_red);
    }
    @Given("George Red has {int} pending items to be returned") //We add dummy loans to the borrower so that he has pending items
    public void givenPendingItemsToBeReturned(Integer pendingItems) {
        for(int i=0;i<pendingItems;i++) {
            Loan dummy_loan = new Loan();
            dummy_loan.setBorrower(george_red);
        }
    }
    @Given("George Red has been assigned a maximum lending limit of {int}")//We add a borrower category with a specific max lending limit number
    public void givenBorrowerMaxLendingLimit(Integer maxLendingLimit) {
        BorrowerCategory category = new BorrowerCategory();
        category.setMaxLendingItems(maxLendingLimit);
        category.setMaxLendingDays(5); //dummy number that will be used later
        george_red.setCategory(category);
    }
    @When("George Red borrows the item Harry Potter")
    public void  whenBorrowerBorrowsItemHarryPotter() {
            if(loanService.findBorrower(george_red.getBorrowerNo())){
                dueDate1 = loanService.borrow(hPotter_item.getItemNumber());
            }
    }
    @Then("the system successfully loans the item Harry Potter to George Red with a due date set")
    public void thenSystemLoansItemHarryPotterToGeorgeRed() {
        Assertions.assertEquals(george_red,loanDao.findPending(hPotter_item.getItemNumber()).getBorrower());//checking if the loan is saved
        Assertions.assertNotNull(dueDate1);//making sure that a due date for the loan has been given
    }
    @Then("George Red's pending items increase to {int}")
    public void thenPendingItemsIncrease(Integer pendingItems) {
        Assertions.assertEquals(pendingItems,george_red.countPendingItems());
    }
    @Given("the library has the items Harry Potter and Moby Dick available")
    public void givenItemsHarryPotterAndMobyDickAvailable() {//Instantiating and creating the books and the copies for the scenario to pass
        hPotter_book = new Book();
        hPotter_book.setTitle("Harry Potter");
        hPotter_item = new Item();
        hPotter_item.setBook(hPotter_book);
        hPotter_book.addItem(hPotter_item);
        hPotter_item.available();
        hPotter_item.setItemNumber(1001112);
        itemDao.save(hPotter_item);

        mDick_book = new Book();
        mDick_book.setTitle("Moby Dick");
        mDick_item = new Item();
        mDick_item.setBook(mDick_book);
        mDick_book.addItem(mDick_item);
        mDick_item.available();
        mDick_item.setItemNumber(1001233);
        itemDao.save(mDick_item);
    }

    @When("George Red tries to borrow both items")
    public void whenBorrowerTriesToBorrowBothItems() {
        if(loanService.findBorrower(george_red.getBorrowerNo())){
            dueDate1 = loanService.borrow(hPotter_item.getItemNumber());
        }
        if(loanService.findBorrower(george_red.getBorrowerNo())){
            dueDate2 = loanService.borrow(mDick_item.getItemNumber());
        }
    }

    @Then("the system does not loan Moby Dick to George Red due to the lending limit reached")
    public void thenSystemDoesNotLoanMobyDickToGeorgeRed() {
        Assertions.assertNull(dueDate2);//We make sure that the system didn't let the user borrow the book moby dick
        Assertions.assertEquals(george_red.countPendingItems(),george_red.getCategory().getMaxLendingItems()); //Making sure the borrower has reached the max lending items he was assigned
    }

    @Given("the item Harry Potter is in the library but not in the system")
    public void givenItemHarryPotterInLibraryNotInSystem() {
        hPotter_book = new Book();
        hPotter_book.setTitle("Harry Potter");
        hPotter_item = new Item();
        hPotter_item.setBook(hPotter_book);
        hPotter_book.addItem(hPotter_item);
        hPotter_item.setItemNumber(1001112);
        hPotter_item.available();
        hPotter_item.withdraw();
    }

    @When("George Red tries to borrow the item Harry Potter")
    public void whenBorrowerTriesToBorrowItemHarryPotter() {
        if(loanService.findBorrower(george_red.getBorrowerNo())){
            dueDate1 = loanService.borrow(hPotter_item.getItemNumber());
        }
    }
    @Then("the system returns an error due to the item's status")
    public void thenSystemReturnsErrorDueToItemsStatus() {
        Assertions.assertNull(dueDate1);
    }
    @Then("the system withdraws the item Harry Potter")
    public void thenSystemWithdrawsItemHarryPotter() {
        Assertions.assertEquals(ItemState.WITHDRAWN, hPotter_item.getState());
    }

    @Then("the system doesn't allow the loan")
    public void thenSystemDoesNotAllowLoan() {
        Assertions.assertNull(loanDao.findPending(hPotter_item.getItemNumber()));//We make sure that the loan was not saved in the dao
    }
    @Then("George Red's pending items remain {int}")
    public void thenBorrowerPendingItemsRemain(Integer pendingItems) {
        Assertions.assertEquals(pendingItems,george_red.countPendingItems());
    }
}
