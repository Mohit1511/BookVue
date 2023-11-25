package book.vue.library.dao.objects;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BookDetailsResponse {
    private String bookName;
    private String isbnNumber;
    private String genre;
    private AuthorDetailsResponse authorDetails;
    private Date updateTimestamp;
    private Long noOfPages;
    private String bookUrl;

    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }
    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public AuthorDetailsResponse getAuthorDetails() {
        return authorDetails;
    }
    public void setAuthorDetails(AuthorDetailsResponse authorDetails) {
        this.authorDetails = authorDetails;
    }

    public Date getUpdateTimestamp() {
        return updateTimestamp;
    }
    public void setUpdateTimestamp(Date updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public Long getNoOfPages() {
        return noOfPages;
    }
    public void setNoOfPages(Long noOfPages) {
        this.noOfPages = noOfPages;
    }

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }
}
