package com.saucedemo.pages;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://www.saucedemo.com/")
public class LoginPage extends PageObject
{
    @FindBy(id = "user-name")
    private WebElementFacade usernameField;

    @FindBy(css = "#password")
    private WebElementFacade passwordField;

    @FindBy(css = "#login-button")
    private WebElementFacade loginButton;

    public void openApp() throws InterruptedException {
        open();
        Thread.sleep(10000);
    }

    public void enterUsername(String username) {
        usernameField.type(username);
    }

    public void enterPassword(String password) {
        passwordField.type(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

}
