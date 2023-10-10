package com.fluxemail.application.core.Networks;


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
@Table(name = "_networks", schema = "_affiliate")
public class NetworkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String network_url;

    private String network_api_url;

    @Enumerated(EnumType.STRING)
    private NetworkType networkType;

    @OneToMany(mappedBy="network")
    private Set<NetworkAccount> networkAccounts;

}
