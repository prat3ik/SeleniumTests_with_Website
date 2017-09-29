package core.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import core.driver.WebDriverManager;
import core.util.TestProperties;
import core.util.WaitUtils;

/**
 * Base Page Object class, all other page object class must extend this class
 * 
 * @author prat3ik
 *
 */
public abstract class BasePO {
	protected WaitUtils waitUtils;
	protected TestProperties testProperties;

	public BasePO() {
		waitUtils = new WaitUtils();
		testProperties = new TestProperties();
		loadProperties();
		initElements();
	}

	private void initElements() {
		CustomPageFactory.initElements(getDriver(), this);
	}

	private void loadProperties() {

	}

	protected WebDriver getDriver() {
		return WebDriverManager.getThreadLocalDriver();
	}
}
