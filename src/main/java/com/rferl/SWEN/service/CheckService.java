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

import java.io.IOException;

@Service
public class CheckService {

    @Autowired
    private RelationsService relationsService;
    private ObjectMapper mapper = new ObjectMapper();

    public String check(String url) {
        try {
            CheckList checkList = new CheckList(checkIfSiteIsFake(url));
            ArticleCheckResult articleCheckResult = new ArticleCheckResult(checkList,
                    relationsService.retrievePositives(url),
                    relationsService.retrieveNegatives(url));
            return mapper.writeValueAsString(articleCheckResult);
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
}
