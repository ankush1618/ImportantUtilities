Feature: Login Page Functionality

  Scenario Outline: User logs in with valid credentials
    Given the user is on the login page
    When the user enters "<UserName>" in username field
    When the user enters "<Password>" in password field
    And clicks the submit button
    Then the user should be logged in successfully
    But user gets Login Failed Error

    Examples:
      | UserName | Password |
      | user1    | pass1    |
      | user2    | pass2    |
