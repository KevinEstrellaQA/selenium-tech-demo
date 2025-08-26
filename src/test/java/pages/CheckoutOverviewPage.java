package pages;

import java.util.ArrayList;
import java.util.List;

public class CheckoutOverviewPage extends BasePage{

    public CheckoutOverviewPage(){
        super(driver);
    }

    private String itemPriceLabels = "//div[@class='inventory_item_price']";
    private String itemTotalLabel = "//div[@class='summary_subtotal_label']";
    private String taxLabel = "//div[@class='summary_tax_label']";
    private String totalLabel = "//div[@class='summary_total_label']";
    private String finishButton = "//button[@id='finish']";

    public List<Double> getItemPrices(){
        List<String> items = getElements(itemPriceLabels);
        List<Double> itemPrices = new ArrayList<>();
        for (String item : items) {
            item = item.replace("$", "");
            itemPrices.add(Double.parseDouble(item));
        }
        return itemPrices;
    }

    public double getItemTotalAmount(){
        String itemTotalText = getElementText(itemTotalLabel).replace("Item total: $", "");
        return Double.parseDouble(itemTotalText);
    }

    public double getTaxAmount(){
        String taxText = getElementText(taxLabel).replace("Tax: $", "");
        return Double.parseDouble(taxText);
    }

    public double getTotalAmount(){
        String totalText = getElementText(totalLabel).replace("Total: $", "");
        return Double.parseDouble(totalText);
    }

    public void clickOnFinish(){
        clickElement(finishButton);
    }

}
