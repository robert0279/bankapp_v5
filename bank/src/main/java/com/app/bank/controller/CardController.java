package com.app.bank.controller;

import com.app.bank.domain.model.CardDTO;
import com.app.bank.domain.model.Status;
import com.app.bank.service.CardService;
import com.app.bank.service.EmailService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "http_basic")
@RestController
@RequestMapping("users/accounts/{id}/cards")
@AllArgsConstructor
public class CardController {

    private final CardService cardService;
    private final EmailService mailService;

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
    public void changePin(@RequestParam("cardNumber") long cardNumber, @RequestParam("initialPin") String initialPin,
                          @RequestParam("newPin") String newPin, @RequestParam("newPinAgain") String newPinAgain) {
        cardService.changePin(cardNumber, initialPin, newPin, newPinAgain);
    }
    @PatchMapping("/resetPin")
    @ResponseStatus(HttpStatus.CREATED)
    public void resetPin(@RequestParam("cardNumber")long cardNumber){
        cardService.resetPin(cardNumber);
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

        @GetMapping("/email")
    public String sendMail (@RequestParam("to") String to, @RequestParam("subject") String subject, @RequestParam("txt") String txt){
        mailService.sendEmail(to, subject, txt);
        return txt;
        }





}
