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
public class AuthController {

    @PostMapping("/api/auth")
    public String auth(@RequestBody String request) {
        System.out.println("========== REQUEST ==========");
        System.out.println(request);
        System.out.println("=============================");

        return "Backend received the request!";
    }
}
