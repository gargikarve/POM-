package com.qa.opencart.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.MyAccountPage;
import com.qa.opencart.testlisteners.ExtentReportListener;
import com.qa.opencart.utils.Constants;


@Listeners(ExtentReportListener.class)
public class MyAccountPageTest extends BaseTest {
	
	@BeforeClass
	public void setUpAccountsPage() {
		accountPage=loginPage.dologinClick(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void verifyTitleTest() {
		String title= accountPage.verifyTitle();
		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test (priority = 2)
	public void getAccountsHeaderSizeTest() {
		int size=accountPage.getAccountsHeaderSize();
		Assert.assertEquals(size,3);
	}
	
	@Test (priority = 3)
	public void getAccountsHeaderTextTest() {
		String header= accountPage.getAccountsHeaderText();
		Assert.assertEquals(header, Constants.ACCOUNTS_PAGE_HEADER);
	}
	
	@Test (priority = 4)
	public void getAccountHeadersListTest() {
		List<String>Headerlist=accountPage.getAccountHeadersList();
		System.out.println(Headerlist);
	}
	
	//@Test (priority = 5)
	public void doSearchTest() {
		Assert.assertTrue(accountPage.doSearch("imac"));
	}
	
	@Test (priority = 6)
	public void selectProducttest() {
		accountPage.doSearch("mac");
		accountPage.selectProduct("MacBook Pro");
	}
	

}
