package com.rferl.SWEN.controller;

import com.google.gson.Gson;
import com.rferl.SWEN.model.Article;
import com.rferl.SWEN.model.Relation;
import com.rferl.SWEN.service.CheckService;
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

/*

        // add in db
        //  Database credentials
        final String DB_URL = "jdbc:h2:C:/data/sample";
        final String USER = "sa";
        final String PASS = "";



        Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement st = connection.prepareStatement(
                "INSERT INTO ARTICLES (url) VALUES(?);");
        st.setString(1, "todo.getUser()");
        st.execute();
        st.close();

        connection.close();

 */

        return checkService.check(art.getUrl()) ;
    }

    @PostMapping(value = "/add", produces = "application/json")
    public String add(@RequestBody String body) {
        Gson g = new Gson();
        Relation relation = g.fromJson(body, Relation.class);
        return relationsService.add(relation);
    }
}
