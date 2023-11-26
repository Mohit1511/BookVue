package book.vue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class Library {
    private static final Logger log = LoggerFactory.getLogger(Library.class);

    public static void main(String[] args) {
        SpringApplication.run(Library.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String,String> kafkaTemplate){
       return args -> {
           kafkaTemplate.send("insertBook","sayHelloBookShop");
//           kafkaTemplate.send("insertBook","{\n" +
//                   "    \"title\":\"The Lores Ipsum\",\n" +
//                   "    \"noOfPages\":38,\n" +
//                   "    \"isbnNumber\":\"0-447-40615-2\",\n" +
//                   "    \"authorName\":\"Rumi Palak\",\n" +
//                   "    \"genre\":\"DRAMA\",\n" +
//                   "    \"content\":\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque non mollis diam. Sed gravida dolor ac orci condimentum, et tempus massa ultricies. Cras dignissim ultricies est ac ullamcorper. Integer facilisis rutrum urna, vitae ultricies nibh maximus posuere. Donec at malesuada mi. Nullam egestas condimentum porttitor. Donec ac nunc felis. Etiam elementum malesuada sodales. Nullam ultrices lacus et metus vehicula scelerisque. Aenean accumsan condimentum dolor, at molestie leo tempor a. Praesent vitae tincidunt leo. Mauris cursus blandit tincidunt. Nulla quis tempus lectus. Vestibulum lectus purus, euismod eleifend sem a, efficitur congue diam\"\n" +
//                   "}");
       };
    }
}