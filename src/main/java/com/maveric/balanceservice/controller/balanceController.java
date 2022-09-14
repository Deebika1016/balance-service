package com.maveric.balanceservice.controller;

import com.maveric.balanceservice.dto.BalanceDto;
import com.maveric.balanceservice.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/api/v1")
public class BalanceController {
    @Autowired
    BalanceService balanceService;


//    @GetMapping("accounts/{accountId}/balances")
//    public ResponseEntity<List<BalanceDto>> getBalances(@PathVariable String accountId, @RequestParam(defaultValue = "0") Integer page,
//                                                                @RequestParam(defaultValue = "10") Integer pageSize) {
//        List<BalanceDto> balanceDtoResponse = balanceService.getBalances(accountId,page,pageSize);
//        return new ResponseEntity<List<BalanceDto>>(balanceDtoResponse, HttpStatus.OK);
//    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("accounts/{accountId}/balances")
    public ResponseEntity<BalanceDto> getBalances(@PathVariable String accountId) {
        BalanceDto balanceDtoResponse = balanceService.getBalanceByAccountId(accountId);
        return new ResponseEntity<BalanceDto>(balanceDtoResponse, HttpStatus.OK);
    }
    @PostMapping("accounts/{accountId}/balances")
    public ResponseEntity<BalanceDto> createBalance(@PathVariable String accountId,@Valid @RequestBody BalanceDto balanceDto) {
        BalanceDto BalanceDtoResponse = balanceService.createBalance(accountId,balanceDto);
        return new ResponseEntity<BalanceDto>(BalanceDtoResponse, HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("accounts/{accountId}/balances/{balanceId}")
    public ResponseEntity<String> getBalanceDetails(@PathVariable String accountId,@PathVariable String balanceId) {
        BalanceDto BalanceDtoResponse = balanceService.getBalanceDetails(accountId,balanceId);
        return new ResponseEntity<String>(String.valueOf(BalanceDtoResponse.getAmount()), HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("accounts/{accountId}/balances/{balanceId}")
    public ResponseEntity<BalanceDto> updateBalance(@PathVariable String accountId,@PathVariable String balanceId,@Valid @RequestBody BalanceDto balanceDto) {
        BalanceDto balanceDtoResponse = balanceService.updateBalance(accountId,balanceId,balanceDto);
        return new ResponseEntity<BalanceDto>(balanceDtoResponse, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("accounts/{accountId}/balances/{balancesId}")
    public ResponseEntity<String> deleteBalance(@PathVariable String accountId,@PathVariable String balancesId) {
        String result = balanceService.deleteBalance(balancesId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("accounts/{accountId}/balances")
    public ResponseEntity<String> deleteBalance(@PathVariable String accountId) {
        String result = balanceService.deleteBalancebyaccountId(accountId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
