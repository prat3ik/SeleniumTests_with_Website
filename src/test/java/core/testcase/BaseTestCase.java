package core.testcase;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import core.driver.WebDriverManager;
import core.util.TestcaseListener;

/**
 * This is a Base test case class. This would define global before and after
 * methods. These methods can be used to initialize/destroy objects (like web
 * driver, reporter, etc...) needed in all tests. All Test classes would be
 * extending this class
 * 
 * @author prat3ik
 *
 */
@Listeners({ TestcaseListener.class })
public class BaseTestCase {

	@BeforeSuite
	public void beforeSuite() {
		this.loadProperties();
	}

	@BeforeClass
	public void beforeClass() {
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(final ITestContext context, Method m) {
		Thread.currentThread().setName(m.getName() + "_" + Thread.currentThread().getId());
		System.out.println("Thread:'" + Thread.currentThread().getName() + "' is executing");
		WebDriverManager.createThreadLocalWebDriver();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(final ITestResult result) {
		String fileName = result.getTestClass().getName() + "_" + result.getName();
		System.out.println("Test Case: [" + fileName + "] executed..!");

		quitWebDriver();
	}

	@AfterClass
	public void afterClass() {

	}

	@AfterSuite
	public void afterSuite() {

	}

	protected WebDriver getDriver() {
		return WebDriverManager.getThreadLocalDriver();
	}

	public void loadProperties() {
	}

	private void quitWebDriver() {
		try {
			this.getDriver().quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		WebDriverManager.setThreadLocalWebDriver(null);
	}
}
