package br.com.valhala.despesas.interfaces.web;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.valhala.despesas.aplicacao.interno.LancamentoCommandService;
import br.com.valhala.despesas.aplicacao.interno.LancamentoQueryService;
import br.com.valhala.despesas.model.comandos.ComandoEdicaoLancamento;
import br.com.valhala.despesas.model.comandos.ComandoExclusaoLancamento;
import br.com.valhala.despesas.model.comandos.ComandoNovoLancamento;
import br.com.valhala.despesas.model.entidades.Lancamento;
import br.com.valhala.despesas.model.enumerados.TipoLancamento;
import br.com.valhala.despesas.model.filtros.FiltroLancamento;

@Controller
@RequestMapping("/lancamento")
public class LancamentoController {

	private LancamentoQueryService queryService;
	private LancamentoCommandService commandService;

	LancamentoController(LancamentoQueryService queryService, LancamentoCommandService commandService) {
		this.queryService = queryService;
		this.commandService = commandService;
	}

	@GetMapping("/lista")
	public String lista(Model model) {
		final Collection<Lancamento> lista = queryService.lista();
		preencheModelListagem(model, lista);
		return "lista";
	}

	private void preencheModelListagem(Model model, final Collection<Lancamento> lista) {
		model.addAttribute("lista", lista);
		model.addAttribute("totalDebitos", lista.stream().filter(l -> l.getTipo() == TipoLancamento.DEBITO)
				.map(l -> l.getValor()).reduce(BigDecimal.ZERO, (subtotal, valor) -> subtotal.add(valor)));
		model.addAttribute("totalCreditos", lista.stream().filter(l -> l.getTipo() == TipoLancamento.CREDITO)
				.map(l -> l.getValor()).reduce(BigDecimal.ZERO, (subtotal, valor) -> subtotal.add(valor)));
		model.addAttribute("filtro", new FiltroLancamento());
	}

	@ModelAttribute("tipos")
	public List<TipoLancamento> obtemTiposLancamento() {
		return Arrays.asList(TipoLancamento.values());
	}

	@PostMapping("/lista")
	public String pesquisar(@ModelAttribute FiltroLancamento filtro, Model model) {
		final Collection<Lancamento> lista = queryService.lista(filtro);
		preencheModelListagem(model, lista);
		model.addAttribute("filtro", filtro);
		return "lista";
	}

	@PostMapping("/salva")
	public String salva(@ModelAttribute @Valid final Lancamento lancamento, final BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "cadastro";
		}
		
		if (lancamento.getId() != null) {
			commandService.edita(new ComandoEdicaoLancamento(lancamento.getId(), lancamento));
		} else {
			commandService.salva(new ComandoNovoLancamento(lancamento));
		}
		return "redirect:lista";
	}

	@RequestMapping("/deletar")
	public String deletar(@RequestParam Long id, Model model) {
		commandService.deleta(new ComandoExclusaoLancamento(id));
		return "redirect:lista";
	}

	@RequestMapping("/editar")
	public String editar(@RequestParam Long id, Model model) {
		Optional<Lancamento> resultado = queryService.buscaPorId(id);
		if (resultado.isPresent()) {
			model.addAttribute("lancamento", resultado.get());
			return "cadastro";
		} else {
			return "erro";
		}
	}

	@RequestMapping("/detalhar")
	public String detalhar(@RequestParam Long id, Model model) {
		Optional<Lancamento> resultado = queryService.buscaPorId(id);
		if (resultado.isPresent()) {
			model.addAttribute("lancamento", resultado.get());
			return "detalhe";
		} else {
			return "erro";
		}
	}

	@RequestMapping("/novo")
	public String novo(Model model) {
		model.addAttribute("lancamento", new Lancamento());
		return "cadastro";
	}

}
