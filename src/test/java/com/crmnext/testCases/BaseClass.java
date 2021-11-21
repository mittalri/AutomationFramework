package com.crmnext.testCases;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.crmnext.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	ReadConfig readConfig = new ReadConfig();
	
	
	//public String baseURL = "https://my.crmnext.com/crmnext/login/login";
	public String baseURL = readConfig.getApplicationURL();
	
			
	//public String username = "ritesh.mittal@crmnext.com";
	public String username = readConfig.getUserName();
	//public String password = "V_vteam11";
	public String password = readConfig.getPassword();
	
	
	public static WebDriver driver;
	public static org.apache.logging.log4j.Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setUp (String br)
	{
		
		logger = LogManager.getLogger(BaseClass.class);
		
		
		//driver.get(baseURL);
		
		logger.info("URL is invoked - " +baseURL);
		
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		//driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		
		//System.out.println("in setup");
		
		if (br.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get(baseURL);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		}
		else if(br.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.get(baseURL);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));			
		}
		else if (br.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.get(baseURL);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		}
	
		
		//logger.trace("This is a trace message");
		//logger.debug("This is a debug message");
        //logger.info("This is an info message");
        //logger.warn("This is a warn message");
        //logger.error("This is an error message");
        //logger.fatal("This is a fatal message");
        
		//logger = Logger.getLogger("BaseClass");
//		PropertyConfigurator().configure("log4j.properties");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.close();
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/screenshots/"+tname+".png");
		FileUtils.copyFile(source, target);
		System.out.println("ScreenShot taken of "+tname);
		
		
	}
	
	
	public String randomString()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(6);
		return generatedstring;
	}
	
	public String randomemail()
	{
		String randomemail = randomString()+"@gmail.com";
		return randomemail;
	}
}
