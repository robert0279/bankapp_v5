package com.app.bank.mapper;

import com.app.bank.domain.entity.AccountEntity;
import com.app.bank.domain.entity.UserEntity;
import com.app.bank.domain.model.AccountDTO;
import com.app.bank.domain.model.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserEntityToUserMapper implements Converter<UserEntity, UserDTO> {
    private final AccountEntityToAccountMapper accountEntityToAccountMapper;

    @Override
    public UserDTO convert(UserEntity source) {
        return UserDTO.builder()
                .id(source.getId())
                .lastName(source.getLastName())
                .firstName(source.getFirstName())
                .cnp(source.getCnp())
                .creationDate(source.getCreationDate())
                //.accounts(mapAccountList(source.getAccounts()))
                .build();
    }
private List<AccountDTO> mapAccountList (List<AccountEntity> accountEntities){
        if (accountEntities == null){
            return Collections.emptyList();
        }return accountEntities.stream()
            .map(accountEntity -> accountEntityToAccountMapper.convert(accountEntity))
            .collect(Collectors.toList());
}



}
