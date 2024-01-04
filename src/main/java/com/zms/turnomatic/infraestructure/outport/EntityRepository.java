package com.zms.turnomatic.infraestructure.outport;

import com.zms.turnomatic.domain.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public interface EntityRepository extends JpaRepository<Turno, UUID> {
    Turno findByCategoria(String categoria);
}
