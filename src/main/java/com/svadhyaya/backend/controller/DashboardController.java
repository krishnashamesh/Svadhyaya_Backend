package com.svadhyaya.backend.controller;

import com.svadhyaya.backend.models.data.DefaultResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    @GetMapping("/dashboard")
    public ResponseEntity<?> registration(){
        return ResponseEntity.ok(new DefaultResponseData("Dashboard View"));
    }
}
