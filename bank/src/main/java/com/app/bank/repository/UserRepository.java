package com.app.bank.repository;

import com.app.bank.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface UserRepository  extends JpaRepository<UserEntity,Long>
{


}
