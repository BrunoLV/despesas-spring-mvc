package br.com.valhala.despesas.model.filtros;

import br.com.valhala.despesas.model.constraints.PeriodoPesquisa;
import br.com.valhala.despesas.model.enumerados.TipoLancamento;
import lombok.Data;

@Data
public class FiltroLancamento {

	@PeriodoPesquisa
	private FiltroPeriodoPesquisa periodo = new FiltroPeriodoPesquisa();
	private String descricao;
	private TipoLancamento tipo;

	public FiltroPeriodoPesquisa getPeriodo() {
		if (this.periodo == null) {
			this.periodo = new FiltroPeriodoPesquisa();
		}
		return this.periodo;
	}

}