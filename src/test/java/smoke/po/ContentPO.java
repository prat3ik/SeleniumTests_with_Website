package smoke.po;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import core.component.ButtonElement;
import core.pageobject.BasePO;

/**
 * This class represents PO of
 * 
 * @author Pratik
 *
 */
public class ContentPO extends BasePO {

	@FindBy(css = ".icon-edit")
	WebElement editButton;

	/**
	 * Click on Edit button, so it would navigate to Post Editor
	 * 
	 * @return
	 */
	public PostPO clickOnEditPage() {
		waitUtils.waitForElementToBeClickable(editButton);
		new ButtonElement(editButton).click();
		return new PostPO();
	}

	/**
	 * This method will get the Content Header Element from the Content list
	 * 
	 * @param contentHeaderText
	 * @return
	 */
	public WebElement getContentHeaderElement(String contentHeaderText) {
		List<WebElement> elements = getDriver().findElements(By.cssSelector(".entry-title"));
		for (WebElement el : elements) {
			if (contentHeaderText.equals(el.getText())) {
				return el;
			}
		}
		return null;
	}

	/**
	 * This method will edit the existing content of Post
	 * 
	 * @param contentHeader
	 * @param newContent
	 */
	public PostPO editContent(String contentHeaderText, String newTitle, String newContent) {
		waitUtils.waitTillDataIsFetched();
		waitUtils.waitForPageToLoad();
		waitUtils.staticWait(2000);
		getContentHeaderElement(contentHeaderText).click();
		editButton.click();
		PostPO po = new PostPO();
		po.setPostTitle(newTitle);
		po.setPostContent(newContent);
		return po;
	}

}
