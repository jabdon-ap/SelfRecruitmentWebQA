package WorkerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.DriverFactory;
import Utilities.Utils;

public class WorkerLoginTest {
	WebDriver driver ;
	String url = "http://self-recruitment-staging-6.s3-website-us-east-1.amazonaws.com/";
	String email1 = "apppartnerqa1+1@gmail.com";
	String pass ="SuperBoise-1";
	// sign up info
	String first_Name = "Ben";
	String last_Name = "Frasier";
	String email = "apppartnerqa2+111@gmail.com";
	String phone_Number = Integer.toString(new Random().nextInt(1000000000) * 2) ;;
	String password ="SuperBoise-1";
	String address_Line1 = "32 court street";
	String town = "Brooklyn";
	String postal_Code = "11201";
	String country = "United States";
	String NIN ;
	
	public void login() {
		driver.get(url);
		// click on log in
		driver.findElement(By.cssSelector("a[href='/login']")).click();
		//enter email and pass
		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(pass);
		// click on submit
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		// assert you are on today's job
		//DriverFactory.loadInfo(3000);
		String confirmationText = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[1]/div/div[1]/h1")).getText();
		assertEquals("Today's Jobs", confirmationText);
	}
	
	@Test
	public void createWorker() {
		driver.get(url);
		System.out.println( "the NIN is : " + Utils.generateNIN() );
		//click on become a worker
		driver.findElement(By.cssSelector("a[href='/worker/register']")).click();
		//enter first name
		driver.findElement(By.name("worker_first_name")).sendKeys(first_Name);
		//enter last name
		driver.findElement(By.name("worker_last_name")).sendKeys(last_Name);
		// enter email
		driver.findElement(By.name("worker_email")).sendKeys(email);
		// enter phone number 
		driver.findElement(By.name("worker_phone_number")).sendKeys(phone_Number);
		// enter password 
		driver.findElement(By.name("worker_password")).sendKeys(password);
		// click on submit button
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		// load 
		Utils.loadInfo(2000);
		
		// close tutorial
		driver.findElement(By.cssSelector("span[aria-hidden='true']")).click();
		Utils.loadInfo(2000);

		// Account  screen 
		// enter address line 1
		driver.findElement(By.name("worker_address_line_1")).sendKeys(address_Line1);
		// enter town line 
		driver.findElement(By.name("worker_town")).sendKeys(town);
		// enter Country
		driver.findElement(By.name("worker_country")).sendKeys(country);
		// enter postal code
		driver.findElement(By.name("worker_postal_code")).sendKeys(postal_Code);
		// enter NIN and UTR
		NIN = Utils.generateNIN();
		driver.findElement(By.name("worker_nin")).sendKeys(NIN);
		driver.findElement(By.name("worker_utr")).sendKeys(NIN);
		// Update Account Details 
		try {
			WebElement update_Account_details = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[1]/div/div[2]/form/div[15]/div/button"));
			update_Account_details.click();
		} catch( NoSuchElementException e) {
			System.out.println("element not found" + e.toString());
		}
		// 5 seconds to load to server
		Utils.loadInfo(5000);
		
		// click on confirmation (Profile updated)
		WebElement okConfirmation = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div.swal2-container.swal2-center.swal2-fade.swal2-shown > div > div.swal2-buttonswrapper > button.swal2-confirm.swal2-styled")));
		okConfirmation.click();
		Utils.loadInfo(3000);
		
		// go to Profile 
		driver.findElement(By.cssSelector("#navbar > ul > li:nth-child(5) > a > a")).click();
		// click on My Profile
		driver.findElement(By.xpath("//*[@id=\"navbar\"]/ul/li[4]/a/ul/li[4]/a")).click();
		
		// 
		
	}
	
	public void fillingOverview() {
		
		String verificationNumber = "98wjwjh7";
		// upload profile photo
		// upload image card front
		// upload image card back
		// click on cis registered
		driver.findElement(By.name("worker_cis_registered")).click();
		// enter verification Number
		driver.findElement(By.name("worker_cscs_number")).sendKeys(verificationNumber);
		// enter expected hourly rate
		driver.findElement(By.name("worker_expected_hourly_rate")).sendKeys("30");
		// enter occupations
		//enter description
		// enter work locations
		// enter skills & experience
		// enter Specialized Training
		// click on Update Information Overview
	}
	
	public void fillingWorkHistory() {
		
	}
	
	@Before
	public void setUp() {
		this.driver = DriverFactory.open();
	}
	
	@After
	public void tearDown() {
		this.driver.close();
	}
}
