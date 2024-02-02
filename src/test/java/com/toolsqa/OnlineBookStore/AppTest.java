package com.toolsqa.OnlineBookStore;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import app.Base.BaseClass;
import appConstants.AppConstants;
import appManagers.DriverManager;
import appPages.LoginPage;


public class AppTest extends BaseClass
{
	/*LoginPage loginPage;
    @Test
    public void VerifyLogin() throws InterruptedException
    {
        loginPage = new LoginPage();
        loginPage.gotoLoginPage();
        String actualusernamevalue = loginPage.LoginintoAccount(AppConstants.Admin_username, AppConstants.Admin_Password);
        Thread.sleep(2000);
        Assert.assertEquals(actualusernamevalue, AppConstants.Admin_username);
    }
    @Test(dependsOnMethods = "VerifyLogin")
    public void VerifyLogout() throws InterruptedException {
    	String actualValue = loginPage.logout();
    	Assert.assertEquals(actualValue, "Login in Book Store");
    	
    }*/
}
