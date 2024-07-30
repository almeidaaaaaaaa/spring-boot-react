package com.start.produtos.contoller;

import com.start.produtos.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationService ls;

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String city) {
        return ls.search(city);
    }
}
