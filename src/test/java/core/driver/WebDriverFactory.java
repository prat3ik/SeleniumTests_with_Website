package core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import core.util.PropertyUtils;

/**
 * This is used to get browser specific WebDriver instance
 * 
 * @author pratikpat
 *
 */
public class WebDriverFactory {

	public enum BrowserType {
		FIREFOX, CHROME;
	}

	public static WebDriver getWebDriver() {
		WebDriver driver = null;
		final BrowserType browserType = BrowserType.valueOf(PropertyUtils.getProperty("automation.browser", "CHROME"));
		final String environmentType = PropertyUtils.getProperty("automation.execution.type", "local");
		final DesiredCapabilities capabilities = CapabilitiesFactory.getCapabilities(browserType);
		System.out.println("getWebDriver():"+browserType);
		System.out.println(capabilities);

		// REMOTE EXECUTION
		if (environmentType.equals("remote")) {
			int tryCount = 0;
			int maxTryCount = 3;
			if (tryCount < maxTryCount) {
				try {
					// driver = getRemoteDriver();
				} catch (Exception e) {
					// Not able to retrieve Remote driver. Retrying
					tryCount++;
					try {
						Thread.sleep(3000);
					} catch (InterruptedException ie) {
						// Paused thread interrupted while getting remote driver
					}
				}
			}
		}

		// LOCAL EXECUTION
		else {
			switch (browserType) {
			case FIREFOX:
				driver = new FirefoxDriver(capabilities);
				break;
			case CHROME:
				driver = new ChromeDriver(capabilities);
				break;
			}
		}
		return driver;
	}
}
