package com.rferl.SWEN;

import com.rferl.SWEN.service.ScanRFERL4MetaService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.rferl.SWEN.service.RelationsService.calculateSimilarityBetweenSentences;


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

    @Test
    public void testSimilarity() {
        printSimilarity("", "");
        printSimilarity("1234567890", "1");
        printSimilarity("1234567890", "123");
        printSimilarity("1234567890", "1234567");
        printSimilarity("1234567890", "1234567890");
        printSimilarity("1234567890", "1234567980");
        printSimilarity("47/2010", "472010");
        printSimilarity("47/2010", "472011");
        printSimilarity("47/2010", "AB.CDEF");
        printSimilarity("47/2010", "4B.CDEFG");
        printSimilarity("47/2010", "AB.CDEFG");
        printSimilarity("The quick fox jumped", "The fox jumped");
        printSimilarity("The quick fox jumped", "The fox");
        printSimilarity("The quick fox jumped", "The quick fox jumped off the balcany");
        printSimilarity("kitten", "sitting");

        //
        printSimilarity("U.S. Lawmakers Condemn Russia's Election 'Failure,' Crackdown Ahead Of Votes", "U.S., Russian Officials To Discuss Counterterrorism In Vienna Next Week");

    }


    public static void printSimilarity(String s, String t) {
        System.out.println(String.format(
                "%.3f is the similarity between \"%s\" and \"%s\"", calculateSimilarityBetweenSentences(s, t), s, t));
    }


    @Test
    public void writeOnDB() throws SQLException {
        //  Database credentials
        final String DB_URL = "jdbc:h2:C:/data/sample";
        final String USER = "sa";
        final String PASS = "";



        Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement st = connection.prepareStatement(
                "INSERT INTO ARTICLES (url) VALUES(?);");
        st.setString(1, "todo.getUser()");
        st.execute();
        st.close();


    }
}
