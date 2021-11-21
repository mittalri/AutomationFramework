package com.crmnext.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {
	
	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.HH.mm.ss").format(new Date());//timestamp
		String repName = "Test-Report-"+timeStamp+".html";
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/"+repName);
		try {
			htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
			
			//(new File ("/CRMNEXTAutomationFramework/extent-config.xml"));
					//System.getProperty(("user.dir"+"\\extent-config.xml"));
			//loadXMLConfig(new File ("src/test/resources/extent-config.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Extent Config file not found");
			e.printStackTrace();
			
		}
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Enviornment", "QA");
		extent.setSystemInfo("user", "Ritesh");
		
		htmlReporter.config().setDocumentTitle("CRMNEXT Testing");
		htmlReporter.config().setReportName("Functional Test Report");
		htmlReporter.config().setTheme(Theme.DARK);
		//htmlReporter.config().setTestViewChartLocation(ChartLocation.Top);
		
	}

	public void onTestSuccess(ITestResult tr)
	{
		logger = extent.createTest(tr.getName());//Create New Entry in report
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}
	
	public void onTestFailure(ITestResult tr)
	{
		logger = extent.createTest(tr.getName());//Create New Entry in report
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		
		String screenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
		File f = new File(screenshotPath);
		if (f.exists())
		{
			logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
		}
		
		
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		logger = extent.createTest(tr.getName());//Create New Entry in report
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
		
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}

}
