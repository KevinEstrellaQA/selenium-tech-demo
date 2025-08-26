package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;
import pages.InventoryPage;
import utils.ConfigReader;

import java.util.List;

public class CommonSteps {

    HomePage home = new HomePage();
    InventoryPage inventory = new InventoryPage();

    //Method to log in with valid credentials
    @Given("I am logged in to www.saucedemo.com with valid credentials")
    public void iAmLoggedInSauceDemoWithValidCredentials(){
        home.navigateToHomePage();
        home.loginCredentials(ConfigReader.get("user.standard"), ConfigReader.get("user.password"));
        home.clickLoginButton();
        String expectedUrl = ConfigReader.get("inventory.url");
        String actualUrl = home.getCurrentPageUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "The URLs do not match");
    }

    //Method to add multiple items to the cart using a DataTable
    @When("I add the following items to the cart:")
    public void iAddTheFollowingItemsToTheCart(DataTable dataTable) {
        List<String> items = dataTable.asList();
        for (String item : items) {
            inventory.addToCart(item);
        }
    }

}
