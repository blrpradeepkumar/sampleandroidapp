package com.amazon.test;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.amazon.base.BaseClass;
import com.amazon.pages.CartPage;
import com.amazon.pages.HomePage;
import com.amazon.pages.LaunchPage;
import com.amazon.pages.SearchPage;
import com.amazon.utilities.Utilities;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author Pradeep Kumar C
 */
public class ProductSearchTest extends BaseClass {

	LaunchPage launchPage;
	HomePage homePage;
	SearchPage searchPage;
	CartPage cartPage;

	/**
	 * Test method to launch the application 
	 */
	@Test(priority = 0)
	public void launchingApplication() {

		// Landing page after launching the application
		launchPage = new LaunchPage(driver);
		log.debug("Application launched");
		test.log(LogStatus.INFO, "The applictaion was launched succesfully.");

		launchPage.clickOnAlreadyCustomerButton();

	}

	/*
	 * Method that performs actions on username page. It reads data from excel and has dependency on launching application method.
	 */
	@Test(dataProviderClass = Utilities.class, dataProvider = "dataprovider", dependsOnMethods = {
			"launchingApplication" })
	public void userNamePage(Hashtable<String, String> data) {
		// Enter username page
		log.debug("On username prompt page");
		test.log(LogStatus.INFO, "On username prompt page");

		wait.until(ExpectedConditions.elementToBeClickable(launchPage.getContinueButton()));

		if (launchPage.getAlreadyACustomerButton().isEnabled()) {
			launchPage.enterUsername(data.get("username"));
			launchPage.clickOnContinueButton();
		}
		log.debug("Username entered is : " + data.get("username"));
		test.log(LogStatus.INFO, "The entered username is : " + data.get("username"));
	}

	/*
	 * Method that performs actions on password page. It reads data from excel and has dependency on usernamePage method.
	 */
	@Test(dataProviderClass = Utilities.class, dataProvider = "dataprovider", dependsOnMethods = { "userNamePage" })
	public void passwordPage(Hashtable<String, String> data) {
		// Enter password page
		log.debug("On password prompt page");
		test.log(LogStatus.INFO, "On password prompt page");

		wait.until(ExpectedConditions.elementToBeClickable(launchPage.getLoginButton()));

		if (launchPage.getShowPasswordCheckBox().isDisplayed()) {
			launchPage.enterPassword(data.get("password"));
			homePage = launchPage.clickLoginButton(driver);
		}
		
		if(!homePage.getSearchTextBoxOnHomePage().isDisplayed()) {
			Assert.fail("The home page was not displayed");
		}

	}

	/**
	 * Method that performs actions on home page. It reads data from excel and has dependency on passwordPage method.
	 * @param data - It will read the data from excel and convert that to Hashtable object.
	 */
	@Test(dataProviderClass = Utilities.class, dataProvider = "dataprovider", dependsOnMethods = { "passwordPage" })
	public void homePageValidation(Hashtable<String, String> data) {
		// Home page to search for products
		// homePage = new HomePage(driver);
		log.debug("On application home page");
		test.log(LogStatus.INFO, "On application home page");

		if (homePage.getEnglishCheckBox().isDisplayed()) {
			homePage.clickOnEnglishCheckbox();
			homePage.clickOnSaveChangesButton();
			wait.until(ExpectedConditions.elementToBeSelected(homePage.getSearchTextBoxOnHomePage()));
			homePage.enterDetailsinSearchTextBox(data.get("searchtext"));
			searchPage = homePage.selectProductFromList(driver);
		} else if (homePage.getSearchTextBoxOnHomePage().isDisplayed()) {
			homePage.enterDetailsinSearchTextBox(data.get("searchtext"));
			searchPage = homePage.selectProductFromList(driver);
		}
		
		if(!searchPage.getListOfProducts().get(0).isDisplayed()) {
			Assert.fail("The search page is not displayed.");
		}
		log.debug("product searched is : " + data.get("searchtext"));
		test.log(LogStatus.INFO, "The product searched is : " + data.get("searchtext"));
	}

	/**
	 * Method that performs actions on searchForProduct page. It has dependency on homePageValidation method.
	 */
	@Test(dependsOnMethods = { "homePageValidation" })
	public void searchForProduct() {
		// Search page displaying list of products

		searchPage.clickToSeeAllResults();
		searchPage.chooseARandomProduct();
	}

	/**
	 * Method that performs actions on selected product page. It has dependency on searchForProduct method.
	 */
	@Test(dependsOnMethods = { "searchForProduct" })
	public void selectedProduct() {
		// Product page displaying the selected product
		String titleOfProduct = searchPage.getProductTitle();
		String priceOfProduct = searchPage.getProductPrice();
		searchPage.clickOnAddtoCartButton();
		searchPage.clickOnCartButton();

		// Cart page displaying the product in the cart
		cartPage = new CartPage(driver);
		String titleOnCartPage = cartPage.getTitleInCartPage();
		String priceOnCartPage = cartPage.getProductPrice().get(0).getText();

		// Validating title on both pages
		Assert.assertEquals(titleOfProduct, titleOnCartPage, "The product title on both the pages do not match");

		// Validating price on both pages
		Assert.assertEquals(priceOfProduct, priceOnCartPage, "The product price on both the pages do not match");
	}

}