package com.rferl.SWEN.repository;

import com.rferl.SWEN.model.Reference;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class ReferenceRepository {
    public void add(Reference reference) {

    }

    public List<Reference> getNegatives(String url) {
        return Collections.emptyList();
    }


    public List<Reference> getPositives(String url) {

        return Collections.emptyList();
    }
}
