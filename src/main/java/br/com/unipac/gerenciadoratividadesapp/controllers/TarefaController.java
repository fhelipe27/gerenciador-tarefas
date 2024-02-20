package br.com.unipac.gerenciadoratividadesapp.controllers;

import br.com.unipac.gerenciadoratividadesapp.models.Tarefa;
import br.com.unipac.gerenciadoratividadesapp.services.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tarefa")
public class TarefaController {

    private final TarefaService tarefaService;

    @GetMapping("/criar-atividade")
    public String showForm(Model model) {
        model.addAttribute("tarefa", new Tarefa());
        return "criar-atividade";
    }

    @PostMapping("/criar-atividade")
    public String create(@ModelAttribute("tarefa") Tarefa tarefa) {
        tarefaService.salvarTarefa(tarefa);
        return "redirect:/tarefa/criar-atividade";
    }

}
