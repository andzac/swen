package com.rferl.SWEN.repository;

import com.rferl.SWEN.model.Reference;
import com.rferl.SWEN.service.DBService;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class ReferenceRepository {
    public int add(Reference reference) throws SQLException {
        return DBService.addReference(reference);
    }

    public List<Reference> getNegatives(String url) {
        return Collections.emptyList();
    }


    public List<Reference> getPositives(String url) {

        return Collections.emptyList();
    }

    public List<String> fullfillPositive(int id) throws SQLException {
        return DBService.selectListOfPositiveArticlesById(id);
    }

    public List<String> fullfillNegative(int id) throws SQLException {
        return DBService.selectListOfNegativeArticlesById(id);
    }


}
