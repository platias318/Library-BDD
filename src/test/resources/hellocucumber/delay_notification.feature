Feature: Delayed Return Notification

  User Story:As a Library Manager
  I want the loan system to automatically notify via email those who have delayed the return of an item
  So that borrowers are informed when they have delayed the return of an item

  Scenario: Notifying the borrower via email
  This scenario handles the case where an item hasn't been returned on time and the system must notify the borrower via email
    Given George Red has borrowed the item Harry Potter
    And Harry Potter's due date has passed
    And George Red has an email address
    When the system executes the delayed return notification process
    Then George Red receives an email notification for the return of the item


  Scenario: Borrower does not have an email address
  This scenario handles the case where an item hasn't been returned on time and the system must notify the user via email but he doesn't have one
    Given George Red has borrowed the item Harry Potter
    And Harry Potter's due date has passed
    And George Red does not have an email address
    When the system executes the delayed return notification process
    Then George Red does not receive an email notification for the return of the item
