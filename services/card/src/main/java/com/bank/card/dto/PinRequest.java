package com.bank.card.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PinRequest {
    @NotBlank
    @Pattern(regexp = "^[0-9]{4,6}$")
    private String pin;
}
