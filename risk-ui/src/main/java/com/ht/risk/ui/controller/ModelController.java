package com.ht.risk.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/model")
public class ModelController {

    @RequestMapping(value = "/listView",method = RequestMethod.GET)
    public String listView(){
        return "model/list";
    }

    @RequestMapping(value = "/addView",method = RequestMethod.GET)
    public String addView(){
        return "model/add";
    }


}