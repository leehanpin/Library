package com.felix.library.repository;

import com.felix.library.entity.RentalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RentalRecordRepository  extends JpaRepository<RentalRecord, String>, JpaSpecificationExecutor<RentalRecord> {
}
