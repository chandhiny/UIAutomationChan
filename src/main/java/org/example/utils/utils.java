package org.example.utils;

import com.google.common.io.Files;
import org.apache.commons.codec.binary.Base64;
import org.example.web.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class utils extends Driver {

    private static WebDriverWait wait;
    public Wait fluentWait;
    public void  waitForElementClickable(Long timeUnit, WebElement element) {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(timeUnit));
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

    public String takesScreenhot() {
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

    public void writeToFile(List<String> values){
        String path = System.getProperty("user.dir") + "//ExtentReports//" + "output.txt";
        // Write the values to a text file
        try {
            FileWriter writer = new FileWriter(path);
            for (String value : values) {
                writer.write(value + "\n");
            }
            writer.close();
            System.out.println("Data written to file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
            e.printStackTrace();
        }
    }

}
