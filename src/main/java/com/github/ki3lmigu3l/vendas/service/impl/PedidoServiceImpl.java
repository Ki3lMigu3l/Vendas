package com.github.ki3lmigu3l.vendas.service.impl;

import com.github.ki3lmigu3l.vendas.dto.PedidoRecordDTO;
import com.github.ki3lmigu3l.vendas.exception.RegraDeNegocioException;
import com.github.ki3lmigu3l.vendas.model.ItemPedido;
import com.github.ki3lmigu3l.vendas.model.Pedido;
import com.github.ki3lmigu3l.vendas.repositories.ClienteRepository;
import com.github.ki3lmigu3l.vendas.repositories.ItemPedidoRepository;
import com.github.ki3lmigu3l.vendas.repositories.PedidoRepository;
import com.github.ki3lmigu3l.vendas.repositories.ProdutoRepository;
import com.github.ki3lmigu3l.vendas.service.PedidoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidos;
    private final ClienteRepository clientes;
    private final ProdutoRepository produtos;
    private final ItemPedidoRepository itemPedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidos, ClienteRepository clientes, ProdutoRepository produtos, ItemPedidoRepository itemsPedidos) {
        this.pedidos = pedidos;
        this.clientes = clientes;
        this.produtos = produtos;
        this.itemPedidoRepository = itemsPedidos;
    }


    @Override
    @Transactional
    public Pedido salvarPedido(PedidoRecordDTO pedidoDto) {
        UUID clienteId = pedidoDto.cliente();
        var cliente = clientes.findById(clienteId)
                .orElseThrow(() ->
                        new RegraDeNegocioException("Código do cliente invalido!"));

        Pedido pedido = new Pedido();
        pedido.setTotalPedido(pedidoDto.totalPedido());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itensPedidos = converterItens(pedido, pedidoDto.itens());
        pedidos.save(pedido);
        itemPedidoRepository.saveAll(itensPedidos);
        pedido.setItens(itensPedidos);
        return pedido;
    }

    private List<ItemPedido> converterItens (Pedido pedido, List<ItemPedido> itemPedidos) {
        if (itemPedidos.isEmpty()) {
            throw new RegraDeNegocioException("Item não informado!");
        }

        return itemPedidos.stream().map( dto -> {
            UUID produtoId = dto.getProduto().getId();
            var produto = produtos
                    .findById(produtoId)
                    .orElseThrow(() ->
                            new RegraDeNegocioException("Código de produto inválido: " + produtoId));

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setQuantidade(dto.getQuantidade());
            itemPedido.setPedido(dto.getPedido());
            itemPedido.setProduto(produto);
            return itemPedido;
        }).collect(Collectors.toList());
    }
}
