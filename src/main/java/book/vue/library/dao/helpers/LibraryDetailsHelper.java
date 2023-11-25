package book.vue.library.dao.helpers;

import book.vue.library.dao.entities.BookDetails;
import book.vue.library.dao.objects.BookDetailsRequest;
import book.vue.library.dao.objects.BookDetailsResponse;
import book.vue.library.dao.objects.KindleResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import book.vue.library.dao.entities.AuthorDetails;
import book.vue.library.dao.objects.AuthorDetailsResponse;
import book.vue.library.dao.repositories.AuthorDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class LibraryDetailsHelper {
    @Autowired
    private AuthorDetailsRepository authorDetailsRepository;
    public BookDetailsResponse createBookDetailResponse(BookDetails bookDetails) {
        BookDetailsResponse bookDetailsResponse = new BookDetailsResponse();
        bookDetailsResponse.setBookName(bookDetails.getTitle());
        bookDetailsResponse.setGenre(bookDetails.getGenre());
        bookDetailsResponse.setIsbnNumber(bookDetails.getIsbnNumber());
        bookDetailsResponse.setNoOfPages(bookDetails.getNoOfPages());
        bookDetailsResponse.setUpdateTimestamp(bookDetails.getUpdateTimestamp());
        bookDetailsResponse.setBookUrl("/book/download/" + bookDetails.getTitle().replaceAll("\\s", ""));
        AuthorDetailsResponse authorDetailResponse = new AuthorDetailsResponse();
        AuthorDetails authorDetails = authorDetailsRepository.findByAuthorId(bookDetails.getAuthorId());
        if(authorDetails != null){
            authorDetailResponse.setId(authorDetails.getId());
            authorDetailResponse.setFullName(authorDetails.getFullName());
            authorDetailResponse.setCreateTimestamp(authorDetails.getCreateTimestamp());
            authorDetailResponse.setUpdateTimestamp(authorDetails.getUpdateTimestamp());
            bookDetailsResponse.setAuthorDetails(authorDetailResponse);
        }
        return bookDetailsResponse;
    }

    public AuthorDetailsResponse createAuthorDetailsResponse(AuthorDetails authorDetails) {
        AuthorDetailsResponse authorDetailsResponse = new AuthorDetailsResponse();
        authorDetailsResponse.setId(authorDetails.getId());
        authorDetailsResponse.setFullName(authorDetails.getFullName());
        authorDetailsResponse.setCreateTimestamp(authorDetails.getCreateTimestamp());
        authorDetailsResponse.setUpdateTimestamp(authorDetails.getUpdateTimestamp());
        return authorDetailsResponse;
    }


    public KindleResponse createKindleResponse(String message){
        KindleResponse kindleResponse = new KindleResponse<>();
        kindleResponse.setStatusMessage(message);
        kindleResponse.setStatusCode(message.equalsIgnoreCase("SUCCESS") ? "SS_001" : "FE_001");
        return kindleResponse;
    }

    public String capitalizeWords(String input) {
        StringBuilder result = new StringBuilder();

        String[] words = input.split("\\s");

        for (String word : words) {
            if (!word.isEmpty()) {  // Skip empty words (e.g., consecutive white spaces)
                char firstChar = Character.toUpperCase(word.charAt(0));
                String restOfWord = word.substring(1).toLowerCase();
                result.append(firstChar).append(restOfWord).append(" ");
            }
        }

        return result.toString().trim();
    }

    public BookDetails convertToBookDetails(BookDetailsRequest bookDetailsRequest){
        BookDetails bookDetails = new BookDetails();
        bookDetails.setIsbnNumber(bookDetailsRequest.getIsbnNumber().trim());
        bookDetails.setNoOfPages(bookDetailsRequest.getNoOfPages());
        bookDetails.setGenre(bookDetailsRequest.getGenre().trim());
        bookDetails.setTitle(this.capitalizeWords(bookDetailsRequest.getTitle().trim()));
        bookDetails.setContent(bookDetailsRequest.getContent().getBytes());
        log.info("bookDetails {}", bookDetails);
        return bookDetails;
    }

    public void validateBookFilterRequest(BookDetailsRequest bookDetailsRequest) throws  Exception{
        if(bookDetailsRequest == null){
            throw  new Exception("bookDetailRequest is null");
        }

        if(!StringUtils.isNotBlank(bookDetailsRequest.getTitle()) || !StringUtils.isNotBlank(bookDetailsRequest.getAuthorName()) || !StringUtils.isNotBlank(bookDetailsRequest.getGenre())){
            throw new Exception();
        }
    }

    public void validateBookDetailRequest(BookDetailsRequest bookDetailsRequest) throws Exception{
        if(bookDetailsRequest == null){
            throw new Exception();
        }

        if(!StringUtils.isNotBlank(bookDetailsRequest.getTitle()) || !StringUtils.isNotBlank(bookDetailsRequest.getAuthorName()) || !StringUtils.isNotBlank(bookDetailsRequest.getGenre()) || !StringUtils.isNotBlank(bookDetailsRequest.getIsbnNumber())){
          throw new Exception();
        }
    }

    public List<BookDetailsResponse> createBookDetailsResponseList(List<BookDetails> bookDetailsList){
        List<BookDetailsResponse> bookDetailsResponseList = new ArrayList<>();
        for(BookDetails bd: bookDetailsList){
            BookDetailsResponse bookDetailsResponse = this.createBookDetailResponse(bd);
            bookDetailsResponseList.add(bookDetailsResponse);
        }
        return bookDetailsResponseList;
    }
}
