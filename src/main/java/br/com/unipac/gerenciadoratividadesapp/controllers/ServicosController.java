package br.com.unipac.gerenciadoratividadesapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/servicos")
public class ServicosController {

    @GetMapping("/criar")
    public String servicos() {
        return "servicos";
    }

}
