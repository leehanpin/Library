package com.felix.library.responseContainer;

import com.felix.library.entity.RentalRecord;
import lombok.Data;

import java.util.List;

@Data
public class ResponseRentalRecord {

    private List<RentalRecord> dataList;

    private String msg;
}
