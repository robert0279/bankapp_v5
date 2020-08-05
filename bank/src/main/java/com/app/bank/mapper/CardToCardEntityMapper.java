package com.app.bank.mapper;

import com.app.bank.domain.entity.CardEntity;
import com.app.bank.domain.model.CardDTO;
import com.app.bank.domain.model.Status;
import com.app.bank.repository.BranchRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
@AllArgsConstructor
public class CardToCardEntityMapper implements Converter<CardDTO, CardEntity> {
    private final BranchRepository branchRepository;

    @Override
    public CardEntity convert(CardDTO source) {
        return CardEntity.builder()
                .cardNumber(branchRepository.passNewCardNumberToNewCard())
                .pin( String.format("%04d",  new Random().nextInt(10000)))
                .status(Status.PENDING)
                //.creationDate(LocalDateTime.now())
               // .lastUpdated(LocalDateTime.now())
                .expirationDate(LocalDateTime.now().plusYears(2L))
                .build();
    }
}
