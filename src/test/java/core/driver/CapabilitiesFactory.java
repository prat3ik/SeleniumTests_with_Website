package core.driver;

import org.openqa.selenium.remote.DesiredCapabilities;

import core.driver.WebDriverFactory.BrowserType;
import core.util.PropertyUtils;

/**
 * This class have method to get browser specific capabilities
 * 
 * @author prat3ik
 *
 */
public class CapabilitiesFactory {

	public static DesiredCapabilities getCapabilities() {
		final BrowserType browserName = BrowserType.valueOf(PropertyUtils.getProperty("automation.browser", "FIREFOX"));
		return CapabilitiesFactory.getCapabilities(browserName);
	}

	public static DesiredCapabilities getCapabilities(BrowserType browserName) {
		DesiredCapabilities desiredCapabilities = null;
		CapabilitiesManager capabilitiesManager;

		switch (browserName) {
		case FIREFOX:
			capabilitiesManager = new FirefoxCapabilities();
			desiredCapabilities = capabilitiesManager.getCapabilities();
			break;

		case CHROME:
			capabilitiesManager = new ChromeCapabilities();
			desiredCapabilities = capabilitiesManager.getCapabilities();
			break;
		}
		return desiredCapabilities;
	}

}
