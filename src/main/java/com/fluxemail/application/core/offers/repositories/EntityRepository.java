package com.fluxemail.application.core.offers.repositories;

import com.fluxemail.application.security.data.entities.EntityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepository extends JpaRepository<EntityEntity , Long> {
}
