package com.crmnext.testCases;

//import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.crmnext.pageObjects.LoginPage;

public class TC1_LoginTest_001 extends BaseClass
{
	@Test
	public void loginTest() throws Exception
	{
		//driver.get(baseURL);
		
		LoginPage lp = new LoginPage(driver);

		lp.setUserName(username);
		logger.info("User Name is entered - "+username);
		
		lp.setPwd(password);
		logger.info ("Password is entered - "+password);
		
		lp.clickLogin();
		logger.info("Login Button Clicked");
		
		System.out.println(driver.getTitle());
		
		if (driver.getTitle().equals("Summary - CRMnext - Smart.Easy.Complete"))
		{
			Assert.assertTrue(true);
			logger.info("Login Successful");
			lp.clickLogout();
		}
		else
		{
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
			logger.info("Login not Successful");
			
		}
				
	}


	
	
}
