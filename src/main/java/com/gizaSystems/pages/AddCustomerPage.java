package com.gizaSystems.pages;

import com.gizaSystems.common.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddCustomerPage extends PageBase {
    private final By firstNameField = By.xpath("//input[@ng-model='fName']");
    private final By lastNameField = By.xpath("//input[@ng-model='lName']");
    private final By postCodeField = By.xpath("//input[@ng-model='postCd']");
    private final By addCustomerButton = By.cssSelector(".btn-default");

    public AddCustomerPage(WebDriver driver) {
        super(driver);
    }

    public AddCustomerPage setFirstName(String firstName) {
        setElementText(firstNameField, firstName);
        return this;
    }

    public AddCustomerPage setLastName(String lastName) {
        setElementText(lastNameField, lastName);
        return this;
    }

    public AddCustomerPage setPostCode(String postCode) {
        setElementText(postCodeField, postCode);
        return this;
    }

    public void clickOnAddCustomerButton() {
        clickOnElement(addCustomerButton);
    }

}
