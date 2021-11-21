package com.crmnext.pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver)
	{
		System.out.println("In Login Page object class");
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(css="#TxtName")
	@CacheLookup
	WebElement txtUsername; 
	
	@FindBy (css = "[name='SecureTextBox.Text']")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy (css ="body.bg-light-gray:nth-child(2) div.body-content.min-vh-100.login-background.h-100:nth-child(1) div.flexbox-row.w-100 div.login-box.relative div.login-container.flexbox-row.items-center.min-vh-100.h-100:nth-child(2) div.flex-1.login-box--inner div:nth-child(1) div.login-form form:nth-child(1) > input.login-button.button.w-100:nth-child(4)")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy (css="body.bg-light-gray:nth-child(2) div.header.bg-white.flex.items-center.justify-between.wt--100.ph-16 div.header-profile.flex.items-center.justify-end.min-wt-15 div.header__item.header__profile.ht-3.mid.relative.pointer:nth-child(5) a.header-profile__media.min-wt-2.wt-2.ht-2.overflow-hidden > img.min-wt--100.wt--100.ht--100.br-pill.pointer")
	@CacheLookup
	WebElement userProfile;
	
	@FindBy (css="body.bg-light-gray:nth-child(2) div.header.bg-white.flex.items-center.justify-between.wt--100.ph-16 div.header-profile.flex.items-center.justify-end.min-wt-15 div.header__item.header__profile.ht-3.mid.relative.pointer:nth-child(5) div.drop-down.isActive.header-profile__dropdown div.tl.bt.b--light-gray:nth-child(3) div.pv-8.ph-16 div.form-element.form-element--labelLeft a.tb.button-icon-text.f12 > span.acd-link-text")
	@CacheLookup
	WebElement btnLogout;
	
//Function to enter user name
	public void setUserName (String uname)
	{
		System.out.println("in set user name "+uname);
		ldriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		txtUsername.sendKeys(uname);
		System.out.println("After enter in user name");
	}
	
//Function to Enter password	
	public void setPwd (String pwd)
	{
		System.out.println("in set password" + pwd);
		txtPassword.sendKeys(pwd);
	}
// Function to click button
	public void clickLogin ()
	{
		btnLogin.click();
	}
	
//
	public void clickLogout () throws Exception
	{
		userProfile.click();
		Thread.sleep(500);
		btnLogout.click();
	}
	

}
