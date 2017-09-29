package core.component;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.WrapsElement;

import core.driver.WebDriverManager;
import core.pageobject.IgnoreInit;
import core.util.StringUtils;

/**
 * This is base element extended from WebElement. This is wrapper around
 * WebElement. All user defined components would extend this class. This class
 * would contain all common methods applicable to all elements
 * 
 * @author prat3ik
 *
 */
public class BaseElement implements WebElement, WrapsElement {
	@IgnoreInit
	protected WebElement el = null;
	protected String fieldName = "";
	protected Integer explicitWait;

	public BaseElement(final String css) {
		this.el = this.getDriver().findElement(By.cssSelector(css));
	}

	public BaseElement(final WebElement el) {
		this.el = el;
	}

	protected WebDriver getDriver() {
		return WebDriverManager.getThreadLocalDriver();
	}

	protected Actions getActionsDriver() {
		return new Actions(this.getDriver());
	}

	public void changeStyleToDisplayHidden() {
		final JavascriptExecutor js = (JavascriptExecutor) this.getDriver();
		js.executeScript("arguments[0].style.display='none';", this.el);

	}

	public void changeStyleToDisplay() {
		final JavascriptExecutor js = (JavascriptExecutor) this.getDriver();
		js.executeScript("arguments[0].style.display='block';", this.el);
	}

	@Override
	public void clear() {
		this.el.clear();
	}

	@Override
	public void click() {
		this.scrollIntoView();
		this.el.click();
	}

	/**
	 * This method implements double click functionality on element
	 *
	 */
	public void doubleClick() {

		this.getActionsDriver().doubleClick(this.el).build().perform();
	}

	/**
	 * This method would drag element to target element
	 *
	 * @param target
	 *            : target element for drop
	 */
	public void dragAndDrop(final BaseElement target) {
		this.getActionsDriver().clickAndHold(this.el).moveToElement(target.el).release(target.el).build().perform();
	}

	@Override
	public WebElement findElement(final By arg0) {
		return this.el.findElement(arg0);

	}

	@Override
	public List<WebElement> findElements(final By arg0) {
		return this.el.findElements(arg0);
	}

	@Override
	public String getAttribute(final String arg0) {
		return this.el.getAttribute(arg0);
	}

	@Override
	public String getCssValue(final String arg0) {
		return this.el.getCssValue(arg0);
	}

	/**
	 * This will return font family for an element
	 *
	 * @return Font Family used in element
	 */
	public String getFontFamily() {
		String font = this.getCssValue("font-family");
		if (font.startsWith("'")) {
			font = font.substring(1, font.length() - 1);
		}

		return font;
	}

	/**
	 * This will return font size for element
	 *
	 * @return font size of element in px unit
	 */
	public String getFontSize() {
		return this.getCssValue("font-size");
	}

	@Override
	public Point getLocation() {
		return this.el.getLocation();
	}

	/**
	 * Returns next sibling relative to this element having specified tag name
	 *
	 * @param tag
	 *            - Tag name of element
	 * @return Next sibling having specified tag name
	 */
	public BaseElement getNextSibling(final String tagXpath) {
		return new BaseElement(this.findElement(By.xpath("./following-sibling::" + tagXpath)));
	}

	@Override
	public Dimension getSize() {
		return this.el.getSize();
	}

	@Override
	public String getTagName() {
		return this.el.getTagName();
	}

	@Override
	public String getText() {
		return this.el.getText();
	}

	@Override
	public WebElement getWrappedElement() {
		return this.el;
	}

	@Override
	public boolean isDisplayed() {
		return this.isElementPresent() && this.el.isDisplayed();
	}

	/**
	 * This method checks if element is active or not
	 *
	 * @return true if element has active class, false otherwise
	 */
	public boolean isElementActive() {
		return this.el.getAttribute("class").contains("active");
	}

