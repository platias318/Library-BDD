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
import hellocucumber.service.EmailProvider;
import hellocucumber.service.EmailProviderStub;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class borrowerHandlingStepDefinitions {
    // Instance variables
    private Boolean answer; //This parameter is used for boolean responses in the scenarios
    private BorrowerDAO borrowerDao; //The DAO where all the borrowers will be saved
    private Borrower george_red; //The borrower persona that will be used for the whole feature

    // Method to initialize DAOs and service before each scenario
    @Before
    public void setUp(){
        borrowerDao = new BorrowerDAOMemory();
        clearData();
    }
    // Method to clear data in DAOs
    public void clearData() {
        // Delete all borrowers
        List<Borrower> allBorrowers = borrowerDao.findAll();
        for (Borrower borrower : allBorrowers) {
            borrowerDao.delete(borrower);
        }
    }
    @Given("{borrower} is not registered as a borrower")
    public void givenBorrowerNotRegistered(Borrower borrower) {
        // Save the borrower
        george_red=borrower;
        //makes sure the borrower is not inside the system
        borrowerDao.delete(borrowerDao.find(george_red.getFirstName(), george_red.getLastName()));
    }
    @When("George Red gets registered in the system with a unique borrower number and his details")
    public void whenGeorgeRedGetsRegisteredInSystem() {
        //Create and save the borrower with dummy details
        george_red.setBorrowerNo(101);
        george_red.setCategory(new BorrowerCategory());
        Address address = new Address();
        address.setCity("Athens");
        address.setNumber("5");
        address.setZipCode(new ZipCode("111"));
        address.setStreet("Patisiwn");
        george_red.setAddress(new Address(address));
        george_red.setEmail(new EmailAddress("georgeRed@gmail.com"));
        george_red.setTelephone(new TelephoneNumber("6987654321"));
        answer = borrowerDao.save(george_red);
    }
    @Then("the system successfully stores the borrower's details")
    public void thenSystemStoresBorrowerDetails() {
        //Assert that ALL the borrowers details are saved in the DAO right
        Assertions.assertNotNull(borrowerDao.find(george_red.getFirstName(), george_red.getLastName())); //make sure that the borrower is saved
        Assertions.assertEquals(borrowerDao.find(george_red.getFirstName(), george_red.getLastName()).getCategory(),george_red.getCategory());
        Assertions.assertEquals(borrowerDao.find(george_red.getFirstName(), george_red.getLastName()).getBorrowerNo(),george_red.getBorrowerNo());
        Assertions.assertEquals(borrowerDao.find(george_red.getFirstName(), george_red.getLastName()).getAddress(),george_red.getAddress());
        Assertions.assertEquals(borrowerDao.find(george_red.getFirstName(), george_red.getLastName()).getEmail(),george_red.getEmail());
        Assertions.assertEquals(borrowerDao.find(george_red.getFirstName(), george_red.getLastName()).getTelephone(),george_red.getTelephone());
    }
    @Given("{borrower} is registered as a borrower")
    public void givenBorrowerRegistered(Borrower borrower) {
        //save the borrower
        george_red=borrower;
        borrowerDao.save(george_red);
    }
    @When("the system attempts to register George Red with a unique borrower number and his details")
    public void whenSystemAttemptsRegisterGeorgeRed() {
        //Create, and attempt to save the borrower while he is already inside the DAO
        george_red.setBorrowerNo(101);
        george_red.setCategory(new BorrowerCategory());
        Address address = new Address();
        address.setCity("Athens");
        address.setNumber("5");
        address.setZipCode(new ZipCode("111"));
        address.setStreet("Patisiwn");
        george_red.setAddress(new Address(address));
        george_red.setEmail(new EmailAddress("georgeRed@gmail.com"));
        george_red.setTelephone(new TelephoneNumber("6987654321"));
        //This variable is true if the process was completed and false if it wasn't
        answer = borrowerDao.save(george_red);
    }
    @Then("the system informs that the user already exists")
    public void thenSystemInformsUserAlreadyExists() {
        // Assert that the user wasn't saved again, based on the answer from the previous step
        Assertions.assertFalse(answer);
    }
    @When("George Red updates his borrowing details")
    public void whenGeorgeRedUpdatesDetails()  {
        //Updating some of the borrower's details
        george_red.setEmail(new EmailAddress("george123@gmail.com"));
        george_red.setTelephone(new TelephoneNumber("6948675838"));
    }
    @Then("the system saves the changes")
    public void thenSystemSavesChanges() {
        // Assert that the details inside the DAO are updated based on the previous provided details
        Assertions.assertEquals(borrowerDao.find(george_red.getFirstName(), george_red.getLastName()).getTelephone(),george_red.getTelephone());
        Assertions.assertEquals(borrowerDao.find(george_red.getFirstName(), george_red.getLastName()).getEmail(),george_red.getEmail());
    }
    @When("George Red tries to update his borrowing details")
    public void whenGeorgeRedTriesUpdateDetails() {
        //Attempting to update some of the borrower's details
        george_red.setEmail(new EmailAddress("george123@gmail.com"));
        george_red.setTelephone(new TelephoneNumber("6948675838"));
    }
    @Then("the system displays an error message indicating that George Red does not exist")
    public void thenSystemDisplaysErrorMessageUserDoesNotExist() {
        // Assert that the borrower is not in the system, so there can't be any updated details in the DAO
        Assertions.assertNull(borrowerDao.find(george_red.getFirstName(), george_red.getLastName()));
    }
    @When("the system deletes George Red's account")
    public void whenSystemDeletesAccount() {
        //Deleting the borrower if there aren't any pending items, and capturing the response of the operation in the variable answer
        if(george_red.countPendingItems()==0) {
            answer = borrowerDao.delete(george_red);
        }
    }
    @Then("the system removes George Red's details")
    public void thenSystemRemovesDetails() {
        //Assert that the borrower was deleted from the DAO
        Assertions.assertNull(borrowerDao.find(george_red.getFirstName(), george_red.getLastName()));
    }
    @When("the system attempts to delete George Red's account")
    public void whenSystemAttemptsDeleteAccount() {
        //Attempting to delete the borrower if there aren't any pending items, and capturing the response of the operation in the variable answer
        if(george_red.countPendingItems()==0) {
            answer = borrowerDao.delete(george_red);
        }
    }
    @Then("the system informs that the borrower does not exist")
    public void theSystemInformsThatTheBorrowerDoesNotExist() {
        // Assert that the answer was false, so the deletion operation didn't happen
        Assertions.assertFalse(answer);
    }
    @Given("George Red has pending items")
    public void givenGeorgeRedHasPendingItems() {
        //Adding dummy loans to the borrower so that he has some pending items
            Loan dummy_loan = new Loan();
            dummy_loan.setBorrower(george_red);
    }
    @Then("the system does not remove George Red's details")
    public void thenSystemDoesNotRemoveDetails() {
        // Assert that the borrower was not deleted because of his pending items
        Assertions.assertNotNull(borrowerDao.find(george_red.getFirstName(), george_red.getLastName()));
    }
    @Then("the system informs about the pending items")
    public void thenSystemInformsAboutPendingItems() {
        // Assert that the reason was his pending items(so we check if he had pending items)
        Assertions.assertNotEquals(0,george_red.countPendingItems());
    }

}
