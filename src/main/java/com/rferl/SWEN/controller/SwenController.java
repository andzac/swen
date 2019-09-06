package com.rferl.SWEN.controller;

import com.rferl.SWEN.model.ArticleCheckResult;
import com.rferl.SWEN.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SwenController {

    @Autowired
    private CheckService checkService;

    @PostMapping("/check")
    public String check(@RequestBody String article) {
        return checkService.check(article);
    }
//
//    @GetMapping("/getall")
//    public ArticleCheckResult getAll()
//    {
//
//    }

}
