package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {

	private WebDriver driver;
	private ElementUtil util;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	// locators
	By registerLink = By.xpath("//div[@class='list-group']//a[contains(text(),'Register')]");
	By fName = By.cssSelector("input#input-firstname");
	By lName = By.cssSelector("input#input-lastname");
	By email = By.xpath("//input[@name='email']");
	By telephone = By.xpath("//input[@name='telephone']");
	By passwd = By.xpath("//input[@name='password']");
	By rePasswd = By.xpath("//input[@name='confirm']");
	By subscribeYes = By.xpath("//label[@class='radio-inline'][1]/input");
	By subscribeNo = By.xpath("//label[@class='radio-inline'][2]/input");

	By privacyPolicy = By.cssSelector("input[name=agree]");
	By continueBtn = By.cssSelector("input[value=Continue]");
	By rightSideMenu = By.xpath("//aside[@id='column-right']/div/a");

	By successRegi = By
			.xpath("//p[contains(text(),'Congratulations! Your new account has been successfully created!')]");
	By logout = By.xpath("//a[contains(text(),'Logout')]");

	// actions

	public boolean enterDetails(String fNm, String lNm, String emailtxt, String telph, String pass, String subscribe ) throws InterruptedException {
		driver.get("https://demo.opencart.com/index.php?route=account/login");
		util.doClick(registerLink);
		util.doSendKeys(fName, fNm);
		util.doSendKeys(lName, lNm);
		util.doSendKeys(email, emailtxt);
		util.doSendKeys(telephone, telph);
		util.doSendKeys(passwd, pass);
		util.doSendKeys(rePasswd, pass);
		if(subscribe.equalsIgnoreCase("yes"))
		{
			util.doClick(subscribeYes);
		}
		else {
			util.doClick(subscribeNo);
		}
		util.doClick(privacyPolicy);
		util.doClick(continueBtn);
		String text = util.doGetText(successRegi);
		if (text.equals(Constants.SUCCESS_MSG)) {
			Thread.sleep(4000);
			//util.doClick(logout);
			getRightMenuList();
			util.doClick(registerLink);
			return true;
		}
		else {
			System.out.println("account not created");
			return false;
		}

		
	}

	public List<String> getRightMenuList() {
		List<String> txtListMenu = new ArrayList<String>();
		List<WebElement> rightListMenu = driver.findElements(rightSideMenu);
		System.out.println(rightListMenu.size());
		for (WebElement e : rightListMenu) {
			String txt = e.getText();
			txtListMenu.add(txt);
			if (txt.equals("Logout")) {
				e.click();
				break;
			}
		}
		System.out.println(txtListMenu);
		return txtListMenu;
	}

//	public void enterDetails(String fNm, String lNm, String emailtxt, String telph, String pass, String subscribe) {
//		// TODO Auto-generated method stub
//		
//	}

}
