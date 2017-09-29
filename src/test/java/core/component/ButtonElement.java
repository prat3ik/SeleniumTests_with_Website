package core.component;

import org.openqa.selenium.WebElement;
/**
 *  This component should be used for elements having 'button' tag or
 *   elements having class '.btn' [or .btn related other classes]
 *
 * @author pratikpat
 *
 */
public class ButtonElement extends WaitEnabledBaseElement {

	public ButtonElement(String css) {
		super(css);
	}
	
	public ButtonElement(WebElement baseElement) {
		super(baseElement);
	}
	
}
