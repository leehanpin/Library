package com.felix.library.entity.container;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberContainer {

    private  Integer id;

    private String name;

    private String mail;

    private String phone;
}
