package com.zms.turnomatic.infraestructure.TurnoOutputPort;

import com.zms.turnomatic.domain.model.Turno;

import java.util.List;
import java.util.UUID;

public interface TurnoOutPort {
    public Turno create(String categoria, String mesa, int maximoContador);
    public Turno getById(UUID id);
    public List<Turno> getAll();
    public Turno findByCategoria(String categoria);

    public String nextContadorByCategoria(String categoria, String mesa);

    public boolean resetearContador(String categoria);
}
