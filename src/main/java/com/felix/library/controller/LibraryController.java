package com.felix.library.controller;

import com.felix.library.entity.Book;
import com.felix.library.entity.Member;
import com.felix.library.entity.RentalRecord;
import com.felix.library.entity.container.BookContainer;
import com.felix.library.entity.container.MemberContainer;
import com.felix.library.entity.container.RentalRecordContainer;
import com.felix.library.responseContainer.ResponseBook;
import com.felix.library.responseContainer.ResponseMember;
import com.felix.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lib")
public class LibraryController {

    @Autowired
    private LibraryService bookService;

    //這邊純粹亂玩
    @PostMapping("/sys")
    public void test(@RequestBody Member ttt) {

        System.out.println(ttt);
    }

    @PostMapping("/insert")
    public ResponseBook insertBook(@RequestBody Book book) {
        BookContainer container = BookContainer.builder()
                .bookName(book.getBookName()).build();

        return bookService.insertBook(container.getBookName());
    }

    @PostMapping("/register")
    public ResponseMember register(@RequestBody Member member) {
        MemberContainer container = MemberContainer.builder()
                .name(member.getName())
                .mail(member.getMail())
                .phone(member.getPhone())
                .build();
        return bookService.register(container);
    }

    @GetMapping("/showList")
    public ResponseBook showList() {
        return bookService.bookList();
    }

    @PostMapping("/borrowBook")
    public ResponseBook borrowBooks(@RequestBody RentalRecord rentalRecord) {
        RentalRecordContainer container = RentalRecordContainer.builder()
                .bookId(rentalRecord.getBookId())
                .memberId(rentalRecord.getMemberId())
                .build();
        return bookService.borrowBooks(container);
    }

    @PostMapping("/returnBook")
    public ResponseBook returnBooks(@RequestBody RentalRecord rentalRecord) {
        RentalRecordContainer container = RentalRecordContainer.builder()
                .bookId(rentalRecord.getBookId())
                .memberId(rentalRecord.getMemberId())
                .build();
        return bookService.returnBook(container);
    }

    @PutMapping("/condition")
    public ResponseBook changeCondition(@RequestBody Book book) {
        BookContainer container = BookContainer.builder()
                .id(book.getId())
                .condition(book.getCondition())
                .build();
        return bookService.changeCondition(container);
    }

}
