package com.app.bank.service;

import com.app.bank.domain.entity.AccountEntity;
import com.app.bank.domain.entity.CardEntity;
import com.app.bank.domain.model.AccountDTO;
import com.app.bank.domain.model.CardDTO;
import com.app.bank.domain.model.Status;
import com.app.bank.exception.UserNotFindException;
import com.app.bank.mapper.CardEntityToCardMapper;
import com.app.bank.mapper.CardToCardEntityMapper;
import com.app.bank.repository.AccountRepository;
import com.app.bank.repository.BranchRepository;
import com.app.bank.repository.CardRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class CardService {

    private final CardRepository repository;

    private final CardEntityToCardMapper cardEntityToCardMapper;

    private final CardToCardEntityMapper cardToCardEntityMapper;

    private final AccountRepository accountRepository;

    private final BranchRepository branchRepository;
    private final EmailService mailService;

    private static int flag = 0;
    private static long tempCardNumber = 0;

    public List<CardDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(cardEntityToCardMapper::convert)
                .collect(Collectors.toList());

    }

    public CardDTO create(long accountId, CardDTO cardDTO) {
      AccountEntity accountEntity = accountRepository.findById(accountId)
             .orElseThrow(()-> new RuntimeException("The Account Id " + accountId + "you have provided, does not exists") );

      CardEntity cardTobeSaved = cardToCardEntityMapper.convert(cardDTO);
      cardTobeSaved.setIban(accountEntity.getIban());
      repository.save(cardTobeSaved);
      mailService.sendEmail("robert.serbanoiu@yahoo.com", "Pin number from the bank", "your Pin number is " + cardTobeSaved.getPin());
      return cardEntityToCardMapper.convert(cardTobeSaved);
    }
    public long findCardIdByCardNumber(long cardNumber) {
        long id= branchRepository.findIdByCardNumber(cardNumber);
        if (id!=0){
            return id;
        } else {
            System.out.println("The card number you have provided is not valid\n" +
                    "Please try again");
            return 99999999999999L;
        }
    }
    public Status findStatusById(long id) {
        CardDTO card = cardEntityToCardMapper.convert(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("The Card with the id " + id + " does not exists")));

        return card.getStatus();
    }
    public boolean checkIfCardIsActive(long cardNumber) {
        if (findStatusById(findCardIdByCardNumber(cardNumber)).equals(Status.ACTIVE)) {
            return true;
        }else {
            return false;}

    }
    @Transactional
    public void blockCard(long cardNumber) {
        CardEntity cardToBeBlocked = repository.findById(findCardIdByCardNumber(cardNumber))
                .orElseThrow(() -> new RuntimeException("The Card with the Card Number" + cardNumber + " is not valid"));
        if (checkIfCardIsActive(cardNumber)) {
            cardToBeBlocked.setStatus(Status.BLOCKED);
            cardToBeBlocked.setLastUpdated(LocalDateTime.now());
        } else {
            System.out.println("The Card with the Card Number + " + cardNumber + " is already Blocked " );

        }
    }

    @Transactional
    public void unblockCard(long cardNumber) {

        CardEntity cardToBeUnblocked = repository.findById(findCardIdByCardNumber(cardNumber))
                .orElseThrow(() -> new RuntimeException("The Card with the Card Number" + cardNumber + " is not valid"));
        if (!checkIfCardIsActive(cardNumber)) {
            cardToBeUnblocked.setStatus(Status.ACTIVE);
            cardToBeUnblocked.setLastUpdated(LocalDateTime.now());

        } else {
            System.out.println("The Card with the Card Number + " + cardNumber + " is already Active " );

        }
    }

    public CardDTO findCardByCardNumber(long cardNumber) {

        CardEntity cardEntity = repository.findById(findCardIdByCardNumber(cardNumber))
                .orElseThrow(()->new RuntimeException("The card Number you have provided is not valid\n" +
                        "Please try again"));
        return cardEntityToCardMapper.convert(cardEntity);
    }

    public boolean checkExpirationDate (long cardNumber) {
        if (LocalDateTime.now().isBefore(findCardByCardNumber(cardNumber).getExpirationDate().minusMonths(6))) {
            return true;
        } else if((LocalDateTime.now().isBefore(findCardByCardNumber(cardNumber).getExpirationDate()))) {
            System.out.println("The card will expire within the next 6 months");
            return true;
        }else {
            System.out.println("Your card is expired\n" +
                    "Your card will be blocked");
            branchRepository.blockCard(findCardIdByCardNumber(cardNumber));
            return false;}
    }

    public boolean checkIfOkForWithdraw(long cardNumber, int pin) {
        if (checkIfCardIsActive(cardNumber)) {

            if (findCardByCardNumber(cardNumber).getPin().equals( pin)) {
                return true;
            } else if (cardNumber != tempCardNumber) {
                tempCardNumber = cardNumber;
                flag = 0;
                System.out.println("The pin it's incorrect, please try again \n" +
                        "You have 2 more attempts, before your card will be blocked");
                flag=2;
                return false;
            } else if (flag!=3) {
                flag += 1;
                System.out.println("The pin it's incorrect, please try again\n" +
                        "You have one more attempt to provide the right pin, otherwise your card will be blocked");
                return false;
            } else {
                System.out.println("You have provided 3 times an incorrect pin, therefore your card is blocked\n" +
                        "Please contact our bank helpDesk");
                flag =0;
                branchRepository.blockCard(findCardIdByCardNumber(cardNumber));
                // branchRepository.setLastUpdate(Date.valueOf(LocalDateTime.now().toLocalDate()), findCardIdByCardNumber(cardNumber));
                findCardByCardNumber(cardNumber).setLastUpdated(LocalDateTime.now());
            }


        }  else {
            System.out.println("Your card with the card number " + cardNumber + " is blocked\n" +
                    "The withdraw operation was aborted\n" +
                    "Please contact our bank helpDesk");
            return false;
        }
        return false;
    }

    @Transactional
    public void changePin (long cardNumber, String initialPin, String newPin, String newPinAgain){
        CardEntity cardToChangePin = repository.findById(branchRepository.findIdByCardNumber(cardNumber))
                .orElseThrow(()->new RuntimeException("The Card number is wrong"));
        if (cardToChangePin.getPin().equals( initialPin) && !initialPin.equals(newPin)){
            if (newPin .equals(newPinAgain)){
                cardToChangePin.setPin(newPin);
                //cardToChangePin.setLastUpdated(LocalDateTime.now());
                System.out.println("The Pin was successfully changed \n" +
                        "The new pin number is " + cardToChangePin.getPin());
            } else {
                System.out.println("Your new pin does not match with the retyped new pin\n" +
                        "Please try again");
            }
        }else {
            System.out.println("You must provide the right existing pin, and this must be different from the new pin\n" +
                    "Please try again");
        }
    }

    public void deleteCardByCardNumber(long cardNumber) {
        CardEntity cardToBeDeleted = repository.findById(findCardIdByCardNumber(cardNumber))
                .orElseThrow(() -> new RuntimeException("The card with the Card Number " + cardNumber + " does not txists"));
        branchRepository.addDeletedCardToTable(cardToBeDeleted.getIban(), cardNumber);
        repository.deleteById(cardToBeDeleted.getId());
        System.out.println("The card with the Card Number " + cardNumber + " was successfully deleted");

    }
    public String findIbanByCardNumber(long cardNumber) {
        return findCardByCardNumber(cardNumber).getIban();
    }








}
