package com.maveric.balanceservice.service;

import com.maveric.balanceservice.dto.BalanceDto;

import java.util.List;

public interface BalanceService {
    //public List<BalanceDto> getBalances(String accountId,Integer page, Integer pageSize);

    BalanceDto getBalanceByAccountId(String accountId);

    public BalanceDto createBalance(String accountId, BalanceDto balanceDto);

    public BalanceDto getBalanceDetails(String accountId,String balanceId);
    public String deleteBalance(String balanceId);
    public String deleteBalancebyaccountId(String accountId);

    public BalanceDto updateBalance(String accountId,String balanceId,BalanceDto balanceDto);
}
