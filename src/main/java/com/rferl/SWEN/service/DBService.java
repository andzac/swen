package com.rferl.SWEN.service;

import com.rferl.SWEN.model.Article;
import com.rferl.SWEN.model.Reference;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBService {

    private static final String DB_URL = "jdbc:h2:C:/data/sample";
    private static final String USER = "sa";
    private static final String PASS = "";
    private static Connection connection = null;
    // private static final String SQL_SELECT_POSITIVE = "SELECT * FROM REFERENCES WHERE ID=1 AND TYPE_OF=" + String.valueOf(true);

    private static final String SQL_SELECT_POSITIVE = "SELECT * FROM REFERENCES WHERE URL_1 =? AND TYPE_OF=" + String.valueOf(true)+" ";
    private static final String SQL_SELECT_NEGATIVE = "SELECT * FROM REFERENCES WHERE URL_1 =? AND TYPE_OF=" + String.valueOf(false) + "";


    public static int addReference(Reference reference) throws SQLException {
        int last_inserted_id = -1;
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement st = connection.prepareStatement(
                "INSERT INTO REFERENCES (url_1, url_2, full_url, type_of, similarity) VALUES(?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);

        st.setString(1, reference.getUrl1());
        st.setString(2, reference.getUrl2());
        st.setString(3, reference.getFullUrl());
        st.setBoolean(4, reference.isType());
        st.setDouble(5, reference.getSimilarity());
        st.execute();

        PreparedStatement st2 = connection.prepareStatement(
                "SELECT last_insert_id() FROM REFERENCES");


        ResultSet rs = st2.executeQuery();
        if (rs.next()) {
            last_inserted_id = rs.getInt(1);
        }
        st.close();
        return last_inserted_id;

    }

    public static void addArticle(Article article) throws SQLException {
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement st = connection.prepareStatement(
                "INSERT INTO ARTICLES (url) VALUES(?);");

        st.setString(1, article.getUrl());
        st.execute();
        connection.close();

    }


    public static List<String> selectListOfPositiveArticlesById(String url) throws SQLException {

        List<String> listArticles = new ArrayList<>();
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement st = connection.prepareStatement(SQL_SELECT_POSITIVE);
        st.setString(1,url);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            listArticles.add((rs.getString("url_2")));
        }

        return listArticles;
    }

    public static List<String> selectListOfNegativeArticlesById(String url) throws SQLException {

        List<String> listArticles = new ArrayList<>();
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement st = connection.prepareStatement(SQL_SELECT_NEGATIVE);
        st.setString(1,url);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            listArticles.add((rs.getString("url_2")));
        }

        return listArticles;
    }
}

