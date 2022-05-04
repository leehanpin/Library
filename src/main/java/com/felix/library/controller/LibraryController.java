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

    //這邊純粹亂玩
    @GetMapping("/sys/{application}/{id}")
    public Book test(@PathVariable(value = "application", required = false) String application,
                     @PathVariable(value = "id", required = false) Integer id) {
        System.out.println(application);
        System.out.println(id);
        if ("borrowBook".equalsIgnoreCase(application)){
            return bookService.borrowBooks(id);
        }else if ("returnBook".equalsIgnoreCase(application)){
            return bookService.returnBook(id);
        }
        return new Book();
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

    @PutMapping("/condition/{id}/{condition}")
    public Book changeCondition(@PathVariable(value = "id", required = false) Integer id,
                                @PathVariable(value = "condition", required = false) String condition){
        return bookService.changeCondition(id, condition);
    }
}
