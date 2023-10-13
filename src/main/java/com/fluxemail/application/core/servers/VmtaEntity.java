package com.fluxemail.application.core.servers;


import com.fluxemail.application.core.offers.models.OfferEntity;
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
@Table( name="_vmtas", schema = "_servers")
public class VmtaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String ip;

    private Boolean isActive = true;

    @Enumerated(EnumType.STRING)
    private VmtaType type=VmtaType.NORMAL;

    @ManyToOne
    @JoinColumn(name="server_id", nullable=false)
    private ServerEntity server;

}
