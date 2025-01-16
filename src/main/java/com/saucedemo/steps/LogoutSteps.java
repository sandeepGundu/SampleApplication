package com.saucedemo.steps;

import net.serenitybdd.annotations.Step;

public class LogoutSteps extends BaseSteps
{
    @Step("Logout From Application")
    public void LogoutFromApplication() {
        productsPage.clickMenuBtn();
        logoutPage.clickLogOutLink();
    }
}
