package com.gizaSystems.pages;

import com.gizaSystems.common.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CustomersPage extends PageBase {
    private final By customerSearchBox = By.cssSelector(".form-control");
    private final By firstNameFirstRow = By.cssSelector("td.ng-binding:nth-of-type(1)");
    private final By postCodeButton = By.xpath("//a[contains(text(),'Post Code')]");
    private final By deleteCustomerButton = By.xpath("//button[@ng-click='deleteCust(cust)']");
    private final By postCodes = By.cssSelector("td.ng-binding:nth-of-type(3)");
    private final By searchedCustomerData = By.cssSelector("tbody > tr");

    public CustomersPage(WebDriver driver) {
        super(driver);
    }

    public CustomersPage setCustomerSearchQuery(String searchQuery) {
        setElementText(customerSearchBox, searchQuery);
        return this;
    }

    public String getFirstNameFirstRowText() {
        return getElementText(firstNameFirstRow);
    }

    public void clickOnPostCodeButton() {
        clickOnElement(postCodeButton);
    }

    public List<String> getPostCodesList() {
        List<WebElement> webElementsList = driver.findElements(postCodes);
        List<String> elementTexts = new ArrayList<>();
        for (WebElement element : webElementsList) {
            elementTexts.add(element.getText());
        }
        return elementTexts;
    }

    public void clickOnDeleteCustomerButton() {
        clickOnElement(deleteCustomerButton);
    }

    public boolean isCustomerDataDisplayed() {
        try {
            return driver.findElement(searchedCustomerData).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}
