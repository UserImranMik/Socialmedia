package com.example.socialmedia;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

public class Instagram {
	
    private static RemoteWebDriver driver;
	
	private static String instagram = "https://www.instagram.com/";

	@Test
	public void uploadImageOnInstagram() {
		
		try {
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			capabilities.setBrowserName("firefox");
			
			FirefoxOptions options = new FirefoxOptions();
			
			options.addPreference("dom.webnotifications.enabled", false);
			
			driver = new RemoteWebDriver(new URL("http://localhost:4446/wd/hub"), capabilities.merge(options));

			driver.manage().window().maximize();

			driver.navigate().to("https://www.google.com/");

			if(driver.getCurrentUrl().equals("https://www.google.com/")) {
				
				driver.get(instagram);
				
				Thread.sleep(1500);
				
				if(driver.getCurrentUrl().equals("https://www.instagram.com/")) {
					
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
		// enter your username
		
	 WebElement userName = driver.findElement(By.xpath("//input[@name='username']"));
			
		 if(userName.isDisplayed()) {
			 
			 wait.until(ExpectedConditions.visibilityOf(userName)).sendKeys("its_roger007");
			 
			 System.out.println("Username is entered");
		 }
			
		// enter your password
			
     WebElement passKey = driver.findElement(By.xpath("//input[@name='password']"));
			
		 if(passKey.isDisplayed()) {
			 
			 wait.until(ExpectedConditions.visibilityOf(passKey)).sendKeys("Steve@123");
			 
			 System.out.println("Password is entered");
		 }
		
	   // click Log in btn
		
	WebElement logIn = driver.findElement(By.xpath("//button[@type='submit']"));
	
	    if(logIn.isDisplayed()) {
	    	
	    	wait.until(ExpectedConditions.visibilityOf(logIn)).click();
	    	
	    	System.out.println("Login success - Instagram");
	 	   
	 	    Thread.sleep(1400);
	    }
	   
	  // click not now btn
	
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text() = 'Not Now']"))).click();
	   
	  // click create in slide bar
	   
	 WebElement create = driver.findElement(By.xpath("//span[text()='Create']"));  
		
	     if(create.isDisplayed()) {
	    	 
	    	 wait.until(ExpectedConditions.visibilityOf(create)).click();
	    	 
	    	 System.out.println("Create button triggered");
	  	   
	  	   Thread.sleep(1000);
	     }
	   
	 WebElement selectFromPC = driver.findElement(By.xpath("(//input[@type='file'])[2] | (//input[@type='file'])[1]"));
	 
	    String uploadPath = "C:\\Users\\DELL\\OneDrive\\Pictures\\Dell.jpg";
	    
	    driver.setFileDetector(new LocalFileDetector());
	    
	    File file = new File("C:\\Users\\DELL\\OneDrive\\Pictures\\Dell.jpg");
	    
	      System.out.println("The file exists: " + file.exists());
	    
	     driver.switchTo().activeElement();
	    
	     selectFromPC.sendKeys(file.getAbsolutePath());
	     
	     Thread.sleep(1000);
	     
	     for(int i=0; i<2; i++) {
	    	 
	    	 driver.findElement(By.xpath("//div[text() = 'Next']")).click();
	     }
	     
	     Thread.sleep(1000);
	   
	     /// write a caption
	     
	 WebElement writeOwn = driver.findElement(By.xpath("//div[@role = 'textbox']"));
	 
	     if(writeOwn.isDisplayed()) {
	    	 
	    	 wait.until(ExpectedConditions.visibilityOf(writeOwn)).sendKeys("Here is my post !");
	    	 
	    	 System.out.println("Caption is entered");
	     }
	   
	    /// click share btn
	   
	 WebElement shareBtn = driver.findElement(By.xpath("//div[text() = 'Share']"));
	 
	   if(shareBtn.isDisplayed()) {
		   
		   wait.until(ExpectedConditions.visibilityOf(shareBtn)).click();
		   
		   System.out.println("Share button is triggered to post");
	   }
	  
	  Date currentDate = new Date();
	  
	  SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
		
	  String screenshotWithDate = dateFormat.format(currentDate);
	  
	  System.out.println(screenshotWithDate);
	  	    	  
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  
	  js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	  
	  Screenshot screenshot = new AShot().takeScreenshot(driver);
	  
	  String screenshotPath = "./screenshot/"+screenshotWithDate.trim()+".png";
       
	  boolean save = ImageIO.write(screenshot.getImage(), "PNG", new File(screenshotPath));
      
	  System.out.println("Successfully Saved: " + screenshotPath);
	  
	 System.out.println("Successfully Save : "+save);
	 
	        }
								
		}
			
		} catch (Exception e) {
			
			e.getStackTrace();
			
			System.out.println(e.getMessage());
		}
		
	}
	
}
