package com.fluxemail.application.core.offers.repositories;

import com.fluxemail.application.core.users.entities.EntityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntityRepository extends JpaRepository<EntityEntity , Long> {


    Optional<EntityEntity> findByName(String name);
}
