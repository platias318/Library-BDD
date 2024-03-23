Feature: Borrower handling by the system
  The system can register new people, modify their credentials or delete their account

  User Story: As a librarian
  I want to be able to manage loaning books to people
  So that borrowers can borrow books

  Scenario: Registering a New Borrower
  This scenario describes the process of registering a new borrower in the library
    Given "Barry Alen" is not registered as a borrower
    When "Barry Alen" gets registered in the system with a unique borrowerΝο and his details
    Then the system successfully stores the borrower's details

  Scenario: Borrower trying to register already exists
  This scenario describes what happens the system tries to register a new borrower who has already registered before
    Given "Barry Alen" is already registered as a borrower
    When the system attempts to register "Barry Alen" with a unique borrowerΝο and his details
    Then the system informs that the user already exists

  Scenario: Updating Borrower details when he exists
  This scenario describes the process of updating the details of a borrower who has already registered before
    Given "Barry Alen" is a registered borrower
    When "Barry Alen" updates his borrowing details
    Then the system saves the changes

  Scenario: Updating Borrower details when he doesn't exist
  This scenario describes what happens when the system tries to update the details of a borrower who has not registered before
    Given "Barry Alen" is not a registered borrower
    When "Barry Alen" tries to update his borrowing details
    Then the system displays an error message indicating that "Barry Alen" does not exist

  Scenario: Deleting a Borrower
  This scenario describes the process of  deleting the account of a borrower who has already registered
    Given "Barry Alen" is a registered borrower
    When the system deletes "Barry Alen"'s account
    Then the system removes "Barry Alen"'s details

  Scenario: Deleting a Borrower that doesn't exist
  This scenario describes what happens when the system tries to delete the account of a borrower who has not registered before
    Given "Barry Alen" is not a registered borrower
    When the system attempts to delete "Barry Alen"'s account ( edw mporw na vgalw to attempts, na mpei idio me epanw , kai na valw na apothikeuei se string ena minima poy sto epomeno vima elegxw ti einai ean einai dld success h oxi)
    Then the system displays an error message indicating that "Barry Alen" does not exist

  Scenario: Handling Unreturned items of Books
  This scenario describes what happens when the system tries to delete the account of a borrower who has pending items to return
    Given "Barry Alen" is a registered borrower
    And "Barry Alen" has pending items
    When the system attempts to delete "Barry Alen"'s account
    Then the system does not remove "Barry Alen"'s details
    And the system informs about the pending items