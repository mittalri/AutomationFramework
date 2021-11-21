package com.crmnext.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crmnext.pageObjects.LoginPage;
import com.crmnext.utilities.XLUtils;

public class TC2_LoginValidation_002 extends BaseClass
{
	@Test(dataProvider="LoginData")
	public void loginValidation(String user, String pwd) throws Exception
	{
		driver.get(baseURL);
		
		LoginPage lp = new LoginPage(driver);
		
		lp.setUserName(user);
		logger.info("User Name Provided");
		lp.setPwd(pwd);
		logger.info("Password Provided");
		
		lp.clickLogin();
		logger.info("Login Clicked");
		
		if (isAlertPresent()==true)
		{
			logger.warn("Login failed");
			
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
		}
		else
		{
			logger.info("Login passed");
	
			Assert.assertTrue(true);
			lp.clickLogout();
			
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();

		}
		
	}

	public boolean isAlertPresent()
	{
		try
		{
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
	}
	
	
	@DataProvider(name = "LoginData")
	String[][] getdata() throws IOException
	{
		String path = System.getProperty("user.dir")+"/src/test/java/com/crmnext/testData/LoginData.xlsx";
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		String logindata[][] = new String [rownum][colcount];
		
		for (int i=1 ; i <= rownum ; i++) //every row
		{
			for (int j = 0; j<colcount; j++)
			{
				
				logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);
			}
			
		}
		
		return logindata;
		
	}

}
