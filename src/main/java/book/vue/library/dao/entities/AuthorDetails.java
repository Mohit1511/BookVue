package book.vue.library.dao.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Date;
import java.util.Set;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "long default 2")
    @SerializedName(value = "id")
    public Long id;
    @SerializedName(value = "fullName")
    private String fullName;

//    @OneToMany(mappedBy = "book_details", cascade = CascadeType.ALL)
//    private Set<BookDetails> bookDetailsSet;

    @Column(name = "create_timestamp", updatable = false)
    @CreationTimestamp
    private Date createTimestamp;
    @Column(name = "update_timestamp")
    @CreationTimestamp
    private Date updateTimestamp;

    public AuthorDetails(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getCreateTimestamp() {
        return createTimestamp;
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
//    public Set<BookDetails> getBookDetailsSet() {
//        return bookDetailsSet;
//    }
//    public void setBookDetailsSet(Set<BookDetails> bookDetailsSet) {
//        this.bookDetailsSet = bookDetailsSet;
//    }
}
