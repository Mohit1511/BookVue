package org.example.library.controller;

import org.example.library.dao.objects.AuthorDetailsResponse;
import org.example.library.dao.objects.BookDetailsResponse;
import org.example.library.dao.objects.KindleResponse;
import org.example.library.service.AuthorDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/author", produces = "application/json")
public class AuthorDetailsController {
    @Autowired
    private AuthorDetailsService authorDetailsService;

    @GetMapping("/fetchAllAuthors")
    public ResponseEntity<KindleResponse<List<AuthorDetailsResponse>>> fetchAllAuthors(){
        KindleResponse<List<AuthorDetailsResponse>> authorDetailsResponseList = authorDetailsService.fetchAllAuthors();
        return new ResponseEntity<>(authorDetailsResponseList, HttpStatus.OK);
    }
}
