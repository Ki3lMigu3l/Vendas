package com.github.ki3lmigu3l.vendas.service;

import com.github.ki3lmigu3l.vendas.dto.PedidoRecordDTO;
import com.github.ki3lmigu3l.vendas.model.Pedido;

public interface PedidoService {

    Pedido salvarPedido (PedidoRecordDTO pedidoDto);
}
