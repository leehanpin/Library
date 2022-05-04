package com.felix.library.service;

import com.felix.library.entity.Book;

import java.util.List;
import java.util.Map;

public interface LibraryService extends Library {

    List<Book> bookList();

    Book borrowBooks(Integer id);

    Book returnBook(Integer id);

    Book changeCondition(Integer id, String condition);

    void insertBook(String BookName);
}
