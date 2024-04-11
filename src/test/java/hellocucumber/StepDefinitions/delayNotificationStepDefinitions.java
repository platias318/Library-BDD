package hellocucumber.StepDefinitions;

import hellocucumber.domain.Borrower;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
public class delayNotificationStepDefinitions {
    @Given("the system calendar")
    public void givenSystemCalendar() {
    }
    @Given("the university's email server")
    public void givenUniversityEmailServer() {
    }
    @Given("the trigger is every Monday at {int}:{int} a.m.")
    public void givenTriggerCreation(Integer hour, Integer minute) {
    }
    @Given("{string}'s due date has passed")
    public void givenItemDueDatePassed(String itemName) {
    }
    @Given("{borrower} had borrowed the item")
    public void givenBorrowerHasBorrowedTheItem(Borrower borrower) {
    }
    @When("the system searches for the item {string}")
    public void whenSystemSearchesForItem(String itemName) {
    }
    @When("the system identifies that George Red has borrowed the item")
    public void whenSystemIdentifiesBorrowerHasItem() {
    }
    @Then("the system sends an email to George Red with the delayed item and the number of delay days")
    public void thenSystemSendsEmailWithDelayedItemAndDelayDays() {
    }
    @Given("George Red does not have an email address")
    public void givenGeorgeRedDoesNotHaveEmailAddress() {
    }
    @Then("the system ignores the borrower and George Red isn't notified")
    public void thenSystemIgnoresBorrowerAndGeorgeRedNotNotified() {
    }
    @When("the Email Server is not working")
    public void whenEmailServerNotWorking() {
    }
    @Then("the use case ends")
    public void thenUseCaseEnds() {
    }

}
