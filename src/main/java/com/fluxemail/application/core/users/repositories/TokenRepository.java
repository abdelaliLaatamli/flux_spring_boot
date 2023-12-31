package com.fluxemail.application.core.users.repositories;

import com.fluxemail.application.core.users.entities.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity,Long> {

    @Query("""
      select t from TokenEntity t inner join UserEntity u on t.user.id = u.id
      where u.id=:userId and ( t.expired = false or t.revoked = false )
            """)
    List<TokenEntity> findAllValidTokensByUser(long userId);
    
    Optional<TokenEntity> findByToken(String token);

}
