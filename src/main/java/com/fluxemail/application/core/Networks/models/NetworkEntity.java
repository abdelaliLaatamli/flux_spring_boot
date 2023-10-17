package com.fluxemail.application.core.Networks.models;


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
@Table(
        name = "_networks",
        schema = "_affiliate" ,
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UniqueNameAndType" ,
                        columnNames = {"name","isActive","networkType"}
                )
        }
)
public class NetworkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String networkUrl;

    @Column(nullable = false)
    private String networkApiUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NetworkType networkType;

    private Boolean isActive=true;

    @OneToMany(mappedBy="network")
    private Set<NetworkAccount> networkAccounts;

}
