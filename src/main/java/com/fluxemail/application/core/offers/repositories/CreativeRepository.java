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

//    @Query("SELECT o FROM CreativeEntity o WHERE o.id= :offerId")
    List<CreativeEntity> findByOfferId(Long offerId );

}
