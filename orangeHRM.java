package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import seleniumUtility.SeleniumUtil;

public class orangeHRM extends SeleniumUtil {
	
	@Test(priority = 1, enabled = true)
	public void createPIM() throws InterruptedException {

		// ----------------------create PIM-------------------------------------------------------------------------------
		clickOnElement(driver.findElement(By.xpath("//a[@href='/web/index.php/pim/viewPimModule']")));

		clickOnElement(driver
				.findElement(By.xpath("//div/div/div[@class='orangehrm-header-container']/button[@type='button']")));

		// fullname
		typeRequiredText(driver.findElement(By.xpath("//input[@name='firstName']")), "Priya");
		typeRequiredText(driver.findElement(By.xpath("//input[@name='middleName']")), "Bhagwan");
		typeRequiredText(driver.findElement(By.xpath("//input[@name='lastName']")), "Wankhede");

		// empid
		WebElement emp_id = driver.findElement(By.xpath("//label[text()='Employee Id']/following::input[1]"));
		emp_id.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
		emp_id.sendKeys("1994");
		String empid = emp_id.getAttribute("value");
		Thread.sleep(1000);

		// enable toggle
		clickOnElement(driver.findElement(By.xpath("//div/div[@class='oxd-switch-wrapper']/label/span")));

		// create uname password and submit
		typeRequiredText(driver.findElement(By.xpath("//label[text()='Username']/following::input[1]")), "priyaw2701");
		typeRequiredText(driver.findElement(By.xpath("//label[text()='Password']/following::input[1]")), "priyaw@1811");
		typeRequiredText(driver.findElement(By.xpath("//label[text()='Confirm Password']/following::input")),
				"priyaw@1811");

		clickOnElement(driver.findElement(By.xpath("//button[@type='submit']")));
		Thread.sleep(2000);

		// -------------------------validate PIM created----------------------------------------------------------------------------------
		clickOnElement(driver.findElement(By.xpath("//a[@href='/web/index.php/pim/viewPimModule']")));

		WebElement searchID = driver.findElement(By.xpath("//div[div/label[text()='Employee Id']]//div[2]/input"));
		searchID.sendKeys(empid);
		Thread.sleep(2000);

		clickOnElement(driver.findElement(By.xpath("//button[@type='submit']")));

		WebElement PIMcreate = driver
				.findElement(By.xpath("//div[@class='oxd-table-card']//div[contains(text(),'" + empid + "')]"));
		Assert.assertTrue(PIMcreate.isDisplayed(), "PIM creation failed");

		// getScreenshot("C:\\AutomationSession\\Workspace\\1stSelenium\\screenshots\\orangeHRM\\PIMcreated.jpg")
		// ;

	}

	@Test(priority = 2, enabled = true)
	public void updatePIM() throws InterruptedException {

		// ------------------------------------Update PIM Personal
		// details----------------------------------------------------------
		clickOnElement(driver.findElement(By.xpath("//a[@href='/web/index.php/pim/viewPimModule']")));

		// enter emp name and submit
		typeRequiredText(driver.findElement(By.xpath(
				"//div/label[text()='Employee Name']/parent::div/following-sibling::div//input[@placeholder='Type for hints...']")),
				"Priya");
		Thread.sleep(2000);

		clickOnElement(driver.findElement(By.xpath("//button[@type='submit']")));

		// click on edit button
		clickOnElement(driver.findElement(By.xpath("//div[@class='oxd-table-cell-actions']/button[1]/i")));

		// update other id
		typeRequiredText(
				driver.findElement(By.xpath(
						"//div[@class='oxd-input-group__label-wrapper']/label[text()='Other Id']/following::input[1]")),
				"45678");

		// update liscence number and expiry date
		typeRequiredText(
				driver.findElement(
						By.xpath("//label[contains(text(),\"Driver's License Number\")]/following::input[1]")),
				"893797");
		WebElement expiryDateField = driver.findElement(
				By.xpath("//label[text()='License Expiry Date']/parent::div/following-sibling::div//input"));
		expiryDateField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
		expiryDateField.sendKeys("2023-12-31");
		expiryDateField.sendKeys(Keys.TAB);
		Thread.sleep(1000);

		// update DOB
		WebElement DOB = driver
				.findElement(By.xpath("//div/label[text()='Date of Birth']/parent::div/following-sibling::div//input"));
		DOB.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
		DOB.sendKeys("2023-11-18");
		DOB.sendKeys(Keys.TAB);
		Thread.sleep(1000);

		// select gender
		clickOnElement(driver.findElement(By.xpath("//label[text()='Female']")));
		Thread.sleep(1000);

		// update nationality
		WebElement Nationality = driver.findElement(By.xpath(
				"//div/label[text()='Nationality']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']"));
		clickOnElement(Nationality);
		clickOnElement(driver.findElement(By.xpath("//div/span[text()='Indian']")));
		Thread.sleep(1000);

		// update marital status
		WebElement Marital_status1 = driver.findElement(By.xpath(
				"//div/label[text()='Marital Status']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']"));
		clickOnElement(Marital_status1);
		clickOnElement(driver.findElement(By.xpath("//div/span[text()='Married']")));
		Thread.sleep(1000);

		// sava data
		clickOnElement(driver.findElement(
				By.xpath("//button[@type='submit' and text()=' Save ']/preceding-sibling::p[text()=' * Required']")));
		Thread.sleep(1000);

		// update blood group
		clickOnElement(driver.findElement(By.xpath(
				"//label[text()='Blood Type']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']")));
		clickOnElement(driver.findElement(By.xpath("//div/span[text()='A+']")));
		Thread.sleep(1000);

		// save data
		clickOnElement(driver.findElement(
				By.xpath("//div[2][@class='oxd-form-actions']/button[@type='submit' and text()=' Save ']")));
		Thread.sleep(1000);

		// attach file
		clickOnElement(driver.findElement(By.xpath("//button[text()=' Add ']")));
		Thread.sleep(1000);
		clickOnElement(driver.findElement(By.xpath("//div[text()='Browse']")));
		WebElement input_file = driver.findElement(By.xpath("//input[@type='file']"));
		input_file.sendKeys("C:\\Users\\dell\\Desktop\\OrangeHRM.txt");
		Thread.sleep(1000);
		System.out.println("file uploaded");

		// add comment
		typeRequiredText(driver.findElement(By.xpath("//textarea")),
				"OrangeHRM.........plz keep updated...................");
		clickOnElement(driver.findElement(By.xpath("//button[text()=' Cancel ']/following::button[text()=' Save ']")));
		Thread.sleep(1000);

		// getScreenshot("C:\\AutomationSession\\Workspace\\1stSelenium\\screenshots\\orangeHRM\\PIMupdated.jpg")
		
	}
		
