package chromeFlipkartproject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PomFlipkartGetPrdDetails {
	 WebDriver d;
 
	By prod_Details=By.xpath("//div[@class='_4rR01T']");
	By priceDetails= By.xpath("//div[text()='₹']");
	
	public PomFlipkartGetPrdDetails (WebDriver temp)
	{
		this.d=temp;
	}
	
	public List<WebElement> get_prdDetails() {
		List<WebElement> prdList=d.findElements(prod_Details);
		return prdList;
		
	}
	
	public List<WebElement> get_priceDetails(){
		List<WebElement> priceList=d.findElements(priceDetails);
		return priceList;
	}
}
