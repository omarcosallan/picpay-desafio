package com.omarcosallan.picpay.repository;

import com.omarcosallan.picpay.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {
}
