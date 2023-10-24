package br.com.unipac.gerenciadoratividadesapp.controllers;

import br.com.unipac.gerenciadoratividadesapp.models.Usuario;
import br.com.unipac.gerenciadoratividadesapp.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    // Mostra todos os usuários
    @GetMapping
    public List<Usuario> findAll() {
        List<Usuario> result = repository.findAll();
        return result;
    }

    // Acha os usuários por ID
    @GetMapping(value = "/{id}")
    public Usuario findById(@PathVariable Long id) {
        Usuario result = repository.findById(id).get();
        return result;
    }

}
