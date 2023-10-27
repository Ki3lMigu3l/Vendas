package com.github.ki3lmigu3l.vendas.controller;

import com.github.ki3lmigu3l.vendas.dto.ProdutoRecordDTO;
import com.github.ki3lmigu3l.vendas.model.Produto;
import com.github.ki3lmigu3l.vendas.repositories.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Produto> salvarProduto (@RequestBody ProdutoRecordDTO produtoDto) {
        var produto = new Produto();
        BeanUtils.copyProperties(produtoDto, produto);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
    }

    @GetMapping
    public ResponseEntity<Page<Produto>> listarProdutos (
            @PageableDefault (page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC)Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAll(pageable));
    }

    @GetMapping("{id}")
    public Produto listarProdutoById (@PathVariable UUID id) {
        return produtoRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
    }


    @PutMapping("{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarProduto (@PathVariable UUID id, @RequestBody Produto produto) {
        produtoRepository
                .findById(id)
                .map(p -> {
                    produto.setId(p.getId());
                    produtoRepository.save(produto);
                    return produto;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @DeleteMapping("{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProduto (@PathVariable UUID id) {
        produtoRepository.findById(id)
                .map(produto -> {
                    produtoRepository.delete(produto);
                    return Void.TYPE;
                }).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
    }


}
