package core.component;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * This would represent Drop down menu element on UI. Element having
 * 'dropdown-menu' class in DOM could be represented by this Class
 *
 * @author pratikpat
 *
 */

public class DropDownMenu extends UnorderedListElement {
	
	protected String xpathForLI = "./li[not(contains(@class,'divider'))]";

	private class OptionNotFoundInMenuException extends RuntimeException {
		public OptionNotFoundInMenuException(final String message) {
			super(message);
		}
	}

	public DropDownMenu(final String css) {
		super(css);
		setLiXpath(xpathForLI);
	}

	public DropDownMenu(final WebElement webElement) {
		super(webElement);
		setLiXpath(xpathForLI);
	}

	/**
	 * This will click on specified option in drop down menu
	 *
	 * @param optionName
	 *            - visible text of option in menu
	 */
	public void clickOnOption(final String optionName) {
		this.getOption(optionName).click();
	}

	/**
	 *
	 * Objective This will return all available options of drop down menu
	 *
	 * @return - List of options
	 */
	public List<DropDownOption> getAllOptions() {
		final List<WebElement> list = this.findElements(By.xpath(this.getLiXpath()));
		final List<DropDownOption> options = new ArrayList<DropDownOption>();
		for (final WebElement el : list) {
			options.add(new DropDownOption(el));
		}
		return options;
	}

	/**
	 * This will return texts of all available options in menu
	 *
	 * @return List of option texts
	 */
	public List<String> getAllOptionsText() {
		final List<DropDownOption> options = this.getAllOptions();
		final List<String> optionsText = new ArrayList<String>();
		for (final DropDownOption option : options) {
			optionsText.add(option.getText());
		}
		return optionsText;
	}

	/**
	 * This will search an option in menu by its index and return the matching
	 * element.
	 *
	 * @param optionIndex
	 *            -index of the option in menu
	 * @return return object of specified optionName
	 * @author pratikpat
	 */
	public DropDownOption getOption(final int optionIndex) {
		final List<DropDownOption> list = this.getAllOptions();
		if (optionIndex <= list.size()) {
			return list.get(optionIndex);
		}

		throw new OptionNotFoundInMenuException(optionIndex
				+ "index is not present in drop down");
	}

	/**
	 * This will search an option in menu by its visible text and return the
	 * matching element.
	 *
	 * @param optionName
	 *            - visible text of option in menu
	 * @return return object of specified optionName
	 */
	public DropDownOption getOption(final String optionName) {
		final List<DropDownOption> list = this.getAllOptions();
		for (final DropDownOption el : list) {
			if (el.getText().trim().equals(optionName)) {

				return el;
			}
		}
		throw new OptionNotFoundInMenuException(
				"'" + optionName + "' is not present in drop down, Available options are: " + getAllOptionsText());
	}

	/**
	 *
	 * @param optionName
	 * @return the dropDownOption by matching text partially
	 * @author pratikpat
	 */
	public DropDownOption getOptionByPartialText(final String optionName) {
		final List<DropDownOption> list = this.getAllOptions();
		for (final DropDownOption el : list) {
			if (el.getText().trim().toLowerCase()
					.contains(optionName.toLowerCase())) {
				return el;
			}
		}
		throw new OptionNotFoundInMenuException("'" + optionName
				+ "' is not present in drop down");
	}

}
