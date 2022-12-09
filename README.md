# OrangeHRM-Demo_Automation_With_Selenium_TestNG

## Pre-Requisites
    Advance knowledge of Java OOP, basic html, basic javascript
    java IDE
    Internet browser (Google Chrome is used here)
## Tools Used
    Automation Tool : Selenium-java, Version: 4.5.0
    Framwork        : TestNG, version: '7.5

## IDE used
    Intelij IDEA
    Version: 2022.2.3
## Test perfomed on
Site: OrangeHRM Demo

URL: https://opensource-demo.orangehrmlive.com/
## Test Cases Covered
    1. Login with valid username and password
    2. Verify Profile Image is displaying correctly in dashboard
    3. Verify Profile Image is displaying correctly in EmployeeList page
    4. Select Employment Status from dropdown
    5. Search Employee List by Employment Status
    6. Update employee information
    7. Logout from the site

## A small project covering following test cases
    1. Visit the site:
        https://opensource-demo.orangehrmlive.com/
    2. Assert the dashboard
    3. Create 2 new employees (create login details showed on class)
    4. Search the employees with their Id and assert that 2 employees are found
    5. Then login with the last employee credential
    6. Update some employee info (e.g Nationality, Date of Birth)
    7. Now go to my info page and assert the edited info
    8. Finally logout your profile
### Find the project here
 https://github.com/Foysal061/OrangeHRM-Demo_Selenium_TestNG/blob/main/src/test/java/testrunner/FullProjectTestRunner.java
