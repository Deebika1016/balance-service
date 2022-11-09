package com.maveric.balanceservice.service;



import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.mapper.BalanceMapper;
import com.maveric.balanceservice.model.Balance;
import com.maveric.balanceservice.repository.BalanceRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.maveric.balanceservice.BalanceServiceApplicationTests.getBalance;
import static com.maveric.balanceservice.BalanceServiceApplicationTests.getBalanceDto;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class BalanceServiceTest {

    @InjectMocks
    private BalanceServiceImpl service;

    @Mock
    private BalanceRepository repository;

    @Mock
    private BalanceMapper mapper;

    @Mock
    private Page pageResult;


    @Test
    public void testCreateBalance() throws Exception{
        when(mapper.map(any(BalanceDto.class))).thenReturn(getBalance());
        when(mapper.map(any(Balance.class))).thenReturn(getBalanceDto());
        when(repository.save(any())).thenReturn(getBalance());
        BalanceDto balanceDto = service.createBalance(getBalance().getAccountId(),getBalanceDto());
        assertSame(balanceDto.getAccountId(), getBalance().getAccountId());
    }

//    @Test
//    public void testGetBalance() {
//        Page<Balance> pagedResponse = new PageImpl(Arrays.asList(getBalance(),getBalance()));
//        when(repository.findAll(any(Pageable.class))).thenReturn(pagedResponse);
//        when(pageResult.hasContent()).thenReturn(true);
//        when(pageResult.getContent()).thenReturn(Arrays.asList(getBalance(),getBalance()));
//        when(mapper.mapToDto(any())).thenReturn(Arrays.asList(getBalanceDto(),getBalanceDto()));
//
//        List<BalanceDto> balances = service.getBalances(getBalance().getAccountId(),1,1);
//
//        assertEquals("123", balances.get(0).getAccountId());
//        assertEquals(Currency.INR, balances.get(1).getCurrency());
//    }

    @Test
    public void testGetBalanceById() {
        when(repository.findById("2")).thenReturn(Optional.of(getBalance()));
        when(mapper.map(any(Balance.class))).thenReturn(getBalanceDto());

        BalanceDto balanceDto = service.getBalanceDetails(getBalance().getAccountId(),"2");

        assertSame(balanceDto.getAccountId(),getBalanceDto().getAccountId());
    }

    @Test
    public void testDeleteBalanceById() {

        when(repository.findById("2")).thenReturn(Optional.of(getBalance()));
        willDoNothing().given(repository).deleteById("2");
        String balanceDto = service.deleteBalance("2");
        assertSame( "Balance deleted successfully.",balanceDto);
    }


}
