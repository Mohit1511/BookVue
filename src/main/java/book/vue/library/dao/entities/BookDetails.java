package book.vue.library.dao.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name="book_details", uniqueConstraints = {@UniqueConstraint(columnNames = {"title", "isbn_no"})})
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDetails {
    @Id
    @Column(columnDefinition = "long default 2")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SerializedName(value = "id")
    private Long id;
    @SerializedName(value = "title")
    private String title;
    @Column(name="isbn_no")
    @SerializedName(value = "isbnNumber")
    private String isbnNumber;

    @Column(name="genre", nullable = false)
    @SerializedName(value = "genre")
    private String genre;

    @Column(name="no_of_pages", nullable = false)
    @SerializedName(value = "noOfPages")
    private Long noOfPages;

    @Column(name="author_id",nullable = false)
    private Long authorId;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Column(name="author_name", nullable = false)
    private String authorName;

    @Column(name="added_on", nullable = false)
    @CreationTimestamp
    private Date createTimestamp;
    @Column(name="modified_on", insertable = false, updatable = false)
    @CreationTimestamp
    private Date updateTimestamp;

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Lob
    @Column(name="content", columnDefinition = "LONGBLOB")
    private byte[] content;

    public BookDetails(Long id, String isbnNumber, byte[] content, String authorName, String title, String genre, Long noOfPages, Long authorId, Date createTimestamp, Date updateTimestamp) {
        this.id = id;
        this.isbnNumber = isbnNumber;
        this.title = title;
        this.genre = genre;
        this.noOfPages = noOfPages;
        this.authorId = authorId;
        this.authorName = authorName;
        this.createTimestamp = createTimestamp;
        this.updateTimestamp = updateTimestamp;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Long getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(Long noOfPages) {
        this.noOfPages = noOfPages;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    @Override
    public String toString() {
        return "BookDetails{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbnNumber='" + isbnNumber + '\'' +
                ", genre='" + genre + '\'' +
                ", noOfPages=" + noOfPages +
                ", authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", createTimestamp=" + createTimestamp +
                ", updateTimestamp=" + updateTimestamp +
                '}';
    }

    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Date getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Date updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public BookDetails(){}
}
