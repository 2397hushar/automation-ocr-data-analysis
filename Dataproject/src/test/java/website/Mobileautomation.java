package website;

//import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.text.Element;


public class Mobileautomation extends Openwebsite {
	
	public static void main(String[] args) {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Google Pixel 7 a");
        caps.setCapability("appPackage", "com.example.android");
        caps.setCapability("appActivity", ".MainActivity");
        
        
       
        caps.setCapability("platformVersion", "14");
        caps.setCapability("udid", "33101JEHN18985");
        caps.setCapability("automationName", "UiAutomator2");
       // caps.setCapability("browserName", "Chrome");


        try {
        	AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/"), caps);
            WebElement productList = driver.findElement(By.cssSelector
            		("android.widget.ListView"));
                File screenshot = productList.getScreenshotAs(OutputType.FILE);
                Files.copy(screenshot.toPath(), Paths.get("product_list.png"));
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


