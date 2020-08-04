package com.app.bank.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
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


    @Max(value = 6991212999999L)
    @Min(value = 1000101000000L)
    private long cnp;

    @CreatedDate
    private LocalDateTime creationDate;


  //  @OneToMany(mappedBy = "userEntity")
  //  private List<AccountEntity> accounts;


}
