Feature: Loaning copies
  The library application allows for the librarian to loan a copy of a book to a student/borrower based
  on some conditions of the system

  user story: As a library member
  I want to be able to borrow copies of a book
  So that I can study them at home

  Scenario: Successful Loaning of one book
    Given the library has a copy of Harry Potter
    And John Terry is a library member
    And John Terry is eligible to borrow a book
    When John Terry wants to borrow Harry Potter
    Then the system approves it
    And the system creates a Date for the return of the book

  Scenario: Successful Loaning of more than one book
    Given the library has a copy of Harry Potter
    And the library has a copy of Moby Dick
    And John Terry is a library member
    And John Terry is eligible to borrow a more than one books
    When John Terry wants to borrow Harry Potter
    And John Terry wants to borrow Moby Dick
    Then the system approves it
    And the system creates a Date for the return of the books
    # and John Terry has 2 books in his borrowing system

  Scenario: Successful Loaning of one but not more books
    Given the library has a copy of Harry Potter
    And the library has a copy of Moby Dick
    And John Terry is a library member
    And John Terry is eligible to borrow only one book
    When John Terry wants to borrow Harry Potter
    And John Terry wants to borrow "Moby Dick"
    Then the system approves only one book
    And the other copy gets returned to the shelf
    And the system creates a Date for the return of the book
    # and John Terry has 2 books in his borrowing system

  Scenario:The system cannot find the copy of the book
    Given the library has a copy of "Harry Potter"
    And "John Terry" is a library member
    And "John Terry" is eligible to borrow a book
    And the copy of the book is not available
    When "John Terry" wants to borrow "Harry Potter"
    Then the system returns an error
    And the loan gets cancelled

  Scenario:The borrower is not eligible to borrow
    Given the library has a copy of "Harry Potter"
    And "John Terry" is a library member
    And "John Terry" has already borrowed the maximum number of books allowed
    When "John Terry" wants to borrow "Harry Potter"
    Then the system doesn't allow the borrow
    And the copy gets return to the shelf

  Scenario: The system crashes when loaning
    Given the library has a copy of "Harry Potter"
    And "John Terry" has already borrowed "Moby Dick"
    And "John Terry" wants to borrow "Harry Potter"
    When the system crashes
    Then "John Terry" should have "Harry Potter" in his borrowed books
