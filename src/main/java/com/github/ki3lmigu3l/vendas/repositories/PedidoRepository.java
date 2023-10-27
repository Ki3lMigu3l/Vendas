package com.github.ki3lmigu3l.vendas.repositories;

import com.github.ki3lmigu3l.vendas.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
}
