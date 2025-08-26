@Catalog
Feature: Catalog and Sorting Functionality
    As a product owner
    I want to validate the sorting functionality
    So that the system behaves as expected

    Background: I am logged in to the SauceDemo website with valid credentials.
      Given I am logged in to www.saucedemo.com with valid credentials

    @Regression
    Scenario Outline: Inventory sorting
      When I sort through "<option>" option
      Then The first product is "<expectedFirst>"
      And The last product is "<expectedLast>"

      Examples:
      |        option        |            expectedFirst            |          expectedLast             |
      |     Name (A to Z)    |         Sauce Labs Backpack         | Test.allTheThings() T-Shirt (Red) |
      |     Name (Z to A)    |   Test.allTheThings() T-Shirt (Red) |       Sauce Labs Backpack         |
      |  Price (low to high) |          Sauce Labs Onesie          |      Sauce Labs Fleece Jacket     |
      |  Price (high to low) |      Sauce Labs Fleece Jacket       |         Sauce Labs Onesie         |

    @Regression
    Scenario Outline: See the product details
      When I click on the item "<productName>"
      Then I see the product price is "<productPrice>"

      Examples:
      |        productName                | productPrice |
      | Sauce Labs Backpack               |     $29.99   |
      | Sauce Labs Bike Light             |     $9.99    |
      | Sauce Labs Bolt T-Shirt           |     $15.99   |
      | Sauce Labs Fleece Jacket          |     $49.99   |
      | Sauce Labs Onesie                 |     $7.99    |
      | Test.allTheThings() T-Shirt (Red) |     $15.99   |