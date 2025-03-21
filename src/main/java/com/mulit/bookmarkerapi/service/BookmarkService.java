package com.mulit.bookmarkerapi.service;

import com.mulit.bookmarkerapi.dto.BookmarkDTO;
import com.mulit.bookmarkerapi.dto.BookmarkMapper;
import com.mulit.bookmarkerapi.dto.BookmarksDTO;
import com.mulit.bookmarkerapi.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final BookmarkMapper bookmarkMapper;

    @Transactional(readOnly = true)

    public BookmarksDTO getBookmarks(Integer page) {
        int pageNo = page<1?1:page-1;
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC,"createdAt");
        Page<BookmarkDTO> bookmarkDTOPage=  bookmarkRepository.findAll(pageable).map(bookmarkMapper::toDTO);
        return new BookmarksDTO(bookmarkDTOPage);

    }


}
