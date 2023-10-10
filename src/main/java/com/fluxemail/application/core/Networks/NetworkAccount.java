package com.fluxemail.application.core.Networks;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_networkaccounts" , schema = "_affiliate")
public class NetworkAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer account_Id;
    private String account_username;
    private String account_password;
    private String account_apikey;

    @ManyToOne
    @JoinColumn(name="network_id", nullable=false)
    private NetworkEntity network;

}
