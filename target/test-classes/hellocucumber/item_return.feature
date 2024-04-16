Feature: Returning a borrowed item to the library
  The borrower returns the book copy in the library and then the system needs to accept it based on some conditions

  User story: As a librarian
  I want to accurately record the return of one or more books
  So that books are returned on time, and late returns are fined

  Scenario:Successful return of an item
  This scenario describes the successful process of returning an item by a borrower
    Given George Red borrowed the item Animal Kingdom 5 days prior to today's date
    And George Red has been assigned maximum lending days of 10
    When the return of Animal Kingdom is processed
    Then the system marks the state of Animal Kingdom as AVAILABLE
    And George Red has one less pending item
    And George Red does not pay a fine
    And the return date of the loan is set to today's date

  Scenario:Late return of an item from a borrower
  This scenario describes what happens when a borrower returns a loaned item late and has to pay a fine
    Given George Red borrowed the item Animal Kingdom 10 days prior to today's date
    And George Red has been assigned maximum lending days of 7
    When the return of Animal Kingdom is processed
    Then the system marks the state of Animal Kingdom as AVAILABLE
    And George Red pays a fine based on the borrower category
    And the return date of the loan is set to today's date
