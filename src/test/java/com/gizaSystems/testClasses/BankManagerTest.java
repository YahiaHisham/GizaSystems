package com.gizaSystems.testClasses;

import com.gizaSystems.pages.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.gizaSystems.utilities.Generators.generateRandomNumbers;
import static com.gizaSystems.utilities.JsonReader.getValueFromJsonFile;
import static com.gizaSystems.utilities.JsonReader.updateValueInJsonFile;
import static com.gizaSystems.utilities.Utilities.isSortedAscending;
import static com.gizaSystems.utilities.Utilities.isSortedDescending;

public class BankManagerTest extends TestBase {

    @DataProvider(name = "AddNewCustomerTestData")
    public Object[][] provideLoginData() {
        return new Object[][]{
                {"Yahia", "Hisham", generateRandomNumbers(6)},
        };
    }

    @Test(dataProvider = "AddNewCustomerTestData")
    public void verifyThatBankManagerCanAddNewCustomer(String firstName, String lastName, String postcode) {
        new LoginPage(driver)
                .clickOnManagerLoginButton()
                .managerHomePage()
                .clickOnAddCustomerButton()
                .addCustomerPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPostCode(postcode)
                .clickOnAddCustomerButton();
        Assert.assertTrue(new AddCustomerPage(driver).getAlertText().contains(getValueFromJsonFile("CustomerIsAddedSuccessMessage", "Validations")));
        new AddCustomerPage(driver)
                .acceptAlert();
    }

    @Test(dataProvider = "AddNewCustomerTestData")
    public void verifyThatBankManagerCanOpenNewAccount(String firstName, String lastName, String postcode) {
        verifyThatBankManagerCanAddNewCustomer(firstName, lastName, postcode);
        new LoginPage(driver)
                .clickOnHomeButton()
                .clickOnManagerLoginButton()
                .managerHomePage()
                .clickOnOpenAccountButton()
                .openAccountPage()
                .selectCustomer(firstName + " " + lastName)
                .selectCurrency("Dollar")
                .clickOnProcessButton();
        Assert.assertTrue(new AddCustomerPage(driver).getAlertText().contains(getValueFromJsonFile("BankAccountIsAddedSuccessMessage", "Validations")));
        updateValueInJsonFile("AccountNumber", new OpenAccountPage(driver).getAccountNumber(), "UserData");
        new OpenAccountPage(driver)
                .acceptAlert();
        new LoginPage(driver)
                .clickOnHomeButton()
                .clickOnCustomerLoginButton()
                .customerHomePage()
                .selectCustomerAccountName(firstName + " " + lastName)
                .clickOnLoginButton();
        Assert.assertEquals(new CustomerHomePage(driver).getAccountNumber(), getValueFromJsonFile("AccountNumber", "UserData"));
    }

    @Test(dataProvider = "AddNewCustomerTestData")
    public void verifyThatBankManagerCanSearchByCustomerFirstName(String firstName, String lastName, String postcode) {
        verifyThatBankManagerCanAddNewCustomer(firstName, lastName, postcode);
        new LoginPage(driver)
                .clickOnHomeButton()
                .clickOnManagerLoginButton()
                .managerHomePage()
                .customersButton()
                .customersPage()
                .setCustomerSearchQuery(firstName);
        Assert.assertEquals(new CustomersPage(driver).getFirstNameFirstRowText(), firstName);
    }

    @Test(dataProvider = "AddNewCustomerTestData")
    public void verifyThatBankCanSortCustomersByPostalCode(String firstName, String lastName, String postcode) {
        verifyThatBankManagerCanAddNewCustomer(firstName, lastName, postcode);
        new LoginPage(driver)
                .clickOnHomeButton()
                .clickOnManagerLoginButton()
                .managerHomePage()
                .customersButton()
                .customersPage()
                .clickOnPostCodeButton();
        Assert.assertTrue(isSortedDescending(new CustomersPage(driver).getPostCodesList()));
        new CustomersPage(driver)
                .clickOnPostCodeButton();
        Assert.assertTrue(isSortedAscending(new CustomersPage(driver).getPostCodesList()));
    }

    @Test(dataProvider = "AddNewCustomerTestData")
    public void verifyThatBankManagerCanDeleteCustomer(String firstName, String lastName, String postcode) {
        verifyThatBankManagerCanAddNewCustomer(firstName, lastName, postcode);
        new LoginPage(driver)
                .clickOnHomeButton()
                .clickOnManagerLoginButton()
                .managerHomePage()
                .customersButton()
                .customersPage()
                .setCustomerSearchQuery(firstName)
                .clickOnDeleteCustomerButton();
        Assert.assertFalse(new CustomersPage(driver)
                .isCustomerDataDisplayed());
    }

    @Test(dataProvider = "AddNewCustomerTestData")
    public void verifyThatCustomerCanCreateTransaction(String firstName, String lastName, String postcode) {
        verifyThatBankManagerCanOpenNewAccount(firstName, lastName, postcode);
        new LoginPage(driver)
                .clickOnHomeButton()
                .clickOnCustomerLoginButton()
                .customerHomePage()
                .selectCustomerAccountName(firstName + " " + lastName)
                .clickOnLoginButton()
                .customerHomePage()
                .clickOnDepositButton()
                .setDepositAmount(generateRandomNumbers(3))
                .clickOnSubmitDepositButton();
        Assert.assertEquals(new CustomerHomePage(driver).getSuccessMessage(), getValueFromJsonFile("DepositSuccessMessage", "Validations"));
        new CustomerHomePage(driver)
                .clickOnTransactionsButton();
        Assert.assertTrue(new CustomerHomePage(driver).isTransactionInserted());
    }

}
