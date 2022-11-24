package chromeFlipkartproject;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class AmazonAutoProject {
	WebDriver driver;
  @BeforeTest
  public void pagesetup() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
	  driver= new ChromeDriver();
	  driver.manage().window().maximize();
       driver.get("https://www.flipkart.com/");
	  Thread.sleep(2000);
	  String p=driver.getTitle();
	  System.out.println(p);
  }

  @Test(priority=1)
  public  void searchPhone() throws InterruptedException {
	  driver.findElement(By.xpath("//input[@title='Search for products, brands and more']")).sendKeys("Mobile Phone");
	  Thread.sleep(4000);
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	  Thread.sleep(4000);
	
	   
  }
  @Test(priority=2)
  public void createWorkBook() throws IOException, InterruptedException, RowsExceededException, WriteException {
	   
	  FileOutputStream fexcel= new FileOutputStream("C:\\Users\\WELCOME\\eclipse-workspace\\MavenProject"
	  		+ "\\src\\test\\resources\\project5.xls");
	  WritableWorkbook wb = Workbook.createWorkbook(fexcel);
	 
	  WritableSheet s = wb.createSheet("ProductDetails", 1);
	  
	  
	  List<WebElement> productlist=driver.findElements(By.xpath("//div[@class='_4rR01T']"));
	   List<WebElement> price= driver.findElements(By.xpath("//div[text()='₹']"));
	   int j=0;
	   for(int i=0;i<price.size();i++) {
	  // System.out.println(productlist.get(i).getText()+"--"+price.get(i).getText());
	   Thread.sleep(1000);
	  
	   Label label = new Label(j,i,productlist.get(i).getText());
	   s.addCell(label);
	   
	   }
	   j=1;
	  int i=0;
	   while(i<price.size())
	   {
		 
		   Label l= new Label(j,i,price.get(i).getText());
		   s.addCell(l);
		   i++;
	   }
	   
	   wb.write();
	   wb.close();
	   
  }
  @AfterTest
  public void tearDown() {
  driver.quit();
  }
	  
}
  

