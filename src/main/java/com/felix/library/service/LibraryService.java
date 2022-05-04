package com.felix.library.service;

import com.felix.library.entity.Book;

import java.util.List;
import java.util.Map;

public interface LibraryService extends Library {

    List<Book> bookList();

    Map<String, String> borrowBooks(String BookName);

    Map<String, String> returnBook(String BookName);

    void insertBook(String BookName);
}
