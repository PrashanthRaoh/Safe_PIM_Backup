package pim.automation.framework;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import common_functions.BaseTest;
import common_functions.Utils;
import pages.HomePage;
import pages.SearchPage2;
import pages.SummaryPage;

public class TC_003_BSAPIE_Test extends BaseTest {
	public ExtentTest test;

	@Test
	public void BSAPIEOwner() throws InterruptedException, IOException {
		String className = this.getClass().getSimpleName();
		System.out.println(className);
		test = BaseTest.extentreport.createTest(className);
		test.assignAuthor(System.getProperty("user.name")).assignCategory("Regression")
				.assignDevice(System.getenv("COMPUTERNAME"));

		homePage = new HomePage(driver);
		SearchPage2 searchPage = new SearchPage2(driver);
		SummaryPage summaryPage = new SummaryPage(driver);

		utils.waitForElement(() -> homePage.sellablematerialtabelement(), "clickable");

		test.pass("Home Page is displayed");
		test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
		utils.waitForElement(() -> homePage.BSAPIEUsecaseApprovalTab(), "visible");
//		/**************************************************
//		 * // ***** Verify that logged in user is Marketing owner**************** //
//		 **************************************************/
		Thread.sleep(5000);
		homePage.BSAPIEUsecaseApprovalTab().click();
		Thread.sleep(5000);
		test.pass("Clicked on Approval tab");
		test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
		Thread.sleep(2000);

		/*
		 * ************************************** ***** Get number of items under use
		 * case approvals ****
		 ***************************************/
		WebElement elementToClick = null;

		List<WebElement> summaryElements = driver.findElement(By.cssSelector("#app")).getShadowRoot()
				.findElement(By.cssSelector("#contentViewManager")).getShadowRoot()
				.findElement(By.cssSelector("[id^='currentApp_home_rs']")).getShadowRoot()
				.findElement(By.cssSelector("[id^='app-dashboard-component-rs']")).getShadowRoot()
				.findElement(By.cssSelector("rock-layout > rock-dashboard-widgets")).getShadowRoot()
				.findElement(By.cssSelector("[id^='rs']")).getShadowRoot().findElement(By.cssSelector("#rock-my-todos"))
				.getShadowRoot().findElement(By.cssSelector("[id^='rock-my-todos-component-rs']")).getShadowRoot()
				.findElement(By.cssSelector("#rock-my-todos-tabs")).getShadowRoot()
				.findElement(By.cssSelector("[id^='my-todo-summary-list-component-rs']")).getShadowRoot()
				.findElements(By.cssSelector("pebble-list-view > pebble-list-item > my-todo-summary"));

		System.out.println("Total items: " + summaryElements.size());

		List<String> expectedItems = Arrays.asList("Pending Usecase Approval - BSA PIE",
				"On Hold - BSA PIE (User Selected)", "On Hold - BSA PIE (Rule Triggered)");

		Assert.assertEquals(summaryElements.size(), expectedItems.size(), "Item count mismatch");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		for (int i = 0; i < summaryElements.size(); i++) {
			WebElement summary = summaryElements.get(i);
			WebElement innerDiv = summary.getShadowRoot().findElement(By.cssSelector("#workflowMetadataContainer"));
			String actualText = innerDiv.getText().trim();
			System.out.println("Item " + (i + 1) + ":--" + actualText);
			Assert.assertEquals(actualText, expectedItems.get(i), "Mismatch at item " + (i + 1));
			
			if (actualText.contains("On Hold - BSA PIE (Rule Triggered)")) {
				// Scroll into view
				js.executeScript("arguments[0].scrollIntoView({block: 'center'});", innerDiv);

				try {
					innerDiv.click();  // Try clicking the real inner part
				} catch (Exception e) {
					System.out.println("Standard click failed, trying JS click.");
					js.executeScript("arguments[0].click();", innerDiv);
				}

				Thread.sleep(5000);
				break;
			}
		}
		test.pass("BSA PIE Use case Approval entities listed ");
		test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());

		/*
		 * ************************************** 
		 * ***** Click on On Hold - BSA PIE(Rule Triggered) ****
		 ***************************************/
		
		
		utils.waitForElement(()->searchPage.getgrid(), "clickable");
		test.pass("Search page grid displayed after clicking on On Hold - BSA PIE");
		test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
	
		/**************************************************
		 *  --------- Get Row count------- *
		 ********************************************************/
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

		System.out.println("Total rows after clicking on On Hold - BSA PIE -- " + arrrowsdefined.size());
		test.pass("Rows after after clicking on On Hold - BSA PIE appeared");
		test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
		assertTrue("There should be results after applying filters", arrrowsdefined.size() > 0);

		WebElement RowByRow = arrrowsdefined.get(0);
		String SellableMaterialDescription = RowByRow
				.findElement(By.cssSelector("div[col-id='sellablematerialdescription']")).getText();
		String matid = RowByRow.findElement(By.cssSelector("div[col-id='sellablematerialid']")).getText();
		System.out.println("Material ID -- " + matid + " Material Description --" + SellableMaterialDescription);

		/************************************************* 
		 * --------- Click on the materialid from the result------- *
		 ************************************************/
		WebElement matidElement = RowByRow.findElement(By.cssSelector("div[col-id='sellablematerialid']"));
		actions.moveToElement(RowByRow).build().perform();
		Thread.sleep(2000);
		matidElement.click();
		Thread.sleep(3000);
		utils.waitForElement(() -> summaryPage.Things_INeedToFix(), "visible");
		test.pass("Material ID -- " + matid + " Material Description --" + SellableMaterialDescription
				+ " is selected for completion");
		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
		
	}

}
