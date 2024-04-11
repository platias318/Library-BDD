package hellocucumber.StepDefinitions;

import hellocucumber.contacts.Address;
import hellocucumber.contacts.EmailAddress;
import hellocucumber.contacts.TelephoneNumber;
import hellocucumber.contacts.ZipCode;
import hellocucumber.dao.BorrowerDAO;
import hellocucumber.domain.Borrower;
import hellocucumber.domain.BorrowerCategory;
import hellocucumber.memorydao.BorrowerDAOMemory;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class borrowerHandlingStepDefinitions {
    BorrowerDAO borrowerDao;
    Borrower george_red;
    Borrower dummy_borrower;
    @Before
    public void setUp(){
        borrowerDao = new BorrowerDAOMemory();
        clearData();
        george_red=null;
        dummy_borrower=null;
    }
    public void clearData() {
        List<Borrower> allBorrowers = borrowerDao.findAll();
        for (Borrower borrower : allBorrowers) {
            borrowerDao.delete(borrower);
        }
    }
    @Given("{borrower} is not registered as a borrower")
    public void givenBorrowerNotRegistered(Borrower borrower) {
        george_red=borrower;
        borrowerDao.delete(borrowerDao.find(george_red.getFirstName(), george_red.getLastName())); //makes sure the borrower is not inside the system
    }
    @When("George Red gets registered in the system with a unique borrower number and his details")
    public void whenGeorgeRedGetsRegisteredInSystem() {
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
        borrowerDao.save(george_red);
    }
    @Then("the system successfully stores the borrower's details")
    public void thenSystemStoresBorrowerDetails() {
        Assertions.assertNotNull(borrowerDao.find(george_red.getFirstName(), george_red.getLastName())); //make sure that the borrower is saved
        Assertions.assertEquals(borrowerDao.find(george_red.getFirstName(), george_red.getLastName()).getCategory(),george_red.getCategory()); //check if the borrower category is saved correctly
        Assertions.assertEquals(borrowerDao.find(george_red.getFirstName(), george_red.getLastName()).getBorrowerNo(),george_red.getBorrowerNo()); //check if the borrower number is saved correctly
        Assertions.assertEquals(borrowerDao.find(george_red.getFirstName(), george_red.getLastName()).getAddress(),george_red.getAddress()); //check if the borrower address is saved correctly
        Assertions.assertEquals(borrowerDao.find(george_red.getFirstName(), george_red.getLastName()).getEmail(),george_red.getEmail()); //check if the email is saved correctly
        Assertions.assertEquals(borrowerDao.find(george_red.getFirstName(), george_red.getLastName()).getTelephone(),george_red.getTelephone()); //check if the telephone number is saved correctly
    }
    @Given("{borrower} is registered as a borrower")
    public void givenBorrowerRegistered(Borrower borrower) {
        george_red=borrower;
        borrowerDao.save(george_red);
    }
    @When("the system attempts to register George Red with a unique borrower number and his details")
    public void whenSystemAttemptsRegisterGeorgeRed() {
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
        dummy_borrower = borrowerDao.find(george_red.getFirstName(),george_red.getLastName());
    }
    @Then("the system informs that the user already exists")
    public void thenSystemInformsUserAlreadyExists() {
        Assertions.assertNotNull(dummy_borrower);
    }
    @When("George Red updates his borrowing details")
    public void whenGeorgeRedUpdatesDetails()  {
        george_red.setEmail(new EmailAddress("george123@gmail.com"));
        george_red.setTelephone(new TelephoneNumber("6948675838"));
    }
    @Then("the system saves the changes")
    public void thenSystemSavesChanges() {
        Assertions.assertEquals(borrowerDao.find(george_red.getFirstName(), george_red.getLastName()).getTelephone(),george_red.getTelephone());
        Assertions.assertEquals(borrowerDao.find(george_red.getFirstName(), george_red.getLastName()).getEmail(),george_red.getEmail());
    }
    @When("George Red tries to update his borrowing details")
    public void whenGeorgeRedTriesUpdateDetails() {
        george_red.setEmail(new EmailAddress("george123@gmail.com"));
        george_red.setTelephone(new TelephoneNumber("6948675838"));
    }
    @Then("the system displays an error message indicating that George Red does not exist")
    public void thenSystemDisplaysErrorMessageUserDoesNotExist() {
        Assertions.assertNull(borrowerDao.find(george_red.getFirstName(), george_red.getLastName()));
    }
    @When("the system deletes George Red's account")
    public void whenSystemDeletesAccount() {
        borrowerDao.delete(george_red);
    }
    @Then("the system removes George Red's details")
    public void thenSystemRemovesDetails() {
        Assertions.assertNull(borrowerDao.find(george_red.getFirstName(), george_red.getLastName()));
    }
    @When("the system attempts to delete George Red's account")
    public void whenSystemAttemptsDeleteAccount() {
    }
    @Given("George Red has pending items")
    public void givenGeorgeRedHasPendingItems() {
    }
    @Then("the system does not remove George Red's details")
    public void thenSystemDoesNotRemoveDetails() {
    }
    @Then("the system informs about the pending items")
    public void thenSystemInformsAboutPendingItems() {
    }

}
