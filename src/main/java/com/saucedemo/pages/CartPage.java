package com.saucedemo.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.stream.Collectors;

public class CartPage extends PageObject
{
    @FindBy(id = "checkout")
    WebElementFacade checkoutBtn;

    @FindBy(xpath = "//button[@id='continue-shopping']")
    WebElementFacade continueShopping_CartPage;

    private WebElementFacade getRemoveFromCartBtn_CartPage(String productName)
    {
        return find(By.xpath("//div[contains(@class, 'cart_item') and contains(., '" + productName + "')]//button[text()='Remove']"));
    }

    public void clickCheckout()
    {
        checkoutBtn.click();
    }

    // New method to click on the remove button for a specific product
    public void clickRemoveButtonForProduct(String productName) {
        getRemoveFromCartBtn_CartPage(productName).click();
    }

    // New method to verify that the product details are not displayed in the cart
    public void verifyProductDetailsNotDisplayed(String productName) {
        List<WebElementFacade> cartItems = findAll(By.className("cart_item"));
        boolean productDisplayed = cartItems.stream().anyMatch(item -> item.getText().contains(productName));
        Assert.assertFalse(productDisplayed);
        //assertThat("Product details should not be displayed.", productDisplayed, is(false));
    }

    public void clickToContinue()
    {
        continueShopping_CartPage.click();
    }
}
