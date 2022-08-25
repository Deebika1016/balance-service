package com.maveric.balanceservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Document(collection = "Balance")
public class Balance {
    @Id
    private String _id;
    private String accountId;
    private Number amount;
    private String currency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
