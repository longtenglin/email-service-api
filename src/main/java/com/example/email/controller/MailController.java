package com.example.email.controller;

import com.example.email.entity.User;
import com.example.email.service.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class MailController {

    @Autowired(required = false)
    private MailService mailService;

    @RequestMapping(path = "/getSms", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getMsg(HttpServletRequest request, @RequestBody User requestData){
        return  mailService.getMsg(request, requestData.getUsername());
    }

}
