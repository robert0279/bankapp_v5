package com.app.bank.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)

public class UserEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String lastName;
    private String firstName;
    private long cnp;
    @CreatedDate
    private LocalDateTime creationDate;


  //  @OneToMany(mappedBy = "userEntity")
  //  private List<AccountEntity> accounts;


}
