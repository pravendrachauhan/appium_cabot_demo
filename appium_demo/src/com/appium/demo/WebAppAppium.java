package com.appium.demo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebAppAppium extends BaseTest{
	private static AppiumDriverLocalService service;

	@Test
	public void testFunc1(){		

		driver.get("http://www.google.com");

		boolean condition = driver.findElement(By.id("lst-ib")).isDisplayed();

		Assert.assertTrue(condition);

		System.out.println("Test1");

	}

	@Test
	public void testFunc2(){		

		driver.get("http://www.google.com");

		driver.findElement(By.id("lst-ib")).sendKeys("pravendra");

		String text = driver.findElement(By.id("lst-ib")).getAttribute("value");

		System.out.println("text: "+text);

		Assert.assertTrue(text.equals("pravendra"));

		System.out.println("Test2");

	}

}
