package com.gizaSystems.pages;

import com.gizaSystems.common.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManagerHomePage extends PageBase {
    private final By addCustomerButton = By.xpath("//button[@ng-click='addCust()']");
    private final By openAccountButton = By.xpath("//button[@ng-click='openAccount()']");
    private final By customersButton = By.xpath("//button[@ng-click='showCust()']");

    public ManagerHomePage(WebDriver driver) {
        super(driver);
    }

    public ManagerHomePage clickOnAddCustomerButton() {
        clickOnElement(addCustomerButton);
        return this;
    }

    public ManagerHomePage clickOnOpenAccountButton() {
        clickOnElement(openAccountButton);
        return this;
    }

    public ManagerHomePage customersButton() {
        clickOnElement(customersButton);
        return this;
    }

    public AddCustomerPage addCustomerPage() {
        return new AddCustomerPage(driver);
    }

    public OpenAccountPage openAccountPage() {
        return new OpenAccountPage(driver);
    }

    public CustomersPage customersPage() {
        return new CustomersPage(driver);
    }
}
