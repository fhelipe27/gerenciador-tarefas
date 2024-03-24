package br.com.unipac.gerenciadoratividadesapp.controllers;

import br.com.unipac.gerenciadoratividadesapp.models.Tarefa;
import br.com.unipac.gerenciadoratividadesapp.services.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

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

    @GetMapping("/lista-atividades")
    public ModelAndView listaAtividades() {
        ModelAndView mv = new ModelAndView("lista-atividades");
        mv.addObject("tarefas", tarefaService.buscarTodos());
        mv.addObject("tarefa", new Tarefa());
        return mv;
    }

    @GetMapping("/editar-atividade/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("editar-atividade");
        Optional<Tarefa> tarefaFind = tarefaService.buscarPorId(id);
        Tarefa tarefa = tarefaFind.orElse(null);
        mv.addObject("tarefa", tarefa);
        return mv;
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable("id") Long id) {
        tarefaService.deletarPorId(id);
        return "redirect:/tarefa/lista-atividades";
    }

    @PostMapping("/criar-atividade")
    public String criar(@ModelAttribute("tarefa") Tarefa tarefa) {
        tarefaService.salvarTarefa(tarefa);
        return "redirect:/tarefa/criar-atividade";
    }

    @PostMapping("/editar-atividade/{id}")
    public String editar(@ModelAttribute("tarefa") Tarefa tarefa, @PathVariable("id") Long id) {
        tarefaService.editarPorId(id, tarefa);
        return "redirect:/tarefa/lista-atividades";
    }

}
