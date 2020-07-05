package br.com.valhala.despesas.model.filtros;

import java.time.LocalDate;

import lombok.Data;

@Data
public class FiltroPeriodoPesquisa {
	
    private LocalDate dataInicio;
    private LocalDate dataFim;

}
