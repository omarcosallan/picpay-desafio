package com.omarcosallan.picpay.controller;

import com.omarcosallan.picpay.dto.TransferDTO;
import com.omarcosallan.picpay.model.Transfer;
import com.omarcosallan.picpay.service.ExternalAuthorizationService;
import com.omarcosallan.picpay.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @Autowired
    private ExternalAuthorizationService externalAuthorizationService;

    @PostMapping("/create")
    public ResponseEntity<Transfer> transfer(@RequestBody TransferDTO transferDTO) {
        Transfer transferCreated = transferService.transfer(transferDTO.getPayer(), transferDTO.getPayee(), transferDTO.getValueTransfer());
        return ResponseEntity.ok(transferCreated);
    }

    @GetMapping("/test-authorization")
    public Boolean testAuthorization() {
        return externalAuthorizationService.isAuthorized();
    }
}
