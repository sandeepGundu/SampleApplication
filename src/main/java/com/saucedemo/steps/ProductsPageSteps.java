package com.saucedemo.steps;

import net.serenitybdd.annotations.Step;
import org.junit.Assert;

import java.util.List;

public class ProductsPageSteps extends BaseSteps
{
    @Step("Add Given Products To Cart")
    public void addGivenProductsToCart(String productName) throws InterruptedException {
        productsPage.addProductToCart(productName);//"Sauce Labs Backpack"
        Thread.sleep(3000);
        productsPage.verifyRemoveBtnIsDisplayed(productName);
    }

    @Step("Navigate to Cart Page")
    public void navigateToCartPage()
    {
        productsPage.openShoppingCartContainer();
    }

    @Step("verify that the product count is not displayed")
    public void verifyProductCountIsNotDisplayed() {
        productsPage.verifyProductCountNotDisplayed();
    }

    // New step definition method to remove a product from the cart
    @Step("Click on the Remove button.")
    public void removeGivenProductFromCart(String productName) {
        productsPage.removeProductFromCart(productName);
    }

    // New step definition method to verify that the 'Add to Cart' button is displayed
    public void verifyAddToCartButtonDisplayed(String productName) {
        productsPage.verifyAddToCartButtonIsDisplayed(productName);
    }

    // New step definition to verify all specified products are displayed
    @Step("Verify that the following products are displayed: {0}")
    public void verifyProductsDisplayed(List<String> productNames) {
        for (String productName : productNames) {
            Assert.assertTrue("Product '" + productName + "' is not displayed.", productsPage.isProductDisplayed(productName));
        }
    }

    public void clearTheCartItems()
    {
        productsPage.removeAllProducts();
    }
}
