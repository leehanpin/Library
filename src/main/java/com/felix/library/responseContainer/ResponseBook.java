package com.felix.library.responseContainer;

import com.felix.library.entity.Book;
import lombok.Data;

import java.util.List;

@Data
public class ResponseBook {

    private List<Book> dataList;

    private String msg;
}
