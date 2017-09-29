package smoke.po;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import core.component.BaseElement;
import core.pageobject.BasePO;

public class HomePagePO extends BasePO {

	final String HEADER_CSS = "article.post h2";
	final String CONTENT_CSS = "article.post section";
	final String READ_MORE_CSS = ".read-more";

	@FindBy(css = ".post-title")
	BaseElement postTitle;

	@FindBy(css = ".post-content")
	BaseElement postContent;

	@FindBy(css = ".error-code")
	BaseElement errorCodeText;

	@FindBy(css = ".error-description")
	BaseElement errorDescriptionText;

	/**
	 * This method will return Error Code
	 * 
	 * @return
	 */
	public String getErrorCode() {
		return errorCodeText.getText();
	}

	/**
	 * This method will return Error Description
	 * 
	 * @return
	 */
	public String getErrorDescription() {
		return errorDescriptionText.getText();
	}

	/**
	 * This will return the index of Post
	 * 
	 * @param postTitle
	 * @return
	 */
	public int getIndexOfPostTitle(String postTitle) {
		List<WebElement> headerElements = this.getDriver().findElements(By.cssSelector(HEADER_CSS));
		for (int i = 0; i < headerElements.size(); i++) {
			if (postTitle.equals(headerElements.get(i).getText().trim())) {
				return i;
			}
		}
		return -1;

	}

	/**
	 * This method is used to click on Read More
	 * 
	 * @param postTitle
	 */
	public void clickOnReadMore(String postTitle) {
		List<WebElement> readMoreElements = this.getDriver().findElements(By.cssSelector(READ_MORE_CSS));
		readMoreElements.get(getIndexOfPostTitle(postTitle)).click();
	}

	/**
	 * This method will return the List of Title(Header) Text
	 * 
	 * @return
	 */
	public List<String> getHeaderTexts() {
		List<WebElement> headerElements = this.getDriver().findElements(By.cssSelector(HEADER_CSS));
		List<String> headerTexts = new ArrayList<String>();
		for (WebElement el : headerElements) {
			headerTexts.add(el.getText());
		}
		return headerTexts;
	}

	/**
	 * This method will return the List of Content Text
	 * 
	 * @return
	 */
	public List<String> getContentTexts() {
		List<WebElement> contentElements = this.getDriver().findElements(By.cssSelector(CONTENT_CSS));
		List<String> contentTexts = new ArrayList<String>();
		for (WebElement el : contentElements) {
			String content = el.getText();
			String contentText;
			try {
				contentText = content.split("»")[0];
			} catch (ArrayIndexOutOfBoundsException e) {
				contentText = "";
			}
			contentTexts.add(contentText.trim());
		}
		return contentTexts;
	}

	/**
	 * This method will confirm whether Text Present in List or not
	 * 
	 * @param text
	 * @param list
	 * @return
	 */
	private boolean isTextPresentInList(String text, List<String> list) {
		boolean flag = false;
		for (String str : list) {
			if (text.equals(str)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * This would Assert that Created Post is present or not on Home Page
	 * 
	 * @param postTitleText
	 * @param postContentText
	 */
	public void assertPostPresentOnHomePage(String postTitleText, String postContentText) {
		boolean isHeaderPresentOnHomePage = isTextPresentInList(postTitleText, getHeaderTexts());
		Assert.assertTrue(isHeaderPresentOnHomePage, "Post Title did not match, Expected was:" + postTitleText
				+ " ,Actual Post Titles are:" + getHeaderTexts());
		boolean isContentPresentOnHomePage = isTextPresentInList(postContentText, getContentTexts());
		Assert.assertTrue(isContentPresentOnHomePage, "Post Content did not match, Expected was:" + postContentText
				+ " ,Actual Post Contents are:" + getContentTexts());
	}

	/**
	 * This method will Assert that Post is present on that Post page or not
	 * 
	 * @param postTitleText
	 * @param postContentText
	 */
	public void assertPostPresentOnPostPage(String postTitleText, String postContentText) {
		Assert.assertEquals(postTitle.getText(), postTitleText,
				"Post Title didn't match, Actual Post Title is:" + postContent.getText());
		Assert.assertEquals(postContent.getText(), postContentText,
				"Post Content didn't match, Actual Post Content is:" + postContent.getText());
	}

	/**
	 * This would verify if Image is present or not
	 * 
	 * @return
	 */
	public boolean isImagePresent() {
		final String POST_CONTENT_IMAGE = ".post-content img";
		By by = By.cssSelector(POST_CONTENT_IMAGE);
		waitUtils.waitForElementToBePresent(by);
		return getDriver().findElement(by).isDisplayed();
	}

	/**
	 * This method will verify that Deleted Post content is not present
	 * 
	 */
	public void assertDeletedPostIsNotPresent(String postTitle) {
		Assert.assertEquals(getErrorCode(), "404", "Error Code didn't match");
		Assert.assertEquals(getErrorDescription(), "Page not found", "Error Description didn't match");
	}
}
