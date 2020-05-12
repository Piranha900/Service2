package com.demo2.demo2.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Demo2Controller {
    @Value("${server.port}")
    private String myPort;

    @GetMapping("/pong")
    public @ResponseBody
    ResponseEntity<String> get() {

        return new ResponseEntity<String>("Pong from port: "+myPort + "\n", HttpStatus.OK);
    }

}
