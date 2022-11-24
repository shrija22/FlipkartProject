package chromeFlipkartproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PomFlipkartLogin {
    WebDriver driver;  
	
	By cross= (By.xpath("//button[@class='_2KpZ6l _2doB4z']"));
	By SearchData=By.xpath("//input[@title='Search for products, brands and more']");
	By submit=By.xpath("//button[@type='submit']");
	
	public PomFlipkartLogin(WebDriver temp)
	{
		this.driver=temp;
	}
	
	public void loginalert() {
		driver.findElement(cross).click();
	}
	public void Search_product(String s) {
		driver.findElement(SearchData).sendKeys(s);
	}
	public void submit_clik() {
		driver.findElement(submit).click();
	}
}
