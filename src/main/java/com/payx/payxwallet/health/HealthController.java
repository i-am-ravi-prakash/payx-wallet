package com.payx.payxwallet.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HealthController {

    @GetMapping("/api/health")
    public String health(){
        return "OK";
    }
}
