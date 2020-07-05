package br.com.valhala.despesas.infraestrutura.repositorios.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.valhala.despesas.model.entidades.Lancamento;
import br.com.valhala.despesas.model.filtros.FiltroLancamento;

public class LancamentoRepositoryImpl implements LancamentoRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Lancamento> findAll(final FiltroLancamento filtro) {
        
    	CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteriaQuery = cb.createQuery(Lancamento.class);
        Root<Lancamento> root = criteriaQuery.from(Lancamento.class);
        
        List<Predicate> predicados = new ArrayList<Predicate>();
        
        if (filtro.getPeriodo().getDataInicio() != null) {
			predicados.add(cb.greaterThanOrEqualTo(root.get("data"), filtro.getPeriodo().getDataInicio()));
		}
        
        if (filtro.getPeriodo().getDataFim() != null) {
			predicados.add(cb.lessThanOrEqualTo(root.get("data"), filtro.getPeriodo().getDataFim()));
		}
        
        if (filtro.getDescricao() != null && !filtro.getDescricao().trim().isEmpty()) {
			predicados.add(cb.like(root.get("descricao"), "%" + filtro.getDescricao() + "%"));
		}
        
        if (filtro.getTipo() != null) {
			predicados.add(cb.equal(root.get("tipo"), filtro.getTipo()));
		}
        
        criteriaQuery.select(root);
        if (!predicados.isEmpty()) {			
        	criteriaQuery.where(cb.and(predicados.toArray(new Predicate[predicados.size()])));
		}
        
        TypedQuery<Lancamento> query = em.createQuery(criteriaQuery);
        return query.getResultList();
        
    }
    
}