package com.github.ki3lmigu3l.vendas.repositories;

import com.github.ki3lmigu3l.vendas.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, UUID> {
}
