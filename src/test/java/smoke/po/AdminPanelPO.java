package smoke.po;

import org.openqa.selenium.support.FindBy;

import core.component.BaseElement;
import core.component.ButtonElement;
import core.pageobject.BasePO;

public class AdminPanelPO extends BasePO {

	@FindBy(css = ".gh-nav-menu-icon")
	private BaseElement userProfileIcon;

	@FindBy(css = ".gh-nav-main .gh-nav-main-editor")
	private ButtonElement newPost;

	@FindBy(css = ".gh-nav-main-content")
	private ButtonElement content;

	/**
	 * Get User Profile Icon element
	 * 
	 * @return
	 */
	public BaseElement getUserProfileIcon() {
		return userProfileIcon;
	}

	/**
	 * Click on New Post and Return New Post PO
	 * 
	 * @return
	 */
	public PostPO clickOnNewPost() {
		newPost.click();
		return new PostPO();
	}

	/**
	 * Click on Content and return Content PO
	 * 
	 * @return
	 */
	public ContentPO clickOnContent() {
		content.click();
		return new ContentPO();
	}
}
