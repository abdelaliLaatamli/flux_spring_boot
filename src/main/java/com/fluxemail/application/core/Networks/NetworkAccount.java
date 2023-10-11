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

    @Column(nullable = false)
    private Integer account_Id;
    @Column(nullable = true)
    private String account_username;
    @Column(nullable = true)
    private String account_password;
    @Column(nullable = true)
    private String account_apikey;

    @ManyToOne
    @JoinColumn(name="network_id", nullable=false)
    private NetworkEntity network;

}
