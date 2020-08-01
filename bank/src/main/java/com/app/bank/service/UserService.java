package com.app.bank.service;

import com.app.bank.domain.entity.UserEntity;
import com.app.bank.domain.model.UserDTO;
import com.app.bank.exception.UserNotFindException;
import com.app.bank.mapper.UserEntityToUserMapper;
import com.app.bank.mapper.UserToUserEntityMapper;
import com.app.bank.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class UserService {
    private final UserRepository repository;
    private final UserEntityToUserMapper userEntityToUserMapper;
    private final UserToUserEntityMapper userToUserEntityMapper;

    public UserDTO create(UserDTO userDTO){
        UserEntity userEntity =userToUserEntityMapper.convert(userDTO);

        UserEntity savedEntity = repository.save(userEntity);
        return userEntityToUserMapper.convert(savedEntity);
    }

    public List<UserDTO> findAll(){
        return repository.findAll()
                .stream()
                .map(userEntityToUserMapper::convert)
                .collect(Collectors.toList());
    }

    public UserDTO findById (long id){
        UserEntity userEntity =repository.findById(id)
                .orElseThrow(()->new UserNotFindException("The User With the id " + id + " does not exists"));

        return userEntityToUserMapper.convert(userEntity);

    }

    public List<UserDTO> findAllByName (String lastName){
       List<UserDTO> listUser = new ArrayList<>();
       UserEntity temp = new UserEntity();
       for (Optional<UserEntity> optional :repository.findAllByLastName(lastName)){
           temp=optional.orElseThrow(()->new RuntimeException("There is no user with this name " + lastName));
           listUser.add(userEntityToUserMapper.convert(temp));
       }
             return listUser;
    }




}
