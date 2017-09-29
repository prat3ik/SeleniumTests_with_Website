package smoke.po;

import org.openqa.selenium.support.FindBy;

import core.component.BaseElement;
import core.component.ButtonElement;
import core.pageobject.BasePO;

public class LoginPO extends BasePO {

	@FindBy(css = "input[type='email']")
	private BaseElement emailField;

	@FindBy(css = "input[type='password']")
	private BaseElement passwordField;

	@FindBy(css = "button[type='submit']")
	private ButtonElement signInButton;

	/**
	 * This method is used to Fill Email Id in Email field
	 * 
	 * @param email
	 */
	public void fillEmailId(String email) {
		emailField.sendKeys(email);
	}

	/**
	 * This method will fill the Password in Password field
	 * 
	 * @param password
	 */
	public void fillPassword(String password) {
		passwordField.sendKeys(password);
	}

	/**
	 * This method will click on Sign in button and Navigate to Admin Panel
	 * 
	 * @return
	 */
	public AdminPanelPO clickOnSignInButton() {
		signInButton.click();
		waitUtils.waitForPageToLoad();
		return new AdminPanelPO();
	}

}
