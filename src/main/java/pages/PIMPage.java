package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class PIMPage {

    WebDriver driver;
    private By addEmployeeButtonLocator = By.xpath("//button[contains(.,'Add')]");
    private By firstNameLocator = By.xpath("//input[@placeholder='First Name']");
    private By lastNameLocator  = By.xpath("//input[@placeholder='Last Name']");
    private By saveButton = By.xpath("//button[contains(.,'Save')]");
    private By  successMsg  =   By.xpath("//p[contains(.,'Successfully Saved')]");

    public PIMPage(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    private void navigate2addEmployeePage(){
        driver.findElement(addEmployeeButtonLocator).click();
    }


    public void addEmployee(String firstname, String lastname){
        navigate2addEmployeePage();
        driver.findElement(firstNameLocator).sendKeys(firstname);
        driver.findElement(lastNameLocator).sendKeys(lastname);
        driver.findElement(saveButton).click();
    }


    public boolean isEmployeeSaved(){
        return !driver.findElements(successMsg).isEmpty();
    }
}
