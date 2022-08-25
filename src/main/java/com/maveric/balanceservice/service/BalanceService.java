package com.maveric.balanceservice.service;

import com.maveric.balanceservice.dto.BalanceDto;

import java.util.List;

public interface BalanceService {
    public BalanceDto createBalance(BalanceDto balanceDto);

    public List<BalanceDto> getBalances(Integer page, Integer pageSize);

    public BalanceDto getBalanceDetails(String balanceId);

    public BalanceDto updateBalance(String balanceId,BalanceDto balanceDto);

    public String deleteBalance(String balanceId);

}
