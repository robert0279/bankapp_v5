package com.app.bank.domain.model;
import com.app.bank.domain.entity.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {


    private long id;
    private String lastName;
    private String firstName;
    private long cnp;
    private LocalDateTime creationDate;



    private List<AccountEntity> accounts;


}
