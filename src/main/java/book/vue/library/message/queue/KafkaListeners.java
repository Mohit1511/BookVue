package book.vue.library.message.queue;

import book.vue.library.controller.BookDetailsController;
import book.vue.library.dao.objects.BookDetailsRequest;
import book.vue.library.dao.objects.KindleResponse;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class KafkaListeners {
    @Autowired
    private BookDetailsController bookDetailsController;
    Gson gson = new Gson();

    @KafkaListener(topics = "insertBook", groupId = "groupId")
    void listener(String data){
        log.info("Listener received {}", data + ":tada");
        try {
            BookDetailsRequest bookDetailsRequest = gson.fromJson(data, BookDetailsRequest.class);
            ResponseEntity<KindleResponse> kindleResponseResponseEntity = bookDetailsController.insertBook(bookDetailsRequest);
            log.info("kindleResponseEntity received {}", Objects.requireNonNull(kindleResponseResponseEntity.getBody()));
        } catch (Exception e){
            log.error("Listener received {} ", data + " which is not equal to bookDetailRequest");
        }
    }
}
