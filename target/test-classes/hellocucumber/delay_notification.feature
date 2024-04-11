Feature: Delayed Return Notification

  User Story:As a Library Manager
  I want the loan system to automatically notify via email those who have delayed the return of an item
  So that borrowers are informed when they have delayed the return of an item

  Scenario: An item hasn't been returned
  This scenario handles the case where an item of a book hasn't been returned on time and the system must notify the user via email
    Given the system calendar
    And the university's email server
    And the trigger is every Monday at 7:00 a.m.
    And "Harry Potter"'s due date has passed
    And George Red had borrowed the item
    When the system searches for the item "Harry Potter"
    And the system identifies that George Red has borrowed the item
    Then the system sends an email to George Red with the delayed item and the number of delay days

  Scenario: Borrower does not have an email address
  This scenario handles the case where an item hasn't been returned on time and the system must notify the user via email but he doesn't have one
    Given the system calendar
    And the university's email server
    And the trigger is every Monday at 7:00 a.m.
    And "Harry Potter"'s due date has passed
    And George Red had borrowed the item
    And George Red does not have an email address
    When the system searches for the item "Harry Potter"
    And the system identifies that George Red has borrowed the item
    Then the system ignores the borrower and George Red isn't notified

  Scenario: The Email Server is not working
  This scenario handles the case where the email server is not working so we cant send any notifications and therefore the procedure gets cancelled
    Given the system calendar
    And the university's email server
    And the trigger is every Monday at 7:00 a.m.
    When the Email Server is not working
    Then the use case ends