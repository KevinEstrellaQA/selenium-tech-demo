package pages;

public class CheckoutComplete extends BasePage{

    public CheckoutComplete(){
        super(driver);
    }

    private String checkoutCompleteMessage = "//h2[normalize-space()='Thank you for your order!']";

    public boolean isCheckoutCompleteMessageDisplayed(){
        return isElementDisplayed(checkoutCompleteMessage);
    }

    public String getCheckoutCompleteMessage(){
        return getElementText(checkoutCompleteMessage);
    }

    public String getCompletePageUrl(){
        return getCurrentUrl();
    }

}
