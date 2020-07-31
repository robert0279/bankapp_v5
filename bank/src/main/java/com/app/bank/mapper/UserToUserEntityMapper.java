package com.app.bank.mapper;

import com.app.bank.domain.entity.UserEntity;
import com.app.bank.domain.model.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor

public class UserToUserEntityMapper implements Converter<UserDTO, UserEntity> {

    @Override
    public UserEntity convert(UserDTO source) {
      return      UserEntity.builder()
                .lastName(source.getLastName())
                .firstName(source.getFirstName())
                .cnp(source.getCnp())
               // .creationDate(source.getCreationDate())
                .build();
}
}
