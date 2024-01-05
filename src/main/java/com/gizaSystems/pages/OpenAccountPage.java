package com.gizaSystems.pages;

import com.gizaSystems.common.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenAccountPage extends PageBase {
    private final By customerNameDropdown = By.id("userSelect");
    private final By currencyDropdown = By.id("currency");
    private final By processButton = By.xpath("//button[@type='submit']");

    public OpenAccountPage(WebDriver driver) {
        super(driver);
    }

    public OpenAccountPage selectCustomer(String customerName) {
        selectByVisibleText(customerNameDropdown, customerName);
        return this;
    }

    public OpenAccountPage selectCurrency(String currency) {
        selectByVisibleText(currencyDropdown, currency);
        return this;
    }

    public void clickOnProcessButton() {
        clickOnElement(processButton);
    }

    public String getAccountNumber() {
        String successMessage = getAlertText();
        Pattern pattern = Pattern.compile("account Number :([0-9]+)");
        Matcher matcher = pattern.matcher(successMessage);
        matcher.find();
        return matcher.group(1);
    }

}
