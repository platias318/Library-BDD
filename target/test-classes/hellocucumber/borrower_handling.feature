Feature: Borrower handling by the system
  The librarian can sign up a new person, modify his credentials or delete his account

  User Story: As a librarian
  I want to be able to manage loaning books to people
  So that borrowers can borrow books

    Scenario: Signing up a New Borrower
      Given "Barry Alen" is eligible to borrow books
      When the librarian assigns a unique borrower ID to him
      And the librarian enters the borrower's details
      Then the system stores the borrower's information

    Scenario: Borrower trying to sign up already exists
      Given "Barry Alen" is already in the system
      And "Barry Alen" is eligible to borrow books
      When the librarian tries to enter the borrower's details
      Then the system informs the librarian that the user already exists

    Scenario: Librarian wants to cancel the sign up
      Given "Barry Alen" is eligible to borrow books
      And the librarian is entering the borrower's details
      When the librarian chooses to cancel the sign up
      Then the system does not store the borrower's information


    Scenario: Updating Borrower Information when he exists
      Given "Barry Alen" has already signed up
      When the librarian modifies his information
      Then the system saves the changes

    Scenario: Updating Borrower Information when he doesn't exist
      Given "Barry Alen" has not signed up
      When the librarian tries to modify his information
      Then the system displays an error message indicating that the borrower does not exist

    Scenario: Canceling Borrower Update
      Given the librarian is updating "Barry Alen"'s information
      When the librarian chooses to cancel the update
      Then the system discards the changes

    Scenario: Deleting a Borrower
      Given "Barry Alen" is in the system
      And the librarian searches for the borrower
      When the librarian selects the option to delete the borrower
      Then the system removes the borrower's information

    Scenario: Deleting a Borrower that doesn't exist
      Given "Barry Alen" is not in the system
      When the librarian searches for him
      Then the system displays an error message indicating that the borrower does not exist

    Scenario: Librarian wants to cancel the deletion
      Given the librarian is deleting "Barry Alen"'s account
      When the librarian chooses to cancel the deletion
      Then the system discards the deletion

    Scenario: Handling Unreturned Books
      Given "Barry Alen" is in the system
      And "Barry Alen" has borrowed books that are not returned
      When the librarian attempts to delete the borrower
      Then the system displays the non-returned copies
      And the librarian chooses to cancel the deletion process