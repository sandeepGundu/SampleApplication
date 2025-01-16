package com.saucedemo.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class CheckoutPage extends PageObject
{
    @FindBy(css = "input#first-name")
    WebElementFacade checkoutInfo_FirstNameField;

    @FindBy(name = "lastName")
    WebElementFacade checkoutInfo_LastNameField;

    @FindBy(xpath = "//input[@id='postal-code']")
    WebElementFacade checkoutInfo_PostalField;

    @FindBy(xpath = "//input[@id='continue']")
    WebElementFacade checkoutInfo_Continue;

    @FindBy(xpath = "//div[@class='cart_item']//div[@class='inventory_item_name']")
    public List<WebElementFacade> checkoutOverView_cartItems;

    @FindBy(xpath = "//button[@id='finish']")
    WebElementFacade checkoutOverView_Finish;

    @FindBy(css = "div#checkout_complete_container h2.complete-header")
    WebElementFacade checkoutComplete_OrderConfirmMsg;

    public void enterFirstName(String firstName)
    {
        checkoutInfo_FirstNameField.type(firstName);
    }

    public void enterLastName(String lastName)
    {
        checkoutInfo_LastNameField.type(lastName);
    }

    public void enterPostalCode(String postalCode)
    {
        checkoutInfo_PostalField.type(postalCode);
    }

    public void clickToContinue()
    {
        checkoutInfo_Continue.click();
    }

    public String itemRowXpath = "//div[@class='cart_item']//div[@class='inventory_item_name']";
    public String cartItemXpath = "//div[@class='cart_item']";

    public int totalItemCount()
    {
        return checkoutOverView_cartItems.size();
    }

    public List<String> getProductsAddedToCart() {
        return findAll(itemRowXpath).stream()
                .map(WebElementFacade::getText)
                .collect(Collectors.toList());
    }

    public void clickFinish()
    {
        checkoutOverView_Finish.click();
    }

    public String getOrderConfirmationMsg()
    {
        return checkoutComplete_OrderConfirmMsg.getText();
    }

    // New method to get the sum of individual product prices
    public BigDecimal calculateTotalOfIndividualProductPrices() {
        BigDecimal total = BigDecimal.ZERO;
        /*List<WebElementFacade> cartItems = checkoutOverView_cartItems;
        for (WebElementFacade item : cartItems) {
            String priceText = find(By.xpath(item + "/parent::a//following-sibling::div[@class='item_pricebar']//div[@class='inventory_item_price']")).getText().replace("$", "");
            BigDecimal price = new BigDecimal(priceText);
            total = total.add(price);
        }*/

        for(int i = 1; i <= totalItemCount(); i++)
        {
            String priceText = find(By.xpath(cartItemXpath + "[" + i + "]//div[@class='inventory_item_price']")).getText().replace("$", "");
            BigDecimal price = new BigDecimal(priceText);
            total = total.add(price);
        }
        return total;
    }

    // New method to get the displayed item total value
    public BigDecimal getDisplayedItemTotalValue() {
        WebElementFacade itemTotalElement = find(By.className("summary_subtotal_label"));
        String itemTotalText = itemTotalElement.getText().replace("Item total: $", "");
        return new BigDecimal(itemTotalText);
    }
}
