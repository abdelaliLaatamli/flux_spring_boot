package com.fluxemail.application.core.offers.repositories;

import com.fluxemail.application.core.offers.models.CreativeEntity;
import com.fluxemail.application.core.offers.models.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreativeRepository extends JpaRepository<CreativeEntity , Long> {


    List<CreativeEntity> findByOfferId(Long offerId );


    @Query("SELECT c FROM CreativeEntity c WHERE c.isActive=true and c.id= :creativeId ")
    Optional<CreativeEntity> findActivatedById( @Param("creativeId") Long creativeId );

}
