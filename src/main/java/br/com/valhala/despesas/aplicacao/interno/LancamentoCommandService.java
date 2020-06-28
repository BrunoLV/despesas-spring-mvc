package br.com.valhala.despesas.aplicacao.interno;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.valhala.despesas.infraestrutura.repositorios.jpa.LancamentoRepository;
import br.com.valhala.despesas.model.comandos.ComandoEdicaoLancamento;
import br.com.valhala.despesas.model.comandos.ComandoExclusaoLancamento;
import br.com.valhala.despesas.model.comandos.ComandoNovoLancamento;
import br.com.valhala.despesas.model.entidades.Lancamento;

@Service
public class LancamentoCommandService {

	private LancamentoRepository repository;
	
	public LancamentoCommandService(LancamentoRepository repository) {
		this.repository = repository;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleta(ComandoExclusaoLancamento comando) {
		repository.deleteById(comando.idLancamento());
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void edita(ComandoEdicaoLancamento comando) {
		Optional<Lancamento> registro = repository.findById(comando.id());
		Lancamento lancamento = registro.orElseThrow();
		Lancamento dadosEdicao = comando.lancamento();
		lancamento.setDescricao(dadosEdicao.getDescricao());
		lancamento.setData(dadosEdicao.getData());
		lancamento.setObservacao(dadosEdicao.getObservacao());
		lancamento.setTipo(dadosEdicao.getTipo());
		lancamento.setValor(dadosEdicao.getValor());
		repository.save(lancamento);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void salva(ComandoNovoLancamento comando) {
		Lancamento lancamento = comando.lancamento();
		repository.save(lancamento);
	}
	
}
