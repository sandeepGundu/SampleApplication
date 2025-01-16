package com.saucedemo.steps;

import net.serenitybdd.annotations.Step;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class CheckoutPageSteps extends BaseSteps
{
    @Step("Enter Checkout Info")
    public void enterCheckoutInfo(String firstName, String lastName, String postalCode)
    {
        checkoutPage.enterFirstName(firstName);
        checkoutPage.enterLastName(lastName);
        checkoutPage.enterPostalCode(postalCode);
    }

    @Step("Click Continue Button to continue")
    public void clickContinueButton()
    {
        checkoutPage.clickToContinue();
    }

    @Step("Verify Products Added To Cart")
    public void verifyProductsAddedToCart(List<String> productList)
    {
        int iTotalItemCount = checkoutPage.totalItemCount();
        if(iTotalItemCount == checkoutPage.getProductsAddedToCart().size())
        {
            Assert.assertTrue(CollectionUtils.isEqualCollection(productList, checkoutPage.getProductsAddedToCart()));
        }
    }

    @Step("Click Finish Button")
    public void clickFinishButton()
    {
        checkoutPage.clickFinish();
    }

    @Step("Verify Order Confirmation Message")
    public void verifyOrderConfirmationMessage(String ExpConfirmMsg)
    {
        Assert.assertEquals(ExpConfirmMsg, checkoutPage.getOrderConfirmationMsg());
    }

    // New step definition method to verify the item total value
    public void verifyItemTotalValue() {
        BigDecimal total_actual = checkoutPage.calculateTotalOfIndividualProductPrices();
        BigDecimal displayedTotal = checkoutPage.getDisplayedItemTotalValue();
        assertThat("The displayed item total value should match the sum of individual product prices.", displayedTotal.compareTo(total_actual) == 0);
    }

}
