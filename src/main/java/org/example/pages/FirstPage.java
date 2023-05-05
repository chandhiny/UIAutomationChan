package org.example.pages;

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

    @FindBy(css ="")
    private WebElement TestButton;

    public WebElement getTestButton() {
        return TestButton;
    }


    public FirstPage(String url){
        getDriver().get(url);
        getDriver().manage().window().maximize();
        waitForPageToLoad();
        PageFactory.initElements(getDriver(), this);
    }

    public SearchContext getShadowRoot(WebDriver driver, WebElement shadowHost) {

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        return (SearchContext) javascriptExecutor.executeScript("return arguments[0].shadowRoot", shadowHost);
    }
    public void TestFlow() throws InterruptedException {

        Thread.sleep(5000);
        WebElement element = driver.findElement(By.cssSelector("cmm-cookie-banner[class='hydrated']"));
        SearchContext context = element.getShadowRoot();
        WebElement cookieAcceptAll = context.findElement(By.cssSelector("button[class='wb-button wb-button--primary wb-button--small wb-button--accept-all']"));
        cookieAcceptAll.click();


        WebElement shadowHost = driver.findElement(By.cssSelector("owc-header[class='webcomponent aem-GridColumn aem-GridColumn--default--12']"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        WebElement shadowTreeElement = shadowRoot.findElement(By.cssSelector("li[class='owc-header-navigation-topic owc-header-navigation-topic--desktop-nav owc-header-navigation-topic__model-flyout']"));
        waitForElementClickable(5000L,shadowTreeElement);
        shadowTreeElement.click();

        shadowHost = getDriver().findElement(By.cssSelector("vmos-flyout[class='webcomponent webcomponent-nested']"));
        shadowRoot = shadowHost.getShadowRoot();
        shadowTreeElement = shadowRoot.findElement(By.cssSelector("div[id='app-vue']"));
        ScrollToElement(shadowTreeElement);
        WebElement sf = shadowTreeElement.findElement(By.cssSelector("wb-icon[name='sportstourer']"));
        waitForElementClickable(5000L,sf);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", sf);

        WebElement sf1 = shadowTreeElement.findElement(By.cssSelector("li[class='@vmos-vmos-flyout-flyout-group__QE2pr @vmos-vmos-flyout-flyout-group--subgroup__EVJA6']"));
        WebElement sf2 = sf1.findElement(By.cssSelector("a[class='@vmos-vmos-flyout-flyout-group-item__link__NeNLP'][href='https://www.mercedes-benz.co.uk/passengercars/models/hatchback/a-class/overview.html']"));
        waitForElementClickable(5000L,sf2);
        sf2.click();


        shadowHost = getDriver().findElement(By.cssSelector("owc-next-best-activities[class='webcomponent aem-GridColumn aem-GridColumn--default--12'][component-id='e4cc7501d9dad127eff448da630cc997']"));
        shadowRoot = shadowHost.getShadowRoot();
        WebElement buildYourCarButton = shadowRoot.findElement(By.cssSelector("a[class='owc-nba-item'][href='https://www.mercedes-benz.co.uk/passengercars/mercedes-benz-cars/car-configurator.html/motorization/CCci/GB/en/bm/1770122,1770512,1770542,1770842,1770872']"));
        Thread.sleep(5000);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeSelected(buildYourCarButton));
        ScrollToElement(buildYourCarButton);
//        if (buildYourCarButton.isDisplayed()) {
            waitForElementClickable(10000L, buildYourCarButton);
            executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", buildYourCarButton);
//        }

        Thread.sleep(5000);

        WebElement fh = getDriver().findElement(By.cssSelector("owcc-car-configurator[class='webcomponent aem-GridColumn aem-GridColumn--default--12']"));
        shadowRoot = fh.getShadowRoot();
        shadowHost = shadowRoot.findElement(By.cssSelector("ccwb-multi-select[class='cc-motorization-filters-primary-filters--multi-select wb-multi-select hydrated']"));
        SearchContext shadowRootInternal = shadowHost.getShadowRoot();
        WebElement chooseFilterButton = shadowRootInternal.findElement(By.cssSelector("button[class='button'][aria-label='Fuel type, selected 0 items']"));
        ScrollToElement(chooseFilterButton);


        WebElement chooseVal = shadowRoot.findElement(By.cssSelector("ccwb-checkbox[class='ng-untouched ng-pristine ng-valid ng-star-inserted wb-checkbox hydrated']"));
        waitForElementClickable(10000L, chooseVal);
        executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", chooseVal);


        Thread.sleep(5000);

        List<WebElement> cards = shadowRoot.findElements(By.cssSelector("span[class='cc-motorization-header__price--with-environmental-hint']"));
//        waitForElementsVisibility(cards);


        List<Double> carPrices = new ArrayList<>();
        for(WebElement we : cards) {
            System.out.println("cars value: " + we.getText());
            double myDouble = Double.parseDouble(we.getText());
            Assert.assertTrue(myDouble >= 15000 && myDouble <= 65000);
            carPrices.add(myDouble);
        }
        double highest = Collections.max(carPrices);
        double lowest = Collections.min(carPrices);

        System.out.println("Highest value: " + highest);
        System.out.println("Lowest value: " + lowest);

        Thread.sleep(5000);

        List<String> values = new ArrayList<>();
        values.add("Highest value: " + highest);
        values.add("Lowest value: " + lowest);


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

        takesScreenhot();

        Thread.sleep(5000);
        closeDriverInstance();
        
//        getDriver().close();
//        getDriver().quit();
    }

}

