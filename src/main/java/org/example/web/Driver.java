package org.example.web;

import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;


public class Driver {

    public static final String BROWSER_CHROME = "chrome";
    public static final String BROWSER_FIREFOX = "firefox";
    public static final String BROWSER_SAFARI = "safari";
    public static final String BROWSER_EDGE = "edge";
    public static WebDriver driver;
    private static WebDriverWait wait;
    public Wait fluentWait;

    public Driver(){}

    public WebDriver getDriver() {
        if(null!=driver)
            return driver;
        else
            return initChromeDriver();
    }

//    public WebDriver getDriver() {
//        if(null!=driver)
//            return driver;
//        else
//            return initializeDriver(BROWSER_CHROME);
//    }

    private WebDriver initChromeDriver(){
//        String platform=System.getProperty("executionPlatform").toLowerCase();
//        System.out.println("platform:"+platform);

        WebDriverManager.chromedriver().setup();

//        Map<String, Object> prefs = new HashMap<String, Object>();
//        prefs.put("profile.default_content_setting_values.cookies", 2);
//        prefs.put("network.cookie.cookieBehavior", 2);
//        prefs.put("profile.block_third_party_cookies", true);

        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        options.addArguments("--incognito");
        options.addArguments("start-maximized");
        options.addArguments("window-size=1920,1080");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--unhandledPromptBehavior=accept");
        options.addArguments("use-fake-device-for-media-stream");
        options.addArguments("use-fake-ui-for-media-stream");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        options.addArguments("disable-infobars");
//        options.setExperimentalOption("geolocation", false);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(capabilities);
//        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
//        driver.manage().deleteAllCookies();

        return driver;
    }

    /*
    public static WebDriver initializeDriver(String browser) {
        WebDriver driver;
        switch (browser) {

            case BROWSER_CHROME: {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--headless");
                options.addArguments("--incognito");
                options.addArguments("start-maximized");
                options.addArguments("window-size=1920,1080");
                options.addArguments("--allow-running-insecure-content");
                options.addArguments("--ignore-certificate-errors");
                options.addArguments("--unhandledPromptBehavior=accept");
                options.addArguments("use-fake-device-for-media-stream");
                options.addArguments("use-fake-ui-for-media-stream");
                options.addArguments("--remote-allow-origins=*");
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                options.merge(capabilities);
                driver = new ChromeDriver(options);
                driver.manage().deleteAllCookies();
                break;
            }
            case BROWSER_FIREFOX: {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            }
            case BROWSER_SAFARI: {
                driver = new SafariDriver();
                break;
            }
            case BROWSER_EDGE: {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            }
            default:
                throw new IllegalStateException("INVALID BROWSER: " + browser);
        }
        driver.manage().window().maximize();
        return driver;
    }
*/
    public void  waitForElementClickable(Long timeUnit, WebElement element) {
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeUnit));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void poll(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickonAction(WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }

    public void  waitForPageToLoad() {
        new WebDriverWait(driver,Duration.ofSeconds(60))
                .until(driver ->((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"));
    }


    public WebDriverWait getExplicitWait(){
        if(null!=wait)
            return wait;
        else
            return new WebDriverWait(driver,Duration.ofSeconds(60));
    }

    public static String takesScreenhot() {
        String path="";
        String encodedBase64 = null;
        FileInputStream fileInputStreamReader = null;
        try {
            File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            System.out.println(System.getProperty("user.dir"));
            path=System.getProperty("user.dir") + "//ExtentReports//Screenshots//"
                    + new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) + ".png";
            File destinationPath = new File(path);
            Files.copy(sourcePath, destinationPath);

            fileInputStreamReader = new FileInputStream(sourcePath);
            byte[] bytes = new byte[(int)sourcePath.length()];
            fileInputStreamReader.read(bytes);
            encodedBase64 = new String(Base64.encodeBase64(bytes));

        } catch (IOException e) {
            System.out.println("Error occured while execution of takescreenshot object");
            System.out.println("Exception:"+e.getLocalizedMessage());
        }
//        new Driver().closeDriverInstance();
        return "data:image/png;base64,"+encodedBase64;
    }

    public void closeDriverInstance(){
        driver.close();
        driver.quit();
        driver=null;
    }

    public void ScrollToElement(WebElement webElement) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", webElement);
    }

    public List<WebElement> waitForElementsVisibility(List<WebElement> element) {
        fluentWait.until(ExpectedConditions.visibilityOfAllElements(element));
        System.out.println("Element visibility check done " + element.toString());
        return wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public WebElement waitForElementVisibility(WebElement element) {
        fluentWait.until(ExpectedConditions.visibilityOf(element));
        System.out.println("Element visibility check done " + element.toString());
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}
