package pages;

import java.util.List;

public class InventoryPage extends BasePage{

    public InventoryPage(){
        super(driver);
    }

    private String dropdown = "//select[@class='product_sort_container']";
    private String dropdownOption = "//option[@value='%s']";
    private String listOfProducts = "//div[@class='inventory_item_name ']";
    private String product = "//div[normalize-space()='%s']";
    private String addToCartButton = "//div[@class='inventory_item_description'][contains(.,'%s')]//div//button";

    public List<String> getDropdownOptions(){
        return elementsFromDropdown(dropdown);
    }

    public void clickOnDropdown(){
        clickElement(dropdown);
    }

    public void selectFromDropdown(String value){
        switch(value){
            case "Name (A to Z)":
                value = "az";
                break;
            case "Name (Z to A)":
                value = "za";
                break;
            case "Price (low to high)":
                value = "lohi";
                break;
            case "Price (high to low)":
                value = "hilo";
                break;
            default:
                throw new IllegalArgumentException("Invalid dropdown option: " + value);
        }
        String optionLocator = String.format(dropdownOption, value);
        clickElement(optionLocator);
    }

    public String getFirstProduct(){
        List<String> products = getElements(listOfProducts);
        return products.getFirst();
    }

    public String getLastProduct(){
        List<String> products = getElements(listOfProducts);
        return products.getLast();
    }

    public void clickOnProduct(String value){
        clickElement(String.format(product, value));
    }

    public int getTotalOfProducts(){
        List<String> products = getElements(listOfProducts);
        return products.size();
    }

    public void addToCart(String productName){
        String addToCart = String.format(addToCartButton, productName);
        clickElement(addToCart);
    }

    public void removeFromCart(String productName){
        String removeFromCart = String.format(addToCartButton, productName);
        clickElement(removeFromCart);
    }

    public String getAddToCartButtonStatus(String productName){
        String addToCart = String.format(addToCartButton, productName);
        return getElementText(addToCart);
    }

}
