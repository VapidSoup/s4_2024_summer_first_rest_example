package com.keyin.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping("/{id}")
    public ResponseEntity<Greeting> getGreeting(@PathVariable long id) {
        Greeting greeting = greetingService.getGreeting(id);
        if (greeting == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(greeting);
    }

    @GetMapping
    public ResponseEntity<List<Greeting>> getAllGreetings() {
        List<Greeting> greetings = greetingService.getAllGreetings();
        return ResponseEntity.ok(greetings);
    }

    @PostMapping
    public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting newGreeting) {
        Greeting createdGreeting = greetingService.createGreeting(newGreeting);
        return ResponseEntity.ok(createdGreeting);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Greeting> updateGreeting(@PathVariable long id, @RequestBody Greeting updatedGreeting) {
        Greeting greeting = greetingService.updateGreeting(id, updatedGreeting);
        if (greeting == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(greeting);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGreeting(@PathVariable long id) {
        greetingService.deleteGreeting(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Greeting>> findGreetingsByNameAndGreeting(@RequestParam String name, @RequestParam String greetingName) {
        List<Greeting> greetings = greetingService.findGreetingsByNameAndGreeting(name, greetingName);
        return ResponseEntity.ok(greetings);
    }

    static class GreetingRequest {
        private String name;
        private String greeting;
        private List<String> languages;
        

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGreeting() {
            return greeting;
        }

        public void setGreeting(String greeting) {
            this.greeting = greeting;
        }

        public List<String> getLanguages() {
            return languages;
        }

        public void setLanguages(List<String> languages) {
            this.languages = languages;
        }
    }
}
