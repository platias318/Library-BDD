Feature: Delayed Return Notification

  User Story:As a Library Manager
  I want the loan system to automatically notify via email those who have delayed the return of an item
  So that borrowers are informed when they have delayed the return of an item

  Scenario: Notifying the borrower via email
  This scenario handles the case where an item hasn't been returned on time and the system must notify the borrower via email
  Given Harry Potter's due date has passed
  And George Red had borrowed the item
  And George Red has an email address
  When the system executes the delayed return notification process
  Then George Red receives an email notification for the return of the item


  Scenario: Borrower does not have an email address
  This scenario handles the case where an item hasn't been returned on time and the system must notify the user via email but he doesn't have one
    Given Harry Potter's due date has passed
    And George Red had borrowed the item
    And George Red does not have an email address
    When the system executes the delayed return notification process
    Then George Red does not receive an email notification for the return of the item

#  Scenario: The Email Server is not working EKSWTERIKO???
#  This scenario handles the case where the email server is not working so we cant send any notifications and therefore the procedure gets cancelled
#    Given the system calendar
#    And the university's email server
#    And the trigger is every Monday at 7:00 a.m.
#    When the Email Server is not working
#    Then the use case ends