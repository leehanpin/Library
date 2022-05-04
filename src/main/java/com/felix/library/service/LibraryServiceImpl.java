package com.felix.library.service;

import com.felix.library.entity.Book;
import com.felix.library.entity.RentalRecord;
import com.felix.library.repository.BookRepository;
import com.felix.library.repository.RentalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, String> borrowBooks(String BookName) {
        Map<String , String> map = new HashMap<>();
        Book book = bookRepository.findByBookName(BookName);
        if (book == null){
            map.put("msg", "沒有該書籍");
            return map;
        }

        if (book.getExisting() == 0){
            map.put("msg", "該書籍全數已借出");
            return map;
        }

        RentalRecord record = new RentalRecord();
        record.setBookName(book.getBookName());
        record.setStatus("借出");
        book.setExisting(book.getExisting()-1);
        recordRepository.save(record);

        bookRepository.save(book);
        map.put("msg", "借書成功");
        return map;
    }

    @Override
    public Map<String, String> returnBook(String BookName) {
        Map<String , String> map = new HashMap<>();
        Book book = bookRepository.findByBookName(BookName);

        if (book.getQuantity() == book.getExisting()){
            map.put("msg", "該書並沒有借出，因此不需歸還");
            return map;
        }
        RentalRecord record = new RentalRecord();
        record.setBookName(book.getBookName());
        record.setStatus("歸還");
        recordRepository.save(record);

        book.setExisting(book.getExisting()+1);
        bookRepository.save(book);
        map.put("msg", "還書成功");
        return map;
    }

    @Override
    public void insertBook(String BookName) {

        Book book = bookRepository.findByBookName(BookName);
        if (book == null){
            book = new Book();
            book.setBookName(BookName);
            book.setQuantity(1);
            book.setExisting(1);
            bookRepository.save(book);
        }else {
            book.setQuantity(book.getQuantity()+1);
            book.setExisting(book.getExisting()+1);
            bookRepository.save(book);
        }

    }
}
