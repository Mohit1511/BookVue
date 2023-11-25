package org.example.library.service.impl;

import org.example.library.dao.entities.AuthorDetails;
import org.example.library.dao.helpers.LibraryDetailsHelper;
import org.example.library.dao.objects.AuthorDetailsResponse;
import org.example.library.dao.objects.KindleResponse;
import org.example.library.dao.repositories.AuthorDetailsRepository;
import org.example.library.service.AuthorDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IAuthorDetailsServiceImpl implements AuthorDetailsService {
    @Autowired
    private AuthorDetailsRepository authorDetailsRepository;

    @Autowired
    private LibraryDetailsHelper libraryDetailsHelper;

    @Override
    public KindleResponse fetchAllAuthors() {
        KindleResponse<List<AuthorDetailsResponse>> kindleResponse = new KindleResponse<>();
        List<AuthorDetails> authorDetailsList = authorDetailsRepository.findAll();
        List<AuthorDetailsResponse> authorDetailsResponseList = new ArrayList<>();
        if(authorDetailsList.size() > 0){
            for(AuthorDetails authorDetails: authorDetailsList){
                AuthorDetailsResponse authorDetailsResponse = libraryDetailsHelper.createAuthorDetailsResponse(authorDetails);
                authorDetailsResponseList.add(authorDetailsResponse);
            }
            kindleResponse = libraryDetailsHelper.createKindleResponse("SUCCESS");
            kindleResponse.setResponse(authorDetailsResponseList);
            return kindleResponse;
        }
        kindleResponse = libraryDetailsHelper.createKindleResponse("FAILURE");
        return kindleResponse;
    }
}
