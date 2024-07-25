package com.client.ws.rasmooplus.controller;

import com.client.ws.rasmooplus.model.SubscriptionType;
import com.client.ws.rasmooplus.service.SubscriptionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/subscription-type")
public class SubscriptionTypeController {
    @Autowired
    private SubscriptionTypeService subscriptionTypeService;

    @GetMapping()
    public ResponseEntity<List<SubscriptionType>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(subscriptionTypeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionType> findById(@PathVariable("id") Long id) {
        SubscriptionType subscriptionType = subscriptionTypeService.findById(id);

        if(Objects.nonNull(subscriptionType)){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(subscriptionTypeService.findById(id));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping()
    public ResponseEntity<SubscriptionType> create(@RequestBody() SubscriptionType subscriptionType) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionType> create(
            @PathVariable("id") Long id,
            @RequestBody() SubscriptionType subscriptionType) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SubscriptionType> create(@PathVariable("id") Long id) {
        return null;
    }
}