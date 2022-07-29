package com.yl.controller;

import com.yl.annotationtest.AfterAciveTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class TestController {


    @GetMapping("/api/test")
    @AfterAciveTest
    public void testC(@PathParam("id") Integer id,@PathParam("name") String name){
        System.out.println("testC executed........"+id+"------------->"+name);
    }

}
