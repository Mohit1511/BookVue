package book.vue.library.controller;

import book.vue.library.dao.objects.BookDetailsRequest;
import book.vue.library.dao.objects.BookDetailsResponse;
import book.vue.library.dao.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import book.vue.library.dao.helpers.LibraryDetailsHelper;
import book.vue.library.dao.objects.KindleResponse;
import book.vue.library.dao.repositories.AuthorDetailsRepository;
import book.vue.library.service.impl.IBooksDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class BookDetailsControllerTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorDetailsRepository authorDetailsRepository;
    @Mock
    private LibraryDetailsHelper libraryDetailsHelper;
    @Mock
    private BookDetailsRequest bookDetailsRequest;
    @Mock
    private BookDetailsResponse bookDetailsResponse;
    @Mock
    private IBooksDetailsServiceImpl bookDetailsService;

    @InjectMocks
    private BookDetailsController bookDetailsController;

    private KindleResponse<String> kindleSuccessfulResponse, kindleFailureResponse;
    private BookDetailsRequest bookDetailSuccessfulRequest, bookDetailFailureRequest;

    @BeforeEach
    public void init(){
        log.info("On startup");
        kindleSuccessfulResponse = new KindleResponse<>("SS_001","SUCCESS","{\"bookName\":\"The Lorem Ipsum\",\"isbnNumber\":\"0-346-40615-2\",\"genre\":\"DRAMA\",\"authorDetails\":{\"fullName\":\"Rumi Palak\",\"id\":2,\"createTimestamp\":\"2023-11-20T12:47:27.984+00:00\",\"updateTimestamp\":\"2023-11-20T12:47:27.984+00:00\"},\"updateTimestamp\":\"2023-11-20T12:47:27.999+00:00\",\"noOfPages\":38,\"bookUrl\":\"/book/download/TheLoremIpsum\"}");
        bookDetailSuccessfulRequest = new BookDetailsRequest("The Lorem Ipsum","Rumi Palak", "DRAMA",38L, "0-3465-40615-2","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque non mollis diam. Sed gravida dolor ac orci condimentum, et tempus massa ultricies. Cras dignissim ultricies est ac ullamcorper. Integer facilisis rutrum urna, vitae ultricies nibh maximus posuere. Donec at malesuada mi. Nullam egestas condimentum porttitor. Donec ac nunc felis. Etiam elementum malesuada sodales. Nullam ultrices lacus et metus vehicula scelerisque. Aenean accumsan condimentum dolor, at molestie leo tempor a. Praesent vitae tincidunt leo. Mauris cursus blandit tincidunt. Nulla quis tempus lectus. Vestibulum lectus purus, euismod eleifend sem a, efficitur congue diam");
        kindleFailureResponse = new KindleResponse<>("FF_001", "FAILURE","");
        bookDetailFailureRequest = new BookDetailsRequest();
    }


    @Test
    void sayHi() {
    }

    @Test
    void fetchAllBooks() {
    }

    @Test
    void testInsertBookSuccess() {
        when(bookDetailsService.insertBook(bookDetailSuccessfulRequest)).thenReturn(kindleSuccessfulResponse);

        ResponseEntity<KindleResponse> responseEntity = bookDetailsController.insertBook(bookDetailSuccessfulRequest);

        // Verify that the service method was called
        verify(bookDetailsService, times(1)).insertBook(bookDetailSuccessfulRequest);

        // Verify the response status and message
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(kindleSuccessfulResponse, responseEntity.getBody());
    }

    @Test
    void testInsertBookFailure(){
        when(bookDetailsService.insertBook(bookDetailFailureRequest)).thenReturn(kindleFailureResponse);

        ResponseEntity<KindleResponse> responseEntity = bookDetailsController.insertBook(bookDetailFailureRequest);
        verify(bookDetailsService, times(1)).insertBook(bookDetailFailureRequest);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(kindleFailureResponse, responseEntity.getBody());
    }

    @Test
    void fetchBookDetails() {
    }

    @Test
    void filterBooks() {
    }
}