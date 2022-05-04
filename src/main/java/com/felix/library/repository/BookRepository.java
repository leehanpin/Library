package com.felix.library.repository;

import com.felix.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String>, JpaSpecificationExecutor<Book> {

    @Query(name = "SELECT * FROM BookList WHERE bookName = ?1")
    Book findByBookName(String BookName);

    Book findById(Integer id);

}
