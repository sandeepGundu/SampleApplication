package orders;

import com.saucedemo.steps.*;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import org.openqa.selenium.WebDriver;

public class BaseTest
{
    @Managed(uniqueSession = true)
    WebDriver browser;

    @Steps
    public LoginSteps loginSteps;

    @Steps
    public LogoutSteps logoutSteps;

    @Steps
    public ProductsPageSteps productsPageSteps;

    @Steps
    public CartPageSteps cartPageSteps;

    @Steps
    public CheckoutPageSteps checkoutPageSteps;

}
