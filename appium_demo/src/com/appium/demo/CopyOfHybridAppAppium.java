package com.appium.demo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class CopyOfHybridAppAppium {
	static AppiumDriver<WebElement> driver;
//	public static void main(String[] args) throws MalformedURLException {
		@Test
		public void testFunc() throws MalformedURLException, InterruptedException {		
		
		DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
		String packageName="com.fourdxfitness.client";
		desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "192.168.101.101:5555");
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM, "Android");
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0.0");
		desiredCapabilities.setCapability("deviceName", "Android Emulator");

		String apkName="9zestClient.apk";
		File classpathRoot=new File(System.getProperty("user.dir"));
		File appDir=new File(classpathRoot,"//testdata//apk");
		File app= new File(appDir, apkName);
		desiredCapabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		desiredCapabilities.setCapability("appPackage", packageName);
		desiredCapabilities.setCapability("appActivity", packageName+".MainActivity");
		desiredCapabilities.setCapability("locale", "US");
		desiredCapabilities.setCapability("deviceReadyTimeout", "450");
		desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60000");
		
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		Thread.sleep(10000);
		
		driver.findElement(By.xpath("//android.widget.Button[@content-desc='REGISTER ']")).click();
		
		Thread.sleep(10000);
		//get all the context
		
		Set<String> allContexts = driver.getContextHandles();
		
		for(String temp: allContexts){
			if(temp.contains("WEBVIEW")){
				driver.context(temp);
			}
		}
		
		

		List<WebElement> textBoxes = driver.findElements(By.xpath("//android.widget.EditText"));
		
		textBoxes.get(0).sendKeys("pravendra");
		
		textBoxes.get(1).sendKeys("pravendra@gmail.com");
		
		textBoxes.get(2).sendKeys("pravendra123");
		
		Thread.sleep(15000);
		
		
		

		
	}

}
