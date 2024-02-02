package appPages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import app.Base.BaseClass;
import appManagers.DriverManager;

public class LoginPage extends BaseClass {

	WebDriver driver;
	WebDriverWait wait;
	
	public LoginPage() {
		this.driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	
	private static LoginPage loginPageInstance ;
	
	public static LoginPage getInstance() {
		if (loginPageInstance == null) {
			loginPageInstance = new LoginPage();
		}
		return loginPageInstance;
	}
	
	@FindBy(id = "login")
	public WebElement loginPagebtn;
	
	@FindBy(id = "userName")
	public WebElement Username;
	
	@FindBy(id = "password")
	public WebElement Password;
	
	@FindBy(id = "login")
	public WebElement Login;
	
	@FindBy(id = "newUser")
	public WebElement NewUser;
	
	@FindBy(id = "userName-value")
	public WebElement UsernameValue;
	
	@FindBy(id = "submit")
	public WebElement LogoutBtn;
	
	@FindBy(xpath = "//*[@id='userForm']/div[1]/h5")
	public WebElement LogoutVerifyValue;
	
	public void gotoLoginPage() throws InterruptedException {
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(loginPagebtn));
		loginPagebtn.click();
		Thread.sleep(1000);
	}
	
	
	public String LoginintoAccount(String username, String password) throws InterruptedException {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(Username));
		wait.until(ExpectedConditions.visibilityOf(Password));
		wait.until(ExpectedConditions.visibilityOf(Login));
		Username.sendKeys(username);
		Password.sendKeys(password);
		wait.until(ExpectedConditions.elementToBeClickable(Login));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 500)");
		Thread.sleep(3000);
		Login.click();
		wait.until(ExpectedConditions.visibilityOf(UsernameValue));
		
		return UsernameValue.getText();
		
	}
	
	
	public String logout() throws InterruptedException {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(LogoutBtn));
		wait.until(ExpectedConditions.elementToBeClickable(LogoutBtn));
		LogoutBtn.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(LogoutVerifyValue));
		return LogoutVerifyValue.getText();
	}
	
}
