package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.springbootdemo.dto.PapersPage;
import ru.itis.springbootdemo.services.PapersService;

@RestController
public class PapersController {

    @Autowired
    private PapersService papersService;

    @GetMapping("/papers/search")
    public ResponseEntity<PapersPage> search(@RequestParam("size") Integer size,
                                             @RequestParam("page") Integer page,
                                             @RequestParam(value = "q", required = false) String query,
                                             @RequestParam(value = "sort", required = false) String sort,
                                             @RequestParam(value = "direction", required = false) String direction) {
        return ResponseEntity.ok(papersService.search(size, page, query, sort, direction));
    }
}
