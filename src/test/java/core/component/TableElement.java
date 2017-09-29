package core.component;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 * This Component would be use fot Table html elements It has methods for select
 * particular row/column in table
 * 
 * @author prat3ik
 *
 */
public class TableElement extends BaseElement {

	private String thCss = "thead th";
	private String tbodyCss = "tbody";

	public TableElement(WebElement el) {
		super(el);
	}

	public TableElement(String css) {
		super(css);
	}

	public BaseElement getHeaderElementByText(String headerName) {
		int index = getColumnIndex(headerName);
		WebElement el = this.findElement(By.cssSelector(this.getThCss(index)));
		return new BaseElement(el);
	}

	public BaseElement getHeaderElementByClass(String className) {
		WebElement el = this.findElement(By.className(className));
		return new BaseElement(el);
	}

	public BaseElement getHeaderElement(int index) {
		WebElement el = this.findElement(By.cssSelector(this.getThCss(index)));
		return new BaseElement(el);
	}

	public BaseElement getHeaderElement(int index, String css) {
		WebElement el = this.findElement(By.cssSelector(this.getThCss(index, css)));
		return new BaseElement(el);
	}

	public BaseElement getCellElement(int rowNum, int colNum) {
		return getCellElement(rowNum, colNum, "");
	}

	public BaseElement getCellElement(int rowNum, int colNum, String css) {
		WebElement el = this.findElement(By.cssSelector(this.getTdCss(rowNum, colNum, css)));
		return new BaseElement(el);
	}

	public BaseElement getCellElementByColumnCss(int rowNum, String colCss) {
		return getCellElementByColumnCss(rowNum, colCss, "");
	}

	public BaseElement getCellElementByColumnCss(int rowNum, String colCss, String css) {
		WebElement el = this.findElement(By.cssSelector(this.getTdCss(rowNum, colCss, css)));
		return new BaseElement(el);
	}

	public BaseElement getCellElement(String cellValue, String cellValueColumnHeader, String columnHeader) {

		int colNum = getColumnIndex(columnHeader);
		int rowNum = getRowIndex(cellValueColumnHeader, cellValue);
		return getCellElement(rowNum, colNum);
	}

	public BaseElement getCellElementByColumnHeader(int rowNum, String columnHeaderText) {
		int colNum = getColumnIndex(columnHeaderText);
		return getCellElement(rowNum, colNum);
	}

	public BaseElement getCellElementByColumnHeader(int rowNum, String columnHeaderText, String css) {
		int colNum = getColumnIndex(columnHeaderText);
		return getCellElement(rowNum, colNum, css);
	}

	public List<String> getColumnValuesByColumnHeader(String columnHeaderText) {
		return getColumnValuesByColumnHeader(columnHeaderText, "");
	}

	public List<String> getColumnValuesByColumnHeader(String columnHeaderText, String childCss) {
		int rowCount = this.getTotalRowCount();
		List<String> allCellElementsValue = new ArrayList<String>();

		for (int i = 1; i <= rowCount; i++) {
			allCellElementsValue.add(getCellElementByColumnHeader(i, columnHeaderText, childCss).getText().trim());
		}
		return allCellElementsValue;
	}

	public List<String> getColumnValuesByColumnCss(String columnCss) {
		int rowCount = this.getTotalRowCount();
		List<String> allCellElementsValue = new ArrayList<String>();

		for (int i = 1; i <= rowCount; i++) {
			allCellElementsValue.add(getCellElementByColumnCss(i, columnCss).getText());
		}
		return allCellElementsValue;
	}

	public List<String> getColumnValuesByColumnHeaderIndex(int columnHeaderIndex) {
		String headerElementValue = this.getHeaderElement(columnHeaderIndex).getText();
		return getColumnValuesByColumnHeader(headerElementValue);
	}

	private int getElementIndexFromList(List<WebElement> list, String elementText) {

		for (int i = 0; i < list.size(); i++) {
			scrollIntoView(list.get(i));
			if (list.get(i).getText().trim().equalsIgnoreCase(elementText)) {
				return i + 1;
			}
		}
		return -1;
	}

	public BaseElement getRow(final int rowNum) {
		return new BaseElement(this.getTrCss(rowNum));
	}

	public int getRowIndex(int columnIndex, String cellValue) {
		List<WebElement> headers = this.findElements(By.cssSelector(getColumnCss(columnIndex)));

		int index = this.getElementIndexFromList(headers, cellValue);
		if (index == -1) {
			throw new ColumnHeaderNotPresentInTableException(
					"Column '" + columnIndex + "' doesnot contain value '" + cellValue + "'");
		}
		return index;
	}

	public int getIndexByColumnClass(final String columnClass, final String columnValue) {
		final List<WebElement> list = this.findElements(By.cssSelector(getTdCssForColumn(columnClass)));

		int index = this.getElementIndexFromList(list, columnValue);
		if (index == -1) {
			throw new ColumnValueNotPresentInTableException("Value '" + columnValue + "' is not present in List");
		}
		return index;
	}

	public int getRowIndexByColumnCss(String columnCss, String cellValue) {
		return getRowIndexByColumnCss(columnCss, cellValue, "");
	}

	public int getRowIndexByColumnCss(String columnCss, String cellValue, String childCss) {
		List<WebElement> cells = this.findElements(By.cssSelector(getTdCss(columnCss) + childCss));
		for (int i = 0; i < cells.size(); i++) {
			if (cells.get(i).getText().equalsIgnoreCase(cellValue)) {
				return i + 1;
			}
		}
		throw new ColumnValueNotPresentInTableException(
				"Column '" + columnCss + childCss + "' does not contain value '" + cellValue + "'");
	}

