Feature: Borrower handling by the system
  The system can register new people, modify their credentials or delete their account

  User Story: As a librarian
  I want to be able to manage loaning books to people
  So that borrowers can borrow books

  Scenario: Registering a New Borrower
  This scenario describes the process of registering a new borrower in the library
    Given "Barry Alen" wants to register to become a borrower
    And "Barry Alen" hasn't already registered
    When "Barry Alen" gets registered in the system with a unique borrowerΝο and his details
    Then the system stores the borrower's details successfully

  Scenario: Borrower trying to register already exists
  This scenario describes what happens the system tries to register a new borrower who has already registered before
    Given "Barry Alen" wants to register to become a borrower
    And "Barry Alen" has already registered
    When "Barry Alen" gets registered in the system with a unique borrowerΝο and his details
    Then the system informs that the user already exists

  Scenario: Updating Borrower details when he exists
  This scenario describes the process of updating the details of a borrower who has already registered before
    Given "Barry Alen" is a borrower
    And "Barry Alen" wants to update his borrowing details
    When the borrower's details are updated
    Then the system saves the changes

  Scenario: Updating Borrower details when he doesn't exist
  This scenario describes what happens when the system tries to update the details of a borrower who has not registered before
    Given "Barry Alen" isn't a borrower
    And "Barry Alen" wants to update his borrowing details
    When the borrower's details are updated
    Then the system displays an error message indicating that the borrower does not exist

  Scenario: Deleting a Borrower
  This scenario describes the process of  deleting the account of a borrower who has already registered
    Given "Barry Alen" is a borrower
    And "Barry Alen" wants to delete his account
    When the deletion of "Barry Alen"'s account is attempted
    Then the system removes the borrower's details

  Scenario: Deleting a Borrower that doesn't exist
  This scenario describes what happens when the system tries to delete the account of a borrower who has not registered before
    Given "Barry Alen" isn't a borrower
    And "Barry Alen" wants to delete his account
    When the deletion of "Barry Alen"'s account is attempted
    Then the system displays an error message indicating that the borrower does not exist

  Scenario: Handling Unreturned items of Books
  This scenario describes what happens when the system tries to delete the account of a borrower who has pending items to return
    Given "Barry Alen" is a borrower
    And "Barry Alen" has pending items
    When the deletion of "Barry Alen"'s account is attempted
    Then the system doesn't remove the borrower's details
    And the system informs about the non returned items