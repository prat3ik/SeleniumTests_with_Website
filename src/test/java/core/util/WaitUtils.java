package core.util;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.driver.WebDriverManager;

/**
 * This will contain all wait related utility methods.
 * 
 * @author prat3ik
 *
 */
public class WaitUtils {

	private Boolean isAngularDefined(final WebDriver driver) {
		boolean isAngularDefined = false;
		isAngularDefined = (Boolean) ((JavascriptExecutor) driver)
				.executeScript("return (typeof(angular)!=='undefined')");
		return isAngularDefined;
	}

	private final int explicitWaitDefault;

	public WaitUtils() {
		this.explicitWaitDefault = PropertyUtils.getIntegerProperty("explicitWait", 30);
	}

	protected WebDriver getDriver() {
		return WebDriverManager.getThreadLocalDriver();
	}

	/**
	 * This will return count of in-progress HTTP calls
	 *
	 * @param driver
	 *            - WebDriver object
	 * @return return count of running HTTP requests
	 */
	private int getHttpRequestsCount(final WebDriver driver) {
		int runningReqs = 0;
		String pendingReq = "";
		try {
			final String script = "return (typeof(angular)!=='undefined' ? (angular.element('.ng-scope').injector() ? angular.element('.ng-scope').injector().get('$http').pendingRequests.length : 999) : 0) + ''";
			pendingReq = (String) ((JavascriptExecutor) driver).executeScript(script);
			runningReqs = Integer.parseInt(pendingReq);

		} catch (final Exception e) {
			// this.logger.error("Exception executing script or incorrect
			// response", e);
		}

		return runningReqs;
	}

	/**
	 * This will check if there is any http call running through angular If any
	 * http call is in progress, it will return true. If there is no http call
	 * running, it will return false
	 *
	 * @param driver
	 *            - WebDriver object
	 * @return true if any request is processing, false otherwise
	 */
	private boolean isDataLoading(final WebDriver driver) {
		final int runningReqs = this.getHttpRequestsCount(driver);
		final boolean isDataLoading = runningReqs != 0;
		return isDataLoading;
	}

	/**
	 * This method is for static wait
	 *
	 * @param millis
	 */
	public void staticWait(final long millis) {
		try {
			TimeUnit.MILLISECONDS.sleep(millis);
		} catch (final InterruptedException e) {
			// this.logger.error("Error in staticWait.", e);
		}
	}

	/**
	 * To wait for button to be clickable
	 *
	 * @param element
	 */
	public void waitForElementToBeClickable(final WebElement element) {
		new WebDriverWait(this.getDriver(), this.explicitWaitDefault)
				.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * To wait for element (By) to be invisible
	 *
	 * @param locator
	 */
	public void waitForElementToBeInvisible(final By locator) {
		long s = System.currentTimeMillis();
		new WebDriverWait(this.getDriver(), this.explicitWaitDefault)
				.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		// this.logger.info("Waiting for 'Element to be Invisible' done. (ms)" +
		// (System.currentTimeMillis() - s));
	}

	/**
	 * To wait for given element (By) to be present
	 *
	 * @param locator
	 */
	public void waitForElementToBePresent(final By locator) {
		new WebDriverWait(this.getDriver(), this.explicitWaitDefault)
				.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/*
	 * public void waitForElementToBePresent(final BaseElement el) { long s =
	 * System.currentTimeMillis(); new
	 * FluentWait<BaseElement>(el).withTimeout(explicitWaitDefault,
	 * TimeUnit.SECONDS) .until(new Function<BaseElement, Boolean>() {
	 * 
	 * @Override public Boolean apply(BaseElement el) { return
	 * el.isElementPresent(); } });
	 * //this.logger.info("Waiting for Element completed. " +
	 * (System.currentTimeMillis() - s)); }
	 */

	/**
	 * To wait for iframe to load and Switch to it.
	 */
	public void waitForIframeToBeAvailableAndSwitchToIt(String cssForIframe) {
		long s = System.currentTimeMillis();
		new WebDriverWait(this.getDriver(), this.explicitWaitDefault)
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector(cssForIframe)));
		// this.logger.info(
		// "Waiting for Iframe to be present and switch to it completed. " +
		// (System.currentTimeMillis() - s));
	}

