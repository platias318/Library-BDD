package hellocucumber;

import hellocucumber.dao.*;
import hellocucumber.domain.*;
import hellocucumber.memorydao.ItemDAOMemory;
import hellocucumber.memorydao.LoanDAOMemory;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.util.List;

public class itemLoaningStepDefinitions {
    private Borrower george_red;
    private LoanDAO loanDao;
    private ItemDAO itemDao;
    private Book hPotter_book;
    private Book mDick_book;
    private Item hPotter_item;
    private Item mDick_item;
    private Loan hPotter_loan;
    private Loan mDick_loan;
    @Before
    public void setUp(){
        itemDao = new ItemDAOMemory();
        loanDao = new LoanDAOMemory();
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
    }
    @Given("the library has the item Harry Potter available")
    public void the_library_has_the_item_available() {
        hPotter_book = new Book();
        hPotter_book.setTitle("Harry Potter");
        hPotter_item = new Item();
        hPotter_item.setBook(hPotter_book);
        hPotter_book.addItem(hPotter_item);
        hPotter_item.available();
        hPotter_item.setItemNumber(1001112);
        itemDao.save(hPotter_item);
    }
    @Given("{borrower} is a registered borrower")
    public void george_red_is_a_registered_borrower(Borrower borrower) {
        george_red = borrower;
    }
    @Given("George Red has {int} pending items to be returned")
    public void george_red_has_pending_items_to_be_returned(Integer pendingItems) {
        for(int i=0;i<pendingItems;i++) {
            Loan dummy_loan = new Loan();
            dummy_loan.setBorrower(george_red);
        }
    }
    @Given("George Red has been assigned a maximum lending limit of {int}")
    public void george_red_has_been_assigned_a_maximum_lending_limit_of(Integer maxLendingLimit) {
        BorrowerCategory category = new BorrowerCategory();
        category.setMaxLendingItems(maxLendingLimit);
        category.setMaxLendingDays(5); //dummy number that will be used later
        george_red.setCategory(category);
    }
    @When("George Red borrows the item Harry Potter")
    public void george_red_borrows_the_item() {
        if(george_red.canBorrow()) {
            hPotter_loan = new Loan(george_red, hPotter_item, LocalDate.of(2024, 10, 25));
            hPotter_loan.setBorrower(george_red);
            loanDao.save(hPotter_loan);
        }
    }
    @Then("the system successfully loans the item Harry Potter to George Red with a due date set")
    public void the_system_successfully_loans_the_item_to_george_red_with_a_return_date_set() {
        Assertions.assertEquals(george_red,loanDao.findPending(hPotter_item.getItemNumber()).getBorrower());
        Assertions.assertNotEquals(null,loanDao.findPending(hPotter_item.getItemNumber()).getDue());
    }
    @Then("George Red's pending items increase to {int}")
    public void george_red_s_pending_items_increase_to(Integer pendingItems) {
        Assertions.assertEquals(pendingItems,george_red.countPendingItems());
    }
    @Given("the library has the items Harry Potter and Moby Dick available")
    public void the_library_has_the_items_and_available() {
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
    public void george_red_tries_to_borrow_both_items() {
        if(george_red.canBorrow()) {
            hPotter_loan = new Loan(george_red, hPotter_item, LocalDate.of(2024, 10, 25));
            hPotter_loan.setBorrower(george_red);
            loanDao.save(hPotter_loan);
        }
        if(george_red.canBorrow()) {
            mDick_loan = new Loan(george_red, mDick_item, LocalDate.of(2024, 10, 25));
            mDick_loan.setBorrower(george_red);
            loanDao.save(mDick_loan);
        }
    }

    @Then("the system does not loan Moby Dick to George Red due to the lending limit reached")
    public void the_system_does_not_loan_to_due_to_the_lending_limit_reached() {
        Assertions.assertNull(loanDao.findPending(mDick_item.getItemNumber()));
        Assertions.assertEquals(george_red.countPendingItems(),george_red.getCategory().getMaxLendingItems());
    }

    @Given("the item Harry Potter is in the library but not in the system")
    public void the_item_is_in_the_library_but_not_in_the_system() {
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
    public void george_red_tries_to_borrow_the_item() {
        if(george_red.canBorrow()) {
            hPotter_loan = new Loan(george_red, hPotter_item, LocalDate.of(2024, 10, 25));
            hPotter_loan.setBorrower(george_red);
            loanDao.save(hPotter_loan);
        }
    }
    @Then("the system returns an error due to the item's status")
    public void the_system_returns_an_error_due_to_the_item_s_status() {
        Assertions.assertNull(loanDao.findPending(hPotter_item.getItemNumber()));
    }
    @Then("the system withdraws the item Harry Potter")
    public void the_system_withdraws_the_item() {
        Assertions.assertEquals(ItemState.WITHDRAWN, hPotter_item.getState());
    }

    @Then("the system doesn't allow the loan")
    public void the_system_doesn_t_allow_the_loan() {
        Assertions.assertNull(loanDao.findPending(hPotter_item.getItemNumber()));
    }
    @Then("George Red's pending items remain {int}")
    public void george_red_s_pending_items_remain(Integer pendingItems) {
        Assertions.assertEquals(pendingItems,george_red.countPendingItems());
    }
}
