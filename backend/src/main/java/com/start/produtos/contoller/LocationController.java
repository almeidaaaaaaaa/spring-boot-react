package com.start.produtos.contoller;

import com.start.produtos.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private SearchService ss;

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String keyWord, @RequestParam String searchParam) {
        return ss.search(keyWord, searchParam);
    }
}
