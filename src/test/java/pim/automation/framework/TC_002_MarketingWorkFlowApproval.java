package pim.automation.framework;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import common_functions.BaseTest;
import common_functions.Utils;
import pages.HomePage;
import pages.SearchPage2;

public class TC_002_MarketingWorkFlowApproval extends BaseTest{
	
//	public ExtentTest test;
	
	@Test
	public void MarketingWorkFlowApproval() throws InterruptedException, IOException {
//		test = BaseTest.extentreport.createTest(this.getClass().getSimpleName());
		homePage = new HomePage(driver);
		SearchPage2 searchPage = new SearchPage2(driver);
		
		utils.waitForElement(homePage.HomePage_SearchButton(), "clickable");
		extentTest.pass("Home Page is displayed");
		extentTest.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
		Thread.sleep(3000);
		/**************************************************
		 ***** Verify that logged in user is Marketing owner****************
		 **************************************************/
		WebElement currentloggedinuser = homePage.loggedin_User();
		System.out.println("Logged in user is  " + currentloggedinuser.getText());
		extentTest.pass("Current user logged in is " + currentloggedinuser.getText());
		extentTest.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
		assertTrue("Logged-in user should be Marketing owner",currentloggedinuser.getText().contains("attributeownermarketing.test1"));
		Thread.sleep(5000);
		/**************************************************
		 ***** Click on Marketing Enrichment in workflow ****************
		 **************************************************/
		WebElement detailsEnrichment = homePage.Moredetails_MarketingEnrich().getShadowRoot()
	    .findElement(By.cssSelector("#viewDetails > span"));
		detailsEnrichment.click();
		Thread.sleep(5000);
//		/**************************************************
//		 ***** Click on Ready for transisition link****************
//		 **************************************************/
//		homePage.ReadyForTransistion_Market_Enrich().click();
//		System.out.println("Clicked on Ready for Transistion on Marketing enrichment more details");
//		Thread.sleep(5000);
//		utils.waitForElement(searchPage.getgrid(), "clickable");
//		System.out.println("Search page grid displayed");
//		test.pass("Search page grid displayed");
//		test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
//		/**************************************************
//		 ***** Click on any one of the entity ****************
//		 **************************************************/
//		Actions actions = new Actions(driver);
//		WebElement rowsredefined = driver.findElement(By.cssSelector("#app")).getShadowRoot()
//				.findElement(By.cssSelector("#contentViewManager")).getShadowRoot()
//				.findElement(By.cssSelector("[id^='currentApp_search-thing_']")).getShadowRoot()
//				.findElement(By.cssSelector("[id^='app-entity-discovery-component-']")).getShadowRoot()
//				.findElement(By.cssSelector("#entitySearchDiscoveryGrid")).getShadowRoot()
//				.findElement(By.cssSelector("#entitySearchGrid")).getShadowRoot()
//				.findElement(By.cssSelector("#entityGrid")).getShadowRoot()
//				.findElement(By.cssSelector("#pebbleGridContainer > pebble-grid")).getShadowRoot()
//				.findElement(By.cssSelector("#grid"));
//
//		List<WebElement> arrrowsdefined = rowsredefined.getShadowRoot().findElements(By.cssSelector(
//				"#lit-grid > div > div.ag-root-wrapper-body.ag-layout-normal.ag-focus-managed > div.ag-root.ag-unselectable.ag-layout-normal > div.ag-body-viewport.ag-layout-normal.ag-row-no-animation > div.ag-center-cols-clipper > div > div> div.ag-row.ag-row-even.ag-row-level-0"));
//
//		System.out.println("Total rows after entering the wide range filter criteria -- " + arrrowsdefined.size());
//		test.pass("Rows after entering 0s appeared");
//		test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
//		assertTrue("There should be results after applying filters", arrrowsdefined.size() > 0);
//
//		WebElement RowByRow = arrrowsdefined.get(0);
//		String SellableMaterialDescription = RowByRow.findElement(By.cssSelector("div[col-id='sellablematerialdescription']")).getText();
//		String matid = RowByRow.findElement(By.cssSelector("div[col-id='sellablematerialid']")).getText();
//		System.out.println("Material ID -- " + matid + " Material Description --" + SellableMaterialDescription);
//
////		/*
////		 * // /************************************************ 
////		 * --------- Click on the materialid from the result------- *
////		 ************************************************/
//		WebElement matidElement = RowByRow.findElement(By.cssSelector("div[col-id='sellablematerialid']"));
//		actions.moveToElement(RowByRow).build().perform();
//		Thread.sleep(2000);
//		matidElement.click();
//		Thread.sleep(3000);
//		test.pass("Material ID -- " + matid + " Material Description --" + SellableMaterialDescription
//				+ " is selected for completion");
//		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
//		
//		
	}


}
