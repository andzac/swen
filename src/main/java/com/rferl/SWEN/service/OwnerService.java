package com.rferl.SWEN.service;

import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Service
public class OwnerService {

    private Map<String, List<String>> owners = new HashMap<>();

    public OwnerService() {
        owners.put("www.dailymail.co.uk", Arrays.asList("Daily Mail and General Trust plc", "Jonathan Harmsworth (shareholder)"));
        owners.put("www.newspunch.com", Arrays.asList("founders: Sean Adl", "Tabatabai, Sinclair Treadway"));
        owners.put("www.channel4.com", Arrays.asList("Channel 4 Television Coorporation", "UK Government Investments"));
        owners.put("www.dailystar.co.uk", Collections.singletonList("Reach plc"));
        owners.put("www.nytimes.com", Arrays.asList("NYT Company", "Sulzberger"));
    }


    public List<String> getOwners(String strUrl) {
        try {
            URL url = new URL(strUrl);
            return Optional.ofNullable(owners.get(url.getHost()))
                    .map(Object::toString)
                    .map(s -> s.split("-"))
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
