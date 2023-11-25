package book.vue.library.dao.objects;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class KindleResponse<R> implements Serializable {
    private String statusCode;
    private String statusMessage;

    @Override
    public String toString() {
        return "KindleResponse{" +
                "statusCode='" + statusCode + '\'' +
                ", statusMessage='" + statusMessage + '\'' +
                ", response=" + response +
                '}';
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public R getResponse() {
        return response;
    }

    public void setResponse(R response) {
        this.response = response;
    }

    private R response;

    public KindleResponse(String statusCode, String statusMessage, R response) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.response = response;
    }

    public KindleResponse(){
    }
}
