package com.felix.library.responseContainer;

import com.felix.library.entity.Member;
import lombok.Data;

import java.util.List;

@Data
public class ResponseMember {

    private List<Member> dataList;

    private String msg;

}
