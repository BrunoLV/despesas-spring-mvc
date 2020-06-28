package br.com.valhala.despesas.model.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.valhala.despesas.model.enumerados.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_lancamento")
public class Lancamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate data;

    private String descricao;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoLancamento tipo;

    @NotNull
    private BigDecimal valor;

    private String observacao;

    public boolean isDebito() {
        return this.tipo == TipoLancamento.DEBITO;
    }

}
