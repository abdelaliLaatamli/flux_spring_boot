package com.fluxemail.application.core.Networks.repositories;

import com.fluxemail.application.core.Networks.models.NetworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NetworkRepository extends JpaRepository<NetworkEntity , Long> {

    @Query("SELECT n FROM NetworkEntity n WHERE n.isActive = true")
    List<NetworkEntity> findAllActivated();
    @Query("SELECT n FROM NetworkEntity n WHERE n.isActive = true and n.id= :networkId")
    Optional<NetworkEntity> findActiveById(@Param("networkId") Long networkId);
}
