package com.github.ki3lmigu3l.vendas.model;

import com.github.ki3lmigu3l.vendas.dto.ClienteRecordDTO;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "Nome", nullable = false)
    private String nome;

    public Cliente() {}

    public Cliente(ClienteRecordDTO dados) {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
