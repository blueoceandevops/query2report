package com.lwr.software.test.selenium;


import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SystemPerformanceDemo {
	
	private static WebDriver driver;
	
	
	@BeforeClass
	public static void init(){
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
		try{
			driver.get("http://localhost:8080/q2r/login.html");
			Thread.sleep(1000);
			driver.findElement(By.id("username")).sendKeys("admin");
			driver.findElement(By.id("password")).sendKeys("admin");
			driver.findElement(By.id("loginButton")).click();
			Thread.sleep(1000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void destroy(){
		try{
			driver.close();
			Thread.sleep(1000);
			driver.findElement(By.id("usericon")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("logoutRef")).click();
			Thread.sleep(1000);
		}catch (Exception e) {
		e.printStackTrace();
		}
	}	
	@Test
	public void step1CreateDriver() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("drivermgmt")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("addDriverButton")).click();
		driver.findElement(By.id("aliasInput")).sendKeys("MySQL");
		Thread.sleep(1000);
		driver.findElement(By.id("classNameInput")).sendKeys("com.mysql.jdbc.Driver");
		Thread.sleep(1000);
		driver.findElement(By.id("jarFileUploadSelectInput")).sendKeys("C:\\myprojects\\mysql-connector-java-8.0.15.jar");
		Thread.sleep(5000);
		driver.findElement(By.id("saveDriverButton")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(.,'Ok')]")).click();
		WebElement elem = driver.findElement(By.tagName("h2"));
		String textToAssert = elem.getText();
		Assert.assertEquals(true, textToAssert.contains("JDBC Driver 'MySQL' upload Succeeded. Restart the application server."));
		Thread.sleep(5000);
	}

	@Test
	public void step2CreateConnection() throws InterruptedException {
		driver.navigate().refresh();
		driver.findElement(By.id("connmgmt")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("addConnectionButton")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("aliasInput")).sendKeys("MySQL");
		Thread.sleep(1000);
		driver.findElement(By.id("driverSelect")).sendKeys("MySQL");
		Thread.sleep(1000);
		driver.findElement(By.id("urlInput")).sendKeys("jdbc:mysql://localhost:3306/sys");
		Thread.sleep(1000);
		driver.findElement(By.id("usernameInput")).sendKeys("root");
		Thread.sleep(1000);
		driver.findElement(By.id("passwordInput")).sendKeys("root");
		Thread.sleep(5000);
		driver.findElement(By.id("saveConnectionButton")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(.,'Ok')]")).click();
		WebElement elem = driver.findElement(By.tagName("h2"));
		String textToAssert = elem.getText();
		Assert.assertEquals(true, textToAssert.contains("Save of alias 'MySQL' Succeeded"));
		
		Thread.sleep(1000);
		driver.findElement(By.id("MySQLTestConnectionButton")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[contains(.,'Ok')]")).click();
		elem = driver.findElement(By.tagName("h2"));
		textToAssert = elem.getText();
		Assert.assertEquals(true, textToAssert.contains("Connection to alias 'MySQL' Succeeded"));
	}

	@Test
	public void step3CreateFixedReport() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(1000);
		driver.findElement(By.id("newreport")).click();
		Thread.sleep(10000);
		driver.findElement(By.id("editReportRef")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("title")).sendKeys("Server Performance - Fixed");
		Thread.sleep(1000);
		driver.findElement(By.id("description")).sendKeys("Performance of server in terms of CPU, Run Q, Memory and Swap Utilization");
		
		Thread.sleep(1000);
		driver.findElement(By.id("saveButton")).click();

		Thread.sleep(1000);
		driver.findElement(By.id("addRow")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("addColumn0")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("addColumn1")).click();
		
		Thread.sleep(1000);
		driver.findElement(By.id("editRef00")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("titleInput")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("titleInput")).sendKeys("CPU Utilization");
		Thread.sleep(1000);
		driver.findElement(By.id("queryInput")).sendKeys("select ts,hostname,cpu from sysperf where hostname in ('myhost1.mydomain.com') and ts>='2018-10-05 10:00:00' and ts<='2018-10-06 12:00:00' order by ts");
		Thread.sleep(1000);
		driver.findElement(By.id("chartSelect")).sendKeys("Line Chart");
		Thread.sleep(1000);
		driver.findElement(By.id("databaseSelect")).sendKeys("MySQL");
		Thread.sleep(1000);
		driver.findElement(By.id("refreshButton")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("saveButton")).click();

		Thread.sleep(1000);
		driver.findElement(By.id("editRef01")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("titleInput")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("titleInput")).sendKeys("Run Queue");
		Thread.sleep(1000);
		driver.findElement(By.id("queryInput")).sendKeys("select ts,hostname,runq from sysperf where hostname in ('myhost1.mydomain.com') and ts>='2018-10-05 10:00:00' and ts<='2018-10-06 12:00:00' order by ts");
		Thread.sleep(1000);
		driver.findElement(By.id("chartSelect")).sendKeys("Scatter Chart");
		Thread.sleep(1000);
		driver.findElement(By.id("databaseSelect")).sendKeys("MySQL");
		Thread.sleep(1000);
		driver.findElement(By.id("refreshButton")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("saveButton")).click();

		Thread.sleep(1000);
		driver.findElement(By.id("editRef10")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("titleInput")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("titleInput")).sendKeys("Memory Queue");
		Thread.sleep(1000);
		driver.findElement(By.id("queryInput")).sendKeys("select ts,hostname,mem from sysperf where hostname in ('myhost1.mydomain.com') and ts>='2018-10-05 10:00:00' and ts<='2018-10-06 12:00:00' order by ts");
		Thread.sleep(1000);
		driver.findElement(By.id("chartSelect")).sendKeys("Column Chart");
		Thread.sleep(1000);
		driver.findElement(By.id("databaseSelect")).sendKeys("MySQL");
		Thread.sleep(1000);
		driver.findElement(By.id("refreshButton")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("saveButton")).click();

		Thread.sleep(1000);
		driver.findElement(By.id("editRef11")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("titleInput")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("titleInput")).sendKeys("Swap Utilization");
		Thread.sleep(1000);
		driver.findElement(By.id("queryInput")).sendKeys("select ts,hostname,swap from sysperf where hostname in ('myhost1.mydomain.com') and ts>='2018-10-05 10:00:00' and ts<='2018-10-06 12:00:00' order by ts");
		Thread.sleep(1000);
		driver.findElement(By.id("chartSelect")).sendKeys("Area Chart");
		Thread.sleep(1000);
		driver.findElement(By.id("databaseSelect")).sendKeys("MySQL");
		Thread.sleep(1000);
		driver.findElement(By.id("refreshButton")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("saveButton")).click();

		Thread.sleep(1000);
		driver.findElement(By.id("saveReportButton")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("savePublicRef")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(.,'Ok')]")).click();
		Thread.sleep(1000);
		
		Thread.sleep(1000);
		driver.findElement(By.id("publicmgmt")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("Server Performance - FixedOpenRef")).click();
		Thread.sleep(3000);
	}


	@Test
	public void step4CreateParameterizedReport() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("newreport")).click();
		Thread.sleep(10000);
		driver.findElement(By.id("editReportRef")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("title")).sendKeys("Server Performance - Parameterized");
		Thread.sleep(1000);
		driver.findElement(By.id("description")).sendKeys("Performance of server in terms of CPU, Run Q, Memory and Swap Utilization");
		Thread.sleep(1000);
		driver.findElement(By.id("saveButton")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("addRow")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("addColumn0")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("addColumn1")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("editRef00")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("titleInput")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("titleInput")).sendKeys("CPU Utilization");
		Thread.sleep(1000);
		driver.findElement(By.id("queryInput")).sendKeys("select ts,hostname,cpu from sysperf where hostname in {list:hostname} and ts>={date:startdate} and ts<={date:enddate} order by ts");
		Thread.sleep(1000);
		driver.findElement(By.id("chartSelect")).sendKeys("Line Chart");
		Thread.sleep(1000);
		driver.findElement(By.id("databaseSelect")).sendKeys("MySQL");

		Thread.sleep(1000);
		driver.findElement(By.id("refreshButton")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("params")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("hostname")).sendKeys("myhost1.mydomain.com");
		Thread.sleep(1000);
		driver.findElement(By.id("startdate")).sendKeys("10/04/2018");
		Thread.sleep(1000);
		driver.findElement(By.id("enddate")).sendKeys("10/06/2018");
		Thread.sleep(1000);
		driver.findElement(By.id("refreshButton")).click();

		Thread.sleep(1000);
		driver.findElement(By.id("saveButton")).click();

		Thread.sleep(1000);
		driver.findElement(By.id("editRef01")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("titleInput")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("titleInput")).sendKeys("Run Queue");
		Thread.sleep(1000);
		driver.findElement(By.id("queryInput")).sendKeys("select ts,hostname,runq from sysperf where hostname in {list:hostname} and ts>={date:startdate} and ts<={date:enddate} order by ts");
		Thread.sleep(1000);
		driver.findElement(By.id("chartSelect")).sendKeys("Scatter Chart");
		Thread.sleep(1000);
		driver.findElement(By.id("databaseSelect")).sendKeys("MySQL");

		Thread.sleep(1000);
		driver.findElement(By.id("refreshButton")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("params")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("hostname")).sendKeys("myhost1.mydomain.com");
		Thread.sleep(1000);
		driver.findElement(By.id("startdate")).sendKeys("10/04/2018");
		Thread.sleep(1000);
		driver.findElement(By.id("enddate")).sendKeys("10/06/2018");
		Thread.sleep(1000);
		driver.findElement(By.id("refreshButton")).click();

		
		Thread.sleep(1000);
		driver.findElement(By.id("saveButton")).click();

		Thread.sleep(1000);
		driver.findElement(By.id("editRef10")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("titleInput")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("titleInput")).sendKeys("Memory Queue");
		Thread.sleep(1000);
		driver.findElement(By.id("queryInput")).sendKeys("select ts,hostname,mem from sysperf where hostname in {list:hostname} and ts>={date:startdate} and ts<={date:enddate} order by ts");
		Thread.sleep(1000);
		driver.findElement(By.id("chartSelect")).sendKeys("Column Chart");
		Thread.sleep(1000);
		driver.findElement(By.id("databaseSelect")).sendKeys("MySQL");
		Thread.sleep(1000);
		driver.findElement(By.id("refreshButton")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("params")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("hostname")).sendKeys("myhost1.mydomain.com");
		Thread.sleep(1000);
		driver.findElement(By.id("startdate")).sendKeys("10/04/2018");
		Thread.sleep(1000);
		driver.findElement(By.id("enddate")).sendKeys("10/06/2018");
		Thread.sleep(1000);
		driver.findElement(By.id("refreshButton")).click();

		Thread.sleep(1000);
		driver.findElement(By.id("saveButton")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("editRef11")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("titleInput")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("titleInput")).sendKeys("Swap Utilization");
		Thread.sleep(1000);
		driver.findElement(By.id("queryInput")).sendKeys("select ts,hostname,swap from sysperf where hostname in {list:hostname} and ts>={date:startdate} and ts<={date:enddate} order by ts");
		Thread.sleep(1000);
		driver.findElement(By.id("chartSelect")).sendKeys("Area Chart");
		Thread.sleep(1000);
		driver.findElement(By.id("databaseSelect")).sendKeys("MySQL");

		Thread.sleep(1000);
		driver.findElement(By.id("refreshButton")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("params")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("hostname")).sendKeys("myhost1.mydomain.com");
		Thread.sleep(1000);
		driver.findElement(By.id("startdate")).sendKeys("10/04/2018");
		Thread.sleep(1000);
		driver.findElement(By.id("enddate")).sendKeys("10/06/2018");
		Thread.sleep(1000);
		driver.findElement(By.id("refreshButton")).click();
		Thread.sleep(1000);
		
		
		Thread.sleep(1000);
		driver.findElement(By.id("saveButton")).click();

		Thread.sleep(1000);
		driver.findElement(By.id("saveReportButton")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("savePublicRef")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(.,'Ok')]")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.id("publicmgmt")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("Server Performance - ParameterizedOpenRef")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("hostname")).sendKeys("myhost1.mydomain.com,myhost2.mydomain.com");
		Thread.sleep(1000);
		driver.findElement(By.id("startdate")).sendKeys("10/04/2018");
		Thread.sleep(1000);
		driver.findElement(By.id("enddate")).sendKeys("10/06/2018");
		Thread.sleep(1000);
		driver.findElement(By.id("applyButton")).click();
		Thread.sleep(3000);
	}

	@Test
	public void step5ChangeColSpanLowToHigh() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.id("publicmgmt")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("Server Performance - ParameterizedEditRef")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("hostname")).sendKeys("myhost1.mydomain.com,myhost2.mydomain.com");
		Thread.sleep(1000);
		driver.findElement(By.id("startdate")).sendKeys("10/04/2018");
		Thread.sleep(1000);
		driver.findElement(By.id("enddate")).sendKeys("10/06/2018");
		Thread.sleep(1000);
		driver.findElement(By.id("applyButton")).click();		
		Thread.sleep(4000);
		driver.findElement(By.id("editRef01")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("colSpanInput")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("colSpanInput")).sendKeys("3");
		Thread.sleep(2000);
		driver.findElement(By.id("chartSelect")).sendKeys("Stepped Area Chartt");
		Thread.sleep(1000);
		driver.findElement(By.id("saveButton")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("saveReportButton")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("savePublicRef")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(.,'Ok')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("publicmgmt")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("Server Performance - ParameterizedOpenRef")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("hostname")).sendKeys("myhost1.mydomain.com,myhost2.mydomain.com");
		Thread.sleep(1000);
		driver.findElement(By.id("startdate")).sendKeys("10/04/2018");
		Thread.sleep(1000);
		driver.findElement(By.id("enddate")).sendKeys("10/06/2018");
		Thread.sleep(1000);
		driver.findElement(By.id("applyButton")).click();
		Thread.sleep(2000);
	}
}
