package com.wrx.schoolcould.controller;

import com.wrx.schoolcould.dto.SigninDTO;
import com.wrx.schoolcould.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600,allowCredentials = "true")
public class SignController {

    @Autowired
    SignService signService;

    @PostMapping("/signin")
    public Object signin(HttpServletRequest req, HttpServletResponse resp){

        System.out.println("=======================================================================================");
        System.out.println("Authorization :" +req.getSession().getAttribute("Authorization"));

        SigninDTO signinDTO = signService.signinProcess(req, resp);
        System.out.println(signinDTO);

        System.out.println("Authorization :" +req.getSession().getAttribute("Authorization"));

        return signinDTO;

    }
}
