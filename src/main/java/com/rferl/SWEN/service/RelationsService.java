package com.rferl.SWEN.service;

import com.rferl.SWEN.model.Relation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RelationsService {
    public String add(Relation relation) {
        String currentTitle = retrieveTitle(relation.getUrlCurrent());
        String otherTitle = retrieveTitle(relation.getUrlOther());
        calculateSImilarity(currentTitle, otherTitle);
        return "";
    }

    private String retrieveTitle(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements newsHeadlines = doc.select("h1");
            return newsHeadlines.size() > 0 ? newsHeadlines.get(0).text() : "";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private double calculateSImilarity(String str1, String str2)
    {
        return 0.0;
    }
}
