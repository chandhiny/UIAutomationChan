package org.example.pages;

import org.example.utils.utils;
import org.example.web.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class AmazonPage extends Driver {

    org.example.utils.utils utils = new utils();
    Actions ac = new Actions(getDriver());
    public AmazonPage(String url){
        getDriver().get(url);
        getDriver().manage().window().maximize();
        utils.waitForPageToLoad();
        PageFactory.initElements(getDriver(), this);
    }
    public void testAmazonWebsite() {

        boolean logoFound = getDriver().findElement(By.xpath("//a[@id='nav-logo-sprites']")).isDisplayed();
        Assert.assertTrue(logoFound);

        getDriver().findElement(By.linkText("Best Sellers")).click();
//        getDriver().findElement(By.cssSelector(".nav-a[href='/gp/bestsellers/?ref_=nav_cs_bestsellers']")).click();

        //handling multiple tabs
        String mainWindow = getDriver().getWindowHandle();
        System.out.println(mainWindow);

        WebElement firstBestSeller = getDriver().findElement(By.xpath("(//a[@role='link'])[1]"));
        String href = firstBestSeller.getAttribute("href");
        // Print the href value
        System.out.println("The href value is: " + href);

        ((JavascriptExecutor) getDriver()).executeScript("window.open('" + href + "', '_blank');");

        // Switch to the new tab
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(1)); // Switch to the new tab

        // Print the title of the new tab
        System.out.println("New Tab Title: " + getDriver().getTitle());


        //add to cart and view it
        ac.moveToElement(getDriver().findElement(By.xpath("//input[@id='add-to-cart-button']")))
        .click().perform();

        //implicit wait
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        getDriver().findElement(By.xpath("//a[@id='nav-link-accountList']")).click();

        //login
        login("chandhinygopalakrishnan@gmail.com","Momdad@143","123123");
        //error message to be received
        boolean errorPresent = getDriver().findElement(By.xpath("//div[@class='a-box-inner a-alert-container']")).isDisplayed();
        Assert.assertTrue(errorPresent);


        //getDriver().navigate().back();


       // getDriver().findElement(By.xpath("//a[@id='nav-cart']"));
        utils.closeDriverInstance();

    }

    public void login(String email, String pwd, String tfa){
        getDriver().findElement(By.xpath("//input[@id='ap_email_login' or @id='ap_email']"))
                .sendKeys(email);
        getDriver().findElement(By.xpath("//*[@id=\"continue\"]/span/input")).click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='ap_password']")));
            // Perform actions on the element
            element.sendKeys(pwd);
        } catch (NoSuchElementException e) {
            System.out.println("Element not found even after waiting");
        }
        //if visible click it
        //getDriver().findElement(By.xpath("//*[@id=\"authportal-main-section\"]/div[2]/div/div[2]/div/form/div/div[2]/div/div/label/div/label/input")).click();
        getDriver().findElement(By.xpath("//*[@id=\"signInSubmit\"]")).click();
        getDriver().findElement(By.xpath("//*[@id=\"auth-mfa-remember-device\"]")).click();
        getDriver().findElement(By.xpath("//*[@id=\"auth-mfa-otpcode\"]"))
                .sendKeys(tfa);
        getDriver().findElement(By.xpath("//*[@id=\"auth-signin-button\"]")).click();
    }
}
