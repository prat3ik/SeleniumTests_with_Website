package smoke.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.component.BaseElement;
import core.component.ButtonElement;
import core.component.ConfirmationModal;
import core.component.UnorderedListElement;
import core.pageobject.BasePO;

public class PostPO extends BasePO {
	final String UPLOADED_IMAGE_VIEW = ".gh-image-uploader img";

	@FindBy(id = "entry-title")
	private BaseElement yourPostTitleField;

	@FindBy(css = ".ember-text-area")
	private BaseElement postTextAreaField;

	@FindBy(css = ".ember-view button.js-publish-button")
	private WebElement actionButton;

	@FindBy(css = ".options.icon-arrow2")
	private BaseElement arrowIconButton;

	@FindBy(css = ".dropdown-triangle-bottom-right")
	private UnorderedListElement dropDownOptions;

	@FindBy(css = "section.modal-content")
	private ConfirmationModal deleteModal;

	@FindBy(css = ".js-drop-zone .upload-form+.image-url>.icon-link")
	private BaseElement uploadLink;

	@FindBy(css = ".btn-blue.gh-input")
	private ButtonElement saveImageButton;

	@FindBy(css = "[placeholder='http://']")
	private BaseElement linkTextField;

	/**
	 * This method will return Uploaded Image View on Right side of Panel
	 * 
	 * @return
	 */
	public BaseElement getUploadedImageView() {
		By by = By.cssSelector(UPLOADED_IMAGE_VIEW);
		waitUtils.waitForElementToBePresent(by);
		return new BaseElement(getDriver().findElement(by));
	}

	/**
	 * This method will click on Upload Link
	 */
	public void clickOnUploadLink() {
		uploadLink.click();
		if (!linkTextField.isDisplayed())
			uploadLink.click();
	}

	/**
	 * This will Upload and Save the Image
	 * 
	 * @param httpLink
	 */
	public void uploadAndSaveImage(String httpLink) {
		clickOnUploadLink();
		linkTextField.sendKeys(httpLink);
		saveImageButton.click();
		waitUtils.waitTillDataIsFetched();
		waitUtils.waitForElementToBePresent(By.cssSelector(UPLOADED_IMAGE_VIEW));
	}

	/**
	 * This will set Post Title
	 * 
	 * @param postTitle
	 */
	public void setPostTitle(String postTitle) {
		yourPostTitleField.sendKeys(postTitle);
	}

	/**
	 * This will set Post Content Text
	 * 
	 * @param postContent
	 */
	public void setPostContent(String postContent) {
		postTextAreaField.sendKeys(postContent);
		waitUtils.waitTillDataIsFetched();
		waitUtils.waitForPageToLoad();
	}

	public enum PostOptions {
		PUBLISH_NOW("Publish Now"), SAVE_DRAFT("Save Draft"), DELETE_POST("Delete Post"), UPDATE_POST("Update Post");

		String optionText;

		PostOptions(String optionText) {
			this.optionText = optionText;
		}

		public String getOptionText() {
			return this.optionText;
		}
	}

	/**
	 * This would select the Post Action(like Publish Post, Update Post, Delete
	 * Post etc..)
	 * 
	 * @param postOptions
	 */
	public void selectOption(PostOptions postOptions) {
		arrowIconButton.click();
		dropDownOptions.getLiElement(postOptions.getOptionText()).click();
		switch (postOptions) {
		case PUBLISH_NOW:
		case SAVE_DRAFT:
		case UPDATE_POST:
			waitUtils.waitForElementToBeClickable(actionButton);
			waitUtils.waitForElementToBeVisible(actionButton);
			new ButtonElement(actionButton).click();
			waitUtils.staticWait(2000);
			break;
		case DELETE_POST:
			deleteModal.getConfirmButton().click();
			break;
		}
	}

}
