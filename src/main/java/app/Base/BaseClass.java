package app.Base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import appConstants.AppConstants;
import appManagers.DriverManager;
import appManagers.ExtentReportManager;

public class BaseClass {


	public static Properties props;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	public static String screenshotfilename;
	public static String formattedDate;
	public static void loadProperties() {
		FileReader reader = null;
		try {
			reader = new FileReader("Config.properties");		

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		props = new Properties();

		try {
			props.load(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		AppConstants.App_URL=props.getProperty("app_url");
		AppConstants.Admin_username=props.getProperty("lgusername");
		AppConstants.Admin_Password=props.getProperty("lgpassword");
		AppConstants.Browser_name = props.getProperty("browser");
	}


	@BeforeSuite
	public void setup() {

		loadProperties();

		if(DriverManager.getDriver() == null) {
			DriverManager.launchBrowser();
		}

		extentReports = ExtentReportManager.createExtentReport();	
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
		formattedDate = myDateObj.format(myFormatObj);
	
		DriverManager.getDriver().get(AppConstants.App_URL);
		DriverManager.getDriver().manage().window().maximize();
		DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
	}

	@BeforeTest
	public void createTest(ITestContext context) {

		Capabilities capabilities = ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities();
		String device = capabilities.getBrowserName() + "_"+capabilities.getBrowserVersion();

		extentTest = extentReports.createTest(context.getName());
		extentTest.assignDevice(device);
	}
	@AfterMethod
	public void checkStatus(Method m, ITestResult result) {

		// System.out.println("After Method");

		if (((result.getStatus() == ITestResult.FAILURE))) {
			String screenshotpath = null;
			screenshotpath = takescreenshot(result.getName() + "_" + result.getMethod().getMethodName() + ".png");
			extentTest.addScreenCaptureFromPath(screenshotpath);
			extentTest.fail(result.getThrowable());

			extentTest.fail("Test " + m.getName() + " is failed.");

		} else if (result.getStatus() == ITestResult.SUCCESS) {

			extentTest.pass("Test " + m.getName() + " is passed.");

		}

		// get groups in test methods
		extentTest.assignCategory(m.getAnnotation(Test.class).groups());

	}
	public static String takescreenshot(String Filename) {
		String finalfile = null;

		TakesScreenshot screenshot = (TakesScreenshot) DriverManager.getDriver();
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		String screenshotpath = "./Screenshots/extentReports/";
		screenshotfilename = formattedDate + Filename;
		finalfile = screenshotpath + screenshotfilename;

		File currentdir = new File(screenshotpath);
		if (!currentdir.exists()) {
			currentdir.mkdirs();

		}
		File destFile = new File(finalfile);

		try {
			FileUtils.copyFile(srcFile, destFile);
			System.out.println("Screenshot saved successfully.");

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception in taking Screenshot");
		}
		return destFile.getAbsolutePath();
		// return finalfile;

	}

	@AfterSuite
	public void teardown() {
		DriverManager.getDriver().quit();
		extentReports.flush();

	}


}
