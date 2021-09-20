package com.svadhyaya.backend.controller;

import com.svadhyaya.backend.models.AuthenticationRequest;
import com.svadhyaya.backend.models.DefaultResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class DashboardController {

    @GetMapping("/dashboard")
    public ResponseEntity<?> registration(){
        return ResponseEntity.ok(new DefaultResponse("Dashboard View"));
    }
}
