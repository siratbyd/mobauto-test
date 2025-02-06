@android
Feature: Login Functionality

  Scenario: Successful login with valid credentials
    Given I am on login screen
    When I enter username "testuser" and password "Test1234"
    And I click login button
    Then I should see welcome message containing "Welcome" 