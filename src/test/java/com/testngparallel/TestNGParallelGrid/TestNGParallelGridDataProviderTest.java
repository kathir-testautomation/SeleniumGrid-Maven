package com.testngparallel.TestNGParallelGrid;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;

/**
 * Unit test for simple App.
 */
public class TestNGParallelGridDataProviderTest 
{
	WebDriver driver;
	
	@Test(dataProvider = "getNames")
	 public void googleSearch(String browser, String searchText) throws MalformedURLException, InterruptedException {
	  
	  System.out.println(browser);
	  
	  DesiredCapabilities cap = null;

	  if (browser.equals("firefox")) {
	   cap = DesiredCapabilities.firefox();
	   cap.setBrowserName("firefox");
	   cap.setPlatform(Platform.WINDOWS);
	  } else if (browser.equals("chrome")) {
	   cap = DesiredCapabilities.chrome();
	   cap.setBrowserName("chrome");
	   cap.setPlatform(Platform.WINDOWS);
	  }

	  driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
//	  driver.manage().window().maximize();

	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      driver.get("http://www.google.com");
      Thread.sleep(500);
      WebElement searchArea = driver.findElement(By.name("q"));
      searchArea.sendKeys(searchText);
      Thread.sleep(4500);
      WebElement searchButton = driver.findElement(By.name("btnG"));
      searchButton.click();
      Thread.sleep(2500);
      String pageSource = driver.getPageSource();
      Assert.assertTrue(pageSource.contains(searchText));
      Thread.sleep(1000);
      driver.close();
      driver.quit();
	 }
	
  /*  @AfterTest
    public void tearDown(){
    	 if (driver != null) {
             driver.quit();
         }
    }*/

	 @DataProvider(parallel = true)
	 public Object[][] getNames() {
	  Object data[][] = new Object[2][2];
	  data[0][0] = "firefox";
	  data[0][1] = "JUnit";

	  data[1][0] = "chrome";
	  data[1][1] = "JUnit";

	  return data;
	 }
}
