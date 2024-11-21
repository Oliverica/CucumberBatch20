Feature: Login related scenarios

  Background:
    #Given user is able to access HRMS application


@sprint1 @smoke @regression @sohel @sprint2
  Scenario: valid admin login
    Given  user is able to access HRMS application
    When user enters valid username and password
    And user clicks on login button
    Then user is able to see dashboard page

  @error @sohel
    Scenario: Invalid admin login
    #Given user is able to access HRMS application
    When user enters invalid username and password
    And user clicks on login button
    Then user can see an error message


