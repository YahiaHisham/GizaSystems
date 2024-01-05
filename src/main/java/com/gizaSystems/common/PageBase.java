package com.gizaSystems.common;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageBase {

    public WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnElement(By elementLocator) {
        waitUntilPresenceOfElement(elementLocator);
        scrollToElementView(elementLocator);
        driver.findElement(elementLocator).click();
    }

    public void setElementText(By elementLocator, String text) {
        waitUntilPresenceOfElement(elementLocator);
        scrollToElementView(elementLocator);
        driver.findElement(elementLocator).sendKeys(text);
    }

    public String getElementText(By elementLocator) {
        return driver.findElement(elementLocator).getText();
    }

    public void waitUntilPresenceOfElement(By elementLocator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(elementLocator));
    }

    public void scrollToElementView(By elementLocator) {
        WebElement element = driver.findElement(elementLocator);
        int elementPositionY = element.getLocation().getY();
        int viewportHeight = ((Long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;")).intValue();
        int scrollPositionY = elementPositionY - (viewportHeight / 2);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, arguments[0]);", scrollPositionY);
    }

    public String getAlertText() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
        moveToDefaultContent();
    }

    public void moveToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public void selectByVisibleText(By dropdownLocator, String text) {
        Select select = new Select(driver.findElement(dropdownLocator));
        select.selectByVisibleText(text);
    }

}