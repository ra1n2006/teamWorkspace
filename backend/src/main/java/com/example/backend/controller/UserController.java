package com.example.backend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(
        origins = "http://localhost:5173",
        methods = {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.OPTIONS
        }
)
public class UserController {
    @GetMapping("/api/main")
    public void getMainData(@RequestBody String request){
    }

}
