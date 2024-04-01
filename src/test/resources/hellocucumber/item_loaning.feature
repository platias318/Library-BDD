Feature: Loaning items
  The library application allows for the librarian to loan an item of a book to a borrower based
  on some conditions of the system

  User story: As a library member
  I want to be able to borrow items
  So that I can study them at home


  Scenario: Successful loaning of an item
  This scenario describes the successful process of loaning an item to a borrower that is entitled to borrow
    Given the library has the item "Harry Potter" available
    And George Red is a registered borrower
    And George Red has 2 pending items to be returned
    And George Red has been assigned a maximum lending limit of 5
    When George Red borrows the item "Harry Potter"
    Then the system successfully loans the item "Harry Potter" to George Red with a return date set
    And George Red's pending items increase to 3

  Scenario: Borrower can borrow only one item due to his lending limit
  This scenario describes the successful process of loaning only one but not two items to a borrower that is entitled to borrow only one item
    Given the library has the items "Harry Potter" and "Moby Dick" available
    And George Red is a registered borrower
    And George Red has 2 pending items to be returned
    And George Red has been assigned a maximum lending limit of 3
    When George Red tries to borrow both items
    Then the system successfully loans the item "Harry Potter" to George Red with a return date set
    And the system does not loan "Moby Dick" to George Red due to the lending limit reached
    And George Red's pending items increase to 3

  Scenario:Item not found
  This scenario describes the edge case where the library system cannot find the item, so the loan isn't happening
    Given the item "Harry Potter" is in the library but marked as "WITHDRAWN"
    And George Red is a registered borrower
    When George Red tries to borrow the item "Harry Potter"
    Then the system returns an error due to the item's status
    And the system withdraws the item "Harry Potter"

  Scenario:The borrower is not eligible to borrow
  This scenario describes the unsuccessful process of loaning an item to a borrower that has reached his max lending limit
    Given the library has the item "Harry Potter" available
    And George Red is a registered borrower
    And George Red has 3 pending items to be returned
    And George Red has been assigned a maximum lending limit of 3
    When George Red tries to borrow the item "Harry Potter"
    Then the system doesn't allow the loan
    And George Red's pending items remain 3


