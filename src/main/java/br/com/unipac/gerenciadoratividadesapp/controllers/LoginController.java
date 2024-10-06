package br.com.unipac.gerenciadoratividadesapp.controllers;

import br.com.unipac.gerenciadoratividadesapp.models.Grupo;
import br.com.unipac.gerenciadoratividadesapp.repositories.GrupoRepository;
import br.com.unipac.gerenciadoratividadesapp.services.GrupoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class LoginController {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private GrupoService grupoService;

    @GetMapping("/signin")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/createGrupo")
    public String createGrupo(@ModelAttribute Grupo grupo1, HttpSession session) {

        boolean f = grupoService.checkEmail(grupo1.getEmail());

        if (f) {
           session.setAttribute("msg", "Email já existe");
        } else {
            Grupo grupo = grupoService.createGrupo(grupo1);
            if (grupo != null) {
                session.setAttribute("msg", "Registrado com sucesso!");

            } else {
                session.setAttribute("msg", "Deu algo errado no servidor!");
            }
        }
        return "redirect:/register";
    }

}
