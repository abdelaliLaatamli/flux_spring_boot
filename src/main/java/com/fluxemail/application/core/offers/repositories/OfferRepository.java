package com.fluxemail.application.core.offers.repositories;

import com.fluxemail.application.core.Networks.models.NetworkAccountEntity;
import com.fluxemail.application.core.offers.models.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity,Long> {


    @Query("SELECT o FROM OfferEntity o WHERE o.status = 'ACTIVE'")
    List<OfferEntity> findAllActivated();

    @Query("SELECT o FROM OfferEntity o WHERE o.status = 'ACTIVE' and o.id= :offerId")
    Optional<OfferEntity> findActivatedById(@Param("offerId") Long offerId );
}
