@Authentication
Feature: Login Functionality
    As a product owner
    I want to validate the login functionality
    So that the system behaves as expected for different user roles

    Background: I am on the SauceDemo website without logging in.
      Given I navigate to www.saucedemo.com

    @Smoke @E2E
    Scenario: Standard user can log in successfully
      When I log in with standard user credentials
      Then I see the inventory products page

    @Negative
    Scenario: Locked user cannot log in and gets an error message
      When I log in with locked out user credentials
      Then I see an error message indicating the user is locked out

    @Negative
    Scenario: User with invalid credentials cannot log in
      When I log in with invalid user credentials
      Then I see an error message 'Username and password do not match any user in this service'
