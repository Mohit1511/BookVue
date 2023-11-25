package book.vue.library.dao.objects;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AuthorDetailsResponse {
    private String fullName;
    private Long id;
    private Date createTimestamp;
    private Date updateTimestamp;
    public AuthorDetailsResponse() {
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
