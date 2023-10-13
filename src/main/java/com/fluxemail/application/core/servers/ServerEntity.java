package com.fluxemail.application.core.servers;

import com.fluxemail.application.core.users.entities.EntityEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="_servers",schema = "_servers")
public class ServerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String mainIp;

    private Boolean isActive=true;


    @Enumerated(EnumType.STRING)
    private ServerType type;


    @OneToMany(mappedBy="server",fetch = FetchType.LAZY)
    private Set<VmtaEntity> creatives;


    @ManyToOne
    @JoinColumn(name="entity_id", nullable=false)
    private EntityEntity entity;

}
