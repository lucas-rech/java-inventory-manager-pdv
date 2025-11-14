package edu.ifrs.si.inventorymanagerpdv.controller;

import edu.ifrs.si.inventorymanagerpdv.model.User;
import edu.ifrs.si.inventorymanagerpdv.service.CustomUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final CustomUserDetailsService customUserDetailsService;

    public AdminController(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @GetMapping("/users/{requestedId}")
    public ResponseEntity<User> findById(@PathVariable Long requestedId) {
        User user = customUserDetailsService.findById(requestedId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }
}
