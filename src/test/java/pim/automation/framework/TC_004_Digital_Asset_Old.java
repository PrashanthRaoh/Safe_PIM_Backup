//package pim.automation.framework;
//
//import static org.junit.Assert.assertTrue;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.MediaEntityBuilder;
//import com.aventstack.extentreports.Status;
//import common_functions.BaseTest;
//import common_functions.Utils;
//import pages.DigitalAsset;
//import pages.HomePage;
//import pages.SearchPage2;
//import pages.SummaryPage;
//
//public class TC_004_Digital_Asset_Old extends BaseTest {
//	public ExtentTest test;
//
//	@Test()
//	public void DigitalAsset() throws IOException, InterruptedException {
//		String className = this.getClass().getSimpleName();
//		System.out.println(className);
//		test = BaseTest.extentreport.createTest(className);
//		test.assignAuthor(System.getProperty("user.name")).assignCategory("Regression").assignDevice(System.getenv("COMPUTERNAME"));
//
//		homePage = new HomePage(driver);
//		SearchPage2 searchPage = new SearchPage2(driver);
//		SummaryPage summaryPage = new SummaryPage(driver);
//		DigitalAsset digitalssetPage = new DigitalAsset(driver);
//
//		utils.waitForElement(() -> homePage.Moredetails_MarketingEnrich(), "clickable");
//		test.pass("Home Page is displayed");
//		test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
//
//		/**************************************************
//		 * ***** Verify that logged in user is Digital Asset owner
//		 **************************************************/
//		WebElement currentloggedinuser = homePage.loggedin_User();
//		System.out.println("Logged in user is  " + currentloggedinuser.getText());
//		test.pass("Current user logged in is " + currentloggedinuser.getText());
//		test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
//		assertTrue("Logged-in user should be Digital Asset Ownerr",
//				currentloggedinuser.getText().contains("attributeownerdigitalassets.test1"));
//		Thread.sleep(2000);
//		/**************************************************
//		 ***** Click on Enrich Digital Assets more details ****
//		 **************************************************/
//		WebElement detailsEnrichment = homePage.Moredetails_MarketingEnrich().getShadowRoot()
//				.findElement(By.cssSelector("#viewDetails > span"));
//		detailsEnrichment.click();
//		Thread.sleep(2000);
//		test.pass("More details clicked on Enrich Digital Asset tab");
//		test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
//
//		/**********************************
//		Modification of Dam review
//		************************************/
//
//		List<WebElement> detailItems = driver.findElement(By.cssSelector("#app")).getShadowRoot()
//				.findElement(By.cssSelector("#contentViewManager")).getShadowRoot()
//				.findElement(By.cssSelector("[id^='currentApp_home_']")).getShadowRoot()
//				.findElement(By.cssSelector("[id^='app-dashboard-component-']")).getShadowRoot()
//				.findElement(By.cssSelector("rock-layout > rock-dashboard-widgets")).getShadowRoot()
//				.findElement(By.cssSelector("[id^='rs']")).getShadowRoot().findElement(By.cssSelector("#rock-my-todos"))
//				.getShadowRoot().findElement(By.cssSelector("[id^='rock-my-todos-component-rs']")).getShadowRoot()
//				.findElement(By.cssSelector("#rock-my-todos-tabs")).getShadowRoot()
//				.findElement(By.cssSelector("[id^='my-todo-summary-list-component-rs']")).getShadowRoot()
//				.findElement(By.cssSelector("pebble-list-view > pebble-list-item > my-todo-summary")).getShadowRoot()
//				.findElement(By.cssSelector("#moreDetails"))
//				.findElements(By.cssSelector("my-todo-detail-view-list-item"));
//
//		utils.waitForElement(() -> detailItems.get(0), "clickable");
//		System.out.println("There are " + detailItems.size() + " elements ");
//		
//		
//		List<String> expectedItems = Arrays.asList("Ready for transition", "DAM: Review 2D Line Drawing","DAM: Review Representative Image (Primary)", "DAM: Review Secondary Image",
//				"DAM: Review Unclassified Images");
//		Assert.assertEquals(detailItems.size(), expectedItems.size(), "Item count mismatch");
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		/**********************************************
//		 * From Summary tab select DAM: Review Representative Image (Primary) business condition
//		 **********************************************/
//		WebElement summarybcs = driver.findElement(By.cssSelector("#app")).getShadowRoot()
//				.findElement(By.cssSelector("#contentViewManager")).getShadowRoot()
//				.findElement(By.cssSelector("[id^='currentApp_entity-manage_rs']")).getShadowRoot()
//				.findElement(By.cssSelector("[id^='app-entity-manage-component-rs']")).getShadowRoot()
//				.findElement(By.cssSelector("#rockDetailTabs")).getShadowRoot()
//				.findElement(By.cssSelector("#rockTabs")).getShadowRoot()
//				.findElement(By.cssSelector("[id^='rock-entity-summary-component-rs']")).getShadowRoot()
//				.findElement(By.cssSelector("[id^='rs']")).getShadowRoot()
//				.findElement(By.cssSelector("#rock-entity-tofix")).getShadowRoot()
//				.findElement(By.cssSelector("[id^='rock-entity-tofix-component-rs']")).getShadowRoot()
//				.findElement(By.cssSelector(".tofix-data-container > pebble-accordion"))
//				.findElement(By.cssSelector("[slot='accordion-content']"));
//
//		List<WebElement> conditions_refresh = summarybcs.findElements(By.cssSelector(".data-list"));
//		System.out.println("There are " + conditions_refresh.size() + " elements after closing the summary tab----");
//
//		int matchedRowIndex_Primary = -1;
//		JavascriptExecutor js1 = (JavascriptExecutor) driver;
//
//		for (int i = 0; i < conditions_refresh.size(); i++) {
//			String busscondname = conditions_refresh.get(i).findElement(By.cssSelector("[class^='entity-content']")).getAttribute("title");
//			System.out.println("Condition " + (i + 1) + " -- " + busscondname );
//
//			if (busscondname.equals("DAM: Review Representative Image (Primary)")) {
//				matchedRowIndex_Primary = i;
//				System.out.println("Clicked on: " + busscondname + " at position " + (i + 1));
//			}
//		}
//
//		if (matchedRowIndex_Primary != -1) {
//			System.out.println("\"DAM: Review Representative Image (Primary)\" is at position " + matchedRowIndex_Primary);
//			conditions_refresh.get(matchedRowIndex_Primary).click();
//		} else {
//			System.out.println("\"DAM: Review Representative Image (Primary)\" was not found.");
//		}
//
//		test.pass("Clicked on DAM: Review Representative Image (Primary) which is found at row -- " + matchedRowIndex_Primary);
//		test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
//
//		/*************************************************
//		 * --------- Wait for the image required drop down after clicking on Review Representative Image (Primary) ------ *
//		 ************************************************/
//		utils.waitForElement(() -> digitalssetPage.primary_Image_Required_dropdown_obj(), "clickable");
//		test.pass("Clicked on DAM: Review Representative Image (Primary) and arrived on Manage attributes page");
//		test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
//
////		/*************************************************
////		 * --------- Select Yes from the dropdown ------ *
////		 ************************************************/
////		digitalssetPage.primary_Image_Required_dropdown_obj().click();
////		Thread.sleep(2000);
////		digitalssetPage.ImageRequired_Yes().click();
////		Thread.sleep(2000);
////		/*************************************************
////		 * --------- Approve Image required ------ *
////		 ************************************************/
////		digitalssetPage.ApprovePrimaryImagedropdown_obj().click();
////		Thread.sleep(2000);
////		digitalssetPage.Approve_Primary_Image_dropdownvalue().click();
////		Thread.sleep(2000);
////		/*************************************************
////		 * --------- Save ------ *
////		 ************************************************/
////		digitalssetPage.AddPrimaryImage_Save_btn().click();
////		Thread.sleep(2000);
//		/*************************************************
//		 * --------- Click Next ------ *
//		 ************************************************/
//		digitalssetPage.Next_btn().click();
//		Thread.sleep(3000);
//		/*******************
//		 * Click Next
//		 *******************/
//		utils.waitForElement(digitalssetPage::MoreActions_Dropdown_1, "clickable");
//		Thread.sleep(2000);
//		test.pass("More actions page displayed to attach a image");
//		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
//
//		/*************************************************
//		 * --------- After approving and adding the image check the % now ------ *
//		 ************************************************/
//		utils.waitForElement(searchPage::ProgressRing, "visible");
//		String percentagecompletion_AddPrimaryimage = searchPage.ProgressRing().getText();
////		System.out.println("Percentage completion of " + matid + " is " + percentagecompletion_AddPrimaryimage + " % ");
//		test.pass("Percentage completion after approving the Add Primary image is " + percentagecompletion_AddPrimaryimage);
//		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
//
//		/*******************************
//		 * Check for how many images attached before adding a image
//		 *******************************/
//		String totalimages_selected_txt = digitalssetPage.txt_Images_Selected().getText();
//		String img_selcted = totalimages_selected_txt.split(" / ")[1];
//		int totalsearchimagescount = Integer.parseInt(img_selcted);
//		System.out.println("There are " + totalsearchimagescount + " before adding the image " );
//		Assert.assertEquals(totalsearchimagescount, 0);
//		/*******************************
//		 * Click Add images drop down value
//		 *******************************/
//		digitalssetPage.MoreActions_Dropdown_2().click();
//		Thread.sleep(2000);
//		digitalssetPage.AddImage_dropdownValue().click();
//		Thread.sleep(2000);
//		utils.waitForElement(() -> digitalssetPage.Search_Images_input(), "clickable");
//		test.pass("Arrived at adding image page");
//		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
//		digitalssetPage.Search_Images_input().sendKeys("primary",Keys.ENTER);
//		Thread.sleep(2000);
//
//		/*******************************
//		 * Select the first image and save
//		 *******************************/
//		digitalssetPage.First_Image_checkbox().click();
//		Thread.sleep(2000);
//		utils.waitForElement(() -> digitalssetPage.First_Image_checkbox(), "clickable");
//		test.pass("Images with the filters appeared and is selected");
//		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
//		digitalssetPage.Save_btn_Add_Image().click();
//		utils.waitForElement(() -> digitalssetPage.MoreActions_Dropdown_1(), "clickable");
//		Thread.sleep(2000);
//		/*******************************
//		 * Check for how many images attached after adding a image
//		 *******************************/
//		String totalimages_selected_txt_after = digitalssetPage.txt_Images_Selected().getText();
//		String img_selcted_after = totalimages_selected_txt_after.split(" / ")[1];
//		int totalsearchimagescount_after = Integer.parseInt(img_selcted_after);
//		System.out.println("There are " + totalsearchimagescount_after + " after adding the image " );
//		Assert.assertTrue(totalsearchimagescount_after>0);
//
//		/*************************************************
//		 * --------- Again click on Back ------ *
//		 ************************************************/
//		digitalssetPage.Back_btn().click();
//		Thread.sleep(2000);
//		System.out.println("Back button clicked");
//		utils.waitForElement(() -> digitalssetPage.primary_Image_Required_dropdown_obj(), "clickable");
//		test.pass("Back button clicked to approve the transaction");
//		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
//
//		digitalssetPage.primary_Image_Required_dropdown_obj().click();
//		Thread.sleep(2000);
//		digitalssetPage.ImageRequired_Yes().click();
//		Thread.sleep(1000);
//		/*******************
//		 Approve Representative Image (Primary)?
//		 ********************/
//		digitalssetPage.ApprovePrimaryImagedropdown_obj().click();
//		Thread.sleep(2000);
//		digitalssetPage.Approve_Primary_Image_dropdownvalue().click();
//		Thread.sleep(2000);
//		/*************************************************
//		 * --------- Save ------ *
//		 ************************************************/
//		digitalssetPage.AddPrimaryImage_Save_btn().click();
//		Thread.sleep(5000);
//
///*************************************************
// * --------- After approving and adding the image check the % now ------ *
// ************************************************/
//		utils.waitForElement(() -> searchPage.ProgressRing(), "visible");
//		String percentagecompletion_Allprocess = searchPage.ProgressRing().getText();
////		System.out.println("Percentage completion after approving the stages for " + matid + " is " + percentagecompletion_Allprocess + " % ");
//		test.pass("Percentage completion after approving the stages is " + percentagecompletion_Allprocess);
//		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////
////		List<WebElement> detailItems = driver.findElement(By.cssSelector("#app")).getShadowRoot()
////				.findElement(By.cssSelector("#contentViewManager")).getShadowRoot()
////				.findElement(By.cssSelector("[id^='currentApp_home_']")).getShadowRoot()
////				.findElement(By.cssSelector("[id^='app-dashboard-component-']")).getShadowRoot()
////				.findElement(By.cssSelector("rock-layout > rock-dashboard-widgets")).getShadowRoot()
////				.findElement(By.cssSelector("[id^='rs']")).getShadowRoot().findElement(By.cssSelector("#rock-my-todos"))
////				.getShadowRoot().findElement(By.cssSelector("[id^='rock-my-todos-component-rs']")).getShadowRoot()
////				.findElement(By.cssSelector("#rock-my-todos-tabs")).getShadowRoot()
////				.findElement(By.cssSelector("[id^='my-todo-summary-list-component-rs']")).getShadowRoot()
////				.findElement(By.cssSelector("pebble-list-view > pebble-list-item > my-todo-summary")).getShadowRoot()
////				.findElement(By.cssSelector("#moreDetails"))
////				.findElements(By.cssSelector("my-todo-detail-view-list-item"));
////
////		utils.waitForElement(() -> detailItems.get(0), "clickable");
////		System.out.println("There are " + detailItems.size() + " elements ");
////
////		List<String> expectedItems = Arrays.asList("Ready for transition", "DAM: Review 2D Line Drawing","DAM: Review Representative Image (Primary)", "DAM: Review Secondary Image",
////				"DAM: Review Unclassified Images");
////		Assert.assertEquals(detailItems.size(), expectedItems.size(), "Item count mismatch");
////		JavascriptExecutor js = (JavascriptExecutor) driver;
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////		/*************************************************
////		 * --------- Check the % completion after approving again------ *
////		 ************************************************/
////		utils.waitForElement(() -> searchPage.ProgressRing(), "visible");
////		String percentagecompletion_Approvingagain = searchPage.ProgressRing().getText();
////		System.out.println("Percentage completion after approving the image primary again clicking on back button for " + matid + " is " + percentagecompletion_Approvingagain + " % ");
////		test.pass("Percentage completion after approving the image primary again clicking on back button is -- " + percentagecompletion_Approvingagain);
////		test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
//
//		/*************************************************
//		 * --------- Click on the search thing bread crum ------- *
//		 ************************************************/
////		searchPage.Search_things_BreadCrum().click();
////		Thread.sleep(2000);
////		utils.waitForElement(() ->searchPage.getgrid(), "clickable");
////		test.pass("Navigated back to search thing ");
////		test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
////
////		/*************************************************
////		 * --------- Verify the record again in the search thing. It should not be listed ------- *
////		 ************************************************/
////		searchPage.searchthingdomain_Input_Mat_Id().click();
////		searchPage.searchthingdomain_Input_Mat_Id().clear();
////		searchPage.searchthingdomain_Input_Mat_Id().sendKeys(matid);
////		searchPage.searchthingdomain_Input_Mat_Id().sendKeys(Keys.ENTER);
////		Thread.sleep(5000);
////
////		try {
////			String txt = searchPage.rowsdisplayedtext().getText();
////			String result = txt.split(" / ")[1];
////			int zerorows = Integer.parseInt(result);
////			System.out.println(zerorows);
////			Assert.assertEquals(zerorows, 0);
////			test.pass(matid + " completion is 100%. Hence not visible");
////			test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
////
////		} catch (Exception e) {
////			WebElement rowsredefined2 = driver.findElement(By.cssSelector("#app")).getShadowRoot()
////					.findElement(By.cssSelector("#contentViewManager")).getShadowRoot()
////					.findElement(By.cssSelector("[id^='currentApp_search-thing_']")).getShadowRoot()
////					.findElement(By.cssSelector("[id^='app-entity-discovery-component-']")).getShadowRoot()
////					.findElement(By.cssSelector("#entitySearchDiscoveryGrid")).getShadowRoot()
////					.findElement(By.cssSelector("#entitySearchGrid")).getShadowRoot()
////					.findElement(By.cssSelector("#entityGrid")).getShadowRoot()
////					.findElement(By.cssSelector("#pebbleGridContainer > pebble-grid")).getShadowRoot()
////					.findElement(By.cssSelector("#grid"));
////			List<WebElement> arrrowsdefined2 = rowsredefined2.getShadowRoot().findElements(By.cssSelector(
////					"#lit-grid > div > div.ag-root-wrapper-body.ag-layout-normal.ag-focus-managed > div.ag-root.ag-unselectable.ag-layout-normal > div.ag-body-viewport.ag-layout-normal.ag-row-no-animation > div.ag-center-cols-clipper > div > div> div.ag-row.ag-row-even.ag-row-level-0"));
////
////			if (arrrowsdefined2.size() > 0) {
////				System.out.println("Records found for the search criteria");
////				test.pass(matid + " completion is NOT 100%. Pleaes verify");
////				test.log(Status.FAIL,
////						MediaEntityBuilder.createScreenCaptureFromPath(Utils.Takescreenshot(driver)).build());
////			}
////		}
//
//
//	}
//}
