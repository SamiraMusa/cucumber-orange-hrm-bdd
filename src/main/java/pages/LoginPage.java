package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class LoginPage {
    public WebDriver driver;
    private By usernameLocator = By.name("username");
    private By passwordLocator = By.name("password");
    private By loginButtonLocator = By.xpath("//div/button");
    private By profileLocator = By.cssSelector("li.oxd-userdropdown");
    private By logoutButton =  By.linkText("Logout");
    private By  userCredLocator = By.cssSelector(".orangehrm-demo-credentials > p:first-child");
    private By  passCredLocator = By.cssSelector(".orangehrm-demo-credentials > p:nth-of-type(2)");
    String  username;
    String password;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    public void getCred(){
        username = driver.findElement(userCredLocator).getText();
        password = driver.findElement(passCredLocator).getText();

        username = username.replace("Username : ","");
        password = password.replace("Password : ","");

    }

    public void login(boolean isDefault){
        if (isDefault){getCred();}
        else {
            username = "test_user";
            password = "P@ssw0rd";
        }
        driver.findElement(usernameLocator).sendKeys(username);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginButtonLocator).click();
    }

    public void logout(){
        driver.findElement(profileLocator).click();
        driver.findElement(logoutButton).click();
    }

    public boolean isLoginSuccess(){
        return !driver.findElements(profileLocator).isEmpty();
    }

}
