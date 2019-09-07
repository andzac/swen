package com.rferl.SWEN.service;

import com.rferl.SWEN.model.Article;
import com.rferl.SWEN.model.Reference;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBService {

    private static final String DB_URL = "jdbc:h2:C:/data/sample";
    private static final String USER = "sa";
    private static final String PASS = "";
    private static Connection connection = null;




    public static void addReference(Reference reference) throws SQLException {
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement st = connection.prepareStatement(
                "INSERT INTO REFERENCES (url_1, url_2, type_of, similarity) VALUES(?,?,?,?);");

        st.setString(1,reference.getUrl1());
        st.setString(2,reference.getUrl2());
        st.setBoolean(3,reference.isType());
        st.setDouble(4,reference.getSimilarity());
        st.execute();
        st.close();
    }

    public static void addArticle(Article article) throws SQLException {
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement st = connection.prepareStatement(
                "INSERT INTO ARTICLES (url) VALUES(?);");

        st.setString(1,article.getUrl());
        st.execute();
        connection.close();

    }




}
