package com.app.bank.mapper;

import com.app.bank.domain.entity.CardEntity;
import com.app.bank.domain.model.CardDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CardEntityToCardMapper implements Converter<CardEntity, CardDTO> {


    @Override
    public CardDTO convert(CardEntity source) {
        return CardDTO.builder()
                .id(source.getId())
                .iban(source.getIban())
                .cardNumber(source.getCardNumber())
                .pin(source.getPin())
                .status(source.getStatus())
                .creationDate(source.getCreationDate())
                .lastUpdated(source.getLastUpdated())
                .expirationDate(source.getExpirationDate())
                .build();
    }
}
