package com.app.bank.repository;

import com.app.bank.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserRepository  extends JpaRepository<UserEntity,Long>{

    List<Optional<UserEntity>>  findAllByLastName (String lastName);


}
