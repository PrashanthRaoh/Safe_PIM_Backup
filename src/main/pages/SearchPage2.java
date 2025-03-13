package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage2  {
	private WebDriver driver;
	private WebDriverWait wait;

	// Locators
	private By searchInputField = By.cssSelector("#app");
	private By rowsLocator = By.cssSelector("#app");
	private By filterButtonLocator = By.cssSelector("#filterButton");

	public SearchPage2(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(90));
	}

	public WebElement getSearchField() {
		return driver.findElement(searchInputField).getShadowRoot()
				.findElement(By.cssSelector("#contentViewManager")).getShadowRoot()
				.findElement(By.cssSelector("[id^='currentApp_search-thing_']")).getShadowRoot()
				.findElement(By.cssSelector("[id^='app-entity-discovery-component-']")).getShadowRoot()
				.findElement(By.cssSelector("#entitySearchDiscoveryGrid")).getShadowRoot()
				.findElement(By.cssSelector("#searchBar")).getShadowRoot().findElement(By.cssSelector("#input"));
	}

	public WebElement getFilterButton() {
		return driver.findElement(searchInputField).getShadowRoot()
				.findElement(By.cssSelector("#contentViewManager")).getShadowRoot()
				.findElement(By.cssSelector("[id^='currentApp_search-thing_']")).getShadowRoot()
				.findElement(By.cssSelector("[id^='app-entity-discovery-component-']")).getShadowRoot()
				.findElement(By.cssSelector("#entitySearchDiscoveryGrid")).getShadowRoot()
				.findElement(By.cssSelector("#entitySearchFilter")).getShadowRoot()
				.findElement(By.cssSelector("#search-filter")).getShadowRoot()
				.findElement(filterButtonLocator).getShadowRoot().findElement(By.cssSelector("#buttonTextBox"));
	}

	public WebElement Search_button_After_Filter() {
		return driver.findElement(searchInputField).getShadowRoot().findElement(By.cssSelector("#contentViewManager"))
				.getShadowRoot().findElement(By.cssSelector("[id^='currentApp_search-thing_']")).getShadowRoot()
				.findElement(By.cssSelector("[id^='app-entity-discovery-component-']")).getShadowRoot()
				.findElement(By.cssSelector("#entitySearchDiscoveryGrid")).getShadowRoot()
				.findElement(By.cssSelector("#entitySearchFilter")).getShadowRoot()
				.findElement(By.cssSelector("#search-filter")).getShadowRoot().findElement(By
						.cssSelector("#refineFilterPopover > div > div.p-relative.p-b-10.base-grid-structure-child-1"));
	}

	public WebElement Filter_Results() {
		return driver.findElement(searchInputField).getShadowRoot().findElement(By.cssSelector("#contentViewManager"))
				.getShadowRoot().findElement(By.cssSelector("[id^='currentApp_search-thing_']")).getShadowRoot()
				.findElement(By.cssSelector("[id^='app-entity-discovery-component-']")).getShadowRoot()
				.findElement(By.cssSelector("#entitySearchDiscoveryGrid")).getShadowRoot()
				.findElement(By.cssSelector("#entitySearchFilter")).getShadowRoot()
				.findElement(By.cssSelector("#search-filter")).getShadowRoot()
				.findElement(By.cssSelector("#attributeModelLov_thing")).getShadowRoot()
				.findElement(By.cssSelector("#modelLov_thing")).getShadowRoot()
				.findElement(By.cssSelector(
						"div.base-grid-structure.p-relative.hideLovHeader > div.base-grid-structure-child-2.overflow-auto.p-relative > pebble-grid"))
				.getShadowRoot().findElement(By.cssSelector("#gird")).getShadowRoot().findElement(By.cssSelector(
						"#lit-grid > div > div.ag-root-wrapper-body.ag-layout-normal.ag-focus-managed > div.ag-root.ag-unselectable.ag-layout-normal > div.ag-body-viewport.ag-layout-normal.ag-row-no-animation > div.ag-center-cols-clipper > div > div"));
	}

	public WebElement getGridRoot() {
		return driver.findElement(rowsLocator).getShadowRoot().findElement(By.cssSelector("#contentViewManager"))
				.getShadowRoot().findElement(By.cssSelector("[id^='currentApp_search-thing_']")).getShadowRoot()
				.findElement(By.cssSelector("[id^='app-entity-discovery-component-']")).getShadowRoot()
				.findElement(By.cssSelector("#entitySearchDiscoveryGrid")).getShadowRoot()
				.findElement(By.cssSelector("#entitySearchGrid")).getShadowRoot()
				.findElement(By.cssSelector("#entityGrid")).getShadowRoot()
				.findElement(By.cssSelector("#pebbleGridContainer > pebble-grid")).getShadowRoot()
				.findElement(By.cssSelector("#grid"));
	}

	public void enterMaterialId(String materialId) {
		WebElement searchField = getSearchField();
		wait.until(ExpectedConditions.elementToBeClickable(searchField));
		
		searchField.sendKeys(materialId);
		searchField.sendKeys(Keys.ENTER);
	}

//    public int getRowCount() {
//        List<WebElement> rows = getGridRoot().findElements(By.cssSelector("div.ag-root-wrapper-body div.ag-center-cols-clipper > div > div > div"));
//        return rows.size();
//    }

	public WebElement getgrid() {
		WebElement gridRows =
//    driver.findElement(By.cssSelector("#app")).getShadowRoot()
//			.findElement(By.cssSelector("#contentViewManager")).getShadowRoot()
//			.findElement(By.cssSelector("[id^='currentApp_search-thing_']")).getShadowRoot()
//			.findElement(By.cssSelector("[id^='app-entity-discovery-component-']")).getShadowRoot()
//			.findElement(By.cssSelector("#entitySearchDiscoveryGrid")).getShadowRoot()
//			.findElement(By.cssSelector("#entitySearchGrid")).getShadowRoot()
//			.findElement(By.cssSelector("#entityGrid")).getShadowRoot()
//			.findElement(By.cssSelector("#pebbleGridContainer > pebble-grid")).getShadowRoot()
//			.findElement(By.cssSelector("#grid")).getShadowRoot()

				getGridRoot().getShadowRoot().findElement(By.cssSelector(
						"#lit-grid> div > div.ag-root-wrapper-body.ag-layout-normal.ag-focus-managed >div.ag-root.ag-unselectable.ag-layout-normal> div.ag-body-viewport.ag-layout-normal.ag-row-no-animation> div.ag-center-cols-clipper > div.ag-center-cols-viewport > div.ag-center-cols-container"));
		return gridRows;
	}

//    public WebElement Filterbutton_On_Search_Page() {
//        WebElement filterButton = getFilterButton();
//        return filterButton;
////        filterButton.click();
//    }
}
