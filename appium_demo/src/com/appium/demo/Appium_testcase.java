package com.appium.demo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;







import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Appium_testcase {
	static AppiumDriver<WebElement> driver;
//	public static void main(String[] args) throws MalformedURLException {
		@Test
		public void testFunc() throws MalformedURLException {
		String expectedManufacturer= "Genymotion";
		String expectedDeviceName= "Google Nexus 5";
		
		
		DesiredCapabilities desiredCapabilities=new DesiredCapabilities();
		String packageName="com.testdroid.sample.android";
		desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "192.168.101.101:5555");
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM, "Android");
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0.0");
		desiredCapabilities.setCapability("deviceName", "Android Emulator");

		String apkName="TestDroid.apk";
		File classpathRoot=new File(System.getProperty("user.dir"));
		File appDir=new File(classpathRoot,"//testdata//apk");
		File app= new File(appDir, apkName);
		desiredCapabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		desiredCapabilities.setCapability("appPackage", packageName);
		desiredCapabilities.setCapability("appActivity", packageName+".MM_MainMenu");
		desiredCapabilities.setCapability("locale", "US");
		desiredCapabilities.setCapability("deviceReadyTimeout", "450");
		desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60000");
		
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
		
		
//		driver.findElement(By.id(packageName+":id/mm_b_native")).click();
//		driver.navigate().back();
		
		
		driver.findElement(By.id(packageName+":id/mm_b_deviceInfo")).click();
		//com.testdroid.sample.android:id/device_property_tv_value
		
		List<WebElement> textList = driver.findElements(By.id(packageName+":id/device_property_tv_value"));
		
//		for(WebElement temp: textList){
//			String deviceName = temp.getText();
//			System.out.println("My device name: "+deviceName);
//		}
		String manufacturer = textList.get(0).getText();
		System.out.println("Manufacturer: "+manufacturer);
		
		String deviceName = textList.get(1).getText();
		System.out.println("My device name: "+deviceName);
		
		Assert.assertEquals(manufacturer, expectedManufacturer);
		boolean chk=false;
		if(deviceName.contains(expectedDeviceName)){
			chk= true;
		}
		Assert.assertTrue(chk);
		
	}

}
