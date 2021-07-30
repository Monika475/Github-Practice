package testng;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners({testnglisterner.Suitelistener.class})
public class DataProvider1 {
	
	WebDriver driver;
@BeforeSuite
public void openBrowser()

{
	System.out.println("DataProvider TestNg");
	System.out.println("Browser Opening");
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\91989\\Dropbox\\My PC (LAPTOP-B71V4SN8)\\Desktop\\Selenium\\chromedriver92.exe");
		driver=new ChromeDriver();
}
//@Parameters({"url"})
@BeforeTest
public void openurl()
{
	System.out.println("Url Opening");
driver.get("https://www.flipkart.com/");	
}

@BeforeClass
public void maximizeWindow()
{
	System.out.println("WindowMaximize");
	driver.manage().window().maximize();
	
	}

@BeforeMethod
public void getCookies()
{
	System.out.println("Cookies Name");
	Set<Cookie> cookies=driver.manage().getCookies();
	for (Cookie cookie : cookies) {
		
		System.out.println("Name " +cookie.getName());
	}
}
//@Parameters({"ph","pass"})

@Test(dataProvider="getdata")
public void Login(String ph,String pass)
{
	System.out.println("Login to Flipkart");
	//driver.findElement(By.xpath("//a[@class='_1_3w1N']")).click();
	driver.findElement(By.xpath("//input[@class='_2IX_2- VJZDxU']")).sendKeys(ph);
	driver.findElement(By.xpath("//input[@class='_2IX_2- _3mctLh VJZDxU']")).sendKeys(pass);
	driver.findElement(By.xpath("//button[@class='_2KpZ6l _2HKlqd _3AWRsL']")).click();
	}

	
@AfterMethod
public void Screenshot() throws IOException
{
	System.out.println("Screen Shot of LoginPage");
	File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFileToDirectory(src, new File("C:\\Users\\91989\\Dropbox\\My PC (LAPTOP-B71V4SN8)\\Desktop\\Selenium"));
	
	}
	
@AfterClass
public void deleteCokkie()
{
	System.out.println("Cookies Deleted");
	driver.manage().deleteAllCookies();
	}

@AfterTest

public void DBCoonection()
{
	
	System.out.println("Close the Connection");
	
	}

@AfterSuite
public void closebrowser()
{
	System.out.println("Close the browser");
	driver.close();
}

@DataProvider
public Object[][] getdata()
{
	return new Object[][]
			{
		
		new Object[] {"7620808344","Monika@1999"}
		
			};
	}
}



