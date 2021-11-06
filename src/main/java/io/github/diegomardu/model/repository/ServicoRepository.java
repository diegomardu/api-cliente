package io.github.diegomardu.model.repository;

import io.github.diegomardu.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {
}
