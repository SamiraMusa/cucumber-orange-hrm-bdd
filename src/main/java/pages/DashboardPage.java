package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class DashboardPage {

    WebDriver driver;

    public DashboardPage(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void navigate2adminPage(){   clickLinkText("Admin"); }

    public void navigate2PIMPage(){
        clickLinkText("PIM");
    }

    public void navigate2LeavePage(){ clickLinkText("Leave"); }

    private void clickLinkText(String linkText){
        driver.findElement(By.linkText(linkText)).click();
    }

}
