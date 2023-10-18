package com.fluxemail.application.core.Networks.repositories;

import com.fluxemail.application.core.Networks.models.NetworkAccountEntity;
import com.fluxemail.application.core.Networks.models.NetworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NetworkAccountRepository extends JpaRepository<NetworkAccountEntity, Long> {

    @Query("SELECT n FROM NetworkAccountEntity n WHERE n.isActive = true and n.network.id= :networkId")
    List<NetworkAccountEntity> findAllByNetworkId(@Param("networkId") Long networkId);

    @Query("SELECT n FROM NetworkAccountEntity n WHERE n.isActive = true")
    List<NetworkAccountEntity> findAllActivated();

    @Query("SELECT n FROM NetworkAccountEntity n WHERE n.isActive = true and n.id= :networkAccountId")
    Optional<NetworkAccountEntity> findActivatedById( @Param("networkAccountId") Long networkAccountId );
}
