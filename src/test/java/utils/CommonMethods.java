package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class CommonMethods extends PageInitializer{

    public static WebDriver driver;

    //Opens the browser and launches the application based on the browser specified in config.properties
    public void openBrowserAndLaunchApplication() {
        switch (ConfigReader.read("browser")) {
            case "Chrome":

               ChromeOptions options = new ChromeOptions();
               options.addArguments("--headless=new");
               options.addArguments("--no-sandbox");
               options.addArguments("--disable-dev-shm-usage");
               options.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(options);
                break;

            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
            case "Safari":
                driver = new SafariDriver();
                break;
            default:
                throw new RuntimeException("Invalid Browser Name");

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(ConfigReader.read("url"));
        //InitializePageObjects, so method will call all the objects
        initializePageObjects();
    }

    //Closes the browser if it's open
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver=null;
        }
    }

    //Sends text to a WebElement
    public void sendText(String text, WebElement element) {
        element.clear();
        element.sendKeys(text);
    }

    //Dropdown interaction: select by visible text
    public void selectFromDropDown(WebElement dropDown, String visibleText) {
        Select select = new Select(dropDown);
        select.selectByVisibleText(visibleText);
    }

    //Dropdown interaction: select by value
    public void selectFromDropDown(String value, WebElement dropDown) {
        Select select = new Select(dropDown);
        select.selectByValue(value);
    }

    //Dropdown interaction: select by index
    public void selectFromDropDown(WebElement dropDown, int index) {
        Select select = new Select(dropDown);
        select.deselectByIndex(index);
    }

    //Get explicit wait for WebDriver
    public WebDriverWait getWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    //Wait for an element to be clickable
    public void waitForElementToBeClickable(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    //Clicks on a WebElement after waiting for it to be clickable
    public void click(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }

    //Get JavaScript Executor instance
    public JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) driver;
    }

    //Perform a click using JavaScript Executor
    public void jsClick(WebElement element) {
        getJSExecutor().executeScript("arguments[0].click();", element);
    }

    public String getTimeStamp(String pattern) {
        //Define the format for the time stamp
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date());
    }

    //Takes a screenshot and saves it to a specified location
    public byte[] takeScreenshot(String fileName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(sourceFile, new File(
                    Constants.SCREENSHOT_FILEPATH + fileName + "_" + getTimeStamp("yyyy-MM-dd-HH-mm-ss") + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picBytes;
    }


    }




