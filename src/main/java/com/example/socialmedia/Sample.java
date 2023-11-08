package com.example.socialmedia;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.logging.FileHandler;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import io.opentelemetry.api.internal.Utils;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

public class Sample {
	
	private static WebDriver driver;

	private static String facebook = "https://www.facebook.com/";
	
	public static void main(String[] args) throws InterruptedException, MalformedURLException {

		try {
			
			System.setProperty("webdriver.chrome.driver",
					"C:/Users/DELL/.cache/selenium/chromedriver/win64/116.0.5845.62/chromedriver.exe");
			
			ChromeOptions options = new ChromeOptions();

			options.setBinary("C:/Users/DELL/.cache/selenium/chrome/win64/116.0.5845.62/chrome.exe");

			options.addArguments("--remote-allow-origins=*");
			
			//options.addArguments("--headless");

			options.addArguments("--disable-notifications");

			driver = new ChromeDriver(options);

			driver.manage().window().maximize();
			
			driver.navigate().to("https://www.google.com/");
			
			 driver.manage().window().maximize();

			if (driver.getCurrentUrl().equals("https://www.google.com/")) {

				driver.get(facebook);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		 WebElement userName = driver.findElement(By.xpath("//input[@name = 'email']"));

		wait.until(ExpectedConditions.visibilityOf(userName)).sendKeys("imranmik1001@gmail.com");
		
		  System.out.println("Username is entered");

		 WebElement passKey = driver.findElement(By.xpath("//input[@name = 'pass']"));

		wait.until(ExpectedConditions.visibilityOf(passKey)).sendKeys("Imran@10");

		 System.out.println("Password is entered");
		
		 WebElement submitBtn = driver.findElement(By.xpath("//button[@type= 'submit']"));

		wait.until(ExpectedConditions.visibilityOf(submitBtn)).click();
		
		 System.out.println("Login success");
		 
		 Thread.sleep(2000);

		 WebElement clickUpload = driver.findElement(By.xpath("//span[text()=\"What's on your mind, Ïmrañ?\"]"));

		wait.until(ExpectedConditions.visibilityOf(clickUpload)).click();
		
		 System.out.println("It clicks What's on your mind");

		 Thread.sleep(2000);

		WebElement selectPhotoPath = driver.findElement(By.xpath("//div[@aria-label = 'Photo/video']"));

		 wait.until(ExpectedConditions.elementToBeClickable(selectPhotoPath)).click();
		 
		 System.out.println("Then it clicks Photo/video icon");

		WebElement fileInput = driver.findElement(By.xpath("(//input[@type='file'])[2] | (//input[@type='file'])[1]"));  //input[type='file']
		
		driver.switchTo().activeElement();
		
		String uploadPath = "D:\\restapi.png";
		
		 // driver.switchTo().activeElement();
		 
		 //	fileInput.sendKeys(uploadPath);
		
		 String file = new File("D:\\restapi.png").getAbsolutePath();
		 
		 fileInput.sendKeys(file);
		
		WebElement captionInput = driver.findElement(By.xpath("//p[@class='xdj266r x11i5rnm xat24cr x1mh8g0r x16tdsg8']"));
		
		 captionInput.sendKeys("Here is my post !");
		 
		 System.out.println("Caption is given");
		 
		 Thread.sleep(1000);
		
        WebElement clickPost = driver.findElement(By.xpath("//span[text() = 'Post']"));	
			  
			 wait.until(ExpectedConditions.visibilityOf(clickPost)).click();			 
			 
		   System.out.println("Post button is triggered");

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

		} catch (Exception e) {

			e.getStackTrace();
			
			System.out.println(e.getMessage());
		}

	}

}
