package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.HeaderPage;
import pages.HomePage;
import pages.InventoryPage;
import java.util.List;

public class CartSteps {

    HomePage home = new HomePage();
    InventoryPage inventory = new InventoryPage();
    HeaderPage header = new HeaderPage();

    //@Given(Located in CommonSteps.java) I am logged in to www.saucedemo.com with valid credentials

    //Badge cart displays total items accurately
    @When("I add the following items to the cart:")
    public void iAddTheFollowingItemsToTheCart(DataTable dataTable) {
        List<String> items = dataTable.asList();
        for (String item : items) {
            inventory.addToCart(item);
        }
    }

    @Then("The cart icon shows 2 items")
    public void theCartIconShowsItems() {
        int itemCount = header.getCartItemCount();
        int expectedItems = 2;
        Assert.assertEquals(itemCount, expectedItems, "The cart item count does not match the expected");
    }

    @When("I remove \"Sauce Labs Backpack\" from the cart")
    public void iRemoveSauceLabsBackpackFromTheCart() {
        inventory.removeFromCart("Sauce Labs Backpack");
    }

    @Then("The cart icon shows 1 item")
    public void theCartIconShows1Item() {
        int itemCount = header.getCartItemCount();
        int expectedItems = 1;
        Assert.assertEquals(itemCount, expectedItems, "The cart item count does not match the expected");
    }

    //Add to cart button changes dynamically
    @When("I add the products \"Sauce Labs Bolt T-Shirt\" and \"Sauce Labs Onesie\" to the cart")
    public void iAddEachProductToTheCart() {
        inventory.addToCart("Sauce Labs Bolt T-Shirt");
        inventory.addToCart("Sauce Labs Onesie");
    }

    @Then("The \"Add to cart\" button changes to \"Remove\" for the product")
    public void theAddToCartButtonChangesToRemoveForEachProduct() {
        SoftAssert soft = new SoftAssert();
        String buttonState1 = inventory.getAddToCartButtonStatus("Sauce Labs Bolt T-Shirt");
        soft.assertEquals(buttonState1, "Remove", "The button state did not change to REMOVE after adding to cart for product: Sauce Labs Bolt T-Shirt");
        String buttonState2 = inventory.getAddToCartButtonStatus("Sauce Labs Onesie");
        soft.assertEquals(buttonState2, "Remove", "The button state did not change to REMOVE after adding to cart for product: Sauce Labs Onesie");
        soft.assertAll();
    }

    @When("I remove the products from the cart")
    public void iRemoveEachProductFromTheCart() {
        inventory.removeFromCart("Sauce Labs Bolt T-Shirt");
        inventory.removeFromCart("Sauce Labs Onesie");
    }

    @Then("The \"Remove\" button changes back to \"Add to cart\"")
    public void theRemoveButtonChangesBackToAddToCart() {
        SoftAssert soft = new SoftAssert();
        String buttonState1 = inventory.getAddToCartButtonStatus("Sauce Labs Bolt T-Shirt");
        soft.assertEquals(buttonState1, "Add to cart", "The button state did not change back to ADD TO CART after removing from cart for product: Sauce Labs Bolt T-Shirt");
        String buttonState2 = inventory.getAddToCartButtonStatus("Sauce Labs Onesie");
        soft.assertEquals(buttonState2, "Add to cart", "The button state did not change back to ADD TO CART after removing from cart for product: Sauce Labs Onesie");
        soft.assertAll();
    }

}
