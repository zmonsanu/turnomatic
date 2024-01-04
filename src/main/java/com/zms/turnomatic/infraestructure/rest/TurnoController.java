package com.zms.turnomatic.infraestructure.rest;


import com.zms.turnomatic.domain.model.Turno;
import com.zms.turnomatic.infraestructure.TurnoOutputPort.TurnoOutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value="turnos")
public class TurnoController {

    @Autowired
    TurnoOutPort turnoOutPort;

    @PostMapping(value="create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Turno create(@RequestParam String categoria, @RequestParam String mesa, @RequestParam int maximoContador){
        return turnoOutPort.create(categoria, mesa, maximoContador);
    }

    @GetMapping(value = "get", produces=MediaType.APPLICATION_JSON_VALUE)
    public Turno get(@PathVariable String id ) {
        return turnoOutPort.getById(UUID.fromString(id));
    }

    @GetMapping(value = "getall", produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Turno> getAll() {
        return turnoOutPort.getAll();
    }

    @PostMapping(value="nextContadorByCategoria", produces = MediaType.APPLICATION_JSON_VALUE)
    public String nextContadorByCategoria(@RequestParam String categoria, @RequestParam String mesa){
        return turnoOutPort.nextContadorByCategoria(categoria, mesa);
    }

    @PostMapping(value="resetContadorByCategoria", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean resetContadorByCategoria(@RequestParam String categoria){
        return turnoOutPort.resetearContador(categoria);
    }
}