	/**
	 * This method checks if element is disabled or not
	 *
	 * @return true if element has disabled class, false otherwise
	 */
	public boolean isElementDisabled() {
		return this.el.getAttribute("class").contains("disabled") || "true".equals(this.el.getAttribute("disabled"));
	}

	/**
	 * This method checks if element is present or not
	 *
	 * @return true if element is present, false otherwise
	 */
	public boolean isElementPresent() {
		boolean isPresent = false;
		try {
			this.el.isDisplayed();
			isPresent = true;
		} catch (final Exception e) {
			isPresent = false;
		}
		return isPresent;
	}

	/**
	 * This method checks if element is selected or not
	 *
	 * @return true if element has selected class, false otherwise
	 */
	public boolean isElementSelected() {
		return this.el.getAttribute("class").contains("selected");
	}

	@Override
	public boolean isEnabled() {
		return this.el.isEnabled();
	}

	@Override
	public boolean isSelected() {
		return this.el.isSelected();
	}

	public BaseElement getParentElement() {
		return new BaseElement(this.el.findElement(By.xpath("..")));
	}

	/**
	 * This method moves mouse to element
	 *
	 */
	public void moveTo() {
		this.getActionsDriver().moveToElement(this.el).build().perform();
	}

	/**
	 * Sometimes element is not visible to user as page is too long. In such
	 * case, it is required to scroll up/down to element so it become visible in
	 * page. This method implements this functionality to scroll to make element
	 * visible in view
	 *
	 */
	public void scrollIntoView() {
		((JavascriptExecutor) this.getDriver()).executeScript("arguments[0].scrollIntoView(false);", this.el);
	}

	@Override
	public void sendKeys(final CharSequence... arg0) {
		this.el.clear();
		this.el.sendKeys(arg0);
	}

	// This method was needed since we encountered a few webelements which don't
	// allow clear operation and it results in exception.
	public void sendKeysWithoutClear(final CharSequence... arg0) {
		this.el.sendKeys(arg0);
	}

	@Override
	public void submit() {
		this.el.submit();
	}

	public String getValue() {
		return this.el.getAttribute("value");
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> arg0) throws WebDriverException {
		return this.el.getScreenshotAs(arg0);
	}

	@Override
	public Rectangle getRect() {
		return this.el.getRect();
	}

	public void removeEventHandler(String event) {
		final JavascriptExecutor js = (JavascriptExecutor) this.getDriver();
		js.executeScript("angular.element(arguments[0]).unbind('" + event + "');", this.el);
	}

	public void clickUsingJavaScript() {
		final JavascriptExecutor js = (JavascriptExecutor) this.getDriver();
		js.executeScript("angular.element(arguments[0]).click();", this.el);
	}

	public void setExplicitWait(Integer explicitWait) {
		this.explicitWait = explicitWait;
	}

	public void setElementName(String elementName) {
		this.fieldName = elementName;
	}

	public void moveToElementAndClick() {
		this.getActionsDriver().moveToElement(el).click().build().perform();
	}

	public boolean isAttributePresent(String attribute) {
		boolean result = false;
		try {
			String value = this.getAttribute(attribute);
			if (!value.isEmpty()) {
				result = true;
			}
		} catch (Exception e) {
			// this.logger.error(e);
		}

		return result;
	}

	public void hover() {
		scrollIntoView();
		getActionsDriver().moveToElement(el).build().perform();
	}

	public void hover(int offsetX, int offsetY) {
		scrollIntoView();
		getActionsDriver().moveToElement(el, offsetX, offsetY).build().perform();
	}

	private final static String PREVENT_DEFAULT = "function(e) { e.preventDefault() }";

	public void preventDefault(String event) {
		final JavascriptExecutor js = (JavascriptExecutor) getDriver();
		// using jQuery#one so that it only works for one time
		js.executeScript("angular.element(arguments[0]).one('" + event + "'," + PREVENT_DEFAULT + ");", el);
	}
}
