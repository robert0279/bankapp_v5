package com.app.bank.controller;

import com.app.bank.domain.model.AccountDTO;
import com.app.bank.service.AccountService;
import com.app.bank.service.CardService;
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
    private final CardService cardService;

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
    @PutMapping ("/withdraw/{iban}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void withdraw (@PathVariable("iban") String iban, @RequestParam("withdraw") double withdraw, @RequestBody AccountDTO account){
        accountService.withdrawInBank(iban, withdraw);
        System.out.println("The new amount for account no " + iban + " is " + accountService.checkBalanceByIban(iban) + " $");
    }
    @PutMapping("/withdraw/pos/{cardNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void withdrawAtPos(@PathVariable("cardNumber")long cardNumber, @RequestParam("withdraw") long withdraw,
                              @RequestParam("pin") int pin, @RequestBody AccountDTO account){
        accountService.withdrawAtPos(cardNumber, pin, withdraw);
        System.out.println("The new amount for your account now is " + accountService.checkBalanceByIban(cardService.findIbanByCardNumber(cardNumber)) + " $");
    }
    @PutMapping ("/deposit/{iban}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deposit (@PathVariable("iban") String iban,@RequestParam("depositAmount") double depositAmount,  @RequestBody AccountDTO account ){

        accountService.depositInBank(iban,depositAmount);
        System.out.println("the new amount for account no " + iban + " is " + accountService.checkBalanceByIban(iban) + " $");
    }

}
