Feature: Returning a borrowed item to the library

  The borrower returns the book copy in the library and then the system needσ to accept it based on some conditions
  User Story:As a librarian
  I want to be able to handle a return of a book copy
  so that another person can borrow it

  Scenario:Successful return of an item
  This scenario describes what happens when a borrower returns a loaned item of a book successfully
    Given "Steven Gerrard" has borrowed the item "Animal Kingdom"
    When "Steven Gerrard" returns the item to the library
    Then the system should mark the itemState of the item as "AVAILABLE"
    And "Steven Gerrard"'s pending items should decrease by 1
    And the items count of the book should increase by 1

  Scenario:Late return of an item from a borrower
  This scenario describes what happens when a borrower returns a loan of an item late and has to pay fine
    Given "Steven Gerrard" has borrowed the item "Animal Kingdom"
    And "Steven Gerrard" belongs in a borrower category
    And the due date for returning the item has passed
    When "Steven Gerrard" returns the item to the library
    Then the system should mark the itemState as "AVAILABLE"
    And the user should pay the daily fine based on the borrower category he belongs