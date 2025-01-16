package com.saucedemo.steps;

import net.serenitybdd.annotations.Step;

public class LoginSteps extends BaseSteps
{

    @Step("Login to the application with valid credentials")
    public void loginToTheApplicationWithValidCredentials()
    {
        loginPage.open();
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
    }
}
