package com.github.ki3lmigu3l.vendas.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    private Cliente cliente;
    @Column
    private LocalDate dataPedido;
    @Column
    private BigDecimal totalPedido;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;
    public Pedido() {
    }

    public Pedido(UUID id, Cliente cliente, LocalDate dataPedido, BigDecimal totalPedido) {
        this.id = id;
        this.cliente = cliente;
        this.dataPedido = dataPedido;
        this.totalPedido = totalPedido;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(BigDecimal totalPedido) {
        this.totalPedido = totalPedido;
    }
}
