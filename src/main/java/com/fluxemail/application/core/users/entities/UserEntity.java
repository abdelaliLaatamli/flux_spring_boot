package com.fluxemail.application.core.users.entities;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column
    protected String fullName;

    @Column(unique = true,nullable = false)
    protected String email;

    @Column
    protected String password;


    @Column
    protected int team = 1 ;

    @Column
    protected Boolean isActive = true;

    @Enumerated(EnumType.STRING)
    protected Role role = Role.USER;



    @Column(updatable = false)
    @CreationTimestamp
    protected LocalDateTime createdAt;

    @UpdateTimestamp
    protected LocalDateTime updatedAt;


    @OneToMany(mappedBy = "user")
    private List<TokenEntity> tokens;


    @ManyToOne
    @JoinColumn(name = "entity_id")
    private EntityEntity entity;


}
