package com.felix.library.service;

import com.felix.library.entity.Book;
import com.felix.library.entity.Member;
import com.felix.library.entity.RentalRecord;
import com.felix.library.entity.container.BookContainer;
import com.felix.library.entity.container.MemberContainer;
import com.felix.library.entity.container.RentalRecordContainer;
import com.felix.library.repository.BookRepository;
import com.felix.library.repository.MemberRepository;
import com.felix.library.repository.RentalRecordRepository;
import com.felix.library.responseContainer.ResponseBook;
import com.felix.library.responseContainer.ResponseMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RentalRecordRepository recordRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public ResponseBook bookList() {
        ResponseBook container = new ResponseBook();
        container.setDataList(bookRepository.findAll());
        container.setMsg("書目清單");
        return container;
    }

    @Override
    public ResponseBook borrowBooks(RentalRecordContainer container) {
        ResponseBook response = new ResponseBook();

        Book book = bookRepository.findById(container.getBookId());
        if (book == null) {
            response.setMsg("無清單資料");
            return response;
        }

        if ("N".equalsIgnoreCase(book.getExisting())) {
            response.setMsg("該書籍已借出");
            return response;
        }

        Member member = memberRepository.findById(container.getMemberId());

        if (member == null){
            response.setMsg("無該用戶");
        }

        RentalRecord record = new RentalRecord();
        record.setBookName(book.getBookName());
        record.setStatus("2");//2:借出
        record.setBookId(book.getId());
        record.setMemberId(member.getId());
        recordRepository.save(record);

        book.setExisting("N");
        bookRepository.save(book);

        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        response.setDataList(bookList);
        response.setMsg("借書成功");

        return response;
    }

    @Override
    public ResponseBook returnBook(RentalRecordContainer container) {
        ResponseBook response = new ResponseBook();
        Book book = bookRepository.findById(container.getBookId());

        if (book == null) {
            response.setMsg("無清單資料");
            return response;
        }

        if ("Y".equalsIgnoreCase(book.getExisting())) {
            response.setMsg("沒有書需要歸還");
            return response;
        }

        Member member = memberRepository.findById(container.getMemberId());

        if (member == null){
            response.setMsg("無該用戶");
        }

        RentalRecord record = new RentalRecord();
        record.setBookName(book.getBookName());
        record.setStatus("1");//1:歸還
        record.setBookId(book.getId());
        record.setMemberId(member.getId());
        recordRepository.save(record);

        book.setExisting("Y");
        bookRepository.save(book);

        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        response.setDataList(bookList);
        response.setMsg("還書成功");
        return response;
    }

    @Override
    public ResponseBook changeCondition(BookContainer container) {
        ResponseBook response = new ResponseBook();
        Book book = bookRepository.findById(container.getId());

        if (book == null) return null;

        book.setCondition(container.getCondition());
        bookRepository.save(book);
        response.setMsg("書本健康度更新");
        return response;
    }

    @Override
    public ResponseBook insertBook(String BookName) {
        Book book = new Book();
        book.setBookName(BookName);
        book.setExisting("Y");
        book.setCondition("良好");
        bookRepository.save(book);

        ResponseBook container = new ResponseBook();
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        container.setDataList(bookList);
        container.setMsg("書籍增加成功");
        return container;
    }

    @Override
    public ResponseMember register(MemberContainer container) {
        Member member = new Member();
        member.setMail(container.getMail());
        member.setName(container.getName());
        member.setPhone(container.getPhone());
        memberRepository.save(member);

        List<Member> memberList = new ArrayList<>();
        memberList.add(member);

        ResponseMember response = new ResponseMember();
        response.setDataList(memberList);
        response.setMsg("用戶新增成功");
        return response;
    }
}
