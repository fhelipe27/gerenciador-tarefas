package br.com.unipac.gerenciadoratividadesapp.controllers;

import br.com.unipac.gerenciadoratividadesapp.models.Tarefa;
import br.com.unipac.gerenciadoratividadesapp.models.Usuario;
import br.com.unipac.gerenciadoratividadesapp.repositories.TarefaRepository;
import br.com.unipac.gerenciadoratividadesapp.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository repository;

    // Mostra todas as tarefas
    @GetMapping
    public String findAll(Model model) {
        List<Tarefa> tarefas = repository.findAll();
        model.addAttribute("tarefas", tarefas);
        return "servicos";
    }

}
