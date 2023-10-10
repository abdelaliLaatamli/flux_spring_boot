package com.fluxemail.application.core.offers.repositories;

import com.fluxemail.application.core.offers.models.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity,Long> {
}
