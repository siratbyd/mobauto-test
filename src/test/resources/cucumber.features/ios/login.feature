@ios
Feature: Login Functionality

  Scenario: Successful login with valid credentials
    * user is on login page
    * write text "testuser" to element "username_input"
    * write text "password123" to element "password_field"
    * click element "login_button"
    * wait "2" seconds
    * element "welcome_message" should contain text "Hoş geldiniz" 