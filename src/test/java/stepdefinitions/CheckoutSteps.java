package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.ConfigReader;

public class CheckoutSteps {

    InventoryPage inventory = new InventoryPage();
    HeaderPage header = new HeaderPage();
    CartPage cart = new CartPage();
    CheckoutInformationPage checkout = new CheckoutInformationPage();
    CheckoutOverviewPage overview = new CheckoutOverviewPage();
    CheckoutComplete complete = new CheckoutComplete();

    //Mandatory fields validation during checkout
    @When("I add one product to the cart")
    public void iAddOneProductToTheCart() {
        inventory.addToCart("Sauce Labs Fleece Jacket");
    }

    @And("I click on the cart icon")
    public void iClickOnTheCartIcon() {
        header.clickOnCart();
    }

    @And("I click on the checkout button")
    public void iClickOnTheCheckoutButton() {
        cart.clickOnCheckout();
    }

    @And("I click on 'Continue' and leave {string} empty")
    public void iClickOnContinueAndLeaveEmpty(String field) {
        switch (field) {
            case "First Name" -> {
                checkout.enterLastName("Hendrix");
                checkout.enterPostalCode("12345");
            }
            case "Last Name" -> {
                checkout.enterFirstName("Richard");
                checkout.enterPostalCode("12345");
            }
            case "Postal Code" -> {
                checkout.enterFirstName("Richard");
                checkout.enterLastName("Hendrix");
            }
        }
        checkout.clickOnContinue();
    }

    @Then("I see the error: {string}")
    public void iSeeTheErrorMessage(String message) {
        SoftAssert soft = new SoftAssert();
        soft.assertTrue(checkout.isErrorMessageDisplayed(), "Error message is not displayed");
        String actualMessage = checkout.getErrorText();
        soft.assertEquals(actualMessage, message, "Error message text does not match");
        soft.assertAll("Errors found in the checkout error message validation");
    }

    //Total price calculation at checkout
    @And("I fill in all mandatory fields with valid data")
    public void iFillInAllMandatoryFieldsWithValidData() {
        checkout.enterFirstName("Richard");
        checkout.enterLastName("Hendrix");
        checkout.enterPostalCode("12345");
    }

    @And("I click on 'Continue'")
    public void iClickOnContinue() {
        checkout.clickOnContinue();
    }

    @Then("The item total is '$39.98'")
    public void theItemTotalIs() {
        double itemTotal = overview.getItemTotalAmount();
        Assert.assertEquals(itemTotal, 39.98, "Item total does not match expected value");
    }

    @And("The tax is '$3.20'")
    public void theTaxIs() {
        double tax = overview.getTaxAmount();
        Assert.assertEquals(tax, 3.20, "Tax amount does not match expected value");
    }

    @And("The total price is '$43.18'")
    public void theTotalPriceIs() {
        double total = overview.getTotalAmount();
        Assert.assertEquals(total, 43.18, "Total amount does not match expected value");
    }

    //Successful order completion
    @And("I click on 'Finish'")
    public void iClickOnFinish() {
        overview.clickOnFinish();
    }

    @Then("I see the message 'Thank you for your order!'")
    public void iSeeTheMessageThankYouForYourOrder() {
        SoftAssert soft = new SoftAssert();
        String expectedMessage = "Thank you for your order!";
        String actualMessage = complete.getCheckoutCompleteMessage();
        soft.assertEquals(actualMessage, expectedMessage, "The order completion message does not match the expected");
        String currentPageUrl = complete.getCompletePageUrl();
        String expectedUrl = ConfigReader.get("checkout.complete.url");
        soft.assertEquals(currentPageUrl, expectedUrl, "The current URL does not match the expected");
        soft.assertAll("Errors found in the order completion validation");
    }

}
