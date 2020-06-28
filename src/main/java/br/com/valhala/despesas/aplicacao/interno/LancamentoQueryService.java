package br.com.valhala.despesas.aplicacao.interno;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.valhala.despesas.infraestrutura.repositorios.jpa.LancamentoRepository;
import br.com.valhala.despesas.model.entidades.Lancamento;
import br.com.valhala.despesas.model.filtros.FiltroLancamento;

@Service
public class LancamentoQueryService {

    private LancamentoRepository repository;

    LancamentoQueryService(LancamentoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Lancamento> lista() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
	public List<Lancamento> lista(FiltroLancamento filtro) {
		return repository.findAll(filtro);
	}

    @Transactional(readOnly = true)
	public Optional<Lancamento> buscaPorId(Long id) {
		return repository.findById(id);
	}

}
