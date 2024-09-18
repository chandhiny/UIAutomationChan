package org.example.testcases;

import org.example.pages.PiaWebPage;
import org.example.pages.YoutubePage;
import org.testng.annotations.Test;

public class YoutubeTest {

    @Test
    public void WebPageValidation(){
        YoutubePage youtubePage = new YoutubePage("https://www.youtube.com");
        youtubePage.testFlow();
    }
}
