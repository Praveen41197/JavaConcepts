package loginLogoutTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import app.Base.BaseClass;
import appConstants.AppConstants;
import appPages.LoginPage;

public class LoginLogoutTest extends BaseClass {

	LoginPage loginPage;
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
    public void VerifyLogout() throws InterruptedException { //Logout perform only if loggedin
    	String actualValue = loginPage.logout();
    	Assert.assertEquals(actualValue, "Login in Book Store");
    	
    }
}

//added new command
