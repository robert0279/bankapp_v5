package com.app.bank.controller;

import com.app.bank.domain.model.CardDTO;
import com.app.bank.domain.model.Status;
import com.app.bank.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users/accounts/{id}/cards")
@AllArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CardDTO> findAll() {
        return cardService.findAll();
    }

    @GetMapping("/cardNumber")
    @ResponseStatus(HttpStatus.OK)
    public long findIdByCardNumber(@RequestParam("cardNumber") long cardNumber) {
        return cardService.findCardIdByCardNumber(cardNumber);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Status checkStatusById(@PathVariable("id") long id) {
        return cardService.findStatusById(id);
    }

    @PutMapping("/block")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void blockCardByCardNumber(@RequestParam("cardNumber") long cardNumber, @RequestBody CardDTO card) {
        cardService.blockCard(cardNumber);
    }

    @PatchMapping("/unblock")
    @ResponseStatus(HttpStatus.CREATED)
    public void unblockCardByCardNumber(@RequestParam("cardNumber") long cardNumber, @RequestBody CardDTO card) {
        cardService.unblockCard(cardNumber);
    }

    @PatchMapping("/changePin")
    @ResponseStatus(HttpStatus.CREATED)
    public void changePin(@RequestParam("cardNumber") long cardNumber, @RequestParam("initialPin") int initialPin,
                          @RequestParam("newPin") int newPin, @RequestParam("newPinAgain") int newPinAgain) {
        cardService.changePin(cardNumber, initialPin, newPin, newPinAgain);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CardDTO createCard(@PathVariable("id") long id, @RequestBody CardDTO cardDTO) {
        return cardService.create(id, cardDTO);
    }
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCardByCardNumber (@RequestParam("cardNumber") long cardNumber){
        cardService.deleteCardByCardNumber(cardNumber);}

}
