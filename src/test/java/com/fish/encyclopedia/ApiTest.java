package com.fish.encyclopedia;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiTest {
        @GetMapping("/test")
        public String index() {
            return "Hello World";
        }
}
