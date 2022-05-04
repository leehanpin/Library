package com.felix.library.service;

import com.felix.library.entity.Member;
import com.felix.library.entity.container.BookContainer;
import com.felix.library.entity.container.MemberContainer;
import com.felix.library.entity.container.RentalRecordContainer;
import com.felix.library.responseContainer.ResponseBook;
import com.felix.library.responseContainer.ResponseMember;

public interface LibraryService extends Library {

    ResponseBook bookList();

    ResponseBook borrowBooks(RentalRecordContainer container);

    ResponseBook returnBook(RentalRecordContainer container);

    ResponseBook changeCondition(BookContainer container);

    ResponseBook insertBook(String BookName);

    ResponseMember register(MemberContainer member);

}
