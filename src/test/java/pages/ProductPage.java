package pages;

public class ProductPage extends BasePage{

    public ProductPage(){
        super(driver);
    }

    private String productTitle = "//div[@class='inventory_details_name large_size']";
    private String productPrice = "//div[@class='inventory_details_price']";

    public String getProductTitle(){
        return getElementText(productTitle);
    }

    public String getProductPrice(){
        return getElementText(productPrice);
    }

    public String getCurrentPageUrl(){
        return getCurrentUrl();
    }


}
