package pages;

public class HeaderPage extends BasePage{

    public HeaderPage(){
        super(driver);
    }

    private String shoppingCartBadge = "//span[@class='shopping_cart_badge']";
    private String shoppingCartLink = "//a[@class='shopping_cart_link']";

    public int getCartItemCount(){
        int itemCount = Integer.parseInt(getElementText(shoppingCartBadge));
        return itemCount;
    }

    public void clickOnCart(){
        clickElement(shoppingCartLink);
    }

}
