package org.example.pages;

import org.example.utils.utils;
import org.example.web.Driver;
import org.openqa.selenium.*;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.w3c.dom.html.HTMLInputElement;

import javax.xml.xpath.XPath;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FirstPage extends Driver {

    // has all the steps need for validating the test case
    utils utils = new utils();
    WebElement shadowHost;
    SearchContext shadowRoot;
    WebElement shadowTreeElement;
    @FindBy(css ="")
    private WebElement TestButton;

    public WebElement getTestButton() {
        return TestButton;
    }


    public FirstPage(String url){
        getDriver().get(url);
        getDriver().manage().window().maximize();
        utils.waitForPageToLoad();
        PageFactory.initElements(getDriver(), this);
    }

    public SearchContext getShadowRoot(WebDriver driver, WebElement shadowHost) {

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        return (SearchContext) javascriptExecutor.executeScript("return arguments[0].shadowRoot", shadowHost);
    }

    public void getElementandClick(By by, String path){
        WebElement element = driver.findElement(by); //gets the element which acts as shadow host
        SearchContext context = element.getShadowRoot(); // gets the shadow root element of the shadow host
        WebElement requiredButton = context.findElement(By.cssSelector(path)); // gets the required element which is present inside the shadow DOM
        utils.waitForElementClickable(5000L,requiredButton); // waiting for the element to be clickable
        requiredButton.click();
    }
    public void TestFlow() throws InterruptedException {

        utils.poll(5000);

        getElementandClick(By.cssSelector("cmm-cookie-banner[class='hydrated']"),"button[class='wb-button wb-button--primary wb-button--small wb-button--accept-all']");

        getElementandClick(By.cssSelector("owc-header[class='webcomponent aem-GridColumn aem-GridColumn--default--12']"),"li[class='owc-header-navigation-topic owc-header-navigation-topic--desktop-nav owc-header-navigation-topic__model-flyout']");


        shadowHost = getDriver().findElement(By.cssSelector("vmos-flyout[class='webcomponent webcomponent-nested']"));
        shadowRoot = shadowHost.getShadowRoot();
        shadowTreeElement = shadowRoot.findElement(By.cssSelector("div[id='app-vue']"));
        utils.ScrollToElement(shadowTreeElement);
        WebElement sf = shadowTreeElement.findElement(By.cssSelector("wb-icon[name='sportstourer']"));
        utils.waitForElementClickable(5000L,sf);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", sf);

        WebElement sf1 = shadowTreeElement.findElement(By.cssSelector("li[class='@vmos-vmos-flyout-flyout-group__QE2pr @vmos-vmos-flyout-flyout-group--subgroup__EVJA6']"));
        WebElement sf2 = sf1.findElement(By.cssSelector("a[class='@vmos-vmos-flyout-flyout-group-item__link__NeNLP'][href='https://www.mercedes-benz.co.uk/passengercars/models/hatchback/a-class/overview.html']"));
        utils.waitForElementClickable(5000L,sf2);
        sf2.click();


        shadowHost = getDriver().findElement(By.cssSelector("owc-next-best-activities[class='webcomponent aem-GridColumn aem-GridColumn--default--12'][component-id='e4cc7501d9dad127eff448da630cc997']"));
        shadowRoot = shadowHost.getShadowRoot();
        WebElement buildYourCarButton = shadowRoot.findElement(By.cssSelector("a[class='owc-nba-item'][href='https://www.mercedes-benz.co.uk/passengercars/mercedes-benz-cars/car-configurator.html/motorization/CCci/GB/en/bm/1770122,1770512,1770542,1770842,1770872']"));
        Thread.sleep(5000);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeSelected(buildYourCarButton));
        utils.ScrollToElement(buildYourCarButton);
//        if (buildYourCarButton.isDisplayed()) {
        utils.waitForElementClickable(10000L, buildYourCarButton);
            executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", buildYourCarButton);
//        }

        Thread.sleep(5000);

        WebElement fh = getDriver().findElement(By.cssSelector("owcc-car-configurator[class='webcomponent aem-GridColumn aem-GridColumn--default--12']"));
        shadowRoot = fh.getShadowRoot();
        shadowHost = shadowRoot.findElement(By.cssSelector("ccwb-multi-select[class='cc-motorization-filters-primary-filters--multi-select wb-multi-select hydrated']"));
        SearchContext shadowRootInternal = shadowHost.getShadowRoot();
        WebElement chooseFilterButton = shadowRootInternal.findElement(By.cssSelector("button[class='button'][aria-label='Fuel type, selected 0 items']"));
        utils.ScrollToElement(chooseFilterButton);
        executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", chooseFilterButton);

        WebElement chooseVal = shadowRoot.findElement(By.cssSelector("ccwb-checkbox[class='ng-untouched ng-pristine ng-valid ng-star-inserted wb-checkbox hydrated']"));
        utils.waitForElementClickable(10000L, chooseVal);
        executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", chooseVal);

//        WebElement element = shadowRoot.findElement(By.cssSelector("ccwb-checkbox[class='ng-valid ng-star-inserted wb-checkbox hydrated ng-dirty ng-touched']"));
//        SearchContext context = element.getShadowRoot();
//        WebElement dieselCheckbox = context.findElement(By.cssSelector("input[id='input-j6il41sc4'][name='Diesel']"));
//        executor = (JavascriptExecutor)driver;
//        executor.executeScript("arguments[0].click();", dieselCheckbox);

        Thread.sleep(3000);

        List<WebElement> cards = shadowRoot.findElements(By.cssSelector("span[class='cc-motorization-header__price--with-environmental-hint']"));
//        waitForElementsVisibility(cards);

        List<Double> carPrices = new ArrayList<>();
        for(WebElement we : cards) {
            System.out.println("cars value: " + we.getText());
            String newString = we.getText().substring(1);
            newString = newString.replace(",", ""); // Remove the comma separator

            double myDouble = Double.parseDouble(newString);

            //checking whether the obtained prices are within the range or not
            Assert.assertTrue(myDouble >= 15000 && myDouble <= 65000);
            carPrices.add(myDouble);
        }
        double highest = Collections.max(carPrices);
        double lowest = Collections.min(carPrices);

        System.out.println("Highest value: " + highest);
        System.out.println("Lowest value: " + lowest);

        List<String> values = new ArrayList<>();
        values.add("Highest value: " + highest);
        values.add("Lowest value: " + lowest);

        //printing the highest and the lowest value obtained into a text file
        utils.writeToFile(values);

        //taking the screenshot of the latest screen
        utils.takesScreenhot();

        utils.poll(5000);
        utils.closeDriverInstance();
        
//        getDriver().close();
//        getDriver().quit();
    }

}

