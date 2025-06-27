package com.omarcosallan.picpay.service;

import com.omarcosallan.picpay.dto.MerchantDTO;
import com.omarcosallan.picpay.model.Merchant;
import com.omarcosallan.picpay.model.User;
import com.omarcosallan.picpay.repository.MerchantRepository;
import com.omarcosallan.picpay.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Transactional
    public User createUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Transactional
    public Merchant createMerchant(MerchantDTO merchantDTO) {
        User user = new User();
        user.setFullName(merchantDTO.getFullName());
        user.setCpf(merchantDTO.getCpf());
        user.setEmail(merchantDTO.getEmail());
        user.setPassword(merchantDTO.getPassword());
        user.setBalance(BigDecimal.ZERO);
        user.setCreatedAt(LocalDateTime.now());
        user = userRepository.save(user);

        Merchant merchant = new Merchant();
        merchant.setMerchant(user);
        merchant.setCnpj(merchantDTO.getCnpj());
        return merchantRepository.save(merchant);
    }
}
