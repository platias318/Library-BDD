Feature: Loaning items
  The library application allows for the librarian to loan an item of a book to a student/borrower based
  on some conditions of the system

  User story: As a library member
  I want to be able to borrow items
  So that I can study them at home

  Scenario: Successful Loaning of an item
  This scenario describes the successful process of loaning an item of a book to a borrower that is entitled to borrow
    Given the library has the item "Harry Potter"
    And "John Terry" is a borrower
    And "John Terry" has less pending items to return than the maximum lending limit he was assigned
    When "John Terry" wants to borrow the item "Harry Potter"
    Then the system successfully loans the item "Harry Potter"
    And the system creates a returnDate for the return of the item "Harry Potter"

  Scenario: Successful Loaning of more than one items
  This scenario describes the successful process of loaning more than one items of a book to a borrower that is entitled to borrow
    Given the library has the item "Harry Potter"
    And the library has the item "Moby Dick"
    And "John Terry" is a borrower
    And "John Terry" has less pending items to return than the maximum lending limit he was assigned
    When "John Terry" wants to borrow the item "Harry Potter"
    And "John Terry" wants to borrow the item "Moby Dick"
    Then the system successfully loans the item "Harry Potter"
    And the system successfully loans the item "Moby Dick"
    And the system creates a returnDate for the return of the item "Harry Potter"
    And the system creates a returnDate for the return of the item "Moby Dick"


  Scenario: Successful Loaning of one but not more items
  This scenario describes the successful process of loaning only one but not two items of books to a borrower that is entitled to borrow only one item
    Given the library has the item "Harry Potter"
    And the library has the item "Moby Dick"
    And "John Terry" is a borrower
    And "John Terry" has one pending item away from reaching the maximum lending limit he was assigned.
    When "John Terry" wants to borrow the item "Harry Potter"
    And "John Terry" wants to borrow the item "Moby Dick"
    Then the system successfully loans the item "Harry Potter"
    And the system unsuccessfully loans the item "Moby Dick"
    And the system creates a returnDate for the return of "Harry Potter"

  Scenario:The system cannot find the item
  This scenario describes the edge case where the library system cannot find the item of the book, so the loan isn't happening
    Given the library has the item "Harry Potter"
    And "John Terry" is a borrower
    And "John Terry" has less pending items to return than the maximum lending limit he was assigned
    And the item is in the itemState "WITHDRAWN"
    When "John Terry" wants to borrow the item "Harry Potter"
    Then the system returns an error
    And the system withdraws the item "Harry Potter"

  Scenario:The borrower is not eligible to borrow
  This scenario describes the unsuccessful process of loaning an item of a book to a borrower that is not entitled to borrow
    Given the library has the item "Harry Potter"
    And "John Terry" is a borrower
    And "John Terry"'s pending items have reached the maximum lending limit he was assigned
    When "John Terry" wants to borrow the item "Harry Potter"
    Then the system doesn't allow the loan and displays an error message


