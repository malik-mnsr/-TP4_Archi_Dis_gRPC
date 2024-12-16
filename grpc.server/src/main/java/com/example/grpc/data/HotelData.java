package com.example.grpc.data;

import com.example.grpc.model.Adresse;
import com.example.grpc.model.Hotel;
import com.example.grpc.repository.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HotelData {
/*
    private Logger logger = LoggerFactory.getLogger(HotelData.class);
    @Bean
    public CommandLineRunner initDatabase(HotelRepository repository){
        logger.info("Initializing database...");
        Adresse adr1 = new Adresse("Italy", "Roma", "Via Salvatore Rebecchini 39","41.81557005574533, 12.408821040476733");
        Adresse adr2 = new Adresse("Greece", "Rhodes", "Ialyssos Avenue ","36.42132684698848, 28.191877269312656");
        return args -> {
            logger.info("Preloading: "+repository.save(new Hotel("Sheraton Rome Parco de' Medici",3,300,adr1)
            ));
            logger.info("Preloading: "+repository.save(new Hotel("Sheraton Rhodes Resort",5,150,adr2)
            ));
        };
    }

    */
}


