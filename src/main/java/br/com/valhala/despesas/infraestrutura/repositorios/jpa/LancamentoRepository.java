package br.com.valhala.despesas.infraestrutura.repositorios.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.valhala.despesas.model.entidades.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryCustom {

}
