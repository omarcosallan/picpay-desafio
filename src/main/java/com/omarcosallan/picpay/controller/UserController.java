package com.omarcosallan.picpay.controller;

import com.omarcosallan.picpay.dto.MerchantDTO;
import com.omarcosallan.picpay.model.Merchant;
import com.omarcosallan.picpay.model.User;
import com.omarcosallan.picpay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User userCreated = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @PostMapping("/create-merchant")
    public ResponseEntity<Merchant> createMerchant(@RequestBody MerchantDTO merchantDTO) {
        Merchant merchantCreated = userService.createMerchant(merchantDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(merchantCreated);
    }
}
