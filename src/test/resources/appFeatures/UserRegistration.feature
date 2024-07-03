Feature: User Registration

  Scenario: User Registration with different Data
    Given User is on regis Page
    When user enters following User details
      | Ankush | Developer  | anku@gmail.com | 9999 | Noida     |
      | Pal    | Automation | pal@gmail.com  | 8888 | Gurdaspur |
    Then User registration should be successful

  Scenario: User Registration with different Data with column Headers
    Given User is on regis Page
    When user enters following User details with columns
      | Name   | Profession | Email          | Phone | Location  |
      | Ankush | Developer  | anku@gmail.com | 9999  | Noida     |
      | Pal    | Automation | pal@gmail.com  | 8888  | Gurdaspur |
    Then User registration should be successful



