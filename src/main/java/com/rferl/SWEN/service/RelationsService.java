package com.rferl.SWEN.service;

import com.rferl.SWEN.model.Reference;
import com.rferl.SWEN.model.Relation;
import com.rferl.SWEN.model.TitleAndUrl;
import com.rferl.SWEN.repository.ReferenceRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelationsService {
    @Autowired
    private ReferenceRepository repository;
    @Autowired
    private CheckService checkService;

    List<TitleAndUrl> listPositive = new ArrayList<>();
    List<TitleAndUrl> listNegative = new ArrayList<>();

    public String add(Relation relation) {
        try {
            createReference(relation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return checkService.check(relation.getUrlCurrent());
    }

    public List<TitleAndUrl> retrievePositives(String url) throws SQLException {
        return repository.fullfillPositive(url).stream().map(it -> new TitleAndUrl(retrieveTitle(it), it)).collect(Collectors.toList());
    }

    public List<TitleAndUrl> retrieveNegatives(String url) throws SQLException {
        return repository.fullfillNegative(url).stream().map(it -> new TitleAndUrl(retrieveTitle(it), it)).collect(Collectors.toList());
    }

    private void createReference(Relation relation) throws SQLException {
        String currentUrl = relation.getUrlCurrent();
        String currentTitle = retrieveTitle(currentUrl);
        String otherUrl = relation.getUrlOther();
        String otherTitle = retrieveTitle(otherUrl);
        boolean type = relation.getRelation();
        double similarity = calculateSimilarityBetweenSentences(currentTitle, otherTitle);

        if (similarity * 100 >= 0) {
            Reference currentReference = new Reference(currentUrl, otherUrl, relation.getUrlCurrent(), type, similarity);
            Reference otherReference = new Reference(otherUrl, currentUrl, relation.getUrlOther(), type, similarity);
            int currentId = repository.add(currentReference);
            int otherId = repository.add(otherReference);
        }


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

    public static double calculateSimilarityBetweenSentences(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) { // longer should always have greater length
            longer = s2;
            shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) {
            return 1.0; /* both strings are zero length */
        }
        /* // If you have StringUtils, you can use it to calculate the edit distance:
        return (longerLength - StringUtils.getLevenshteinDistance(longer, shorter)) /
                                                             (double) longerLength; */
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
    }


    // Example implementation of the Levenshtein Edit Distance
    // See http://r...content-available-to-author-only...e.org/wiki/Levenshtein_distance#Java
    public static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }
}
