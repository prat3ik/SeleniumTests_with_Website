package core.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * This will represent confirmation modal appearing on campaign suite pages
 * 
 * @author pratikpat
 * 
 */
public class ConfirmationModal extends BaseElement {

	public ConfirmationModal(final String css) {
		super(css);
	}

	public ConfirmationModal(final WebElement el) {
		super(el);
	}

	String cssForConfirmButton = ".btn-red";
	String cssForCancelButton = ".btn-minor";
	String cssForCloseButton = "[title='Close']";

	/**
	 * This will click on cancel button
	 */
	public void cancel() {
		this.getCancelButton().click();
	}

	/**
	 * This will click on close(x) button
	 */
	public void close() {
		this.getCloseButton().click();
	}

	/**
	 * This will click on confirm button
	 */
	public void confirm() {
		this.getConfirmButton().click();
	}

	public WaitEnabledBaseElement getCancelButton() {
		return new WaitEnabledBaseElement(this.findElement(By.cssSelector(cssForCancelButton)));
	}

	public WaitEnabledBaseElement getCloseButton() {
		return new WaitEnabledBaseElement(this.findElement(By.cssSelector(cssForCloseButton)));
	}

	public WaitEnabledBaseElement getConfirmButton() {
		return new WaitEnabledBaseElement(this.findElement(By.cssSelector(cssForConfirmButton)));
	}

	public void setCssForCancelButton(String cssForCancelButton) {
		this.cssForCancelButton = cssForCancelButton;
	}

	public void setCssForConfirmButton(String cssForConfirmButton) {
		this.cssForConfirmButton = cssForConfirmButton;
	}

	public void setCssForCloseButton(String cssForCloseButton) {
		this.cssForCloseButton = cssForCloseButton;
	}

}
