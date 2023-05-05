package org.example.testcases;

import org.example.pages.FirstPage;
import org.testng.annotations.Test;
public class TestCheck {

    //Validate A Class models price are between £15,000 and £60,000

    //get the url to hit
    @Test
    public void Validation() throws InterruptedException {
        FirstPage fp = new FirstPage("https://www.mercedes-benz.co.uk/?group=all&subgroup=see-all&view=BODYTYPE");
        fp.TestFlow();
    }

}

