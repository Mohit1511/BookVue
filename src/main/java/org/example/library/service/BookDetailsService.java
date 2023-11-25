package org.example.library.service;

import org.example.library.dao.objects.BookDetailsRequest;
import org.example.library.dao.objects.BookDetailsResponse;
import org.example.library.dao.objects.KindleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookDetailsService {


    public KindleResponse<BookDetailsResponse> fetchBookDetails(String title);

    public KindleResponse<List<BookDetailsResponse>> fetchAllBooks();

    public KindleResponse insertBook(BookDetailsRequest bookDetailsRequest);

    public KindleResponse<List<BookDetailsResponse>> filterBooks(BookDetailsRequest bookDetailsRequest);
}
