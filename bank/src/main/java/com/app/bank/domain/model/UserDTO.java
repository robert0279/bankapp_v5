package com.app.bank.domain.model;
import com.app.bank.domain.entity.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

   // @Max(value = 999999999999999L)
    //@Pattern("")
    @Size(min =13, max = 13)
    private long cnp;
    private LocalDateTime creationDate;



    //private List<AccountDTO> accounts;


}
