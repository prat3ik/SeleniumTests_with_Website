package core.component;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

/**
 * This class represents options present in a DropDownMenu
 * 
 * @author pratikpat
 * 
 */
public class DropDownOption extends BaseElement {

	private class SubMenuNotFoundForOptionException extends RuntimeException {

		public SubMenuNotFoundForOptionException(final String message) {
			super(message);
		}
	}

	@FindBy(css = "a")
	private WaitEnabledBaseElement optionElement;

	@FindBy(css = ".dropdown-menu.submenu")
	private DropDownMenu subMenu;

	public DropDownOption(final WebElement el) {
		super(el);
		PageFactory.initElements(new DefaultElementLocatorFactory(this.el), this);
	}

	@Override
	public void click() {
		if (this.optionElement.isElementPresent()) {
			this.optionElement.click();
		} else {
			super.click();
		}
	}

	public DropDownMenu getSubMenu() {
		if (this.isSubMenuPresent()) {
			return this.subMenu;
		} else {
			final String message = "Sub Menu is not present for '" + this.getText() + "' option";
			throw new SubMenuNotFoundForOptionException(message);
		}
	}

	@Override
	public String getText() {
		if (this.optionElement.isElementPresent()) {
			return this.optionElement.getText();
		} else {
			return super.getText();
		}
	}

	public boolean isSubMenuPresent() {
		return this.subMenu.isElementPresent();
	}
}
