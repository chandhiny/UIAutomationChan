package org.example.pages;

import org.example.utils.utils;
import org.example.web.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
public class KristalWebsite extends Driver {

    utils utils = new utils();
    Actions ac = new Actions(getDriver());
    public KristalWebsite(String url){
        getDriver().get(url);
        getDriver().manage().window().maximize();
        utils.waitForPageToLoad();
        PageFactory.initElements(getDriver(), this);
    }

    public void testWebsite() throws InterruptedException {
        By countryDropDown = By.xpath("//input[@id='countryDropdown']");
        ac.moveToElement(getDriver().findElement(countryDropDown)).click();
        WebElement countryname = getDriver().findElement(By.cssSelector("div[class='div-block-495'] li:nth-child(3)"));
        ac.moveToElement(countryname).click().build().perform();
        utils.waitForPageToLoad();
        
        getDriver().findElement(By.xpath("//div[@id='kristalAdvisoryBannerClose']")).click();

        WebElement investors= getDriver().findElement(By.cssSelector("#w-dropdown-toggle-4"));
        ac.moveToElement(investors).perform();
        Thread.sleep(5000);

        WebElement investmentProduct= getDriver().findElement(By.xpath("//nav[@id='w-dropdown-list-4'] //a[@id='investment-click']"));
        ac.moveToElement(investmentProduct).perform();
        Thread.sleep(5000);

        ac.click(investmentProduct).perform();

        utils.getExplicitWait();

        getDriver().findElement(By.xpath("//a[@id='signup-click']")).click();


    }
}
