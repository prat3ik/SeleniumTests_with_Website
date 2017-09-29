package core.component;

import org.openqa.selenium.WebElement;

import core.util.WaitUtils;

/**
 * This class would represent an element that need a wait statement after click
 * and sendKeys operations.
 *
 * @author pratikpat
 *
 */
public class WaitEnabledBaseElement extends BaseElement {

	protected WaitUtils waitUtils;

	public WaitEnabledBaseElement(final String css) {
		super(css);
		this.waitUtils = new WaitUtils();
	}

	public WaitEnabledBaseElement(final WebElement baseElement) {
		super(baseElement);
		this.waitUtils = new WaitUtils();
	}

	@Override
	public void click() {
		this.waitUtils.staticWait(2000);
		super.click();
		this.waitUtils.waitTillDataIsFetched(this.explicitWait, this.fieldName);
	}

	@Override
	public void sendKeys(final CharSequence... text) {
		super.sendKeys(text);
		this.waitUtils.waitTillDataIsFetched(this.explicitWait, this.fieldName);
	}

	// This method was needed since we encountered a few webelements which don't
	// allow clear operation and it results in exception.
	public void sendKeysWithoutClear(final CharSequence... text) {
		super.sendKeysWithoutClear(text);
		this.waitUtils.waitTillDataIsFetched(this.explicitWait, this.fieldName);
	}

	public void moveToElementAndClick() {
		super.moveToElementAndClick();
		this.waitUtils.waitTillDataIsFetched(this.explicitWait, this.fieldName);
	}

}
