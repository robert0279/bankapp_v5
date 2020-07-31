package com.app.bank.domain.model;

import com.app.bank.domain.entity.CardEntity;
import com.app.bank.domain.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AccountDTO {

      private long id;
      private String iban;
      private BigDecimal balance;

      private long user_cnp;

      private LocalDateTime creationDate;
      private LocalDateTime lastUpdated;
     // private UserEntity userEntity;
     // private List<CardEntity> cards;

}
