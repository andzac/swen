package com.rferl.SWEN.service;

import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class JokeSitesService {
    private List<String> jokeSites = new ArrayList<>();

    public JokeSitesService() {
        jokeSites.add("https://www.theonion.com/");
        jokeSites.add("https://www.clickhole.com/");
        jokeSites.add("https://www.thepoke.co.uk/");
        jokeSites.add("https://www.thedailymash.co.uk/");
        jokeSites.add("http://www.fakingnews.com/");
        jokeSites.add("http://waterfordwhispersnews.com/");
        jokeSites.add("https://zdoggmd.com/");
        jokeSites.add("https://newsthump.com/");
        jokeSites.add("https://www.betootaadvocate.com/");
        jokeSites.add("https://www.thebeaverton.com/");
        jokeSites.add("https://www.dailysquib.co.uk/");
        jokeSites.add("https://www.therisingwasabi.com/");
        jokeSites.add("https://gomerblog.com/");
        jokeSites.add("https://www.duffelblog.com/");
        jokeSites.add("https://www.huzlers.com/");
        jokeSites.add("https://chaser.com.au/");
        jokeSites.add("http://www.theshovel.com.au/");
        jokeSites.add("https://wokennews.com/");
        jokeSites.add("http://www.breakingburgh.com/");
    }

    public boolean checkIsDefined(String url) {
        try {
            return jokeSites.contains(new URL(url).getHost());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
