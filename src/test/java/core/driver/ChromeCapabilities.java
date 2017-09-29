package core.driver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * This class defines Chrome browser Capabilities 
 * @author prat3ik
 *
 */
public class ChromeCapabilities extends AbstractCapabilities implements CapabilitiesManager {

	@Override
	protected DesiredCapabilities getBrowserCapabilities() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		DesiredCapabilities dc = DesiredCapabilities.chrome();
		dc.setCapability(ChromeOptions.CAPABILITY, options);
		return dc;
	}

}
