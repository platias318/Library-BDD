Feature: Borrower handling by the system
  The system can register a new person, modify their credentials or delete their account

  User Story: As a librarian,
  I want to efficiently manage the loaning process of books to registered borrowers
  So that borrowers can easily borrow books from the library.

  Scenario: Registering a new borrower
  This scenario describes the process of registering a new borrower in the library system
    Given George Red is not registered as a borrower
    When George Red gets registered in the system with a unique borrower number and his details
    Then the system successfully stores the borrower's details

  Scenario: Borrower trying to register has registered before
  This scenario describes what happens when the system tries to register a new borrower who has already registered before
    Given George Red is already registered as a borrower
    When the system attempts to register George Red with a unique borrower number and his details
    Then the system informs that the user already exists

  Scenario: Updating the borrower's details when he is registered
  This scenario describes the process of updating the details of a borrower who has already registered before
    Given George Red is registered as a borrower
    When George Red updates his borrowing details
    Then the system saves the changes

  Scenario: Updating the borrower's details when he hasn't registered
  This scenario describes what happens when the system tries to update the details of a borrower who has not registered before
    Given George Red is not registered as a borrower
    When George Red tries to update his borrowing details
    Then the system displays an error message indicating that George Red does not exist

  Scenario: Deleting a borrower
  This scenario describes the process of deleting the account of a borrower who has already registered
    Given George Red is registered as a borrower
    When the system deletes George Red's account
    Then the system removes George Red's details

  Scenario: Deleting a borrower when he hasn't registered
  This scenario describes what happens when the system tries to delete the account of a borrower who has not registered before
    Given George Red is not registered as a borrower
    When the system attempts to delete George Red's account
    Then the system displays an error message indicating that George Red does not exist

  Scenario: Handling unreturned items of books
  This scenario describes what happens when the system tries to delete the account of a borrower who has pending items to return
    Given George Red is registered as a borrower
    And George Red has pending items
    When the system attempts to delete George Red's account
    Then the system does not remove George Red's details
    And the system informs about the pending items