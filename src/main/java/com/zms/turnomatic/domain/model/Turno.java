package com.zms.turnomatic.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@Entity
@Table(name="TURNOS")
public class Turno implements Serializable {

    @Id
    @Column(name="ID")
    private UUID id;

    @Column(name="CATEGORIA")
    private String categoria;

    @Column(name="MESA")
    private String mesa;

    @Column(name="CONTADOR")
    private int contador;

    @Column(name="MAXIMOCONTADOR")
    private int maximoContador;

    @Column(name="ULTIMAMODIFICACION")
    private LocalDateTime ultimaModificacion;

    public Turno() {
        super();
    }

    public Turno(UUID id, String categoria, String mesa, int contador, int maximoContador, LocalDateTime ultimaModificacion) {
        this.id = id;
        this.categoria = categoria;
        this.mesa = mesa;
        this.contador = contador;
        this.maximoContador = maximoContador;
        this.ultimaModificacion = ultimaModificacion;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", categoria='" + categoria + '\'' +
                ", mesa='" + mesa + '\'' +
                ", contador=" + contador +
                ", maximoContador='" + maximoContador + '\'' +
                ", ultimaModificacion=" + ultimaModificacion +
                '}';
    }
}
