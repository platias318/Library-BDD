package hellocucumber.StepDefinitions;

import hellocucumber.contacts.Address;
import hellocucumber.contacts.EmailAddress;
import hellocucumber.contacts.TelephoneNumber;
import hellocucumber.contacts.ZipCode;
import hellocucumber.dao.BorrowerDAO;
import hellocucumber.domain.Borrower;
import hellocucumber.domain.BorrowerCategory;
import hellocucumber.domain.Loan;
import hellocucumber.memorydao.BorrowerDAOMemory;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class borrowerHandlingStepDefinitions {
    // Instance variables
    private Boolean answer; //This parameter is used for boolean responses in the scenarios
    private BorrowerDAO borrowerDao;
    private Borrower georgeRed;
    @Before
    public void setUp(){
        borrowerDao = new BorrowerDAOMemory();
        clearData();
    }
    public void clearData() {
        List<Borrower> allBorrowers = borrowerDao.findAll();
        for (Borrower borrower : allBorrowers) {
            borrowerDao.delete(borrower);
        }
    }
    @Given("{borrower} is not registered as a borrower")
    public void givenBorrowerNotRegistered(Borrower borrower) {
        georgeRed =borrower;
        borrowerDao.delete(borrowerDao.find(georgeRed.getFirstName(), georgeRed.getLastName()));
    }
    @When("George Red gets registered in the system with a unique borrower number and his details")
    public void whenGeorgeRedGetsRegisteredInSystem() {
        //Create and save the borrower with dummy details
        georgeRed.setBorrowerNo(101);
        georgeRed.setCategory(new BorrowerCategory());
        Address address = new Address();
        address.setCity("Athens");
        address.setNumber("5");
        address.setZipCode(new ZipCode("111"));
        address.setStreet("Patisiwn");
        georgeRed.setAddress(new Address(address));
        georgeRed.setEmail(new EmailAddress("georgeRed@gmail.com"));
        georgeRed.setTelephone(new TelephoneNumber("6987654321"));
        answer = borrowerDao.save(georgeRed);
    }
    @Then("the system successfully stores the borrower's details")
    public void thenSystemStoresBorrowerDetails() {
        //Assert that ALL the borrowers details are saved in the system correctly
        Assertions.assertNotNull(borrowerDao.find(georgeRed.getFirstName(), georgeRed.getLastName()));
        Assertions.assertEquals(borrowerDao.find(georgeRed.getFirstName(), georgeRed.getLastName()).getCategory(), georgeRed.getCategory());
        Assertions.assertEquals(borrowerDao.find(georgeRed.getFirstName(), georgeRed.getLastName()).getBorrowerNo(), georgeRed.getBorrowerNo());
        Assertions.assertEquals(borrowerDao.find(georgeRed.getFirstName(), georgeRed.getLastName()).getAddress(), georgeRed.getAddress());
        Assertions.assertEquals(borrowerDao.find(georgeRed.getFirstName(), georgeRed.getLastName()).getEmail(), georgeRed.getEmail());
        Assertions.assertEquals(borrowerDao.find(georgeRed.getFirstName(), georgeRed.getLastName()).getTelephone(), georgeRed.getTelephone());
    }
    @Given("{borrower} is registered as a borrower")
    public void givenBorrowerRegistered(Borrower borrower) {
        georgeRed =borrower;
        borrowerDao.save(georgeRed);
    }
    @When("the system attempts to register George Red with a unique borrower number and his details")
    public void whenSystemAttemptsRegisterGeorgeRed() {
        //Create, and attempt to save the borrower
        georgeRed.setBorrowerNo(101);
        georgeRed.setCategory(new BorrowerCategory());
        Address address = new Address();
        address.setCity("Athens");
        address.setNumber("5");
        address.setZipCode(new ZipCode("111"));
        address.setStreet("Patisiwn");
        georgeRed.setAddress(new Address(address));
        georgeRed.setEmail(new EmailAddress("georgeRed@gmail.com"));
        georgeRed.setTelephone(new TelephoneNumber("6987654321"));
        //This variable is true if the process was completed and false if it wasn't
        answer = borrowerDao.save(georgeRed);
    }
    @Then("the system informs that the user already exists")
    public void thenSystemInformsUserAlreadyExists() {
        Assertions.assertFalse(answer);
    }
    @When("George Red updates his borrowing details")
    public void whenGeorgeRedUpdatesDetails()  {
        georgeRed.setEmail(new EmailAddress("george123@gmail.com"));
        georgeRed.setTelephone(new TelephoneNumber("6948675838"));
    }
    @Then("the system saves the changes")
    public void thenSystemSavesChanges() {
        Assertions.assertEquals(borrowerDao.find(georgeRed.getFirstName(), georgeRed.getLastName()).getTelephone(), georgeRed.getTelephone());
        Assertions.assertEquals(borrowerDao.find(georgeRed.getFirstName(), georgeRed.getLastName()).getEmail(), georgeRed.getEmail());
    }
    @When("George Red tries to update his borrowing details")
    public void whenGeorgeRedTriesUpdateDetails() {
        georgeRed.setEmail(new EmailAddress("george123@gmail.com"));
        georgeRed.setTelephone(new TelephoneNumber("6948675838"));
    }
    @Then("the system displays an error message indicating that George Red does not exist")
    public void thenSystemDisplaysErrorMessageUserDoesNotExist() {
        // Assert that the borrower is not in the system, so there can't be any updated details in the DAO
        Assertions.assertNull(borrowerDao.find(georgeRed.getFirstName(), georgeRed.getLastName()));
    }
    @When("the system deletes George Red's account")
    public void whenSystemDeletesAccount() {
        //Deleting the borrower if there aren't any pending items
        if(georgeRed.countPendingItems()==0) {
            answer = borrowerDao.delete(georgeRed);
        }
    }
    @Then("the system removes George Red's details")
    public void thenSystemRemovesDetails() {
        Assertions.assertNull(borrowerDao.find(georgeRed.getFirstName(), georgeRed.getLastName()));
    }
    @When("the system attempts to delete George Red's account")
    public void whenSystemAttemptsDeleteAccount() {
        if(georgeRed.countPendingItems()==0) {
            answer = borrowerDao.delete(georgeRed);
        }
    }
    @Then("the system informs that the borrower does not exist")
    public void theSystemInformsThatTheBorrowerDoesNotExist() {
        Assertions.assertFalse(answer);
    }
    @Given("George Red has pending items")
    public void givenGeorgeRedHasPendingItems() {
        //Adding dummy loans to the borrower so that he has some pending items
            Loan dummy_loan = new Loan();
            dummy_loan.setBorrower(georgeRed);
    }
    @Then("the system does not remove George Red's details")
    public void thenSystemDoesNotRemoveDetails() {
        Assertions.assertNotNull(borrowerDao.find(georgeRed.getFirstName(), georgeRed.getLastName()));
    }
    @Then("the system informs about the pending items")
    public void thenSystemInformsAboutPendingItems() {
        Assertions.assertNotEquals(0, georgeRed.countPendingItems());
    }

}
