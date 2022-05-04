package com.felix.library.service;

import com.felix.library.entity.Book;
import com.felix.library.entity.RentalRecord;
import com.felix.library.repository.BookRepository;
import com.felix.library.repository.RentalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RentalRecordRepository recordRepository;

    @Override
    public List<Book> bookList() {
        return bookRepository.findAll();
    }

    @Override
    public Book borrowBooks(Integer id) {
        Book book = bookRepository.findById(id);
        if (book == null) {
            return null;
        }

        if ("N".equalsIgnoreCase(book.getExisting())) {
            return book;
        }

        RentalRecord record = new RentalRecord();
        record.setBookName(book.getBookName());
        record.setStatus("借出");
        record.setBookId(book.getId());
        recordRepository.save(record);

        book.setExisting("N");
        bookRepository.save(book);
        return book;
    }

    @Override
    public Book returnBook(Integer id) {
        Book book = bookRepository.findById(id);

        if ("Y".equalsIgnoreCase(book.getExisting())) {
            return book;
        }
        RentalRecord record = new RentalRecord();
        record.setBookName(book.getBookName());
        record.setStatus("歸還");
        record.setBookId(book.getId());
        recordRepository.save(record);

        book.setExisting("Y");
        bookRepository.save(book);
        return book;
    }

    @Override
    public Book changeCondition(Integer id, String condition) {
        Book book = bookRepository.findById(id);

        if (book == null) return null;

        book.setCondition(condition);
        bookRepository.save(book);

        return book;
    }

    @Override
    public void insertBook(String BookName) {
        Book book = bookRepository.findByBookName(BookName);

        book = new Book();
        book.setBookName(BookName);
        book.setExisting("Y");
        book.setCondition("良好");
        bookRepository.save(book);

    }
}
