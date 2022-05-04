package com.felix.library.entity.container;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookContainer {

    private  Integer id;

    private String bookName;

    private String existing;

    private String condition;
}
