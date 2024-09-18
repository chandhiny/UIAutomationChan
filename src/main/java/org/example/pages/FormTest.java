package org.example.pages;

import org.example.web.Driver;
import org.openqa.selenium.By;

public class FormTest extends Driver {


    //url -- https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
    
    public void firstPage(){
        getDriver().findElement(By.xpath("//input[@placeholder='Username']"))
                .sendKeys("Admin");
        getDriver().findElement(By.xpath("//input[@placeholder='Password']"))
                .sendKeys("admin123");
        getDriver().findElement(By.xpath("//button[normalize-space()='Login']")).click();

        getDriver().findElement(By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']")).click();

    }
}
