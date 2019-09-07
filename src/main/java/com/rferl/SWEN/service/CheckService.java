package com.rferl.SWEN.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rferl.SWEN.model.ArticleCheckResult;
import com.rferl.SWEN.model.CheckList;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CheckService {

    @Autowired
    private RelationsService relationsService;
    private ObjectMapper mapper = new ObjectMapper();

    private Pattern dateTimePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}");

    public String check(String url) {
        try {
            String content = getUrlContents(url);
            return mapper.writeValueAsString(createCheckResult(url, content));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getUrlContents(String strUrl) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(strUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    private ArticleCheckResult createCheckResult(String url, String content) {
        try {
            CheckList checkList = new CheckList(checkIfSiteIsFake(url),
                    checkIfPublishedInFirstApril(content));
            return new ArticleCheckResult(checkList,
                    relationsService.retrievePositives(url),
                    relationsService.retrieveNegatives(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private boolean checkIfSiteIsFake(String url) throws IOException {

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        request.setHeader("X-RapidAPI-Host", "adverifai-api.p.rapidapi.com");
        request.setHeader("X-RapidAPI-Key", "10441ce356msh72b539394e91da7p142221jsnf18818f08494");

        ResponseHandler<String> handler = new BasicResponseHandler();
        return !client.execute(request, handler).isEmpty();
    }

    private boolean checkIfPublishedInFirstApril(String content) {
        Matcher matcher = dateTimePattern.matcher(content);
        if (matcher.find()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(matcher.group(), formatter);
            return dateTime.getDayOfMonth() == 1 && Month.APRIL.equals(dateTime.getMonth());
        }
        return false;
    }
}
