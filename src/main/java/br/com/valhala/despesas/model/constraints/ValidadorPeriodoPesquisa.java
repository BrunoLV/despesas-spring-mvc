package br.com.valhala.despesas.model.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.valhala.despesas.model.filtros.FiltroPeriodoPesquisa;

public class ValidadorPeriodoPesquisa implements ConstraintValidator<PeriodoPesquisa, FiltroPeriodoPesquisa> {

	@Override
	public boolean isValid(FiltroPeriodoPesquisa filtro, ConstraintValidatorContext context) {
		if (filtro.getDataInicio() != null && filtro.getDataFim() != null) {
			if (filtro.getDataInicio().isAfter(filtro.getDataFim())) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("{periodo.dataInicio.maior.dataFim}").addConstraintViolation();
				return false;
			}
		}
		return true;
	}

}
