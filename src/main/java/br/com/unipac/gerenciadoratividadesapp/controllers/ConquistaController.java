package br.com.unipac.gerenciadoratividadesapp.controllers;

import br.com.unipac.gerenciadoratividadesapp.models.Grupo;
import br.com.unipac.gerenciadoratividadesapp.models.GrupoConquista;
import br.com.unipac.gerenciadoratividadesapp.repositories.GrupoConquistaRepository;
import br.com.unipac.gerenciadoratividadesapp.services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ConquistaController {

    @Autowired
    private GrupoConquistaRepository grupoConquistaRepository;

    @Autowired
    private GrupoService grupoService;

    @GetMapping("/conquistas")
    public String listarConquistas(Model model, Principal principal) {
        String email = principal.getName();
        Grupo grupo = grupoService.findByEmail(email);
        List<GrupoConquista> grupoConquistas = grupoConquistaRepository.findByGrupo(grupo);
        model.addAttribute("grupoConquistas", grupoConquistas);
        return "conquistas"; // Nome da página HTML (ex: conquistas.html)
    }
}
