package com.app.bank;

import com.app.bank.domain.model.UserDTO;
import com.app.bank.mapper.UserEntityToUserMapper;
import com.app.bank.mapper.UserToUserEntityMapper;
import com.app.bank.repository.UserRepository;
import com.app.bank.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.BDDAssumptions.given;
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
        UserDTO mockUser = mock(UserDTO.class);

        String lastName = "Georgescu";
        String firstName ="Mihai";
        long cnp = 1800101021925L;

        given(userToUserEntityMapper.convert(mockUser));

        //when
        sut.create(mockUser);

        //then
        verify(userToUserEntityMapper.convert(mockUser)).setLastName(lastName);
        verify(userToUserEntityMapper.convert(mockUser)).setFirstName(firstName);
        verify(userToUserEntityMapper.convert(mockUser)).setCnp(cnp);
        verify(repository).save(userToUserEntityMapper.convert(mockUser));



        /*
         public UserDTO create(UserDTO userDTO){
        UserEntity userEntity =userToUserEntityMapper.convert(userDTO);

        UserEntity savedEntity = repository.save(userEntity);
        return userEntityToUserMapper.convert(savedEntity);
    }
         */

    }


}
