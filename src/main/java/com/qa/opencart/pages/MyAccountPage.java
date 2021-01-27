package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class MyAccountPage {

	private WebDriver driver;
	private ElementUtil util;

	By logo = By.cssSelector("div#logo a");
	By sectionHeaders = By.cssSelector("div#content h2");

	By searchTxt = By.cssSelector("div#search input[name='search']");
	By searchBtn = By.cssSelector("div#search button[type='button']");
	By searchResult = By.cssSelector("div.product-layout div.product-thumb");
	By searchItems = By.cssSelector("div.product-thumb h4 a");

	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
	}

	public String verifyTitle() {
		return util.waitForExactPageTitle(Constants.ACCOUNT_PAGE_TITLE, 5);
	}

	public int getAccountsHeaderSize() {
		int i = util.getElements(sectionHeaders).size();// driver.findElements(sectionHeaders).size();
		return i;
	}

	public List<String> getAccountHeadersList() {
		List<String> headerTextList = new ArrayList<String>();
		List<WebElement> headerList = util.getElements(sectionHeaders);// driver.findElements(sectionHeaders);
		System.out.println(headerList.size());
		for (WebElement e : headerList) {
			String txt = e.getText();
			System.out.println("account header is" + txt);
			headerTextList.add(txt);
		}
		return headerTextList;
	}

	public String getAccountsHeaderText() {
		if (util.isElementDisplayed(logo)) {
			return util.doGetText(logo);
		} else {
			return null;
		}

	}

	public boolean doSearch(String prodname) {
		util.doSendKeys(searchTxt, prodname);
		util.doClick(searchBtn);

		if ((util.getElements(searchResult).size()) > 0) {
			return true;
		} else 
		{
			return false;
		}
	}

	public ProductInfoPage selectProduct(String proName) {
		List<WebElement>result= util.getElements(searchItems);
		System.out.println(result.size());
		for (WebElement e : result) {
		 String txt=e.getText();
		 if(txt.equals(proName)) {
			 e.click();
			 break;
		 }
		}
		
		return new ProductInfoPage(driver);
	}
}
