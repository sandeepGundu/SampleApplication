package com.saucedemo.steps;

import net.serenitybdd.annotations.Step;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;

import java.util.List;

public class CartPageSteps extends BaseSteps
{
    @Step("Click Continue Checkout button")
    public void clickContinueCheckoutButton()
    {
        cartPage.clickCheckout();
    }

    @Step("click on the remove button for a specific product")
    public void clickOnRemoveButtonForProduct(String productName) {
        cartPage.clickRemoveButtonForProduct(productName);
    }

    @Step("verify that the product details are not displayed in the cart")
    public void verifyProductDetailsAreNotDisplayed(String productName) {
        cartPage.verifyProductDetailsNotDisplayed(productName);
    }

    @Step("click the continue shopping button to navigate back to the products screen")
    public void clickContinueShoppingButton() {
        cartPage.clickToContinue();
    }
}
