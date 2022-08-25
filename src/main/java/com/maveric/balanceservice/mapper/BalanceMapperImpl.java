package com.maveric.balanceservice.mapper;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.model.Balance;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BalanceMapperImpl implements BalanceMapper {
    @Override
    public Balance map(BalanceDto balanceDto) {
        return new Balance(
                balanceDto.get_id(),
                balanceDto.getAccountId(),
                balanceDto.getAmount(),
                balanceDto.getCurrency(),
                balanceDto.getCreatedAt(),
                balanceDto.getUpdatedAt()
        );
    }

    @Override
    public BalanceDto map(Balance balance) {
        return new BalanceDto(
                balance.get_id(),
                balance.getAccountId(),
                balance.getAmount(),
                balance.getCurrency(),
                balance.getCreatedAt(),
                balance.getUpdatedAt()
        );
    }

    @Override
    public List<Balance> map(List<BalanceDto> balances) {
        List<Balance> list = new ArrayList<Balance>(balances.size());
        for(BalanceDto balanceDto:balances)
        {
            list.add(map(balanceDto));
        }
        return list;
    }
}
