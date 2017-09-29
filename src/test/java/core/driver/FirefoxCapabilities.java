package core.driver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * This class defines the capabilities for Firefox driver
 * 
 * @author prati3k
 *
 */
public class FirefoxCapabilities extends AbstractCapabilities implements CapabilitiesManager {

	@Override
	public DesiredCapabilities getBrowserCapabilities() {
		FirefoxProfile fp = new FirefoxProfile();
		// set this to try to disable the "unresponsive" script warning.
		fp.setPreference("dom.max_script_run_time", 0);
		fp.setPreference("dom.max_chrome_script_run_time", 0);
		fp.setPreference("browser.download.folderList", 2);
		fp.setPreference("browser.download.manager.showWhenStarting", false);
		fp.setPreference("browser.helperApps.alwaysAsk.force", false);
		fp.setPreference("browser.helperApps.neverAsk.saveToDisk",
				"application/vnd.ms-excel;image/png;application/rtf;text/richtex;image/tiff;text/plain;application/xml;text/yaml;application/zip;text/html;application/xhtml+xml");
		fp.setPreference("pdfjs.disabled", true);
		fp.setAcceptUntrustedCertificates(true);
		fp.setAssumeUntrustedCertificateIssuer(false);
		// fp.setPreference("webdriver_enable_native_events", false);
		DesiredCapabilities dc = DesiredCapabilities.firefox();
		dc.setCapability(FirefoxDriver.PROFILE, fp);
		dc.setCapability(FirefoxDriver.MARIONETTE, false);
		return dc;
	}

}
