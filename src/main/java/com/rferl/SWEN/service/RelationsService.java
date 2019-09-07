package com.rferl.SWEN.service;

import com.rferl.SWEN.model.Reference;
import com.rferl.SWEN.model.Relation;
import com.rferl.SWEN.repository.ReferenceRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class RelationsService {
    @Autowired
    private ReferenceRepository repository;
    @Autowired
    private CheckService checkService;

    public String add(Relation relation) {
        Reference reference = createReference(relation);
        repository.add(reference);
        return checkService.check(relation.getUrlCurrent());
    }

    public List<String> retrievePositives(String url) {
        return Collections.emptyList();
    }

    public List<String> retrieveNegatives(String url) {
        return Collections.emptyList();
    }

    private Reference createReference(Relation relation) {
        String currentTitle = retrieveTitle(relation.getUrlCurrent());
        String otherTitle = retrieveTitle(relation.getUrlOther());
        boolean type = relation.getRelation();
        double similarity = calculateSimilarityBetweenSentences(currentTitle, otherTitle);
        return new Reference(currentTitle, otherTitle, type, similarity);
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
