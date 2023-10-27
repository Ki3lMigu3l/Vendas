package com.github.ki3lmigu3l.vendas.dto;

import com.github.ki3lmigu3l.vendas.model.ItemPedido;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record PedidoRecordDTO(

        UUID cliente,
        BigDecimal totalPedido,

        List<ItemPedido> itens
        ) {
}
