package com.example.springgreetingapp.controller;
import com.example.springgreetingapp.model.Greeting;
import com.example.springgreetingapp.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/greetings")
public class GreetingController {
    @Autowired
    private GreetingService greetingService;

    @PostMapping
    public ResponseEntity<?> greet(@RequestParam(required = false) String firstName,
                                   @RequestParam(required = false) String lastName) {
        try {
            Greeting greeting = greetingService.saveGreeting(firstName, lastName);
            return ResponseEntity.ok(greeting);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Something went wrong! " + e.getMessage()));
        }
    }
    @GetMapping("/all")
    public List<Greeting> getAllGreetings() {
        return greetingService.getAllGreetings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Greeting> getGreetingById(@PathVariable Long id) {
        Optional<Greeting> greeting = greetingService.findGreetingById(id);
        return greeting.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}



