package org.example.testcases;

import org.example.pages.FirstPage;
import org.example.pages.PiaWebPage;
import org.testng.annotations.Test;
public class TestCheck {

    //Validate A Class models price are between £15,000 and £60,000


    @Test
    public void Validation() throws InterruptedException {
        //get the url to hit
        FirstPage fp = new FirstPage("https://www.mercedes-benz.co.uk/?group=all&subgroup=see-all&view=BODYTYPE");
        //calling the method where the given testcase is validated
        fp.TestFlow();
    }

    @Test
    public void WebPageValidation(){
        PiaWebPage piaWebPage = new PiaWebPage("https://www.dymatrix.de/");
        piaWebPage.testFlow();
    }
}

