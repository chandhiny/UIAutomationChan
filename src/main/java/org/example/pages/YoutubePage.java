package org.example.pages;

import org.example.utils.utils;
import org.example.web.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class YoutubePage extends Driver {

    utils utils = new utils();

    @FindBy(xpath = "//ytd-button-renderer[@class='style-scope ytd-masthead']//a[@aria-label='Sign in']")
    public WebElement SignInButton;

    @FindBy(id = "identifierId")
    public WebElement LoginId;

    @FindBy(xpath="//*[@id=\"identifierNext\"]/div/button")
    public WebElement LoginNextButton;


    public WebElement getSignInButton(){
        return  SignInButton;
    }
    public WebElement getLoginId(){
        return  LoginId;
    }
    public WebElement getLoginNextButton(){
        return  LoginNextButton;
    }

    public YoutubePage(String url){
        getDriver().get(url);
        getDriver().manage().window().maximize();
        utils.waitForPageToLoad();
        PageFactory.initElements(getDriver(), this);
    }

    By password = By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input");
    public void testFlow(){
        utils.waitForPageToLoad();
        getSignInButton().click();
        getLoginId().sendKeys("chandhinyg@gmail.com");
        getLoginNextButton().click();
//        utils.getExplicitWait();
        getDriver().findElement(password).sendKeys("Momdad@143");
        getDriver().findElement(By.className("VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc LQeN7 BqKGqe Jskylb TrZEUc lw1w4b")).click();
        getDriver().findElement(By.xpath("//form[@id='search-form']//div[@id='container']")).sendKeys("selenium");

        utils.closeDriverInstance();

    }
}
