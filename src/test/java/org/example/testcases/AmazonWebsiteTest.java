package org.example.testcases;

import org.example.pages.AmazonPage;
import org.example.pages.KristalWebsite;
import org.testng.annotations.Test;

public class AmazonWebsiteTest {

    @Test
    public void TestWebsite() throws InterruptedException {
        AmazonPage kw = new AmazonPage("https://www.amazon.in/");
        kw.testAmazonWebsite();
    }
}
