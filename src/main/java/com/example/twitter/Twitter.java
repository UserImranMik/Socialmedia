package com.example.twitter;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Twitter {

	private static WebDriver driver;

	private static String twitter = "https://twitter.com/i/flow/login";
	
	public static void main (String [] args) {		
		
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
			
			if(driver.getCurrentUrl() != null) {
				
				driver.get(twitter);
				
				Thread.sleep(1500);
				
		if(driver.getCurrentUrl() != null) {
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
		// enter username
	
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='text']"))).sendKeys("info_carer");
	
	   // click next btn
	 
	WebElement nextBtn = driver.findElement(By.xpath("//Span[text()= 'Next']"));
	
	 wait.until(ExpectedConditions.visibilityOf(nextBtn)).click();
	 
	 Thread.sleep(2000);
			
	  // enter password
	 
    WebElement passKey = driver.findElement(By.xpath("//input[@type='password']"));
    
     wait.until(ExpectedConditions.visibilityOf(passKey)).sendKeys("Icpl@2023");
     
     // click log in btn
	
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = 'Log in']"))).click();
		      
            Thread.sleep(2000);
            
    //WebElement post = driver.findElement(By.xpath("//a[@aria-label='Post']")); 
       
       //wait.until(ExpectedConditions.visibilityOf(post)).click();
       
    WebElement writeTwit = driver.findElement(By.xpath("//div[@class='public-DraftStyleDefault-block public-DraftStyleDefault-ltr']"));
    
      wait.until(ExpectedConditions.visibilityOf(writeTwit)).sendKeys("Here is my twit");
      
    WebElement fileInput = driver.findElement(By.xpath("(//input[@type = 'file'])[1]"));
    
     String uploadPath = "C:\\Users\\DELL\\OneDrive\\Pictures\\Dell.jpg";
    
        fileInput.sendKeys(uploadPath);
      
    WebElement postTwit = driver.findElement(By.xpath("(//span[text()='Post'])[1]"));
    
      wait.until(ExpectedConditions.visibilityOf(postTwit)).click();
            
            
		      }
				
			}
				
		} catch (Exception e) {
			
			e.getStackTrace();
			
			System.out.println(e.getMessage());
		}
		
	}
	
	
	
}
