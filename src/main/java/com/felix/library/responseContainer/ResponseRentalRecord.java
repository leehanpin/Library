package com.felix.library.responseContainer;

import com.felix.library.repository.RentalRecordRepository;
import lombok.Data;

import java.util.List;

@Data
public class ResponseRentalRecord {

    private List<RentalRecordRepository> dataList;

    private String msg;
}
