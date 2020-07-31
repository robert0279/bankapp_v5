package com.app.bank.mapper;

import com.app.bank.domain.entity.AccountEntity;
import com.app.bank.domain.model.AccountDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountEntityToAccountMapper implements Converter<AccountEntity, AccountDTO> {

    @Override
    public AccountDTO convert(AccountEntity source) {
        return AccountDTO.builder()
                .id(source.getId())
                .iban(source.getIban())
                .balance(source.getBalance())
                .user_cnp(source.getUserCnp())
                //.userEntity(source.getUserEntity())
                .creationDate(source.getCreationDate())
                .lastUpdated(source.getLastUpdated())
                .build();
    }
}
