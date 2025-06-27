package com.omarcosallan.picpay.repository;

import com.omarcosallan.picpay.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
