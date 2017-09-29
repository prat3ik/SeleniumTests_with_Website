package core.driver;

import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * This interface has method for get Capabilities, Implementing class need to
 * define that
 * 
 * @author prat3ik
 *
 */
public interface CapabilitiesManager {

	public DesiredCapabilities getCapabilities();
}
