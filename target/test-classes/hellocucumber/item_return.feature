Feature: Returning a borrowed item to the library

  The borrower returns the book copy in the library and then the system needs to accept it based on some conditions
  User Story:As a librarian
  I want to be able to handle a return of a book copy
  so that another person can borrow it

  Scenario:Successful return of an item
  This scenario describes what happens when a borrower returns a loaned item of a book successfully
    Given "Steven Gerrard" has borrowed the item "Animal Kingdom"
    When the return of "Animal Kingdom" by "Steven Gerrard" is processed
    Then the system should mark the itemState of the item as "AVAILABLE"
    And "Steven Gerrard" has one less pending item
    And the items count of the book should increase by one

  Scenario:Late return of an item from a borrower
  This scenario describes what happens when a borrower returns a loan of an item late and has to pay fine
    Given "Steven Gerrard" has borrowed the item "Animal Kingdom"
    And "Steven Gerrard" belongs in a borrower category
    And the due date for returning the item has passed
    When the return of "Animal Kingdom" by "Steven Gerrard" is processed
    Then the system should mark the itemState of "Animal Kingdom" as "AVAILABLE"
    And "Steven Gerrard" incurs a fine based on the borrower category