	public int getColumnIndex(String columnHeaderText) {
		List<WebElement> headers = this.findElements(By.cssSelector(getThCss()));

		int index = this.getElementIndexFromList(headers, columnHeaderText);
		if (index == -1) {
			throw new ColumnHeaderNotPresentInTableException(
					"Column header '" + columnHeaderText + "' is not present in table");
		}
		return index;
	}

	public int getRowIndex(String columnHeaderText, String columnValue) {
		int columnIndex = getColumnIndex(columnHeaderText);

		List<WebElement> columnValues = this.findElements(By.cssSelector(getTdCssForColumn(columnIndex)));
		int index = this.getElementIndexFromList(columnValues, columnValue);
		if (index == -1) {
			throw new ColumnValueNotPresentInTableException(
					"Value '" + columnValue + "' is not present in column '" + columnHeaderText + "'.");
		}
		return index;
	}

	public int getRowIndex(String columnHeaderText, String columnValue, String css) {
		int columnIndex = getColumnIndex(columnHeaderText);
		List<WebElement> columnValues = this.findElements(By.cssSelector(getTdCssForColumn(columnIndex, css)));

		int index = this.getElementIndexFromList(columnValues, columnValue);
		if (index == -1) {
			throw new ColumnValueNotPresentInTableException(
					"Value '" + columnValue + "' is not present in column '" + columnHeaderText + "'.");
		}
		return index;
	}

	public int getTotalColumnsCount() {
		final List<WebElement> list = this.findElements(By.cssSelector(this.getThCss()));
		return list.size();
	}

	public int getTotalRowCount() {
		final List<WebElement> list = this.findElements(By.cssSelector(this.getTrCss()));
		return list.size();
	}

	public boolean isCellPresent(String columnHeaderText, String cellElementName, String css) {
		try {
			getRowIndex(columnHeaderText, cellElementName, css);
			return true;
		} catch (ColumnValueNotPresentInTableException e) {
			return false;
		}

	}

	public boolean isCellPresent(String columnHeaderText, String cellElementName) {
		return isCellPresent(columnHeaderText, cellElementName, "");
	}

	public boolean isCellPresentByColumnCss(String columnCss, String cellElementName) {
		return isCellPresentByColumnCss(columnCss, cellElementName, "");
	}

	public boolean isCellPresentByColumnCss(String columnCss, String cellElementName, String childCss) {
		try {
			getRowIndexByColumnCss(columnCss, cellElementName, childCss);
			return true;
		} catch (ColumnValueNotPresentInTableException e) {
			return false;
		}
	}

	protected String getThCss() {
		return thCss;
	}

	protected String getThCss(String css) {
		return this.getThCss() + css;
	}

	protected String getThCss(int colNum) {
		return getThCss() + ":nth-child(" + colNum + ")";
	}

	protected String getThCss(int colNum, String css) {
		return getThCss(colNum) + css;
	}

	protected String getTBodyCss() {
		return tbodyCss;
	}

	protected String getTrCss() {
		return getTBodyCss() + ">tr";
	}

	protected String getTrCss(int rowNum) {
		return getTrCss() + ":nth-child(" + rowNum + ")";
	}

	protected String getTdCss() {
		return getTrCss() + ">td";
	}

	protected String getTdCss(String colCss) {
		return getTdCss() + colCss;
	}

	protected String getTdCss(int rowNum) {
		return getTrCss(rowNum) + ">td";
	}

	protected String getColumnCss(int colNum) {
		return getTdCss() + ":nth-child(" + colNum + ")";
	}

	protected String getTdCss(int rowNum, int colNum) {
		return getTdCss(rowNum) + ":nth-child(" + colNum + ")";
	}

	protected String getTdCss(int rowNum, String colClass) {
		return getTdCss(rowNum) + colClass;
	}

	protected String getTdCss(int rowNum, int colNum, String css) {
		return getTdCss(rowNum) + ":nth-child(" + colNum + ")" + css;
	}

	protected String getTdCss(int rowNum, String colCss, String css) {
		return getTdCss(rowNum, colCss) + css;
	}

	protected String getTdCssForColumn(int colNum) {
		return getTdCss() + ":nth-child(" + colNum + ")";
	}

	protected String getTdCssForColumn(int colNum, String css) {
		return getTdCssForColumn(colNum) + css;
	}

	protected String getTdCssForColumn(String colClass) {
		return getTdCss() + "." + colClass;
	}

	protected String getTdCssForColumn(String colClass, String css) {
		return getTdCssForColumn(colClass) + css;
	}

	public void setTbodyCss(String tbodyCss) {
		this.tbodyCss = tbodyCss;
	}

	public void setThCss(String thCss) {
		this.thCss = thCss;
	}

	private class ColumnHeaderNotPresentInTableException extends RuntimeException {

		public ColumnHeaderNotPresentInTableException(final String message) {
			super(message);
		}
	}

	private class ColumnValueNotPresentInTableException extends RuntimeException {

		public ColumnValueNotPresentInTableException(final String message) {
			super(message);
		}
	}

	public List<String> getColumnHeaders() {
		return getTextsFromListWebElements(this.findElements(By.cssSelector(getThCss())));
	}

	private List<String> getTextsFromListWebElements(List<WebElement> listOfElements) {
		List<String> texts = new ArrayList<String>();
		for (WebElement el : listOfElements) {
			BaseElement element = new BaseElement(el);
			element.scrollIntoView();
			texts.add(element.getText());
		}
		return texts;
	}

	public void scrollIntoView(WebElement el) {
		((JavascriptExecutor) this.getDriver()).executeScript("arguments[0].scrollIntoView(false);", el);
	}
}
