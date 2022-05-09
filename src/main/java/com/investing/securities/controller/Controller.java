package com.investing.securities.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/controller")
public class Controller {

    @PostMapping("/upload")
    public ResponseEntity<String> upload(
            @RequestParam("id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(String.format("File uploaded successfully: %s", "Hello"));
    }
}
