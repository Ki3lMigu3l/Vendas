package com.github.ki3lmigu3l.vendas.controller;

import com.github.ki3lmigu3l.vendas.dto.ClienteRecordDTO;
import com.github.ki3lmigu3l.vendas.model.Cliente;
import com.github.ki3lmigu3l.vendas.repositories.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Cliente> salvarCliente (@RequestBody @Valid ClienteRecordDTO clienteDto) {
        var cliente = new Cliente();
        BeanUtils.copyProperties(clienteDto, cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(cliente));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obterTodosClientes () {
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> obterClienteById (@PathVariable UUID id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if (clienteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
        }

        clienteRepository.save(clienteOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body(clienteOptional.get());


    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<Object> atualizarCliente (@PathVariable UUID id, @RequestBody ClienteRecordDTO clienteDto) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if (clienteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado ou ID invalido!");
        }

        var clienteExistente = clienteOptional.get();
        BeanUtils.copyProperties(clienteDto, clienteExistente);
        return ResponseEntity.status(HttpStatus.OK).body(clienteOptional.get());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletandoCliente (@PathVariable UUID id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if (clienteOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado ou ID invalido!");
        }

        clienteRepository.delete(clienteOptional.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cliente deletado com sucesso!");
    }
}
