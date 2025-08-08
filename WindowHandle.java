package switching;

import org.testng.annotations.Test;

import seleniumUtility.SeleniumUtil;

import org.testng.annotations.BeforeTest;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class WindowHandle extends SeleniumUtil {
	
	public static String HomeID;
 
  @Test(priority=1,enabled=false)
  public void Catering() throws InterruptedException
  {
	  clickOnElement(driver.findElement(By.xpath("//a[text()='eCatering']")));
	  Set <String>AllWinID=driver.getWindowHandles();
	  AllWinID.remove( HomeID);
	  
	  Iterator<String> itr=AllWinID.iterator();
		String childWinId=itr.next();
	  
		//switch control from Home to child
		
		driver.switchTo().window(childWinId);
		
		//validate title
		
		String actualTitle=driver.getTitle();
		//System.out.println(actualTitle);
		String expectedTitle="Order Food on Train Online, Food and Meal on Train, Tasty Food for Train Journey | eCatering IRCTC";
	    Assert.assertEquals(actualTitle, expectedTitle);
		
	    //search station and select
	    
	    clickOnElement(driver.findElement(By.xpath("//input[@placeholder='Search food, brand, station, etc.']")));
		
	    typeRequiredText(driver.findElement(By.xpath("//div/input[@class='form-input pl-12  text-sm' ]")),"Itarsi");
	    //Thread.sleep(1000);
	   
	  wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='flex items-center pb-3']")));

		
	    List<WebElement>list=driver.findElements(By.xpath("//div[@class='flex items-center pb-3']"));
	    System.out.println("Size is: "+list.size());
		//list.get(0).click();
	    for(int i=0;i<list.size();i++)
	    {
	    	String suggestion=list.get(i).getText();
	    	System.out.println(suggestion);
	    	if(suggestion.contains("ITARSI\n"+ "ET"))
	    			
	    	{
	    		list.get(i).click();
	    	}
	    }
	  
	  //  Thread.sleep(2000);
	    wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h5[@class='tracking-normal ']")));
	    List<WebElement>list1=driver.findElements(By.xpath("//h5[@class='tracking-normal ']"));
	    System.out.println("size: "+list1.size());
	    for(int i=0;i<list1.size();i++)
	    {
	    	System.out.println(list1.get(i).getText());
	    }
  
  
  }
  @Test(priority=2)
  public void GetTrains() throws InterruptedException
  {
	  driver.switchTo().window(HomeID);
	  System.out.println(getCurrentUrlOfApplication());
	  wait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath("//input[@id='txtStationFrom']")));
	  typeRequiredText(driver.findElement(By.xpath("//input[@id='txtStationFrom']")), "Pune");
	  Thread.sleep(1000);
	  clickOnElement(driver.findElement(By.xpath("//div[@title='Pune Jn']//div[1]")));
	  
	 
	/*  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//body/div[6]/div[@class='autocomplete-w1']//div[@title]")));
	  List<WebElement>list2=driver.findElements(By.xpath("//body/div[6]/div[@class='autocomplete-w1']//div[@title]"));
	  System.out.println("size: "+list2.size());
	  for(int i=0;i<list2.size();i++)
	  {
		  String From=list2.get(i).getText();
		  System.out.println(From);
		  if(From.contains("Pune Jn"));
		  list2.get(i).click();
		  
	  }*/
	  wait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath("//input[@placeholder='To Station']")));
	  typeRequiredText(driver.findElement(By.xpath("//input[@placeholder='To Station']")), "nagpur");
	  Thread.sleep(1000);
	  clickOnElement(driver.findElement(By.xpath("//div[@title='Nagpur']//div//strong[contains(text(),'Nagpur')]")));
	 
	/* wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='autocomplete-w1']//div[@title]")));
	  List<WebElement>list3=driver.findElements(By.xpath("//div[@class='autocomplete-w1']//div[@title]"));
	  System.out.println("size: "+list3.size());
	  for(int i=0;i<list3.size();i++)
	  {
		  String To=list3.get(i).getText();
		  System.out.println(To);
		  if(To.contains("Nagpur"))
		 list3.get(i).click();
		  
	  }*/
	  
	  //select date
	  clickOnElement(driver.findElement(By.xpath("//input[@title='Select Departure date for availability']")));
	  clickOnElement(driver.findElement(By.xpath("//td[@onclick='DoDateSelect(1757097000000)']")));
	  
	  //train name and train no
	  List<WebElement>rows=driver.findElements(By.xpath("//table[@class='DataTable TrainList TrainListHeader stickyTrainListHeader']/tbody/tr"));
 
  for(WebElement row:rows)
  {
	  List<WebElement>columns=row.findElements(By.tagName("td"));
	  if(columns.size()>1)
	  {
		  String Train_no=columns.get(0).getText();
		  String train_name=columns.get(1).getText();
		  System.out.println("Train_no: "+Train_no+"| Train_name: "+train_name);
	  }
  }
  
  }
  
  
 
 
 @BeforeTest
  public void beforeTest() 
  {
	 driver=setUp("Chrome","https://erail.in/") ;
	 
	 //Get current window id
	  HomeID=driver.getWindowHandle();
	  System.out.println(HomeID);
  }

  @AfterTest
  public void afterTest() 
  {
	 // driver.quit();
  }

}
