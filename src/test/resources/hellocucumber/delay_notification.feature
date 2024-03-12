Feature: Delayed Return Notification

#  User Story:As a Library Manager
#  I want the loan system to automatically notify via email those who have delayed the return of an item
#  So that borrowers are informed when they have delayed the return of an item of a book

  Scenario: An item hasn't been returned
    This scenario handles the case where an item of a book hasn't been returned and the system must notify the user via email
    Given the system calendar
    And the university's email server
    And the trigger is every Monday at 7:00 a.m.
    And "Harry Potter"'s return date has passed
    And "John Terry" had borrowed the item
    When the system searches for "Harry Potter"
    And the system identifies the borrower who has borrowed the item
    Then the system sends an email to the borrower with the delayed item and the number of delay days

  Scenario: Borrower does not have an email address
  This scenario handles the case where an item hasn't been returned and the system must notify the user via email but he doesn't have one
    Given the system calendar
    And the university's email server
    And the trigger is every Monday at 7:00 a.m.
    And "Harry Potter"'s deadline date has passed
    And "John Terry" had borrowed the item
    And the borrower does not have an email address
    When the system searches for "Harry Potter"
    And the system identifies the borrower who has borrowed the item
    Then the system ignores the borrower

  Scenario: The Email Server is not working
  This scenario handles the case where the email server is not working so we cant send any notifications and cancel the procedure
    Given the system calendar
    And the university's email server
    And the trigger is every Monday at 7:00 a.m.
    When the Email Server is not working
    Then the use case ends