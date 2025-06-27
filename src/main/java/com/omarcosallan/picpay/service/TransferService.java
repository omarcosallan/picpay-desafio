package com.omarcosallan.picpay.service;

import com.omarcosallan.picpay.model.Transfer;
import com.omarcosallan.picpay.model.User;
import com.omarcosallan.picpay.repository.MerchantRepository;
import com.omarcosallan.picpay.repository.TransferRepository;
import com.omarcosallan.picpay.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransferService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private ExternalAuthorizationService externalAuthorizationService;

    @Autowired
    private NotificationService notificationService;

    @Transactional
    public Transfer transfer(Long payerId, Long payeeId, BigDecimal valueTransfer) {
        merchantRepository.findById(payerId).orElseThrow(() -> new RuntimeException("Merchant cannot make transactions"));
        User uPayer = userRepository.findById(payerId).orElseThrow(() -> new RuntimeException("User not found. Try again!"));
        User uPayee = userRepository.findById(payeeId).orElseThrow(() -> new RuntimeException("User not found. Try again!"));

        if (uPayer.getBalance().compareTo(valueTransfer) < 0) {
            throw new RuntimeException("Insufficient balance!");
        }

        if (!externalAuthorizationService.isAuthorized()) {
            throw new RuntimeException("Authorization failed!");
        }

        uPayer.setBalance(uPayer.getBalance().subtract(valueTransfer));
        uPayee.setBalance(uPayee.getBalance().add(valueTransfer));

        Transfer transfer = new Transfer();
        transfer.setPayer(uPayer);
        transfer.setPayee(uPayee);
        transfer.setTransferAt(LocalDateTime.now());
        transfer.setValueTransfer(valueTransfer);

        userRepository.save(uPayer);
        userRepository.save(uPayee);
        transferRepository.save(transfer);

        notificationService.notify(uPayer, "You send a transfer of: " + valueTransfer);
        notificationService.notify(uPayer, "You received a transfer of: " + valueTransfer);

        return transfer;
    }
}
