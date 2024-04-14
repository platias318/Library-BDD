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
    // Instance variables
    private LoanService loanService; //An instance of the loanService class to help with the loaning of the copies
    private LocalDate dueDate1; //Due date for the book Harry Potter
    private LocalDate dueDate2; //Due date for the book Moby Dick
    private Borrower george_red; //The borrower persona that will be used for the whole feature
    private LoanDAO loanDao; //The DAO where all the loans will be saved
    private ItemDAO itemDao; //The DAO where all the items will be saved
    private BorrowerDAO borrowerDao; //The DAO where all the borrowers will be saved
    private Book hPotter_book; //A book that will be used in this feature(title:"Harry Potter")
    private Book mDick_book; //A book that will be used in this feature(title:"Moby Dick")
    private Item hPotter_item; //The item of the book we instantiated above
    private Item mDick_item; //The item of the book we instantiated above

    // Method to initialize DAOs and service before each scenario
    @Before
    public void setUp(){
        itemDao = new ItemDAOMemory();
        loanDao = new LoanDAOMemory();
        borrowerDao = new BorrowerDAOMemory();
        loanService = new LoanService();
        clearData();
    }
    // Method to clear data in DAOs
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
    //Creating the books and the items needed for the scenarios to be executed
    @Given("the library has the item Harry Potter available")
    public void givenItemHarryPotterAvailable() {
        // Create and save the item/book Harry Potter
        hPotter_book = new Book();
        hPotter_book.setTitle("Harry Potter");
        hPotter_item = new Item();
        hPotter_item.setBook(hPotter_book);
        hPotter_book.addItem(hPotter_item);
        hPotter_item.available();
        hPotter_item.setItemNumber(1001112);
        itemDao.save(hPotter_item);
    }
    //Creating the borrower that will be used in the scenario
    @Given("{borrower} is a registered borrower")
    public void givenBorrowerRegistered(Borrower borrower) {
        // Save the borrower
        george_red = borrower;
        borrowerDao.save(george_red);
    }
    @Given("George Red has {int} pending items to be returned")
    public void givenPendingItemsToBeReturned(Integer pendingItems) {
        // Adding dummy loans to create pending items (not reflected in DAO)
        for(int i=0;i<pendingItems;i++) {
            Loan dummy_loan = new Loan();
            dummy_loan.setBorrower(george_red);
        }
    }
    @Given("George Red has been assigned a maximum lending limit of {int}")
    public void givenBorrowerMaxLendingLimit(Integer maxLendingLimit) {
        // Set maximum lending limit for borrower category
        BorrowerCategory category = new BorrowerCategory();
        category.setMaxLendingItems(maxLendingLimit);
        category.setMaxLendingDays(5); //dummy number that will be used later
        george_red.setCategory(category);
    }
    //Using the loanService object to complete the loan with a due date
    @When("George Red borrows the item Harry Potter")
    public void  whenBorrowerBorrowsItemHarryPotter() {
        // Attempt to borrow the item and get due date if successful
        if(loanService.findBorrower(george_red.getBorrowerNo())){
            dueDate1 = loanService.borrow(hPotter_item.getItemNumber());
        }
    }
    @Then("the system successfully loans the item Harry Potter to George Red with a due date set")
    public void thenSystemLoansItemHarryPotterToGeorgeRed() {
        // Assert that loan is saved and due date is set
        Assertions.assertEquals(george_red,loanDao.findPending(hPotter_item.getItemNumber()).getBorrower());
        Assertions.assertNotNull(dueDate1);
    }
    @Then("George Red's pending items increase to {int}")
    public void thenPendingItemsIncrease(Integer pendingItems) {
        // Assert that pending items count matches expected value
        Assertions.assertEquals(pendingItems,george_red.countPendingItems());
    }
    @Given("the library has the items Harry Potter and Moby Dick available")
    public void givenItemsHarryPotterAndMobyDickAvailable() {
        // Create and save the book and the item Harry Potter
        hPotter_book = new Book();
        hPotter_book.setTitle("Harry Potter");
        hPotter_item = new Item();
        hPotter_item.setBook(hPotter_book);
        hPotter_book.addItem(hPotter_item);
        hPotter_item.available();
        hPotter_item.setItemNumber(1001112);
        itemDao.save(hPotter_item);
        // Create and save the book and the item Moby Dick
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
        // Attempt to borrow both items and get due dates if successful
        if(loanService.findBorrower(george_red.getBorrowerNo())){
            dueDate1 = loanService.borrow(hPotter_item.getItemNumber());
        }
        if(loanService.findBorrower(george_red.getBorrowerNo())){
            dueDate2 = loanService.borrow(mDick_item.getItemNumber());
        }
    }

    @Then("the system does not loan Moby Dick to George Red due to the lending limit reached")
    public void thenSystemDoesNotLoanMobyDickToGeorgeRed() {
        // Assert that Moby Dick is not loaned and lending limit is reached
        Assertions.assertNull(dueDate2);
        Assertions.assertEquals(george_red.countPendingItems(),george_red.getCategory().getMaxLendingItems());
    }

    @Given("the item Harry Potter is in the library but not in the system")
    public void givenItemHarryPotterInLibraryNotInSystem() {
        // Create Harry Potter item and mark it as withdrawn
        hPotter_book = new Book();
        hPotter_book.setTitle("Harry Potter");
        hPotter_item = new Item();
        hPotter_item.setBook(hPotter_book);
        hPotter_book.addItem(hPotter_item);
        hPotter_item.setItemNumber(1001112);
        hPotter_item.available();//the book must be available before being withdrawn
        hPotter_item.withdraw();
    }

    @When("George Red tries to borrow the item Harry Potter")
    public void whenBorrowerTriesToBorrowItemHarryPotter() {
        // Attempt to borrow the item and get due date if successful
        if(loanService.findBorrower(george_red.getBorrowerNo())){
            dueDate1 = loanService.borrow(hPotter_item.getItemNumber());
        }
    }
    @Then("the system returns an error due to the item's status")
    public void thenSystemReturnsErrorDueToItemsStatus() {
        // Assert that no due date is returned due to item's withdrawn status
        Assertions.assertNull(dueDate1);
    }
    @Then("the system withdraws the item Harry Potter")
    public void thenSystemWithdrawsItemHarryPotter() {
        // Assert that the item's state is withdrawn
        Assertions.assertEquals(ItemState.WITHDRAWN, hPotter_item.getState());
    }

    @Then("the system doesn't allow the loan")
    public void thenSystemDoesNotAllowLoan() {
        // Assert that the loan is not saved
        Assertions.assertNull(loanDao.findPending(hPotter_item.getItemNumber()));
    }
    @Then("George Red's pending items remain {int}")
    public void thenBorrowerPendingItemsRemain(Integer pendingItems) {
        // Assert that pending items count remains unchanged
        Assertions.assertEquals(pendingItems,george_red.countPendingItems());
    }
}
