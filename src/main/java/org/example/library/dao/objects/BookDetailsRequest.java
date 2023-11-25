package org.example.library.dao.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDetailsRequest {
    @SerializedName(value = "title")
    public String title;

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    @Override
    public String toString() {
        return "BookDetailsRequest{" +
                "title='" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                ", genre='" + genre + '\'' +
                ", noOfPages=" + noOfPages +
                ", isbnNumber='" + isbnNumber + '\'' +
                ", content=" + content +
                '}';
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String bookGenre) {
        this.genre = bookGenre;
    }

    public Long getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(Long noOfPages) {
        this.noOfPages = noOfPages;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }
    @SerializedName(value = "authorName")
    public String authorName;
    @SerializedName(value = "genre")
    public String genre;
    @SerializedName(value = "noOfPages")
    public Long noOfPages;
    @SerializedName(value = "isbnNumber")
    public String isbnNumber;

    public String getContent() {
        return content;
    }

    public BookDetailsRequest() {
    }

    public BookDetailsRequest(String title, String authorName, String genre, Long noOfPages, String isbnNumber, String content) {
        this.title = title;
        this.authorName = authorName;
        this.genre = genre;
        this.noOfPages = noOfPages;
        this.isbnNumber = isbnNumber;
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @SerializedName(value = "content")
    public String content;
}
