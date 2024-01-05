package com.gizaSystems.pages;

import com.gizaSystems.common.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends PageBase {
    private final By customerLoginButton = By.xpath("//button[@ng-click='customer()']");
    private final By managerLoginButton = By.xpath("//button[@ng-click='manager()']");
    private final By homeButton = By.xpath("//button[@ng-click='home()']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickOnCustomerLoginButton() {
        clickOnElement(customerLoginButton);
        return this;
    }

    public LoginPage clickOnManagerLoginButton() {
        clickOnElement(managerLoginButton);
        return this;
    }

    public LoginPage clickOnHomeButton() {
        clickOnElement(homeButton);
        return this;
    }

    public ManagerHomePage managerHomePage() {
        return new ManagerHomePage(driver);
    }

    public CustomerHomePage customerHomePage() {
        return new CustomerHomePage(driver);
    }


}
