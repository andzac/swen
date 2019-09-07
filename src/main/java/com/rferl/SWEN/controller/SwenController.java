package com.rferl.SWEN.controller;

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

        String url = "";
        String externalResponse = checkService.checkIfSiteIsFake(url);

        return checkService.check(article);
    }



}
