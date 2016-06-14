package com.testngparallel.TestNGParallelGrid;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class TestNGParameterizedGridTest {

	WebDriver driver;

	@Parameters("browser")
	@BeforeTest
	public void setPrerequisite(String browser) throws MalformedURLException
	{
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
//		  driver.manage().window().maximize();

		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	 public void googleSearch() throws InterruptedException{

      driver.get("http://www.google.com");
      Thread.sleep(500);
      WebElement searchArea = driver.findElement(By.name("q"));
      searchArea.sendKeys("TestNG");
      Thread.sleep(4500);
      WebElement searchButton = driver.findElement(By.name("btnG"));
      searchButton.click();
      Thread.sleep(2500);
      String pageSource = driver.getPageSource();
      Assert.assertTrue(pageSource.contains("TestNG"));
      Thread.sleep(1000);
//      driver.close();
//      driver.quit();
	 }
	
    @AfterTest
    public void tearDown(){
    	 if (driver != null) {
             driver.quit();
         }
    }

}
