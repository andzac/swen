package com.rferl.SWEN.controller;

import com.google.gson.Gson;
import com.rferl.SWEN.model.Article;
import com.rferl.SWEN.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SwenController {

    @Autowired
    private CheckService checkService;

    @PostMapping("/check")
    public String check(@RequestBody String article) throws IOException {

        Gson g = new Gson();
        Article art = g.fromJson(article, Article.class);
        return checkService.check(art.getUrl());
    }



}
