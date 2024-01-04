package com.zms.turnomatic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.zms.turnomatic")
public class MicroServiceBoot {
    public static void main(String[] args) {

        SpringApplication.run(MicroServiceBoot.class, args);
        System.out.println("Servicio Turno ON!!");
    }
}