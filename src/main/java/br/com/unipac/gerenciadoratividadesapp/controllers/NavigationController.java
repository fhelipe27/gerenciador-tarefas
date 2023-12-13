package br.com.unipac.gerenciadoratividadesapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {
    @GetMapping("/")
    public String inicio() {
        return "index";
    }

    @GetMapping("/servicos")
    public String servicos() {
        return "servicos";
    }

    @GetMapping("/sobre")
    public String sobre() {
        return "sobre";
    }
    @GetMapping("/contato")
    public String contato() {
        return "contato";
    }

}
