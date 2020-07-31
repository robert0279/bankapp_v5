package com.app.bank.controller;

import com.app.bank.domain.model.AccountDTO;
import com.app.bank.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayDeque;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDTO> findAll(){
        return accountService.findAll();
    }
    @GetMapping("/{iban}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO findAccountByIban(@PathVariable("iban") String iban){
        return accountService.findAccountByIban(iban);
    }
    @GetMapping("/cnp/{cnp}")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDTO> findAllAccountByCnp(@PathVariable("cnp")long cnp){
        return accountService.findAllAccountsByCnp(cnp);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO createAccount(@PathVariable ("id") long id,  @RequestBody AccountDTO accountDTO){
        return accountService.createAccount(accountDTO,id);
    }

}
