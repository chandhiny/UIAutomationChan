package org.example.testcases;

import org.example.pages.KristalWebsite;
import org.testng.annotations.Test;

public class KristalWebsiteCheck {

    @Test
    public void TestWebsite() throws InterruptedException {
        KristalWebsite kw = new KristalWebsite("https://www.kristal.ai/");
        kw.testWebsite();
    }
}
