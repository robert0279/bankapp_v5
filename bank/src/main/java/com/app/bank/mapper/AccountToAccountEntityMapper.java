package com.app.bank.mapper;

import com.app.bank.domain.entity.AccountEntity;
import com.app.bank.domain.model.AccountDTO;
import com.app.bank.repository.BranchRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Dictionary;


@Component
@AllArgsConstructor
public class AccountToAccountEntityMapper implements Converter<AccountDTO, AccountEntity> {
   private final BranchRepository branchRepository;

    @Override
    public AccountEntity convert(AccountDTO source) {
        return AccountEntity.builder()
                //.userCnp(source.getUser_cnp())
                .balance(BigDecimal.valueOf(0.00))
                .iban(branchRepository.generateIbanForAccount())
                .lastUpdated(LocalDateTime.now())
                .build();
    }
}
