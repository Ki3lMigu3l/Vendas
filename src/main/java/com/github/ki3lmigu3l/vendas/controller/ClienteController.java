package com.github.ki3lmigu3l.vendas.controller;

import com.github.ki3lmigu3l.vendas.model.Cliente;
import com.github.ki3lmigu3l.vendas.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public void cadastrarCliente(@RequestBody Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @GetMapping
    public List<Cliente> obterClientes(){
       return clienteRepository.findAll();
    }

    @GetMapping("{id}")
    public Cliente obterClienteById(@PathVariable UUID id) {
        var cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @PutMapping("{id}")
    public void atualizarCliente(@PathVariable UUID id, @RequestBody Cliente cliente) {
        var clienteEncontrado = clienteRepository.findById(id);

        clienteEncontrado.map(c -> {
            c.setNome(cliente.getNome());
            clienteRepository.save(c);
            return c.getNome();
        });
    }

    @DeleteMapping("{id}")
    public void deletarCliente(@PathVariable UUID id) {
        clienteRepository.deleteById(id);
    }
}
