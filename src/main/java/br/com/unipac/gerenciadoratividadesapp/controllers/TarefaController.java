package br.com.unipac.gerenciadoratividadesapp.controllers;

import br.com.unipac.gerenciadoratividadesapp.models.Grupo;
import br.com.unipac.gerenciadoratividadesapp.models.Tarefa;
import br.com.unipac.gerenciadoratividadesapp.services.GrupoService;
import br.com.unipac.gerenciadoratividadesapp.services.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tarefa")
public class TarefaController {

    private final TarefaService tarefaService;
    private final GrupoService grupoService;

    @GetMapping("/criar-atividade")
    public String showForm(Model model, Principal principal) {
        String email = principal.getName();
        Grupo grupo = grupoService.findByEmail(email); // Busca o grupo pelo email do usuário autenticado
        Tarefa tarefa = new Tarefa();
        tarefa.setGrupo(grupo);
        model.addAttribute("tarefa", tarefa);
        return "criar-atividade";
    }

    @GetMapping("/lista-atividades")
    public ModelAndView listaAtividades(Principal principal) {
        ModelAndView mv = new ModelAndView("lista-atividades");
        String email = principal.getName();
        Grupo grupo = grupoService.findByEmail(email); // Busca o grupo pelo email do usuário autenticado
        mv.addObject("tarefas", tarefaService.buscarPorGrupo(grupo)); // Busca tarefas apenas do grupo
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

    @GetMapping("/editar-atividade-sem-id")
    public String editarSemId(Model model) {
        model.addAttribute("tarefa", new Tarefa());
        return "editar-atividade-sem-id";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable("id") Long id) {
        tarefaService.deletarPorId(id);
        return "redirect:/tarefa/lista-atividades";
    }

    @PostMapping("/criar-atividade")
    public String criar(@ModelAttribute("tarefa") Tarefa tarefa, Principal principal) {
        String email = principal.getName();
        Grupo grupo = grupoService.findByEmail(email); // Busca o grupo pelo email do usuário autenticado
        tarefa.setGrupo(grupo); // Associa o grupo à tarefa
        tarefaService.salvarTarefa(tarefa);
        return "redirect:/tarefa/lista-atividades";
    }

    @PostMapping("/editar-atividade/{id}")
    public String editar(@ModelAttribute("tarefa") Tarefa tarefa, @PathVariable("id") Long id) {
        tarefaService.editarPorId(id, tarefa);
        return "redirect:/tarefa/lista-atividades";
    }

}
