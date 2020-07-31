package com.app.bank.domain.entity;

import com.app.bank.domain.model.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "card")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long cardNumber;
    private int pin;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreatedDate
    private LocalDateTime creationDate;
    @LastModifiedDate
    private LocalDateTime lastUpdated;

    private LocalDateTime expirationDate;

  //  @JsonIgnore
  //  @ToString.Exclude
  //  @ManyToOne(fetch = FetchType.LAZY) //by default e EAGER

    @OneToMany
    @JoinColumn(name = "iban")
    private AccountEntity accountEntity;
}
