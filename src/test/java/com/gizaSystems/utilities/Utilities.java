package com.gizaSystems.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utilities {

    static String screenshotLocation = "src/test/java/com/bayt/data/screenshots/";

    public static void takeScreenshot(WebDriver driver, String imageName) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File(screenshotLocation + imageName + ".png"));
            System.out.println("Screenshot taken: " + imageName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to check if a list is sorted in ascending order
    public static boolean isSortedAscending(List<String> list) {
        List<String> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList);
        return list.equals(sortedList);
    }

    // Helper method to check if a list is sorted in descending order
    public static boolean isSortedDescending(List<String> list) {
        List<String> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList, Collections.reverseOrder());
        return list.equals(sortedList);
    }

}
