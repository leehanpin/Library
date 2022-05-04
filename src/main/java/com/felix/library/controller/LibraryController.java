package com.felix.library.controller;

import com.felix.library.entity.Book;
import com.felix.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lib")
public class LibraryController {

    @Autowired
    private LibraryService bookService;

    @PostMapping("/test")
    public void test(@RequestBody Map<String, String> test) {
        System.out.println(test);
    }

    @PostMapping("/insert")
    public void insertBook(@RequestBody Map<String, String> book) {
        bookService.insertBook(book.get("BookName"));
    }

    @GetMapping("/showList")
    public List<Book> showList() {
        return bookService.bookList();
    }

    @PostMapping("/borrowBook/{id}")
    public Book borrowBooks(@PathVariable(value = "id", required = false) Integer id) {
        return bookService.borrowBooks(id);
    }

    @PostMapping("/returnBook/{id}")
    public Book returnBooks(@PathVariable(value = "id", required = false) Integer id) {
        return bookService.returnBook(id);
    }
}
