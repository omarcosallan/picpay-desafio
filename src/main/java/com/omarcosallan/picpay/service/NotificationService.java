package com.omarcosallan.picpay.service;

import com.omarcosallan.picpay.model.User;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public boolean notify(User user, String message) {
        return true;
    }
}
