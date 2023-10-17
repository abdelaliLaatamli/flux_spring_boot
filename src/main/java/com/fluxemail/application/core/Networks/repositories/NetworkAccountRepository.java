package com.fluxemail.application.core.Networks.repositories;

import com.fluxemail.application.core.Networks.models.NetworkAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetworkAccountRepository extends JpaRepository<NetworkAccount , Long> {
}
