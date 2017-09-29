package smoke.testcases;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import smoke.po.AdminPanelPO;
import smoke.po.ContentPO;
import smoke.po.HomePagePO;
import smoke.po.LoginPO;
import smoke.po.PostPO;
import smoke.po.PostPO.PostOptions;

/**
 * This is the main Test Case file
 * It contains the Test Cases of Admin Panel
 * @author Pratik
 *
 */

public class SmokeTest extends SmokeBaseTestCase {

	final String EMAIL = "admin@test.com";
	final String PASSWORD = "1q2w3e4r";
	final String IMAGE_MARKDOWN = "![](http://)";
	final String IMAGE_LINK = "https://drive.google.com/uc?export=view&id=0BwJ0TxDHsfqGMUdKc2pXNE05WU0";
	final String EDITOR_PATH = "editor/";

	/**
	 * POST CREATION
	 * 
	 * This Test will Verify whether Post(with Image) can be published
	 * successfully
	 */
	@Test
	public void verifyNewCreatedPostWithImageCanBePublished() {
		final String POST_TITLE = "PRATIK_" + System.currentTimeMillis();
		final String POST_CONTENT = "Hello_Pratik_" + System.currentTimeMillis();

		AdminPanelPO adminPanel = login();
		PostPO postPO = adminPanel.clickOnNewPost();
		postPO.setPostTitle(POST_TITLE);
		postPO.setPostContent(POST_CONTENT + Keys.ENTER + IMAGE_MARKDOWN);
		postPO.uploadAndSaveImage(IMAGE_LINK);
		Assert.assertTrue(postPO.getUploadedImageView().isDisplayed(), "Image is not being uploaded on New Post page");
		postPO.selectOption(PostOptions.PUBLISH_NOW);

		HomePagePO homePagePO = this.navigateToHomePage();
		homePagePO.assertPostPresentOnHomePage(POST_TITLE, POST_CONTENT);
		homePagePO.clickOnReadMore(POST_TITLE);
		homePagePO.assertPostPresentOnPostPage(POST_TITLE, POST_CONTENT);
		Assert.assertTrue(homePagePO.isImagePresent(), "Image is not present/loaded");
	}

	/**
	 * POST UPDATION
	 * 
	 * This test would verify that Created Post can be Edited
	 */
	@Test
	public void verifyNewCreatedPostCanBeEdited() {
		final String POST_TITLE = "PRATIK_" + System.currentTimeMillis();
		final String NEW_POST_TITLE = "PRATIK_NEW_" + Math.random();
		final String POST_CONTENT = "Hello_Pratik_" + System.currentTimeMillis();
		final String NEW_POST_CONTENT = "Hello_Pratik_This_is_New_Content_" + System.currentTimeMillis();

		AdminPanelPO adminPanel = login();
		PostPO postPO = adminPanel.clickOnNewPost();
		postPO.setPostTitle(POST_TITLE);
		postPO.setPostContent(POST_CONTENT);
		postPO.selectOption(PostOptions.PUBLISH_NOW);
		String postIndex = getDriver().getCurrentUrl().split(EDITOR_PATH)[1];

		HomePagePO homePagePO = this.navigateToHomePage();
		homePagePO.assertPostPresentOnHomePage(POST_TITLE, POST_CONTENT);

		this.navigateToAdminPage();
		ContentPO contentPO = this.navigateToContentEditPage(postIndex);
		PostPO postPO2 = contentPO.editContent(POST_TITLE, NEW_POST_TITLE, NEW_POST_CONTENT);

		postPO2.selectOption(PostOptions.UPDATE_POST);

		HomePagePO homePagePO2 = this.navigateToHomePage();
		homePagePO2.assertPostPresentOnHomePage(NEW_POST_TITLE, NEW_POST_CONTENT);
		this.navigateToLink(HOME_PAGE_URL + "/" + POST_TITLE);
		homePagePO2.assertPostPresentOnPostPage(NEW_POST_TITLE, NEW_POST_CONTENT);
	}

	/**
	 * POST DELETION
	 * 
	 * This test will verify that Created Post can be Deleted
	 */
	@Test
	public void verifyNewCreatedPostCanBeDeleted() {
		final String POST_TITLE = "PRATIK_" + System.currentTimeMillis();
		final String POST_CONTENT = "Hello_Pratik_" + System.currentTimeMillis();

		AdminPanelPO adminPanel = login();
		PostPO postPO = adminPanel.clickOnNewPost();
		postPO.setPostTitle(POST_TITLE);
		postPO.setPostContent(POST_CONTENT);
		postPO.selectOption(PostOptions.PUBLISH_NOW);
		String postIndex = getDriver().getCurrentUrl().split(EDITOR_PATH)[1];


		HomePagePO homePagePO = this.navigateToHomePage();
		homePagePO.assertPostPresentOnHomePage(POST_TITLE, POST_CONTENT);
		this.navigateToLink(HOME_PAGE_URL + "/" + POST_TITLE);
		homePagePO.assertPostPresentOnPostPage(POST_TITLE, POST_CONTENT);

		this.navigateToAdminPage();
		ContentPO contentPO = this.navigateToContentEditPage(postIndex);
		PostPO postPO1 = contentPO.clickOnEditPage();
		postPO1.selectOption(PostOptions.DELETE_POST);

		HomePagePO homePagePO1 = this.navigateToHomePage();
		this.navigateToLink(HOME_PAGE_URL + "/" + POST_TITLE);
		homePagePO1.assertDeletedPostIsNotPresent(POST_TITLE);
	}

	/**
	 * This method will Login to Admin page
	 * 
	 * @return
	 */
	public AdminPanelPO login() {
		LoginPO loginPO = this.navigateToAdminPage();
		loginPO.fillEmailId(EMAIL);
		loginPO.fillPassword(PASSWORD);
		AdminPanelPO adminPanelPO = loginPO.clickOnSignInButton();
		Assert.assertTrue(adminPanelPO.getUserProfileIcon().isDisplayed(), "Sign In is not successful");
		return adminPanelPO;
	}
}
