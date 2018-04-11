package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

	public static WebDriver open() {
		System.setProperty("webdriver.chrome.driver",  "lib/chromedriver");
		WebDriver driver = new ChromeDriver();
		return driver;
		//driver.get(url);
	}
}
