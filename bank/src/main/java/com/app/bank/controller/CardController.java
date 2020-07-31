package com.app.bank.controller;

import com.app.bank.domain.model.CardDTO;
import com.app.bank.domain.model.Status;
import com.app.bank.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@AllArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CardDTO> findAll (){
        return cardService.findAll();
    }

    @GetMapping("/cardNumber/{cardNumber}")
    @ResponseStatus(HttpStatus.OK)
    public long findIdByCardNumber(@PathVariable("cardNumber")long cardNumber){
        return cardService.findCardIdByCardNumber(cardNumber);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Status checkStatusById (@PathVariable("id")long id){
        return cardService.findStatusById(id);
    }
    @PutMapping("/block/{cardNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void blockCardByCardNumber(@PathVariable("cardNumber") long cardNumber, @RequestBody CardDTO card){
        cardService.blockCard(cardNumber);
    }
    @PutMapping("/unblock/{cardNumber}")
    @ResponseStatus(HttpStatus.CREATED)
    public  void unblockCardByCardNumber(@PathVariable("cardNumber") long cardNumber, @RequestBody CardDTO card){
        cardService.unblockCard(cardNumber);
    }

    @PostMapping("/{iban}")
    @ResponseStatus(HttpStatus.CREATED)
    public CardDTO createCard(@PathVariable("iban") String  iban, CardDTO cardDTO){
        return cardService.create(iban, cardDTO);
    }

}