		@Test(priority = 3, enabled = false)
		public void UpdateJob() throws InterruptedException  
		{
			//----------------------Update job---------------------------------------------------------------------
			clickOnElement(driver.findElement(By.xpath("//a[@href='/web/index.php/pim/viewPimModule']")));

			// enter emp name and submit
			typeRequiredText(driver.findElement(By.xpath(
					"//div/label[text()='Employee Name']/parent::div/following-sibling::div//input[@placeholder='Type for hints...']")),
					"Priya");
			Thread.sleep(2000);

			clickOnElement(driver.findElement(By.xpath("//button[@type='submit']")));

			// click on edit button
			clickOnElement(driver.findElement(By.xpath("//div[@class='oxd-table-cell-actions']/button[1]/i")));
			
			// click on job
			clickOnElement(driver.findElement(By.xpath("//div/a[text()='Job']")));
			Thread.sleep(2000);
			
			//click on job title
			clickOnElement(driver.findElement(By.xpath("//div[div[label[text()='Job Title']]]/div[2]/div/div/div[1]")));
			//clickOnElement(driver.findElement(By.xpath("//div[div/label[text()='Job Title']]//div[text()='-- Select --']")));
			//Thread.sleep(2000);
			
			WebElement tester=driver.findElement(By.xpath("//span[text()='Account Assistant']"));
			//String testertext=tester.getText();
			clickOnElement(tester);
			//String testertext=tester.getText();
		
			//-------------------------------validate job update------------------------------------------------------------	
			
			clickOnElement(driver.findElement(By.xpath("//a[@href='/web/index.php/pim/viewPimModule']")));

			typeRequiredText(driver.findElement(By.xpath(
					"//div/label[text()='Employee Name']/parent::div/following-sibling::div//input[@placeholder='Type for hints...']")),
					"Priya");
		//	Thread.sleep(2000);

			clickOnElement(driver.findElement(By.xpath("//button[@type='submit']")));

			WebElement Jobrole = driver.findElement(By.xpath("//div[@class='oxd-table-card']//div[text()='Account Assistant']"));
					
			//driver.findElement(By.xpath("//div[@class='oxd-table-card']//div[contains(text(),'" + testertext + "')]"));
			Assert.assertTrue(Jobrole.isDisplayed(), "PIM job updation failed");

			
	}

	@Test(priority = 4, enabled = true)
	public void deletePIM() throws InterruptedException {
        //-----------------------Delete PIM-----------------------------------------------------------------
		clickOnElement(driver.findElement(By.xpath("//a[@href='/web/index.php/pim/viewPimModule']")));

		// enter emp name
		WebElement searchID = driver.findElement(By.xpath("//div[div/label[text()='Employee Id']]//div[2]/input"));
		searchID.sendKeys("1994");
		Thread.sleep(2000);

		clickOnElement(driver.findElement(By.xpath("//button[@type='submit']")));

		// click on delete button
		clickOnElement(driver.findElement(By.xpath("//div[@class='oxd-table-cell-actions']/button[2]/i")));
		clickOnElement(driver.findElement(By.xpath("//button[text()=' Yes, Delete ']")));
		
		
		//----------------------------Validate Delete----------------------------------------------------------
		clickOnElement(driver.findElement(By.xpath("//a[@href='/web/index.php/pim/viewPimModule']")));

		//search by id 
		WebElement searchID1 = driver.findElement(By.xpath("//div[div/label[text()='Employee Id']]//div[2]/input"));
		searchID1.clear();
		searchID1.sendKeys("1994");
		
		clickOnElement(driver.findElement(By.xpath("//button[@type='submit']")));
		Thread.sleep(2000);
		
		//validation using Assert
		String expected="No Records Found";
		WebElement NoReord=driver.findElement(By.xpath("//div/span[text()='No Records Found']"));
		String actual=NoReord.getText();
		Assert.assertEquals(actual, expected, "deletion failed");
       // Assert.assertTrue(NoReord.isDisplayed(), "PIM delete failed");
		
		

	}

	@BeforeMethod
	public void loginIntoApp() {
		typeRequiredText(driver.findElement(By.xpath("//input[@name='username']")), "Admin");
		typeRequiredText(driver.findElement(By.xpath("//input[@name='password']")), "admin123");
		clickOnElement(driver.findElement(By.xpath("//button[@type='submit']")));

	}

	@AfterMethod(enabled = true)
	public void logoutFromApp() {
		clickOnElement(driver.findElement(By.className("oxd-userdropdown-tab")));
		clickOnElement(driver.findElement(By.linkText("Logout")));

	}

	@BeforeTest
	public void openBrowser() {
		setUp("Edge", "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@AfterTest(enabled = true)
	public void closeBrowser() {
		driver.close();
	}

}
