Feature: Checkout Functionality
    As a Product Owner
    I want to ensure that the checkout process works correctly
    So that users can complete their purchases successfully

    Background: I am logged in to the SauceDemo website with valid credentials.
      Given I am logged in to www.saucedemo.com with valid credentials

    @Negative
    Scenario Outline: Mandatory fields validation during checkout
      When I add one product to the cart
      And I click on the cart icon
      And I click on the checkout button
      And voy a checkout y dejo "<campo>" vacío
      Then veo el error "<mensaje>"
      Examples:
        | campo        | mensaje                         |
        | First Name   | Error: First Name is required   |
        | Last Name    | Error: Last Name is required    |
        | Postal Code  | Error: Postal Code is required  |