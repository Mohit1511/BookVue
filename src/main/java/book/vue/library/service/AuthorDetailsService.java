package book.vue.library.service;

import book.vue.library.dao.objects.KindleResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthorDetailsService {
    public KindleResponse fetchAllAuthors();
}
