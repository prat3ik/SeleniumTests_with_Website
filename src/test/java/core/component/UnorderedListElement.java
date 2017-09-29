package core.component;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * This class represents Unordered List component on UI. This is generic component fot UL LI
 * (Unordered/Bulleted List) List on UI
 * 
 * @category Component
 * @author pratikpat
 * 
 */

public class UnorderedListElement extends BaseElement {

	protected List<BaseElement> lists;
	protected List<String> listsText;
	protected By textLocatorWithInLI = By.xpath(".");
	protected String liXpath = "./li";

	public void setTextLocatorWithInLI(By textLocator) {
		this.textLocatorWithInLI = textLocator;
	}

	protected void initList() {
		if (lists == null) {
			lists = new ArrayList<BaseElement>();
			List<WebElement> elList = this.findElements(By.xpath(this.getLiXpath()));
			for (WebElement el : elList) {
				lists.add(new BaseElement(el));
			}
		}
	}

	protected void initListText() {
		initList();
		if (listsText == null) {
			listsText = new ArrayList<String>();
			for (BaseElement el : lists) {
				listsText.add(el.findElement(textLocatorWithInLI).getText());
			}
		}
	}

	public void reinitialize() {
		lists = null;
		listsText = null;
		initListText();
	}

	public UnorderedListElement(String css) {
		super(css);
	}

	public UnorderedListElement(WebElement el) {
		super(el);
	}

	public BaseElement getLiElement(int index) {
		initList();
		return lists.get(index);
	}

	public BaseElement getChildLiElement(int index, By childElementLocator) {
		BaseElement el = getLiElement(index);
		return new BaseElement(el.findElement(childElementLocator));
	}

	public BaseElement getLiElement(String liText) {
		int index = getLiIndex(liText);
		return lists.get(index);
	}

	public BaseElement getChildLiElement(String liText, By childElementLocator) {
		return new BaseElement(getLiElement(liText).findElement(childElementLocator));
	}

	private int getLiIndex(String liText) {
		initListText();
		
		for (int i = 0; i < listsText.size(); i++) {
			String liValue = listsText.get(i);
			if (liText.equals(liValue)) {
				return i;
			}
		}
		throw new ListItemNotPresentInUnorderedList(liText +
			" is not present in UnorderedList, Available List is below:\n" + this.listsText);
	}

	public boolean isLiElementPresent(String liText) {
		try {
			this.getLiIndex(liText);
			return true;
		} catch (ListItemNotPresentInUnorderedList e) {
			return false;
		}
	}

	protected String getLiXpath() {
		return liXpath;
	}

	public void setLiXpath(String liXpath) {
		this.liXpath = liXpath;
	}

	public String getLiText(int index) {
		initListText();
		return listsText.get(index);
	}

	public List<String> getLiText() {
		initListText();
		return listsText;
	}
	
	public int getLiCount() {
		initListText();
		return lists.size();
	}

	/**
	 * @return the All ListItem Elements
	 */
	public List<BaseElement> getAllLiElements() {
		return lists;
	}

	private class ListItemNotPresentInUnorderedList extends RuntimeException {

		public ListItemNotPresentInUnorderedList(final String message) {
			super(message);
		}
	}
}
