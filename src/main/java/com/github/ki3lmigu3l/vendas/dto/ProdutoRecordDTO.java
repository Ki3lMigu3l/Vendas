package com.github.ki3lmigu3l.vendas.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoRecordDTO (

        UUID id,
        String nome,
        String descricao,
        BigDecimal preco,
        int estoque
) {
}
