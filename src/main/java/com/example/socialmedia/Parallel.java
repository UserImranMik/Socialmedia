package com.example.socialmedia;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

public class Parallel {

	private static RemoteWebDriver driver;
	
	private static String facebook = "https://www.facebook.com/";

	private static String instagram = "https://www.instagram.com/";
	
	@Test
	@Parameters({"browser1","passwordFb", "browser2", "passwordInsta"})
	public void parallelExecution(String browser1, String browser2, 
			
			String passwordFb, String passwordInsta) {
		
		try {
			
			if(browser2.equals("firefox")) {
				
          DesiredCapabilities capabilities = new DesiredCapabilities();
				
				capabilities.setBrowserName("firefox");
				
				FirefoxOptions options = new FirefoxOptions();
				
				options.addPreference("dom.webnotifications.enabled", false);
				
				driver = new RemoteWebDriver(new URL("http://localhost:4442/wd/hub"), capabilities.merge(options));

				Reporter.log("Firefox Launched", true);
				
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
				 
				 wait.until(ExpectedConditions.visibilityOf(passKey)).sendKeys(passwordInsta);
				 
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
				
			} else if(browser1.equals("chrome")) {
				
				ChromeOptions options = new ChromeOptions();

				//options.setBinary("C:/Users/DELL/.cache/selenium/chrome/win64/116.0.5845.62/chrome.exe");
				
				options.addArguments("--remote-allow-origins=*");
				
				//options.addArguments("--headless");

				options.addArguments("--disable-notifications");		
				
				DesiredCapabilities capabilities = new DesiredCapabilities();

				capabilities.setBrowserName("chrome");
				
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				
				URL url = new URL("http://localhost:4444/wd/hub");
				
				RemoteWebDriver driver = null;
		       
				driver = new RemoteWebDriver(url, capabilities);
				
				Reporter.log("Chrome Launched", true);
				
				driver.navigate().to("https://www.google.com/");
				
				 driver.manage().window().maximize();

				if (driver.getCurrentUrl().equals("https://www.google.com/")) {

					driver.get(facebook);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			 WebElement userName = driver.findElement(By.xpath("//input[@name = 'email']"));

	             if(userName.isDisplayed()) {
	            	 
	            	 wait.until(ExpectedConditions.visibilityOf(userName)).sendKeys("imranmik1001@gmail.com");
	         		
	       		     System.out.println("Username is entered");
	             }
	 
			 WebElement passKey = driver.findElement(By.xpath("//input[@name = 'pass']"));

			  if(passKey.isDisplayed()) {
				  
				  wait.until(ExpectedConditions.visibilityOf(passKey)).sendKeys(passwordFb);

					System.out.println("Password is entered");
			  }
			
			 WebElement submitBtn = driver.findElement(By.xpath("//button[@type= 'submit']"));

			  if(submitBtn.isDisplayed()) {
				  
				  wait.until(ExpectedConditions.visibilityOf(submitBtn)).click();
					
					System.out.println("Login success - Facebook");
					 
					 Thread.sleep(2000);
			  }

			 WebElement clickUpload = driver.findElement(By.xpath("//span[text()=\"What's on your mind, Ïmrañ?\"]"));

			  if(clickUpload.isDisplayed()) {
				  
				  wait.until(ExpectedConditions.visibilityOf(clickUpload)).click();
					
					 System.out.println("It clicks What's on your mind");

					 Thread.sleep(2000);
			  }

			WebElement selectPhotoPath = driver.findElement(By.xpath("//div[@aria-label = 'Photo/video']"));

			 if(selectPhotoPath.isDisplayed()) {
				 
				 wait.until(ExpectedConditions.elementToBeClickable(selectPhotoPath)).click();		 
					
				 System.out.println("Then it clicks Photo/video icon");

			 }
			
			WebElement fileInput = driver.findElement(By.xpath("(//input[@type='file'])[2] | (//input[@type='file'])[1]"));  //input[type='file']
			
			String uploadPath = "D:/restapi.png";
			
			 // driver.switchTo().activeElement();
			 
			 //	fileInput.sendKeys(uploadPath);
			
			 driver.setFileDetector(new LocalFileDetector());
		    
			File file = new File("D:/restapi.png");

		       System.out.println("The file exists: " + file.exists());
		      
		       fileInput.sendKeys(file.getAbsolutePath());
			
			WebElement captionInput = driver.findElement(By.xpath("//p[@class='xdj266r x11i5rnm xat24cr x1mh8g0r x16tdsg8']"));
			
			  if(captionInput.isDisplayed()) {
				  
				  captionInput.sendKeys("Here is my post !");
					 
				   System.out.println("Caption is given");
					 
					 Thread.sleep(1000);
			   }
			
	        WebElement clickPost = driver.findElement(By.xpath("//span[text() = 'Post']"));	
				  
				 if(clickPost.isDisplayed()) {
					 
					 wait.until(ExpectedConditions.visibilityOf(clickPost)).click();			 
					 
					   System.out.println("Post button is triggered to post");
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
				
			} else {
	           
				throw new IllegalArgumentException("Invalid browser parameter");
	        }			
			
		} catch (Exception e) {
			
			e.getStackTrace();
			
			System.out.println(e.getMessage());
		}
		
	}
	
	
}
