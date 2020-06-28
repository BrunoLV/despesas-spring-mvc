package br.com.valhala.despesas.model.filtros;

import java.time.LocalDate;

import br.com.valhala.despesas.model.enumerados.TipoLancamento;
import lombok.Data;

@Data
public class FiltroLancamento {
    
    private LocalDate data;
    private String descricao;
    private TipoLancamento tipo;
    
}