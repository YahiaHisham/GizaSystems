# Giza Systems Task

This project contains 6 test cases in UI Automation using Selenium, Java, Maven, TestNG and Allure Report for reporting
1. Create new customer
2. Open a new account
3. Search on an exist customer by first name
4. Sort the customers by postal code
5. Delete customer
6. Create new transaction

## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)


## Prerequisites

Ensure you have the following software installed:

- Java [latest version]
- Allure reports [if you do not have it please follow steps here](https://allurereport.org/docs/gettingstarted-installation/)

## Getting Started

1. Clone the repository.
2. Navigate to the project directory.
3. Install project dependencies using Maven and reload your maven xml file.
4. wait until all dependencies are resolved
5. run the project
6. after running the project
7. open terminal inside project directory or the terminal inside intellij
8. write this command in the terminal to open allure report "allure serve allure-results"

## Project Structure

```plaintext
GizaSystems
│   .gitignore
│   README.md
│   pom.xml
│   testng.xml
│
└───src
    └───main
    │   └───java
    │       └───com.gizaSystems
    │           └───common
    │           └───pages
    │
    └───test
        └───java
            └───com.gizaSystems
                └───data [contains all required test data]
                └───testClasses [contains all test classes]
                └───utilities [contains all utilities that helps in executing the test cases]
