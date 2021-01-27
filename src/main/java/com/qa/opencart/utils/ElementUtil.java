package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	
	
	private static WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);

	}

	public void doSendKeys(By locator, String value) {
		WebElement ele= getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public boolean isElementDisplayed(By locator) {
		return driver.findElement(locator).isDisplayed();
	}
	
	public String doGetText(By locator) {
		return driver.findElement(locator).getText();
	}
	// ********************************DropDownUtils******************************************

	public void doSelectByVisibleText(By locator, String value) {

		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);

	}

	public void doSelectByIndex(By locator, int value) {

		Select select = new Select(getElement(locator));
		select.selectByIndex(value);

	}

	public void doSelectByValue(By locator, String value) {

		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectByXpath(By locator, String value) {

		List<WebElement> optionsList = getElements(locator);
		System.out.println(optionsList.size());

		for (WebElement e : optionsList) {
			String txt = e.getText();
			if (txt.equals(value)) {
				e.click();
				break;
			}

		}

	}

	public void selectFromSuggetionList(By locator, String value) throws InterruptedException {

		List<WebElement> optionList = getElements(locator);
		System.out.println(optionList.size());
		for (WebElement e : optionList) {
			String suggetionText = e.getText();
			System.out.println(suggetionText);
			if (suggetionText.equals(value)) {
				e.click();
				break;
			}
		}
	}

	// pass the locator and get all the options from drop down
	public List<String> getOptionsTextList(By locator, String text) {
		List<String> optionListItems = new ArrayList<String>();
		Select select = new Select(getElement(locator));
		List<WebElement> optionList = select.getOptions();
		System.out.println(optionList.size());
		for (WebElement e : optionList) {
			String txt = e.getText();
			optionListItems.add(txt);
			if (txt.equals("Education")) {
				e.click();
			}

		}
		return optionListItems;
	}

	public List<String> getAllFooterLinks(By locator, String value) {
		List<String> footerLink = new ArrayList<String>();
		List<WebElement> langList = getElements(locator);
		System.out.println(langList.size());
		for (WebElement e : langList) {
			String langTxt = e.getText();
			System.out.println(langTxt);
			footerLink.add(langTxt);
			/*
			 * if (langTxt.equals("मराठी")) { e.click(); break; }
			 */
		}
		return footerLink;
	}

	public List<String> getRightClickOptions(WebElement rightClickLoc, By locator) {
		Actions act = new Actions(driver);
		act.contextClick(rightClickLoc).build().perform();

		List<String> optionsText = new ArrayList();
		List<WebElement> options = getElements(locator);
		System.out.println(options.size());
		for (WebElement e : options) {
			String text = e.getText();
			System.out.println(text);
			optionsText.add(text);

		}
		return optionsText;
	}

	public void doRightClick(By locator, String value) {
		List<WebElement> options = getElements(locator);
		System.out.println(options.size());
		for (WebElement e : options) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
			}
		}
	}

	public void doActionSendKeys(By locator, String value) {

		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).build().perform();
	}

	public void doActionsClick(By locator) {

		Actions act = new Actions(driver);
		act.click(getElement(locator)).click().build().perform();
	}

//	wait functons
	public String waitForPageTitleWithContain(String pageTitle, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleContains(pageTitle));
		return driver.getTitle();

	}

	public String waitForExactPageTitle(String pageTitle, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleIs(pageTitle));
		return driver.getTitle();

	}

	public String waitForExactPageURL(String URL, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.urlToBe(URL));
		return driver.getCurrentUrl();

	}

	public Alert isAlertPresent(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		return alert;
	}

	public Alert getAlertText(int timeout) {
		return isAlertPresent(timeout);

	}

	public void acceptAlert(int timeout) {
		isAlertPresent(timeout).accept();
	}

	public void dismissAlert(int timeout) {
		isAlertPresent(timeout).accept();
	}

	public WebElement waitForWebElementt(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));// (elements)(URL));
		return webElement;

	}

	public List<WebElement> waitForAllWebElementt(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		List<WebElement> webElementList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));// (elements)(URL));
		return webElementList;

	}

	public void waitForElementClickableWithActions(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(locator));// (elements)(URL));
		Actions act = new Actions(driver);
		act.moveToElement(webElement).click().build().perform();

	}

	public void waitForElementClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(locator));// (elements)(URL));
		webElement.click();
	}
	
	public WebElement waitForElementWuthFluentWait(By Locator, int timeout, int pollingtime ) {
		Wait<WebDriver> newWait= new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingtime))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		
		return newWait.until(ExpectedConditions.presenceOfElementLocated(Locator));
	}
	
	public List<WebElement> waitForElementsWuthFluentWait(By Locator, int timeout, int pollingtime ) {
		Wait<WebDriver> newWait= new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingtime))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		
		return newWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Locator));
	}

	public void getPageElementsText(By locator, int timeout) {
		waitForAllWebElementt(locator, timeout).stream().forEach(ele -> System.out.println(ele.getText()));
	}

	public int getPageElementsCount(By locator, int timeout) {
		return waitForAllWebElementt(locator, timeout).size();
	}

//	custom  dynamic wait  to find web element

	public WebElement retryingElemt(By locator) {
		WebElement element = null;
		int attempts = 0;
		while (attempts < 30) {
			try {
				element = driver.findElement(locator);
				break;
			} catch (NoSuchElementException e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (StaleElementReferenceException e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			System.out.println("no element found"+attempts);
			attempts++;
			
		}
		return element;
	
	}



}
