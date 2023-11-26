package book.vue.library.service;

import book.vue.library.dao.objects.BookDetailsRequest;
import book.vue.library.dao.objects.BookDetailsResponse;
import book.vue.library.dao.objects.KindleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookDetailsService {


    public KindleResponse<BookDetailsResponse> fetchBookDetails(String title);

    public KindleResponse<List<BookDetailsResponse>> fetchAllBooks();

    public KindleResponse insertBook(BookDetailsRequest bookDetailsRequest);
    public KindleResponse updateBook(BookDetailsRequest bookDetailsRequest);

    public KindleResponse<List<BookDetailsResponse>> filterBooks(BookDetailsRequest bookDetailsRequest);
}
