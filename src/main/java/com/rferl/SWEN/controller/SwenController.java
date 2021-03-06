package com.rferl.SWEN.controller;

import com.google.gson.Gson;
import com.rferl.SWEN.model.Article;
import com.rferl.SWEN.model.Relation;
import com.rferl.SWEN.service.CheckService;
import com.rferl.SWEN.service.DBService;
import com.rferl.SWEN.service.RelationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class SwenController {

    @Autowired
    private CheckService checkService;
    @Autowired
    private RelationsService relationsService;

    @PostMapping(value="/check", produces = "application/json")
    public String check(@RequestBody String article) throws SQLException {
        Gson g = new Gson();
        Article art = g.fromJson(article, Article.class);

        // add in db
        String result = checkService.check(art.getUrl());
        DBService.addArticle(art);
        return result ;
    }

    @PostMapping(value = "/add", produces = "application/json")
    public String add(@RequestBody String body) throws SQLException {
        Gson g = new Gson();
        Relation relation = g.fromJson(body, Relation.class);
        return relationsService.add(relation);
    }
}
