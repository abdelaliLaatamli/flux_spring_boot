package com.fluxemail.application.web.seeds;

import com.fluxemail.application.core.offers.repositories.EntityRepository;
import com.fluxemail.application.security.data.entities.EntityEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Seeder {

    private final EntityRepository entityRepository;



    @EventListener
    public void seed(ContextRefreshedEvent event){
        System.out.println("------------------------------");
        System.out.println("        DATA SEEDING          ");
        System.out.println("------------------------------");
        this.seedEntityTable();
        System.out.println("------------------------------");
        System.out.println("       END DATA SEEDING       ");
        System.out.println("------------------------------");
    }

    private void seedEntityTable(){
        var defaultEntity = EntityEntity.builder().name("default").build();

        this.entityRepository.save(defaultEntity);
    }

}
