@Checkout
Feature: Checkout Functionality
    As a Product Owner
    I want to ensure that the checkout process works correctly
    So that users can complete their purchases successfully

    Background: I am logged in to the SauceDemo website with valid credentials.
      Given I am logged in to www.saucedemo.com with valid credentials

    @Negatives
    Scenario Outline: Mandatory fields validation during checkout
      When I add one product to the cart
      And I click on the cart icon
      And I click on the checkout button
      And I click on 'Continue' and leave "<field>" empty
      Then I see the error: "<message>"

      Examples:
        |     field    |            message              |
        | First Name   | Error: First Name is required   |
        | Last Name    | Error: Last Name is required    |
        | Postal Code  | Error: Postal Code is required  |

    @E2E
    Scenario: Total price calculation during checkout
      When I add the following items to the cart:
        | Sauce Labs Backpack   |
        | Sauce Labs Bike Light |
      And I click on the cart icon
      And I click on the checkout button
      And I fill in all mandatory fields with valid data
      And I click on 'Continue'
      Then The item total is '$39.98'
      And The tax is '$3.20'
      And The total price is '$43.18'

    @Smoke @E2E
    Scenario: Successful checkout process
      When I add one product to the cart
      And I click on the cart icon
      And I click on the checkout button
      And I fill in all mandatory fields with valid data
      And I click on 'Continue'
      And I click on 'Finish'
      Then I see the message 'Thank you for your order!'