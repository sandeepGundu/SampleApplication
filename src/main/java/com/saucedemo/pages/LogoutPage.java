package com.saucedemo.pages;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

public class LogoutPage  extends PageObject
{
    @FindBy(css = "a#logout_sidebar_link")
    private WebElementFacade logoutLink;

    public void clickLogOutLink()
    {
        logoutLink.click();
    }
}
