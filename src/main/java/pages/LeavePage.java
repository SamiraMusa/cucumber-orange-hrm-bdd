package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LeavePage {
    WebDriver   driver;
    private String employeeName;
    private By  entitlementLinkLocator = By.xpath("//span[contains(.,'Entitlements ')]");
    private By  addEntitlementLink  = By.linkText("Add Entitlements");
    private By  employeeNameLocator   = By.xpath("//input[@placeholder='Type for hints...']");
    private By  employeeOption = By.xpath("//div[@role='option'][1]");
    private By  leaveTypeDropdown   =   By.xpath("//div[@class='oxd-select-wrapper']/div/div[contains(.,'Select')]");
    private By  usVacationLeave =   By.xpath("//span[contains(.,'US - Vacation')]");
    private By  entitlementInput  =   By.xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']/div/input");
    private By  saveButton  =   By.xpath("//button[@type='submit']");
    private By  confirmButton   =   By.xpath("//button[contains(.,'Confirm')]");
    private By  successMsg  =   By.xpath("//p[contains(.,'Successfully Saved')]");
    private By  assignLeaveLink =   By.linkText("Assign Leave");
    private By  fromDateLocator = By.xpath("//form/div[3]/div/div[1]/div/div[2]/div/div/input");
    private By  toDateLocator   = By.xpath("//form/div[3]/div/div[2]/div/div[2]/div/div/input");
    private By  assignButton    =   By.xpath("//button[@type='submit']");

    public LeavePage(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    private void navigate2LeaveEntitlements(){
        driver.findElement(entitlementLinkLocator).click();
        driver.findElement(addEntitlementLink).click();
    }

    private void navigate2AssignLeave(){
        driver.findElement(assignLeaveLink).click();
    }

    public void setEmployeeName(String firstname, String lastname){
        employeeName = firstname + " " + lastname;
    }
    private void enterEmployeeName(String name){
        driver.findElement(employeeNameLocator).sendKeys(employeeName);
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@role='listbox']//div[contains(.,'Search')]"))));
        driver.findElement(employeeOption).click();
    }

    private void selectLeaveType(){
        driver.findElement(leaveTypeDropdown).click();
        driver.findElement(usVacationLeave).click();
    }
    public void addLeaveEntitlements(String firstname, String lastname, String leaveDays){
        navigate2LeaveEntitlements();
        setEmployeeName(firstname, lastname);
        enterEmployeeName(employeeName);

        selectLeaveType();

        driver.findElement(entitlementInput).sendKeys(leaveDays);
        driver.findElement(saveButton).click();
        driver.findElement(confirmButton).click();

    }

    public void assignLeave2Employee(String fromDate, String toDate){
        navigate2AssignLeave();
        enterEmployeeName(employeeName);
        selectLeaveType();
        driver.findElement(fromDateLocator).sendKeys(fromDate);
        driver.findElement(toDateLocator).sendKeys(Keys.CONTROL, "a");
        driver.findElement(toDateLocator).sendKeys(Keys.DELETE);
        driver.findElement(toDateLocator).sendKeys(toDate);
        driver.findElement(assignButton).click();

    }

    public boolean  isEntitlementAdded(){
        return !driver.findElements(successMsg).isEmpty();
    }

    public boolean  isLeaveAssigned(){
        return !driver.findElements(successMsg).isEmpty();
    }

}
