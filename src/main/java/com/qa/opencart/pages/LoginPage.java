package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil util;

	// 1. By locators or OR

	private By uname = By.xpath("//input[@id='input-email']");
	private By passwd = By.xpath("//input[@id='input-password']");
	private By login = By.cssSelector("input[value='Login']");
	private By forgottenPass = By.xpath("//div[@class='well']//a[text()='Forgotten Password']");

	// 2.constructor

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	public String getPageTitle() {
		return util.waitForPageTitleWithContain(Constants.LOGIN_PAGE_TITLE,5);
	}
	
	public boolean isForgotPasswordLinkPresent() {
		return	util.isElementDisplayed(forgottenPass);
	}
	
	public MyAccountPage dologinClick(String username, String password) {
//		driver.findElement(uname).sendKeys(username);
//		driver.findElement(passwd).sendKeys(password);
//		driver.findElement(login).click();
		
		util.doSendKeys(uname, username);
		util.doSendKeys(passwd, password);
		util.doClick(login);
		return new MyAccountPage(driver);
	}

}
