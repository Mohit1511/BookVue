package org.example.library.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.library.dao.entities.AuthorDetails;
import org.example.library.dao.entities.BookDetails;
import org.example.library.dao.helpers.LibraryDetailsHelper;
import org.example.library.dao.objects.BookDetailsRequest;
import org.example.library.dao.objects.BookDetailsResponse;
import org.example.library.dao.objects.KindleResponse;
import org.example.library.dao.repositories.AuthorDetailsRepository;
import org.example.library.dao.repositories.BookRepository;
import org.example.library.service.BookDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Slf4j
public class IBooksDetailsServiceImpl implements BookDetailsService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorDetailsRepository authorDetailsRepository;
    @Autowired
    private LibraryDetailsHelper libraryDetailsHelper;

    @Override
    public KindleResponse<BookDetailsResponse> fetchBookDetails(String title) {
        KindleResponse<BookDetailsResponse> kindleResponse;
        if(title != null){
            Iterable<BookDetails> bookDetails = bookRepository.findByTitle(title);
            if(bookDetails != null){
                BookDetailsResponse bookDetailsResponse = libraryDetailsHelper.createBookDetailResponse(bookDetails.iterator().next());
                kindleResponse = libraryDetailsHelper.createKindleResponse("SUCCESS");
                kindleResponse.setResponse(bookDetailsResponse);
                return kindleResponse;
            }
        }
        kindleResponse = libraryDetailsHelper.createKindleResponse("FAILURE");
        return kindleResponse;
    }

    @Override
    public KindleResponse<List<BookDetailsResponse>> fetchAllBooks() {
        KindleResponse<List<BookDetailsResponse>> kindleResponse = new KindleResponse<>();
        List<BookDetails> bookDetails = bookRepository.findAll();
        if (!bookDetails.isEmpty()){
            List<BookDetailsResponse> bookDetailsResponseList = libraryDetailsHelper.createBookDetailsResponseList(bookDetails);
            kindleResponse = libraryDetailsHelper.createKindleResponse("SUCCESS");
            kindleResponse.setResponse(bookDetailsResponseList);
            return kindleResponse;
        }
        kindleResponse = libraryDetailsHelper.createKindleResponse("FAILURE");
        return kindleResponse;
    }

    @Override
    public KindleResponse insertBook(BookDetailsRequest bookDetailsRequest) {
        KindleResponse<Object> kindleResponse;
        try {
                libraryDetailsHelper.validateBookDetailRequest(bookDetailsRequest);
                log.info("bookDetailsRequestString {}", bookDetailsRequest);
                BookDetails bookDetails = libraryDetailsHelper.convertToBookDetails(bookDetailsRequest);
                AuthorDetails authorDetails = authorDetailsRepository.findByFullName(bookDetailsRequest.getAuthorName());
                if(authorDetails == null){
                    authorDetails = new AuthorDetails();
                    authorDetails.setFullName(bookDetailsRequest.getAuthorName());
                    authorDetails = authorDetailsRepository.save(authorDetails);
                }
                bookDetails.setAuthorId(authorDetails.getId());
                bookDetails.setAuthorName(authorDetails.getFullName());
                bookDetails = bookRepository.save(bookDetails);
                kindleResponse = libraryDetailsHelper.createKindleResponse("SUCCESS");
                kindleResponse.setResponse(libraryDetailsHelper.createBookDetailResponse(bookDetails));
                return kindleResponse;
        } catch (Exception e){
            log.error("Exception occurred {}", (Object) e.getStackTrace());
        }
        return libraryDetailsHelper.createKindleResponse("FAILURE");
    }

    @Override
    public KindleResponse<List<BookDetailsResponse>> filterBooks(BookDetailsRequest bookDetailsRequest) {
        KindleResponse<List<BookDetailsResponse>> kindleResponse;
        try {
                libraryDetailsHelper.validateBookFilterRequest(bookDetailsRequest);
                List<BookDetails> bookDetails = bookRepository.findAll();
                AuthorDetails authorDetails = StringUtils.isNotBlank(bookDetailsRequest.getAuthorName()) ? authorDetailsRepository.findByFullName(bookDetailsRequest.getAuthorName().toUpperCase()) : null;
                if(!bookDetails.isEmpty()) {
                    Predicate<BookDetails> titleCondition = b -> Objects.isNull(bookDetailsRequest.getTitle()) || b.getTitle().contains(bookDetailsRequest.getTitle());
                    Predicate<BookDetails> genreCondition = b -> Objects.isNull(bookDetailsRequest.getGenre()) || b.getGenre().equalsIgnoreCase(bookDetailsRequest.getGenre());
                    Predicate<BookDetails> authorCondition = b -> Objects.isNull(authorDetails) || b.getAuthorId().equals(authorDetails.getId());

                    List<BookDetails> filteredBookList = bookDetails.stream()
                            .filter(titleCondition.and(genreCondition).and(authorCondition))
                            .collect(Collectors.toList());

                    List<BookDetailsResponse> bookDetailsResponseList = libraryDetailsHelper.createBookDetailsResponseList(filteredBookList);

                    kindleResponse = libraryDetailsHelper.createKindleResponse("SUCCESS");
                    kindleResponse.setResponse(bookDetailsResponseList);
                    return kindleResponse;
                }
            kindleResponse = libraryDetailsHelper.createKindleResponse("FAILURE");
            return kindleResponse;
        } catch (Exception e){
            log.error("Exception occurred {}", (Object) e.getStackTrace());
        }
        kindleResponse = libraryDetailsHelper.createKindleResponse("FAILURE");
        return kindleResponse;
    }
}
