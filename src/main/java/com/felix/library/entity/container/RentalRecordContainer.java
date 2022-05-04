package com.felix.library.entity.container;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RentalRecordContainer {

    private Integer memberId;

    private Integer bookId;
}
