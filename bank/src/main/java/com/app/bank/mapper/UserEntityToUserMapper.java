package com.app.bank.mapper;

import com.app.bank.domain.entity.UserEntity;
import com.app.bank.domain.model.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserEntityToUserMapper implements Converter<UserEntity, UserDTO> {

    @Override
    public UserDTO convert(UserEntity source) {
        return UserDTO.builder()
                .id(source.getId())
                .lastName(source.getLastName())
                .firstName(source.getFirstName())
                .cnp(source.getCnp())
                .creationDate(source.getCreationDate())
                .build();
    }
}
