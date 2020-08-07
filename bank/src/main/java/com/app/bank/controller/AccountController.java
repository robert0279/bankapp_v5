package com.app.bank.controller;

import com.app.bank.domain.model.AccountDTO;
import com.app.bank.service.AccountService;
import com.app.bank.service.CardService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayDeque;
import java.util.List;

@SecurityRequirement(name = "http_basic")
@RestController
@RequestMapping("users/{id}/accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final CardService cardService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDTO> findAll(){
        return accountService.findAll();
    }


  @GetMapping("/iban")
  @ResponseStatus(HttpStatus.OK)
  public AccountDTO findAccountByIban(@RequestParam("iban") String iban){
      return accountService.findAccountByIban(iban);
  }
   @GetMapping("/cnp")
   @ResponseStatus(HttpStatus.OK)
   public List<AccountDTO> findAllAccountByCnp(@RequestParam("cnp")long cnp){
       return accountService.findAllAccountsByCnp(cnp);
   }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO createAccount(@PathVariable ("id") long id,  @RequestBody AccountDTO accountDTO){
        return accountService.createAccount(accountDTO,id);
    }
    @PostMapping("/withdraw")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void withdraw (@RequestParam("iban") String iban, @RequestParam("withdraw") double withdraw, @RequestBody AccountDTO account){
        accountService.withdrawInBank(iban, withdraw);
        System.out.println("The new amount for account no " + iban + " is " + accountService.checkBalanceByIban(iban) + " $");
    }
    @PostMapping("/withdraw/pos")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void withdrawAtPos( @RequestParam("cardNumber")long cardNumber, @RequestParam("withdraw") long withdraw,
                              @RequestParam("pin") int pin, @RequestBody AccountDTO account){
        accountService.withdrawAtPos(cardNumber, pin, withdraw);
        System.out.println("The new amount for your account now is " + accountService.checkBalanceByIban(cardService.findIbanByCardNumber(cardNumber)) + " $");
    }
    @PostMapping("/deposit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deposit ( @RequestParam("iban") String iban,@RequestParam("depositAmount") double depositAmount,  @RequestBody AccountDTO account ){

        accountService.depositInBank(iban,depositAmount);
        System.out.println("the new amount for account no " + iban + " is " + accountService.checkBalanceByIban(iban) + " $");
    }
   @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void transfer( @RequestParam("amountToTransfer") double amountToTransfer, @RequestParam("ibanFrom") String ibanFrom,
                         @RequestParam("ibanTo")String ibanTo, @RequestBody AccountDTO account){
        accountService.transferMoney(ibanFrom, ibanTo, amountToTransfer);
    }

}
