package br.com.valhala.despesas.infraestrutura.repositorios.jpa;

import java.util.List;

import br.com.valhala.despesas.model.entidades.Lancamento;
import br.com.valhala.despesas.model.filtros.FiltroLancamento;

public interface LancamentoRepositoryCustom {

    List<Lancamento> findAll(FiltroLancamento filtro);
    
}