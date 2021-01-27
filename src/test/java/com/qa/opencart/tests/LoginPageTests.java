package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.MyAccountPage;
import com.qa.opencart.utils.Constants;

public class LoginPageTests extends BaseTest {

	@Test
	public void loginPageTitleTest() {
		String title = loginPage.getPageTitle();
		System.out.println("title is : "+title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test (priority = 1)
	public void isForgottenPasswdLinkExist() {
		Assert.assertTrue(loginPage.isForgotPasswordLinkPresent());
	}
	
	@Test (priority = 2)
	public void loginTest() {
		accountPage=loginPage.dologinClick(prop.getProperty("username"), prop.getProperty("password"));
		String title=accountPage.verifyTitle();
		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
	}
}
