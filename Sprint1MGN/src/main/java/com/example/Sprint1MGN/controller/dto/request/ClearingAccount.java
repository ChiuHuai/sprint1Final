package com.example.Sprint1MGN.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClearingAccount {
    @NotBlank(message = "accNo should not be blank")
    @Length(max = 7, message = "accNo less than 7 characters")
    private String accNo;//存入結算賬號
    @NotNull(message = "amt should not be null")
    @DecimalMin(value = "0", inclusive = false, message = "amt must be greater than 0")
    @Digits(integer = 20, fraction = 4, message = "digits of price is not correct")
    private BigDecimal amt;//存入結算金額
}
