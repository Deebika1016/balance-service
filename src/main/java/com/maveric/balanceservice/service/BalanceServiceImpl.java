package com.maveric.balanceservice.service;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.exceptionhandler.BalanceNotFoundException;
import com.maveric.balanceservice.exceptionhandler.BalanceNotFoundException;
import com.maveric.balanceservice.mapper.BalanceMapper;
import com.maveric.balanceservice.model.Balance;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.maveric.balanceservice.constants.Constants.getCurrentDateTime;

@Service
public class BalanceServiceImpl implements BalanceService{
    @Autowired
    private BalanceRepository repository;

    @Autowired
    private BalanceMapper mapper;
    @Override
    public BalanceDto createBalance(BalanceDto balanceDto) {
        balanceDto.setCreatedAt(getCurrentDateTime());
        balanceDto.setUpdatedAt(getCurrentDateTime());
        Balance balance = mapper.map(balanceDto);
        Balance balanceResult = repository.save(balance);
        return  mapper.map(balanceResult);
    }

    public List<BalanceDto> getBalances(Integer page, Integer pageSize) {
        Pageable paging = (Pageable) PageRequest.of(page, pageSize);
        Page<Balance> pageResult = repository.findAll(paging);
        if(pageResult.hasContent()) {
            return pageResult.getContent().stream()
                    .map(
                            transaction -> mapper.map(transaction)
                    ).collect(
                            Collectors.toList()
                    );
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public BalanceDto getBalanceDetails(String balanceId) {
        Balance balanceResult=repository.findById(balanceId).orElseThrow(() -> new BalanceNotFoundException("Balance not found"));
        return mapper.map(balanceResult);
    }

    @Override
    public BalanceDto updateBalance(String balanceId, BalanceDto balanceDto) {
        Balance balanceResult=repository.findById(balanceId).orElseThrow(() -> new BalanceNotFoundException("Balance not found"));
        balanceResult.set_id(balanceResult.get_id());
        balanceResult.setAmount(balanceDto.getAmount());
        balanceResult.setCurrency(balanceDto.getCurrency());
        balanceResult.setAccountId(balanceResult.getAccountId());
        balanceResult.setCreatedAt(balanceResult.getCreatedAt());
        balanceResult.setUpdatedAt(getCurrentDateTime());
        Balance accountUpdated = repository.save(balanceResult);
        return mapper.map(accountUpdated);
    }
    @Override
    public String deleteBalance(String balanceId) {
        repository.deleteById(balanceId);
        return "Balance deleted successfully.";
    }
}
