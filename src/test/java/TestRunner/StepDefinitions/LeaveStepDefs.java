package TestRunner.StepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import pages.DashboardPage;
import pages.LeavePage;
import pages.LoginPage;
import pages.PIMPage;

import java.util.List;

public class LeaveStepDefs{
    public static  String  baseURL = "https://opensource-demo.orangehrmlive.com/";
    public static WebDriver driver;
    public static ChromeOptions op = new ChromeOptions();
    public static LoginPage loginPage;
    public static DashboardPage dashboard;
    public static PIMPage pimPage;
    public static LeavePage leavePage;
    String  employee_firstname;
    String  employee_lastname;
    String  fromDate;
    String  toDate;

    @BeforeAll
    public static void launchWebsite(){
        op.addArguments("--remote-allow-origins=*","--start-maximized");
        driver  = new ChromeDriver(op);
        driver.get(baseURL);
        loginPage = new LoginPage(driver);
        dashboard = new DashboardPage(driver);
        pimPage = new PIMPage(driver);
        leavePage = new LeavePage(driver);
        loginPage.login(true);
    }


    @AfterAll
    public static void closeWebsite(){
        driver.close();
    }

    @Given("A new employee joins the company")
    public void a_new_employee_joins_the_company(DataTable  name) {
        List<String> nameList = name.asList();
        employee_firstname = nameList.get(0);
        employee_lastname = nameList.get(1);
    }

    @When("I add an employee")
    public void i_add_an_employee() {
        dashboard.navigate2PIMPage();
        pimPage.addEmployee(employee_firstname, employee_lastname);
    }

    @Then("Employee should be added successfully")
    public void employee_should_be_added_successfully() {
        Assert.assertTrue(pimPage.isEmployeeSaved(), "Employee creation failed!");
    }


    @When("I add leave entitlement of {int} days")
    public void i_add_leave_entitlement_of_days(int days) {
        String  leaveDays = Integer.toString(days);
        dashboard.navigate2LeavePage();
        leavePage.addLeaveEntitlements(employee_firstname, employee_lastname, leaveDays);
    }

    @Then("Leave Entitlements should be updated")
    public void leave_entitlements_should_be_updated() {
        Assert.assertTrue(leavePage.isEntitlementAdded(), "Leave is not added successfully!");
    }


    @Given("Employee requests a leave from date to date")
    public void employee_requests_leave_from_date_to_date(DataTable  dates) {
        List<String>  leaveDates = dates.asList();
        fromDate = leaveDates.get(0);
        toDate = leaveDates.get(1);

    }

    @When("I assign leave to employee")
    public void i_assign_leave_to_employee() {
        leavePage.assignLeave2Employee(fromDate, toDate);
    }

    @Then("Leave should be assigned successfully")
    public void leaveShouldBeAssignedSuccessfully() {
        Assert.assertTrue(leavePage.isLeaveAssigned(), "Leave not assigned to employee!");
    }


}
