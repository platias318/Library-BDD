Feature: Returning a borrowed item to the library
  The borrower returns the book copy in the library and then the system needs to accept it based on some conditions

  User story: As a librarian
  I want to accurately record the return of one or more books
  So that books are returned on time, and late returns are fined

  Scenario:Successful return of an item
  This scenario describes the successful process of returning an item by a borrower
    Given George Red has borrowed the item "Animal Kingdom" with a return date of "2024-03-15"
    And today's date is "2024-03-12"
    When the return of "Animal Kingdom" is processed
    Then the system marks the state of "Animal Kingdom" as "AVAILABLE"
    And George Red has one less pending item
    And the items count of the book increases by one

  Scenario:Late return of an item from a borrower
  This scenario describes what happens when a borrower returns a loaned item late and has to pay a fine
    Given George Red has borrowed the item "Animal Kingdom" with a return date of "2024-03-15"
    And today's date is "2024-03-20"
    When the return of "Animal Kingdom" is processed
    Then the system marks the state of "Animal Kingdom" as "AVAILABLE"
    And George Red pays a fine based on the borrower category

  Scenario:Borrower unable to pay fine
  This scenario describes what happens when a borrower returns a loan of an item late and doesn't have enough money to pay for the fine
    Given George Red has borrowed the item "Animal Kingdom" with a return date of "2024-03-15"
    And today's date is "2024-03-20"
    And George Red doesn't have enough money to pay the fine
    When the return of "Animal Kingdom" is processed
    Then the system does not accept the return
    And George Red has the same number of pending items