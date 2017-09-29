package core.driver;

import java.util.logging.Level;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * This class intialize the common capabilities irrespective of all browsers
 * 
 * @author prat3ik
 *
 */
public abstract class AbstractCapabilities {

	protected DesiredCapabilities dc;

	public AbstractCapabilities() {
		initializeCommonCapabilities();
	}

	protected abstract DesiredCapabilities getBrowserCapabilities();

	public DesiredCapabilities getCapabilities() {
		return this.dc;
	}

	private void initializeCommonCapabilities() {
		this.dc = getBrowserCapabilities();
		this.dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		this.dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		this.dc.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
		this.dc.setCapability(CapabilityType.SUPPORTS_FINDING_BY_CSS, true);
		this.dc.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
		this.dc.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
		this.dc.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, 1);

		LoggingPreferences logs = new LoggingPreferences();
		logs.enable(LogType.BROWSER, Level.ALL);
		logs.enable(LogType.PERFORMANCE, Level.ALL);
		this.dc.setCapability(CapabilityType.LOGGING_PREFS, logs);
	}
}
