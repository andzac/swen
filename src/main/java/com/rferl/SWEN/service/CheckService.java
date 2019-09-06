package com.rferl.SWEN.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rferl.SWEN.model.ArticleCheckResult;
import org.springframework.stereotype.Service;

@Service
public class CheckService {

    private ObjectMapper mapper = new ObjectMapper();

    public String check(String article) {
        try {
            return mapper.writeValueAsString(new ArticleCheckResult());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
