package com.app.bank.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PinDTO {

    private String initialPin;
    private String newPin;
    private String newPinAgain;

}
