package br.com.valhala.despesas.model.comandos;

import br.com.valhala.despesas.model.entidades.Lancamento;

public record ComandoEdicaoLancamento(Long id, 
									  Lancamento lancamento) {

}
