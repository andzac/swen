package com.rferl.SWEN;

import com.rferl.SWEN.service.ScanRFERL4MetaService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SwenApplicationTests {

    @Test
    public void checkMetadata() {
        List<String> urls = new ArrayList<String>();
        urls.add("https://www.rferl.org/a/iran-confirms-to-eu-latest-step-away-from-nuclear-deal/30149693.html");
        urls.add("https://www.rferl.org/a/u-s-lawmakers-condemn-russia-s-election-failure-crackdown-ahead-of-votes/30149641.html");
        urls.add("https://www.rferl.org/a/czech-republic-soviet-history-konev/30150467.html");
        urls.add("https://www.rferl.org/a/the-hijab-debate-intensifies-as-school-starts-in-kazakhstan/30148088.html");
        ScanRFERL4MetaService scanRFERL4MetaService = new ScanRFERL4MetaService();
        scanRFERL4MetaService.checkUrlForRFLRL(urls);
        Assert.assertTrue("Should return true: ", scanRFERL4MetaService.checkUrlForRFLRL(urls));

    }


}
