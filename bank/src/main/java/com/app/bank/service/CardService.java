package com.app.bank.service;

import com.app.bank.domain.entity.AccountEntity;
import com.app.bank.domain.entity.CardEntity;
import com.app.bank.domain.model.AccountDTO;
import com.app.bank.domain.model.CardDTO;
import com.app.bank.domain.model.Status;
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

    public List<CardDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(cardEntityToCardMapper::convert)
                .collect(Collectors.toList());

    }

    public CardDTO create(String iban, CardDTO cardDTO) {
      AccountEntity accountEntity = accountRepository.findById(branchRepository.findIdByIban(iban))
              .orElseThrow(()-> new RuntimeException("The IBAN " + iban + "you have provided, does not exists") );

      CardEntity cardTobeSaved = cardToCardEntityMapper.convert(cardDTO);
      cardTobeSaved.setIban(accountEntity.getIban());
      repository.save(cardTobeSaved);
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







}
