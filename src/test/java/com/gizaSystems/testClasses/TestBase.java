package com.gizaSystems.testClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import static com.gizaSystems.utilities.JsonReader.getValueFromJsonFile;

public class TestBase {
    public WebDriver driver;

    public static void deleteFilesInFolder(String folderPath) {
        try {
            Path directory = Paths.get(folderPath);
            // Use try-with-resources to close the directory stream automatically
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directory)) {
                for (Path file : directoryStream) {
                    // Delete each file in the folder
                    Files.delete(file);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeSuite
    public void beforeSuite() {
        // this is to delete all previous allure reports and clear it
        String allureResultFilePath = System.getProperty("user.dir") + "/allure-results";
        deleteFilesInFolder(allureResultFilePath);
    }

    @BeforeMethod
    public void startBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.navigate().to(getValueFromJsonFile("GlobalsQaUrl", "Urls"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
