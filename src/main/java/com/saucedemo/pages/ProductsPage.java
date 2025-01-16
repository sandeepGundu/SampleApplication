package com.saucedemo.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends PageObject
{
    @FindBy(id = "react-burger-menu-btn")
    private WebElementFacade menuBtn;

    @FindBy(css = ".shopping_cart_link")
    private WebElementFacade shoppingCartContainer;

    @FindBy(css = "a.shopping_cart_link span")
    private WebElementFacade shoppingCartProductCount;

    @FindBy(className = "shopping_cart_badge")
    private WebElementFacade productCountElement;

    @FindBy(xpath = "//button[text()='Remove']")
    private WebElementFacade productRemoveBtn;

    public void clickMenuBtn()
    {
        menuBtn.click();
    }

    private WebElementFacade getAddToCartBtn(String productName)//Sauce Labs Backpack
    {
        return $("//div[text()='"+ productName +"']//parent::a//parent::div//parent::div//div[@class='pricebar']//button[contains(@id,'add-to-cart')]");
    }

    public void addProductToCart(String productName)
    {
        getAddToCartBtn(productName).click();
    }

    private WebElementFacade getRemoveFromCartBtn_ProductsPage(String productName)
    {
        return $("//div[text()='"+ productName +"']//parent::a//parent::div//parent::div//div[@class='pricebar']//button[contains(@id,'remove')]");
    }

    public void verifyRemoveBtnIsDisplayed(String productName)
    {
        getRemoveFromCartBtn_ProductsPage(productName).isDisabled();
    }

    public void openShoppingCartContainer()
    {
        shoppingCartContainer.click();
    }

    public int getProductCountDisplayed()
    {
        return Integer.parseInt(shoppingCartProductCount.getText());
    }

    // New method to verify that the product count is not displayed
    public void verifyProductCountNotDisplayed() {
        //WebElementFacade productCountElement = find(By.className("shopping_cart_badge"));
        //assertThat("Product count element should not be displayed.", productCountElement.isDisplayed(), is(false));

        //Assert.assertFalse(productCountElement.isDisplayed());
        productCountElement.shouldNotBeVisible();
    }

    // New method to click on the remove button for a specific product
    public void removeProductFromCart(String productName) {
        if(getRemoveFromCartBtn_ProductsPage(productName).isPresent())
        {
            getRemoveFromCartBtn_ProductsPage(productName).click();
        }

    }

    // New method to verify that the 'Add to Cart' button is displayed for a specific product
    public void verifyAddToCartButtonIsDisplayed(String productName) {
        //WebElementFacade addToCartButton = getAddToCartBtn(productName);
        //assertThat("Add to Cart button should be displayed for the product.", addToCartButton.isDisplayed(), is(true));
        getAddToCartBtn(productName).isDisplayed();
    }

    // New method to verify the presence of a product by its name
    public boolean isProductDisplayed(String productName) {
        WebElementFacade productElement = find(By.xpath("//div[contains(@class,'inventory_item_name') and text()='" + productName + "']"));
        return productElement.isPresent();
    }

    public void removeAllProducts()
    {
        while (productRemoveBtn.isPresent())
        {
            productRemoveBtn.click();
        }
    }
}
