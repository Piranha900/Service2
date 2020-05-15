package com.demo2.demo2.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Demo2Controller {

    @Qualifier("eurekaClient")
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/pong")
    public @ResponseBody
    ResponseEntity<String> getPong() {

        List<String> RegistredName = eurekaClient.getApplications().getRegisteredApplications().stream()
                .map(Application::getName)
                .collect(Collectors.toList());
        String prettyGson = new GsonBuilder().setPrettyPrinting().create().toJson(RegistredName);

        return new ResponseEntity<String>("Pong from service " +appName + " with services registered in Server Registry:\n"+
                prettyGson +"\n", HttpStatus.OK);
    }

}
