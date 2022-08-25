package com.maveric.balanceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BalanceDto {
    private String  _id;
    private String accountId;
    private Number amount;
    private String currency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
