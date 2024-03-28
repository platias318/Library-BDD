Feature: Loaning items
  The library application allows for the librarian to loan an item of a book to a borrower based
  on some conditions of the system

  User story: As a library member
  I want to be able to borrow items
  So that I can study them at home

  Scenario: Successful loaning of an item
  This scenario describes the successful process of loaning an item of a book to a borrower that is entitled to borrow
    Given the library has the item "Harry Potter" available
    And "John Terry" is a registered borrower
    And "John Terry" has less pending items to return than the maximum lending limit he was assigned
    When "John Terry" borrows the item "Harry Potter"
    Then the system successfully loans the item "Harry Potter" to "John Terry" with a return date set

  Scenario: Successful loaning of more than one items
  This scenario describes the successful process of loaning more than one items of a book to a borrower that is entitled to borrow
    Given the library has the items "Harry Potter" and "Moby Dick" available
    And "John Terry" is a registered borrower
    And "John Terry" has less pending items to return than the maximum lending limit he was assigned
    When "John Terry" borrows the items "Harry Potter" and "Moby Dick"
    Then the system successfully loans both items to "John Terry" with return dates set for each


  Scenario: Borrower can borrow only one item due to his lending limit
  This scenario describes the successful process of loaning only one but not two items of books to a borrower that is entitled to borrow only one item
    Given the library has the items "Harry Potter" and "Moby Dick" available
    And "John Terry" is a registered borrower
    And "John Terry" has one pending item away from reaching the maximum lending limit he was assigned
    When "John Terry" tries to borrow both items
    Then the system successfully loans the item "Harry Potter" to "John Terry" with a return date set
    And the system does not loan "Moby Dick" to "John Terry" due to the lending limit reached

  Scenario:Item not found
  This scenario describes the edge case where the library system cannot find the item of the book, so the loan isn't happening
    Given the item "Harry Potter" is in the library but marked as "WITHDRAWN"
    And "John Terry" is a registered borrower
    And "John Terry" has less pending items to return than the maximum lending limit he was assigned
    When "John Terry" tries to borrow the item "Harry Potter"
    Then the system returns an error due to the item's status
    And the system withdraws the item "Harry Potter"

  Scenario:The borrower is not eligible to borrow
  This scenario describes the unsuccessful process of loaning an item of a book to a borrower that is not entitled to borrow
    Given the library has the item "Harry Potter" available
    And "John Terry" is a registered borrower
    And "John Terry"'s pending items have reached the maximum lending limit he was assigned
    When "John Terry" tries to borrow the item "Harry Potter"
    Then the system doesn't allow the loan and displays an error message


