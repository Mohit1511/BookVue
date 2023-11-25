package org.example.library.service;

import org.example.library.dao.objects.KindleResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthorDetailsService {
    public KindleResponse fetchAllAuthors();
}
