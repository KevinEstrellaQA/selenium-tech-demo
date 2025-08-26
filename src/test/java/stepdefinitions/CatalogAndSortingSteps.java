package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.InventoryPage;
import pages.ProductPage;
import utils.ConfigReader;

import java.util.List;

public class CatalogAndSortingSteps {

    HomePage home = new HomePage();
    InventoryPage inventory = new InventoryPage();
    ProductPage product = new ProductPage();
    Hooks hooks = new Hooks();

    //Inventory sorting
    //@Given("I am logged in to www.saucedemo.com with valid credentials")

    @When("I sort through {string} option")
    public void iSortThroughOption(String option) {
        List<String> currentOptions = inventory.getDropdownOptions();
        List<String> expectedOptions = List.of("Name (A to Z)", "Name (Z to A)", "Price (low to high)", "Price (high to low)");
        Assert.assertEquals(currentOptions, expectedOptions, "The dropdown options do not match the expected");
        inventory.clickOnDropdown();
        inventory.selectFromDropdown(option);
    }

    @Then("The first product is {string}")
    public void theFirstProductIs(String expectedFirst) {
        String currentProduct = inventory.getFirstProduct();
        Assert.assertEquals(currentProduct, expectedFirst, "The first product does not match the expected");
    }

    @And("The last product is {string}")
    public void theLastProductIs(String expectedLast) {
        String currentProduct = inventory.getLastProduct();
        Assert.assertEquals(currentProduct, expectedLast, "The last product does not match the expected");
    }


    //See the product details
    @When("I click on the item {string}")
    public void iClickOnTheItem(String productName) {
        SoftAssert soft = new SoftAssert();
        inventory.clickOnProduct(productName);
        String currentName = product.getProductTitle();
        soft.assertEquals(currentName, productName, "The product name does not match the expected");
        String expectedUrl = ConfigReader.get("product.url");
        String actualUrl = product.getCurrentPageUrl();
        soft.assertTrue(actualUrl.contains(expectedUrl), "The URLs do not match");
        soft.assertAll();
    }

    @Then("I see the product price is {string}")
    public void iSeeTheProductPriceIs(String productPrice) {
        String currentPrice = product.getProductPrice();
        Assert.assertEquals(currentPrice, productPrice, "The product price does not match the expected");
    }


}
