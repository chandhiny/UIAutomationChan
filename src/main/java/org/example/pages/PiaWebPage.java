package org.example.pages;

import org.example.utils.utils;
import org.example.web.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.TestNGUtils;
import org.testng.internal.Utils;

public class PiaWebPage extends Driver {

   utils utils = new utils();

   @FindBy(css = "a[title=\"Customer Experience Management & Marketing Automation mit DYMATRIX\"]")
   public WebElement productRef;

   public WebElement getProductRef(){
       return  productRef;
   }

    public PiaWebPage(String url){
        getDriver().get(url);
        getDriver().manage().window().maximize();
        utils.waitForPageToLoad();
        PageFactory.initElements(getDriver(), this);
    }


    public void testFlow(){
       if(getProductRef().isDisplayed()){
           utils.clickonAction(getProductRef()); // waiting for the element to be clickable

           utils.closeDriverInstance();
       }
    }

}
