Feature: Login Functionality
  Scenario: Successful login with valid credentials
    Given I launch the application
    When I enter username "testuser"
    And I enter password "Test1234"
    And I click login button
    Then I should see welcome message 