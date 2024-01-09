package com.zms.turnomatic.application;

import com.zms.turnomatic.domain.model.Turno;
import com.zms.turnomatic.infraestructure.TurnoOutputPort.TurnoOutPort;
import com.zms.turnomatic.infraestructure.outport.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TurnoSevice implements TurnoOutPort {
    @Autowired
    EntityRepository entityRepository;

    @Override
    public Turno create(String categoria, String mesa, int maximoContador) {
        Turno turno = entityRepository.findByCategoria(categoria);
        if (turno == null) {
            return crearTurno(categoria, mesa, maximoContador);
        } else {
            return  turno;
        }
    }

    @Override
    public Turno getById(UUID id) {
        return entityRepository.findById(id).orElse(new Turno());
    }

    @Override
    public List<Turno> getAll() {
        return entityRepository.findAll();
    }

    @Override
    public Turno findByCategoria(String categoria) {
        return entityRepository.findByCategoria(categoria);
    }

    @Override
    public String nextContadorByCategoria(String categoria, String mesa){
        Turno turno = entityRepository.findByCategoria(categoria);
        if (turno == null) {
            System.out.println("No se encontró el turno para la categoría: " + categoria);
            return "";
        }
        int newContador = turno.getContador();
        if (turno.getContador() <= turno.getMaximoContador()){
            newContador++;
        } else {
            newContador = 0;
        }
        turno.setContador(newContador);
        turno.setUltimaModificacion(LocalDateTime.now());
        turno.setMesa(mesa);
        entityRepository.save(turno);
        return categoria + LocalDateTime.now().getYear() + "-"+ newContador;
    }

    @Override
    public boolean resetearContador(String categoria) {
        try{
            Turno turno = entityRepository.findByCategoria(categoria);
            if (turno == null) {
                System.out.println("No se encontró el turno para la categoría: " + categoria);
                return false;
            }
            turno.setContador(0);
            turno.setUltimaModificacion(LocalDateTime.now());
            turno.setMesa("MESA NO ASIGNADA");
            entityRepository.save(turno);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    private Turno crearTurno(String categoria, String mesa, int maximoContador){
        LocalDateTime ldt = LocalDateTime.now();
        Turno turno = Turno.builder()
                .id(UUID.randomUUID())
                .categoria(categoria)
                .mesa(mesa)
                .contador(0)
                .maximoContador(maximoContador)
                .ultimaModificacion(ldt)
                .build();
        return entityRepository.save(turno);
    }
}
