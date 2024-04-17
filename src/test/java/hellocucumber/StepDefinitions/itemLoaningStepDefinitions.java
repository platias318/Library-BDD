package hellocucumber.StepDefinitions;

import hellocucumber.dao.*;
import hellocucumber.domain.*;
import hellocucumber.memorydao.BorrowerDAOMemory;
import hellocucumber.memorydao.ItemDAOMemory;
import hellocucumber.memorydao.LoanDAOMemory;
import hellocucumber.service.LoanService;
import hellocucumber.support.LibraryWorld;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.List;

public class itemLoaningStepDefinitions {
    // Instance variables
    private LocalDate dueDate1; //Due date for the book Harry Potter
    private LocalDate dueDate2; //Due date for the book Moby Dick
    private Borrower georgeRed;
    private Item hPotterItem;
    private Item mDickItem;
    private final LibraryWorld world;
    public itemLoaningStepDefinitions(LibraryWorld world){
        this.world=world;
    }
    @Before
    public void setUp(){
        world.clearItems();
        world.clearLoans();
        world.clearBorrowers();
    }
    @Given("the library has the item Harry Potter available")
    public void givenItemHarryPotterAvailable() {
        // Create and save the item/book Harry Potter
        hPotterItem=world.createItem("Harry Potter", 1001112);
    }
    @Given("{borrower} is a registered borrower")
    public void givenBorrowerRegistered(Borrower borrower) {
        georgeRed = borrower;
    }
    @Given("George Red has {int} pending items to be returned")
    public void givenPendingItemsToBeReturned(Integer pendingItems) {
        // Adding dummy loans to create pending items (not reflected in DAO)
        for(int i=0;i<pendingItems;i++) {
            Loan dummy_loan = new Loan();
            dummy_loan.setBorrower(georgeRed);
        }
    }
    @Given("George Red has been assigned a maximum lending limit of {int}")
    public void givenBorrowerMaxLendingLimit(Integer maxLendingLimit) {
        BorrowerCategory category = new BorrowerCategory();
        category.setMaxLendingItems(maxLendingLimit);
        category.setMaxLendingDays(5); //dummy number that will be used later
        georgeRed.setCategory(category);
    }
    @When("George Red borrows the item Harry Potter")
    public void  whenBorrowerBorrowsItemHarryPotter() {
        // Attempt to borrow the item and get due date if successful
        if(world.loanService.findBorrower(georgeRed.getBorrowerNo())){
            dueDate1 = world.loanService.borrow(hPotterItem.getItemNumber());
        }
    }
    @Then("the system successfully loans the item Harry Potter to George Red with a due date set")
    public void thenSystemLoansItemHarryPotterToGeorgeRed() {
        Assertions.assertEquals(georgeRed,world.loanDao.findPending(hPotterItem.getItemNumber()).getBorrower());
        Assertions.assertNotNull(dueDate1);
    }
    @Then("George Red's pending items increase to {int}")
    public void thenPendingItemsIncrease(Integer pendingItems) {
        Assertions.assertEquals(pendingItems, georgeRed.countPendingItems());
    }
    @Given("the library has the items Harry Potter and Moby Dick available")
    public void givenItemsHarryPotterAndMobyDickAvailable() {
        // Create and save the book and the item Harry Potter
        hPotterItem=world.createItem("Harry Potter", 1001112);
        // Create and save the book and the item Moby Dick
        mDickItem = world.createItem("Moby Dick",1001233);
    }

    @When("George Red tries to borrow both items")
    public void whenBorrowerTriesToBorrowBothItems() {
        if(world.loanService.findBorrower(georgeRed.getBorrowerNo())){
            dueDate1 = world.loanService.borrow(hPotterItem.getItemNumber());
        }
        if(world.loanService.findBorrower(georgeRed.getBorrowerNo())){
            dueDate2 = world.loanService.borrow(mDickItem.getItemNumber());
        }
    }

    @Then("the system does not loan Moby Dick to George Red due to the lending limit reached")
    public void thenSystemDoesNotLoanMobyDickToGeorgeRed() {
        Assertions.assertNull(dueDate2);
        Assertions.assertEquals(georgeRed.countPendingItems(), georgeRed.getCategory().getMaxLendingItems());
    }

    @Given("the item Harry Potter is in the library but not in the system")
    public void givenItemHarryPotterInLibraryNotInSystem() {
        // Create Harry Potter item and mark it as withdrawn
        hPotterItem=world.createItem("Harry Potter", 1001112);
        hPotterItem.withdraw();
    }

    @When("George Red tries to borrow the item Harry Potter")
    public void whenBorrowerTriesToBorrowItemHarryPotter() {
        if(world.loanService.findBorrower(georgeRed.getBorrowerNo())){
            dueDate1 = world.loanService.borrow(hPotterItem.getItemNumber());
        }
    }
    @Then("the system returns an error due to the item's status")
    public void thenSystemReturnsErrorDueToItemsStatus() {
        Assertions.assertNull(dueDate1);
    }
    @Then("the system withdraws the item Harry Potter")
    public void thenSystemWithdrawsItemHarryPotter() {
        // Assert that the item's state is withdrawn
        Assertions.assertEquals(ItemState.WITHDRAWN, hPotterItem.getState());
    }

    @Then("the system doesn't allow the loan")
    public void thenSystemDoesNotAllowLoan() {
        Assertions.assertNull(world.loanDao.findPending(hPotterItem.getItemNumber()));
    }
    @Then("George Red's pending items remain {int}")
    public void thenBorrowerPendingItemsRemain(Integer pendingItems) {
        Assertions.assertEquals(pendingItems, georgeRed.countPendingItems());
    }
}
