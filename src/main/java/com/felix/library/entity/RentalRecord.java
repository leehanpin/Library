package com.felix.library.entity;





import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "RentalRecord")
public class RentalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer bookId;

    private Integer memberId;

    private String bookName;

    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "timestamp")
    private Date timestamp;

    @PrePersist // 設定物件轉換為 Persistent 以前執行
    private void onCreate() {
        if(timestamp == null) {
            timestamp = new Date();
        }
    }

}
