package es_ontrack.backend.src;		

import org.openqa.selenium.By;		
import org.openqa.selenium.WebDriver;		
import org.openqa.selenium.firefox.FirefoxDriver;		
import org.testng.Assert;		
import org.testng.annotations.Test;	
import org.testng.annotations.BeforeTest;	
import org.testng.annotations.AfterTest;		

public class TestSelenium {		
	    private WebDriver driver;		
		@Test				
		public void testEasy() {	
			driver.get("http://192.168.160.87:23000/");  
			String title = driver.getTitle();				 
			Assert.assertTrue(title.contains("OnTrack")); 		
		}	
		@BeforeTest
		public void beforeTest() {	
		    driver = new FirefoxDriver();  
		}		
		@AfterTest
		public void afterTest() {
			driver.quit();			
		}		
}	
