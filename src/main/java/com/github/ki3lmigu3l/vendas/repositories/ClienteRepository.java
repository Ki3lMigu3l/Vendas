package com.github.ki3lmigu3l.vendas.repositories;

import com.github.ki3lmigu3l.vendas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
}
