package pages;

public class CartPage extends BasePage{

    public CartPage(){
        super(driver);
    }

    private String checkoutButton = "//button[@id='checkout']";

    public void clickOnCheckout(){
        clickElement(checkoutButton);
    }

}
