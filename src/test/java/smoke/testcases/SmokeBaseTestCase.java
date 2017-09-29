package smoke.testcases;

import core.testcase.BaseTestCase;
import core.util.WaitUtils;
import smoke.po.ContentPO;
import smoke.po.HomePagePO;
import smoke.po.LoginPO;

/**
 * Base Test case for Blog site Testing
 * 
 * @author prat3ik
 *
 */
public class SmokeBaseTestCase extends BaseTestCase {

	final String ADMIN_PAGE_URL = "http://localhost:2368/ghost/";
	final String HOME_PAGE_URL = "http://localhost:2368/";

	/**
	 * This method would Navigate to Admin Page
	 * 
	 * @return
	 */
	public LoginPO navigateToAdminPage() {
		navigateToLink(ADMIN_PAGE_URL);
		return new LoginPO();
	}

	/**
	 * This method would Navigate to Home Page
	 * 
	 * @return
	 */
	public HomePagePO navigateToHomePage() {
		navigateToLink(HOME_PAGE_URL);
		return new HomePagePO();
	}

	/**
	 * This method will Navigate to Content Edit page
	 * 
	 * @param index
	 * @return
	 */
	public ContentPO navigateToContentEditPage(String index) {
		navigateToLink(ADMIN_PAGE_URL + index);
		return new ContentPO();
	}

	/**
	 * This method will Navigate to Link
	 * 
	 * @param link
	 */
	public void navigateToLink(String link) {
		getDriver().navigate().to(link);
		new WaitUtils().waitForPageToLoad();
	}
}
