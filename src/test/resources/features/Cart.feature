@Cart
Feature: Cart Functionality
    As a product owner
    I want to validate the cart functionality
    So that the system behaves as expected

    Background: I am logged in to SauceDemo and located on the inventory page.
      Given I am logged in to www.saucedemo.com with valid credentials

    @Smoke
    Scenario: Badge cart displays total items accurately
      When I add the following items to the cart:
        | Sauce Labs Backpack   |
        | Sauce Labs Bike Light |
      Then The cart icon shows 2 items
      When I remove "Sauce Labs Backpack" from the cart
      Then The cart icon shows 1 item

    @Regression
    Scenario: Add to cart button changes if the product is added or removed from the cart
      When I add the products "Sauce Labs Bolt T-Shirt" and "Sauce Labs Onesie" to the cart
      Then The "Add to cart" button changes to "Remove" for the product
      When I remove the products from the cart
      Then The "Remove" button changes back to "Add to cart"