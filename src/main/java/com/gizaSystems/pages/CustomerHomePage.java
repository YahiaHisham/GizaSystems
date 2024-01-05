package com.gizaSystems.pages;

import com.gizaSystems.common.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CustomerHomePage extends PageBase {
    private final By accountNumberValue = By.cssSelector("strong.ng-binding:nth-of-type(1)");
    private final By customerNameDropdown = By.id("userSelect");
    private final By loginButton = By.cssSelector(".btn-default");
    private final By depositButton = By.xpath("//button[@ng-click='deposit()']");
    private final By depositAmountField = By.xpath("//input[@type='number']");
    private final By submitDepositButton = By.cssSelector(".btn-default");
    private final By successMessage = By.cssSelector("div > span.ng-binding");
    private final By transactionsButton = By.xpath("//button[@ng-click='transactions()']");
    private final By transactionsData = By.cssSelector("tbody > tr");

    public CustomerHomePage(WebDriver driver) {
        super(driver);
    }

    public CustomerHomePage selectCustomerAccountName(String customerName) {
        selectByVisibleText(customerNameDropdown, customerName);
        return this;
    }

    public CustomerHomePage clickOnLoginButton() {
        clickOnElement(loginButton);
        return this;
    }

    public String getAccountNumber() {
        return getElementText(accountNumberValue);
    }

    public CustomerHomePage clickOnDepositButton() {
        clickOnElement(depositButton);
        return this;
    }

    public CustomerHomePage setDepositAmount(String amount) {
        setElementText(depositAmountField, amount);
        return this;
    }

    public void clickOnSubmitDepositButton() {
        clickOnElement(submitDepositButton);
    }

    public String getSuccessMessage() {
        clickOnElement(successMessage);
        return getElementText(successMessage);
    }

    public void clickOnTransactionsButton() {
        clickOnElement(transactionsButton);
    }

    public boolean isTransactionInserted() {
        try {
            return driver.findElement(transactionsData).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public CustomerHomePage customerHomePage() {
        return new CustomerHomePage(driver);
    }

}
