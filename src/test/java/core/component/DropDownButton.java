package core.component;

import org.openqa.selenium.WebElement;

/**
 * This would represent Button elements on UI. Element having 'btn' or
 * 'btn-action' class in DOM could be represented by this Class
 * 
 * @author pratikpat
 * 
 */
public class DropDownButton extends BaseElement {

	private DropDownMenu subMenu;

	public DropDownButton(final String css) {
		super(css);
	}

	public DropDownButton(final WebElement el) {
		super(el);
	}

	@Override
	public void click() {
		new WaitEnabledBaseElement(this.el).click();
		this.initSubMenu();
	}

	/**
	 * This will return Object of drop down menu appearing after clicking on
	 * Button
	 * 
	 * @return drop down menu object
	 */
	private DropDownMenu getDropDownMenu() {
		return this.getDropDownMenu("ul");
	}

	private DropDownMenu getDropDownMenu(final String tagXpath) {
		return new DropDownMenu(this.getNextSibling(tagXpath));
	}

	public DropDownMenu getSubMenu() {
		return this.subMenu;
	}

	private void initSubMenu() {
		this.subMenu = this.getDropDownMenu();
	}

	/**
	 * This will select an value from Drop down menu
	 */
	public void select(final String text) {
		this.click();
		this.getSubMenu().clickOnOption(text);
	}

}
