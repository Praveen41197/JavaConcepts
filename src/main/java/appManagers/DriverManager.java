package appManagers;

import org.openqa.selenium.WebDriver;

import appConstants.AppConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

	private static WebDriver driver = null;

	public static void launchBrowser() {

		switch (AppConstants.Browser_name.toLowerCase()) {
		case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = WebDriverManager.chromedriver().create();
			break;
		case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = WebDriverManager.firefoxdriver().create();
			break;
		case "edge":
				WebDriverManager.edgedriver().setup();
				driver = WebDriverManager.edgedriver().create();
			break;

		default:
			WebDriverManager.chromedriver().setup();
			driver = WebDriverManager.chromedriver().create();
			break;
		}


	}

public static WebDriver getDriver() {
	return driver;
}
	
}
