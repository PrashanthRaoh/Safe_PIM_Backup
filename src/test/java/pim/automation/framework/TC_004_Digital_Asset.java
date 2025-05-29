package pim.automation.framework;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import common_functions.BaseTest;
import common_functions.Utils;
import pages.DigitalAsset;
import pages.HomePage;
import pages.SearchPage2;
import pages.SummaryPage;

public class TC_004_Digital_Asset extends BaseTest {
	public ExtentTest test;

	@Test()
	public void DigitalAsset() throws IOException, InterruptedException {
		String className = this.getClass().getSimpleName();
		System.out.println(className);
		test = BaseTest.extentreport.createTest(className);
		test.assignAuthor(System.getProperty("user.name")).assignCategory("Regression")
				.assignDevice(System.getenv("COMPUTERNAME"));

		homePage = new HomePage(driver);
		SearchPage2 searchPage = new SearchPage2(driver);
		SummaryPage summaryPage = new SummaryPage(driver);
		DigitalAsset digitalssetPage = new DigitalAsset(driver);

		utils.waitForElement(() -> homePage.Moredetails_MarketingEnrich(), "clickable");
		test.pass("Home Page is displayed");
		test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());

		/**************************************************
		 * ***** Verify that logged in user is Digital Asset owner
		 **************************************************/
		WebElement currentloggedinuser = homePage.loggedin_User();
		System.out.println("Logged in user is  " + currentloggedinuser.getText());
		test.pass("Current user logged in is " + currentloggedinuser.getText());
		test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
		assertTrue("Logged-in user should be Digital Asset Ownerr",
				currentloggedinuser.getText().contains("attributeownerdigitalassets.test1"));
		Thread.sleep(2000);
		/**************************************************
		 ***** Click on Enrich Digital Assets more details ****
		 **************************************************/
		WebElement detailsEnrichment = homePage.Moredetails_MarketingEnrich().getShadowRoot()
				.findElement(By.cssSelector("#viewDetails > span"));
		detailsEnrichment.click();
		Thread.sleep(2000);
		test.pass("More details clicked on Enrich Digital Asset tab");
		test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());

		List<WebElement> detailItems = driver.findElement(By.cssSelector("#app")).getShadowRoot()
				.findElement(By.cssSelector("#contentViewManager")).getShadowRoot()
				.findElement(By.cssSelector("[id^='currentApp_home_']")).getShadowRoot()
				.findElement(By.cssSelector("[id^='app-dashboard-component-']")).getShadowRoot()
				.findElement(By.cssSelector("rock-layout > rock-dashboard-widgets")).getShadowRoot()
				.findElement(By.cssSelector("[id^='rs']")).getShadowRoot().findElement(By.cssSelector("#rock-my-todos"))
				.getShadowRoot().findElement(By.cssSelector("[id^='rock-my-todos-component-rs']")).getShadowRoot()
				.findElement(By.cssSelector("#rock-my-todos-tabs")).getShadowRoot()
				.findElement(By.cssSelector("[id^='my-todo-summary-list-component-rs']")).getShadowRoot()
				.findElement(By.cssSelector("pebble-list-view > pebble-list-item > my-todo-summary")).getShadowRoot()
				.findElement(By.cssSelector("#moreDetails"))
				.findElements(By.cssSelector("my-todo-detail-view-list-item"));

		utils.waitForElement(() -> detailItems.get(0), "clickable");

		System.out.println("There are " + detailItems.size() + " elements ");

		List<String> expectedItems = Arrays.asList("Ready for transition",
				"DAM: Review 2D Line Drawing", "DAM: Review Representative Image (Primary)","DAM: Review Secondary Image","DAM: Review Unclassified Images");

		Assert.assertEquals(detailItems.size(), expectedItems.size(), "Item count mismatch");
		JavascriptExecutor js = (JavascriptExecutor) driver;

		for (int i = 0; i < detailItems.size(); i++) {
			WebElement summary = detailItems.get(i);
			WebElement innerDiv = summary.getShadowRoot().findElement(By.cssSelector("#button-text-box"));
			String actualText = innerDiv.getAttribute("title").trim().replaceFirst("^\\d+\\s", "");
			System.out.println("Item " + (i + 1) + ":--" + actualText);
			Assert.assertEquals(actualText, expectedItems.get(i), "Mismatch at item " + (i + 1));

			if (actualText.contains("DAM: Review 2D Line Drawing")) {
				js.executeScript("arguments[0].scrollIntoView({block: 'center'});", innerDiv);
				try {
					innerDiv.click(); 
				} catch (Exception e) {
					js.executeScript("arguments[0].click();", innerDiv);
				}
				Thread.sleep(5000);
				break;
			}
		}
		test.pass("Clicked on DAM: Review 2D Line Drawing ");
		test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
		
		/*************************************** ***** 
		 * Click on On Hold - BSA PIE(Rule Triggered) ****
		 ***************************************/
		utils.waitForElement(() -> searchPage.getgrid(), "clickable");
		test.pass("Search page grid displayed after clicking on On Hold - BSA PIE");
		test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());


		/**************************************************
		 * --------- Select filters for rows not having images------- *
		 ********************************************************/
		digitalssetPage.AdvanceSearch_Dropdown().click();
		Thread.sleep(2000);
		utils.waitForElement(() -> digitalssetPage.generalDropdown_First(), "clickable");
		test.pass("Advance Search option clicked");
		test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
		digitalssetPage.generalDropdown_second().click();
		Thread.sleep(2000);
		digitalssetPage.HavingNoImagesFilterDropdownValue().click();
		Thread.sleep(2000);
		
		/**************************************************
		 * --------- Select relationship dropdown------- *
		 ********************************************************/
		digitalssetPage.RelationshipMaindropdown_Obj().click();
		Thread.sleep(1000);
		digitalssetPage.HasImagesDropdownvalue().click();
		Thread.sleep(1000);
		test.pass("Has No images filter value set");
		test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
		digitalssetPage.Apply_btn_onFilter().click();
		
		/**************************************************
		 * --------- wait for rows to appear after Filtering data with No Images------- *
		 ********************************************************/
		utils.waitForElement(() -> searchPage.getgrid(), "clickable");
		test.pass("Search page No images records");
		test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
		
		/**************************************************
		 ***** Click on any one of the entity****
		 **************************************************/
		Actions actions = new Actions(driver);
		WebElement rowsredefined = driver.findElement(By.cssSelector("#app")).getShadowRoot()
				.findElement(By.cssSelector("#contentViewManager")).getShadowRoot()
				.findElement(By.cssSelector("[id^='currentApp_search-thing_']")).getShadowRoot()
				.findElement(By.cssSelector("[id^='app-entity-discovery-component-']")).getShadowRoot()
				.findElement(By.cssSelector("#entitySearchDiscoveryGrid")).getShadowRoot()
				.findElement(By.cssSelector("#entitySearchGrid")).getShadowRoot()
				.findElement(By.cssSelector("#entityGrid")).getShadowRoot()
				.findElement(By.cssSelector("#pebbleGridContainer > pebble-grid")).getShadowRoot()
				.findElement(By.cssSelector("#grid"));

		List<WebElement> arrrowsdefined = rowsredefined.getShadowRoot().findElements(By.cssSelector(
				"#lit-grid > div > div.ag-root-wrapper-body.ag-layout-normal.ag-focus-managed > div.ag-root.ag-unselectable.ag-layout-normal > div.ag-body-viewport.ag-layout-normal.ag-row-no-animation > div.ag-center-cols-clipper > div > div> div.ag-row.ag-row-even.ag-row-level-0"));

		System.out.println("Total rows after entering setting the filter criteria -- " + arrrowsdefined.size());
		test.pass("Rows after setting the filter criteria appeared");
		test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
		assertTrue("There should be results after applying filters", arrrowsdefined.size() > 0);

		WebElement RowByRow = arrrowsdefined.get(0);
		String SellableMaterialDescription = RowByRow
				.findElement(By.cssSelector("div[col-id='sellablematerialdescription']")).getText();
		String matid = RowByRow.findElement(By.cssSelector("div[col-id='sellablematerialid']")).getText();
		System.out.println("Material ID -- " + matid + " Material Description --" + SellableMaterialDescription);

		/************************************************** --------- 
		 * Click on the materialid from the result------- *
		 ************************************************/
		WebElement matidElement = RowByRow.findElement(By.cssSelector("div[col-id='sellablematerialid']"));
		actions.moveToElement(RowByRow).build().perform();
		Thread.sleep(2000);
		matidElement.click();
		Thread.sleep(5000);
		utils.waitForElement(() -> summaryPage.Things_INeedToFix(), "visible");
		test.pass("Material ID -- " + matid + " Material Description --" + SellableMaterialDescription
				+ " is selected for completion");
		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
	}

}
