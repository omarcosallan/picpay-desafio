package com.omarcosallan.picpay.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TransferDTO {

    private BigDecimal valueTransfer;
    private Long payer;
    private Long payee;
}
