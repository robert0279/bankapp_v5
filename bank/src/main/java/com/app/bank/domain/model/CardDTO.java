package com.app.bank.domain.model;

import com.app.bank.domain.entity.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CardDTO {


    private long id;
    private long cardNumber;
    private String pin;
    private String iban;

    //@Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdated;
    private LocalDateTime expirationDate;
   // private AccountEntity accountEntity;

}
