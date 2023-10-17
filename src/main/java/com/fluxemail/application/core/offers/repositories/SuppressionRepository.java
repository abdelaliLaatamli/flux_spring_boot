package com.fluxemail.application.core.offers.repositories;

import com.fluxemail.application.core.offers.models.SuppressionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppressionRepository extends JpaRepository<SuppressionEntity,Long> {
}
