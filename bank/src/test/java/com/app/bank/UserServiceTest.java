package com.app.bank;

import com.app.bank.domain.entity.UserEntity;
import com.app.bank.domain.model.UserDTO;
import com.app.bank.mapper.UserEntityToUserMapper;
import com.app.bank.mapper.UserToUserEntityMapper;
import com.app.bank.repository.UserRepository;
import com.app.bank.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private  UserRepository repository;

    @Mock
    private  UserEntityToUserMapper userEntityToUserMapper;

    @Mock
    private  UserToUserEntityMapper userToUserEntityMapper;

    @InjectMocks
    private UserService sut;

    @Test
    public void givenUserUserIsCreated(){
        //Given
        UserDTO userDTOTest= UserDTO.builder()
                .id(100L)
                .lastName("Popescu")
                .firstName("Ion")
                .cnp(1790318048078L)
                .creationDate(LocalDateTime.now())
                .build();



        UserDTO mockUserDTO = mock(UserDTO.class);
        UserEntity mockUserEntity = mock(UserEntity.class);
        UserEntity savedUserEntity = mock(UserEntity.class);

        given(userToUserEntityMapper.convert(mockUserDTO)).willReturn(mockUserEntity);
        given(repository.save(mockUserEntity)).willReturn(savedUserEntity);
        given(userEntityToUserMapper.convert(savedUserEntity)).willReturn(userDTOTest);
       // given(mockUserDTO.getId()==100L);


        //WHEN
        UserDTO userDTOCreated =sut.create(mockUserDTO);

        //THEN
        verify(repository).save(mockUserEntity);
        Assertions.assertThat(userDTOCreated).isEqualTo(userDTOTest);






        //when
        sut.create(mockUserDTO);

        //then



        /*
         public UserDTO create(UserDTO userDTO){
        UserEntity userEntity =userToUserEntityMapper.convert(userDTO);

        UserEntity savedEntity = repository.save(userEntity);
        return userEntityToUserMapper.convert(savedEntity);
    }
         */

    }


}
