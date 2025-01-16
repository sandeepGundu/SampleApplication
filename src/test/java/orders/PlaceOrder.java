package orders;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

@ExtendWith(SerenityJUnit5Extension.class)
public class PlaceOrder extends BaseTest
{
    List<String> prodList = new ArrayList<>(asList("Sauce Labs Backpack", "Sauce Labs Fleece Jacket"));

    @Test
    public void placeOrderForGivenProduct() throws InterruptedException
    {
        //Login to the application with valid credentials
        loginSteps.loginToTheApplicationWithValidCredentials();

        productsPageSteps.clearTheCartItems();

        for(String prod : prodList)
        {
            //Remove the product from the cart if already added before going to add freshly
            //productsPageSteps.removeGivenProductFromCart(prod);

            //Add Product To Cart
            productsPageSteps.addGivenProductsToCart(prod);
        }

        //Navigate to Cart Page
        productsPageSteps.navigateToCartPage();

        //Click Continue Checkout button
        cartPageSteps.clickContinueCheckoutButton();

        //Enter Checkout Info
        checkoutPageSteps.enterCheckoutInfo("testFName", "testLName", "123456");

        //Click Continue Button to continue
        checkoutPageSteps.clickContinueButton();

        //Verify Products Added To Cart
        checkoutPageSteps.verifyProductsAddedToCart(prodList);

        //Click Finish Button
        checkoutPageSteps.clickFinishButton();

        //Verify Order Confirmation Message
        checkoutPageSteps.verifyOrderConfirmationMessage("Thank you for your order!");

        //Logout From Application
        logoutSteps.LogoutFromApplication();
    }

    @Test
    public void shouldRemoveProductFromCart()  throws InterruptedException
    {

        // Step 1: Log in to the application with valid credentials
        loginSteps.loginToTheApplicationWithValidCredentials();

        //Remove the product from the cart if already added before going to add freshly
        productsPageSteps.clearTheCartItems();

        // Step 2: Add given products to the cart
        productsPageSteps.addGivenProductsToCart("Sauce Labs Backpack");
        productsPageSteps.addGivenProductsToCart("Sauce Labs Bike Light");

        // Step 3: Navigate to the cart page
        productsPageSteps.navigateToCartPage();

        // Step 4: Click on the remove button for a product
        cartPageSteps.clickOnRemoveButtonForProduct("Sauce Labs Backpack");
        cartPageSteps.clickOnRemoveButtonForProduct("Sauce Labs Bike Light");

        // Step 5: Verify product details are not displayed
        cartPageSteps.verifyProductDetailsAreNotDisplayed("Sauce Labs Backpack");
        cartPageSteps.verifyProductDetailsAreNotDisplayed("Sauce Labs Bike Light");

        // Step 6: Verify the product count is not displayed
        productsPageSteps.verifyProductCountIsNotDisplayed();

        // Step 7: Click continue shopping button to navigate back to the products screen
        cartPageSteps.clickContinueShoppingButton();

        //Logout From Application
        logoutSteps.LogoutFromApplication();
    }

    @Test
    public void verifyAllProductsDisplayed()
    {
        loginSteps.loginToTheApplicationWithValidCredentials();

        List<String> productsToVerify = Arrays.asList(
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Onesie",
                "Test.allTheThings() T-Shirt (Red)"
        );

        productsPageSteps.verifyProductsDisplayed(productsToVerify);

        //Logout From Application
        logoutSteps.LogoutFromApplication();
    }

    @Test
    public void shouldRemoveProductFromProductsScreen() throws InterruptedException
    {
        // Step 1: Log in to the application with valid credentials
        loginSteps.loginToTheApplicationWithValidCredentials();

        //Remove the product from the cart if already added before going to add freshly
        productsPageSteps.clearTheCartItems();

        // Step 2: Add a given product to the cart
        productsPageSteps.addGivenProductsToCart("Sauce Labs Backpack");

        // Step 3: Remove the product from the cart
        productsPageSteps.removeGivenProductFromCart("Sauce Labs Backpack");

        // Verify that the 'Add to Cart' button is displayed again
        productsPageSteps.verifyAddToCartButtonDisplayed("Sauce Labs Backpack");

        //Logout From Application
        logoutSteps.LogoutFromApplication();
    }

    @Test
    public void shouldVerifyItemTotalPriceInCheckoutOverview() throws InterruptedException
    {
        // Step 1: Log in to the application with valid credentials
        loginSteps.loginToTheApplicationWithValidCredentials();

        //Remove the product from the cart if already added before going to add freshly
        productsPageSteps.clearTheCartItems();

        // Step 2: Add given products to the cart
        productsPageSteps.addGivenProductsToCart("Sauce Labs Backpack");
        productsPageSteps.addGivenProductsToCart("Sauce Labs Bike Light");

        // Step 3: Navigate to the cart page
        productsPageSteps.navigateToCartPage();

        // Step 4: Click continue checkout button
        cartPageSteps.clickContinueCheckoutButton();

        // Step 5: Enter checkout information
        checkoutPageSteps.enterCheckoutInfo("John", "Doe", "12345");

        // Step 6: Click continue button to proceed to checkout overview
        checkoutPageSteps.clickContinueButton();

        // Step 7: Verify products added to the cart
        checkoutPageSteps.verifyProductsAddedToCart(Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light"));

        // Step 8 and 9: Verify item total value
        checkoutPageSteps.verifyItemTotalValue();

        //Logout From Application
        logoutSteps.LogoutFromApplication();
    }
}