	/**
	 * To wait for page to load.
	 */
	public void waitForPageToLoad() {
		long s = System.currentTimeMillis();
		final ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(final WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};

		final Wait<WebDriver> wait = new WebDriverWait(WebDriverManager.getThreadLocalDriver(), explicitWaitDefault);
		wait.until(expectation);
		// this.logger.info("Page load completed. " +
		// (System.currentTimeMillis() - s));
	}

	/**
	 * To wait for element (By) to be visible
	 *
	 * @param element
	 */
	public void waitForElementToBeVisible(final By locator) {
		new WebDriverWait(this.getDriver(), this.explicitWaitDefault)
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * To wait for element to be visible
	 *
	 * @param element
	 */
	public void waitForElementToBeVisible(final WebElement element) {
		long s = System.currentTimeMillis();
		new WebDriverWait(this.getDriver(), this.explicitWaitDefault).until(ExpectedConditions.visibilityOf(element));
		// this.logger.info("Waiting for Element to be visible completed. " +
		// (System.currentTimeMillis() - s));
	}

	public void waitForElementsToBeInvisible(final List<WebElement> elements) {
		final long s = System.currentTimeMillis();
		new WebDriverWait(this.getDriver(), this.explicitWaitDefault)
				.until(ExpectedConditions.invisibilityOfAllElements(elements));
		// this.logger.info("Waiting for 'Elements to be Invisible' done. (ms)"
		// + (System.currentTimeMillis() - s));
	}

	public void waitForElementToBeNotPresent(final By element) {
		long s = System.currentTimeMillis();
		new WebDriverWait(this.getDriver(), this.explicitWaitDefault)
				.until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(element)));
		// this.logger.info("Waiting for Element to be not present completed. "
		// + (System.currentTimeMillis() - s));
	}

	public void waitTillAngularToBePresent() {
		final long s = System.currentTimeMillis();
		new WebDriverWait(this.getDriver(), explicitWaitDefault).until(new ExpectedCondition<Boolean>() {

			@Override
			public Boolean apply(final WebDriver d) {
				return WaitUtils.this.isAngularDefined(d);
			}
		});
		// this.logger.info("Angular loading completed: (ms)" +
		// (System.currentTimeMillis() - s));
	}

	public void waitTillDataIsFetched(String fieldName) {
		waitTillDataIsFetched(null, fieldName);
	}

	public void waitTillDataIsFetched() {
		waitTillDataIsFetched(null, "");
	}

	/**
	 * This method waits until all http calls finish processing. This would be
	 * helpful in case of data loading API calls
	 * 
	 * @param fieldName
	 *            TODO
	 */
	public void waitTillDataIsFetched(Integer explicitWait, String fieldName) {
		if (explicitWait == null) {
			explicitWait = this.explicitWaitDefault;
		}
		try {
			//this.waitTillAngularToBePresent();
		} catch (Exception e) {
			// logger.error(
			// "Angular is not loaded in specified time. Either page did not
			// load correctly or it's not an Angular page.",
			// e);
		}
		// logger.info("Waiting for data to be fetched and rendered for
		// field/page: " + fieldName);

		final long s = System.currentTimeMillis();
		final int renderTime = PropertyUtils.getIntegerProperty("renderTime", 300);
		this.staticWait(renderTime);
		//this.waitTillAngularFinishProcess(explicitWait);
		//this.waitTillAngularRequestCompletes(explicitWait);
		this.waitTillJQueryRequestCompletes(explicitWait);
		this.staticWait(renderTime);
		//this.waitTillAngularFinishProcess(explicitWait);

		// this.logger.info("Data loading completed for field/page: " +
		// fieldName + ". Time: "
		// + (System.currentTimeMillis() - s) + "(ms)");
	}

	private void waitTillAngularRequestCompletes(Integer explicitWait) {
		try {
			new WebDriverWait(this.getDriver(), explicitWait).until(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(final WebDriver d) {
					return !WaitUtils.this.isDataLoading(d);
				}
			});
		} catch (Exception e) {
			// logger.error(e);
			String script = "var list =angular.element('.ng-scope').injector().get('$http').pendingRequests;"
					+ "var urls = [];" + "list.forEach(function(obj){" + "urls.push(obj.url);" + "});" + "return urls;";
			String pendingReq = "";
			try {
				pendingReq = ((JavascriptExecutor) getDriver()).executeScript(script).toString();
			} catch (Exception e1) {
				// logger.error(e1);
			}
			throw new RuntimeException("Timed out [" + explicitWait
					+ " sec] waiting for HTTP requests to complete. Pending Requests are : " + pendingReq, e);
		}
	}

	public void waitTillAngularFinishProcess() {
		waitTillAngularFinishProcess(this.explicitWaitDefault);
	}

	public void waitTillAngularFinishProcess(Integer explicitWait) {
		new WebDriverWait(this.getDriver(), explicitWait).until(angularHasFinishedProcessing());
	}

	private ExpectedCondition<Boolean> angularHasFinishedProcessing() {
		return new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				StringBuilder hasAngularFinishedScript = new StringBuilder(
						"var callback = arguments[arguments.length - 1];")
								.append("var el = document.querySelector('html');\n").append("if (!window.angular) {\n")
								.append("    callback('true')\n").append("}\n")
								.append("if (angular.getTestability) {\n")
								.append("    angular.getTestability(el).whenStable(function(){callback('true')});\n")
								.append("} else {\n").append("    if (!angular.element(el).injector()) {\n")
								.append("        callback('false')\n").append("    }\n")
								.append("    var browser = angular.element(el).injector().get('$browser');\n")
								.append("    browser.notifyWhenNoOutstandingRequests(function(){callback('true')});\n")
								.append("}");
				String isProcessingFinished = "false";
				try {
					JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
					isProcessingFinished = javascriptExecutor.executeAsyncScript(hasAngularFinishedScript.toString())
							.toString();

				} catch (Exception e) {
					// logger.error(e);
				}
				return Boolean.valueOf(isProcessingFinished);
			}
		};
	}

	public void applyStaticWaitIfCurrentMarketTimeNearsMidnight(String timezoneCode) {
		Long staticWait = new Long(121000);

		Calendar currentMarketTime = Calendar.getInstance(TimeZone.getTimeZone(timezoneCode));

		Calendar preMidnightTime = (Calendar) currentMarketTime.clone();
		preMidnightTime.set(Calendar.HOUR_OF_DAY, 23);
		preMidnightTime.set(Calendar.MINUTE, 58);
		preMidnightTime.set(Calendar.SECOND, 0);
		preMidnightTime.set(Calendar.MILLISECOND, 0);

		Calendar midnightTime = (Calendar) currentMarketTime.clone();
		midnightTime.add(Calendar.DATE, 1);
		;
		midnightTime.set(Calendar.HOUR_OF_DAY, 0);
		midnightTime.set(Calendar.MINUTE, 0);
		midnightTime.set(Calendar.SECOND, 0);
		midnightTime.set(Calendar.MILLISECOND, 0);

		if (currentMarketTime.after(preMidnightTime) && currentMarketTime.before(midnightTime)) {
			// this.logger.info(
			// "Current market time is near midnight and date is about to
			// change. So waiting for a couple of mins to avoid the date related
			// conflict");
			this.staticWait(staticWait);
		}
	}

	private void waitTillJQueryRequestCompletes(Integer explicitWait) {
		final long s = System.currentTimeMillis();
		new WebDriverWait(this.getDriver(), explicitWait).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(final WebDriver d) {
				return !WaitUtils.this.isJQueryRequestInProgress(d);
			}
		});
		// this.logger.info("JQuery Request completed: (ms)" +
		// (System.currentTimeMillis() - s));
	}

	private Boolean isJQueryRequestInProgress(final WebDriver driver) {
		boolean isAngularDefined = false;
		isAngularDefined = (Boolean) ((JavascriptExecutor) driver)
				.executeScript("return (typeof(jQuery)!=='undefined' ? jQuery.active!==0 : false)");
		return isAngularDefined;
	}

	public void waitUntilNestedElementPresent(WebElement element, By locator) {
		new WebDriverWait(getDriver(), explicitWaitDefault)
				.until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, locator));
	}
}