package com.example.simpleapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/healthz")
public class HealthCheck {
   @GetMapping
   public Map healthCheck() {
       return Collections.singletonMap("status", "OK");
   }
}
