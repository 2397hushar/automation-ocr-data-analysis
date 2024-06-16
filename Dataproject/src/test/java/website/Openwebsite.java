package website;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Openwebsite {
    public static void main(String[] args) {
        // Use WebDriverManager to manage ChromeDriver binaries
        // WebDriverManager.chromedriver().setup();
    	
        System.setProperty("webdriver.chrome.driver","/Dataproject/src/driver/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.amazon.in/");
        driver.manage().window().fullscreen();

        WebElement searchBox = driver.findElement(By.cssSelector("#twotabsearchtextbox"));
        searchBox.sendKeys("Hindi Books");
        searchBox.submit();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".s-main-slot")));

        
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll down by 1000 pixels
        js.executeScript("window.scrollBy(0,1000)");

        // Add a delay to observe the scroll (optional)
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Find product elements (adjust the locator based on the actual structure)
        List<WebElement> products = driver.findElements(By.cssSelector(".s-main-slot .s-result-item"));

        JSONArray jsonArray = new JSONArray();

        for (WebElement product : products) {
            try {
                String name = product.findElement(By.cssSelector("span.a-text-normal")).getText();
                
                String price = product.findElement(By.xpath("//span[@class='a-price-whole']")).getText();
                String rating = product.findElement(By.xpath("//span[@class='a-icon-alt']")).getText();
                JSONObject jsonObject = new JSONObject();
              //  jsonObject.put("name", name);
                jsonObject.put("price", price);
                jsonObject.put("rating", rating);

                jsonArray.put(jsonObject);
            } catch (Exception e) {
                // Handle cases where the element might not be found
                System.out.println("Error finding element: " + e.getMessage());
            }
        }

        try (FileWriter file = new FileWriter("web_scraped_data.json")) {
            file.write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}
