package chromeFlipkartproject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class FlipkartMainclass {
	static PomFlipkartLogin log;
	static WebDriver x;
	static PomFlipkartGetPrdDetails det;
	
  @BeforeTest
  public void flipkartsetup() throws InterruptedException {
	  System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
	  x= new ChromeDriver();
	  Thread.sleep(5000);
	  x.manage().window().maximize();
	  //access flipkart page
       x.get("https://www.flipkart.com/");
	  Thread.sleep(2000);
	  String p=x.getTitle();
	  Thread.sleep(2000);
	  System.out.println(p);
  }
  
  @Test(priority=1)
  public void perform_action() throws InterruptedException
  {
	  log= new PomFlipkartLogin(x);
	  log.loginalert();
	  Thread.sleep(2000);
	  //search for product
	  log.Search_product("Mobile Phones");
	  Thread.sleep(2000);
	  //submit action performed
	  log.submit_clik();
	  Thread.sleep(2000);
  }
  
  @Test(priority=2)
  public void getDetailsExcel() throws IOException, InterruptedException, RowsExceededException, WriteException {
	  
	  det= new PomFlipkartGetPrdDetails(x);
	  //get the details from flipkart
	  List<WebElement> prd_Details= det.get_prdDetails();
	  List<WebElement> price_Details=det.get_priceDetails();
	  
	  FileOutputStream f= new FileOutputStream("C:\\Users\\WELCOME\\eclipse-workspace\\FlipkartRealTimeProject\\src\\test\\resources\\FlipkartPrdDetails.xls");
	  WritableWorkbook w= Workbook.createWorkbook(f);
	  WritableSheet s=w.createSheet("ProductDetails&Prices", 0);
	  

	 
	  
	  WritableFont cellFont = new WritableFont(WritableFont.ARIAL,14);
	  cellFont.setBoldStyle(WritableFont.BOLD); 
	  WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
	  cellFormat.setWrap(true); 
	
	    
	  
	  //code to give row headings
	  
	  Label l= new Label(0, 0, "Product List",cellFormat);
	  Label l2=new Label(0, 0, "Price List",cellFormat);
	  s.addCell(l);
	  Thread.sleep(1000);
	  s.addCell(l2);
	  
	  //Logic to write the product details into excel
	  int j=0,i=1;
	  
	  while (i<prd_Details.size()) {
		  Thread.sleep(1000);
		  
		   Label label = new Label(j,i,prd_Details.get(i).getText());
		   s.addCell(label);
	  }
	  //Logic to write the price details into excel
	  j=1;i=1;
	  while (i<price_Details.size()) {
		  Thread.sleep(1000);
		  
		   Label label = new Label(j,i,price_Details.get(i).getText());
		   s.addCell(label);
	  }
	  w.write();
	  w.close();
	  
  }
  
  @AfterTest
  public void tearDown() throws InterruptedException {
	  Thread.sleep(5000);
	  x.quit();
  }
}
