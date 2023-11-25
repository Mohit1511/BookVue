package org.example.library.controller;

import org.example.library.dao.objects.BookDetailsRequest;
import org.example.library.dao.objects.BookDetailsResponse;
import org.example.library.service.BookDetailsService;
import org.example.library.dao.objects.KindleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/books", produces = "application/json")
public class BookDetailsController {
    @Autowired
    private BookDetailsService bookDetailsService;

    @GetMapping("/home")
    public String sayHi(){
        return "Hi, from BookShop";
    }

    @GetMapping("/fetchAllBooks")
    public ResponseEntity<KindleResponse<List<BookDetailsResponse>>> fetchAllBooks(){
        KindleResponse<List<BookDetailsResponse>> bookDetailsResponse = bookDetailsService.fetchAllBooks();
        return (bookDetailsResponse.getStatusMessage().equalsIgnoreCase("FAILURE")) ? new ResponseEntity<>(bookDetailsResponse, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(bookDetailsResponse,HttpStatus.OK);
    }

    @PostMapping("/insertBook")
    public ResponseEntity<KindleResponse> insertBook(@RequestBody BookDetailsRequest bookDetailsRequest){
        KindleResponse kindleResponse = bookDetailsService.insertBook(bookDetailsRequest);
        return (kindleResponse.getStatusMessage().equalsIgnoreCase("FAILURE")) ? new ResponseEntity<>(kindleResponse, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(kindleResponse,HttpStatus.OK);
    }

    @PostMapping("/fetchBookDetails")
    public ResponseEntity<KindleResponse<BookDetailsResponse>> fetchBookDetails(@RequestBody BookDetailsRequest bookDetailsRequest){
        KindleResponse<BookDetailsResponse> kindleResponse = bookDetailsService.fetchBookDetails(bookDetailsRequest.getTitle());
        return (kindleResponse.getStatusMessage().equalsIgnoreCase("FAILURE")) ? new ResponseEntity<>(kindleResponse, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(kindleResponse,HttpStatus.OK);
    }

    @PostMapping("/filterBooks")
    public ResponseEntity<KindleResponse<List<BookDetailsResponse>>> filterBooks(@RequestBody BookDetailsRequest bookDetailsRequest){
        KindleResponse<List<BookDetailsResponse>> bookDetailsResponse = bookDetailsService.filterBooks(bookDetailsRequest);
        return (bookDetailsResponse.getStatusMessage().equalsIgnoreCase("FAILURE")) ? new ResponseEntity<>(bookDetailsResponse, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(bookDetailsResponse,HttpStatus.OK);
    }
}
