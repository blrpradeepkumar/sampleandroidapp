package com.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.amazon.utilities.Utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LaunchPage {
	
	//Launch application page

	@AndroidFindBy(xpath = "//*[contains(@text,'Already a customer')]")
	private WebElement alreadyACustomerButton;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='New to Amazon.in? Create an account']")
	private WebElement createAnAccountButton;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Skip sign in']")
	private WebElement skipSignInButton;
	
	@AndroidFindBy(xpath = "//*[@text='Create account. New to Amazon?']")
	private WebElement createAccountRadioButton;
	
	//Page to enter username
	@AndroidFindBy(xpath = "//*[@text='Login. Already a customer?']")
	private WebElement loginAlreadyACustomerRadioButton;
	
	@AndroidFindBy(className = "android.widget.EditText")
	private WebElement emailOrPhoneTextBox;
	
	@AndroidFindBy(xpath = "//*[@text='Continue']")
	private WebElement continueButton;

	//Page to enter password
	@AndroidFindBy(className="android.widget.EditText")
	private WebElement passwordTextBox;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='signInSubmit']")
	private WebElement loginButton;
	
	@AndroidFindBy(xpath = "//android.widget.CheckBox[@text='Show password']")
	private WebElement showPasswordCheckBox;
	
	/**
	 * Constructor to initialize the launch page. 
	 * @param driver - It takes the driver object as paramater.
	 */
	public LaunchPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	/**
	 * Getter method to get the already customer button.
	 * @return - returns the get already a customer WebElement.
	 */
	public WebElement getAlreadyACustomerButton() {
		return alreadyACustomerButton;
	}

	/**
	 * Getter method to get the create an account button.
	 * @return - returns the create an account button WebElement.
	 */
	public WebElement getCreateAnAccountButton() {
		return createAnAccountButton;
	}

	/**
	 * Getter method for the skip sign in button.
	 * @return - returns the skip sign in button WebElement.
	 */
	public WebElement getSkipSignInButton() {
		return skipSignInButton;
	}

	/**
	 * Getter method for create account radio button.
	 * @return - returns the create account radio button WebElement.
	 */
	public WebElement getCreateAccountRadioButton() {
		return createAccountRadioButton;
	}

	/**
	 * Getter method for the Login already customer radio button.
	 * @return - returns the login already a customer radio button WebElement.
	 */
	public WebElement getLoginAlreadyACustomerRadioButton() {
		return loginAlreadyACustomerRadioButton;
	}

	public WebElement getEmailOrPhoneTextBox() {
		return emailOrPhoneTextBox;
	}

	public WebElement getContinueButton() {
		return continueButton;
	}

	public WebElement getPasswordTextBox() {
		return passwordTextBox;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	public WebElement getShowPasswordCheckBox() {
		return showPasswordCheckBox;
	}

	public void clickOncreateAnAccountButton() {
		createAnAccountButton.click();
	}

	public void clickOnAlreadyCustomerButton() {
		alreadyACustomerButton.click();
	}

	public void clickOnSkipSignInButton() {
		skipSignInButton.click();
	}

	public void enterUsername(String userName) {
		Utilities.tapOnElement(getEmailOrPhoneTextBox());
		getEmailOrPhoneTextBox().sendKeys(userName);
	}
	
	public void clickOnLoginAlreadyACustomerRadioButton() {
		getLoginAlreadyACustomerRadioButton().click();
	}
	
	public void clickOnContinueButton() {
		Utilities.tapOnElement(continueButton);
	}
	
	public void enterPassword(String password) {
		Utilities.tapOnElement(getPasswordTextBox());
		getPasswordTextBox().sendKeys(password);
		
	}
	
	public HomePage clickLoginButton(AndroidDriver<AndroidElement> driver) {
		
		Utilities.tapOnElement(loginButton);
		return new HomePage(driver);
	}

}