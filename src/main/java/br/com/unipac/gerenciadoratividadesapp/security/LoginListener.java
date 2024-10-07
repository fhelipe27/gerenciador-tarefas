package br.com.unipac.gerenciadoratividadesapp.security;

import br.com.unipac.gerenciadoratividadesapp.models.Grupo;
import br.com.unipac.gerenciadoratividadesapp.models.GrupoConquista;
import br.com.unipac.gerenciadoratividadesapp.repositories.GrupoConquistaRepository;
import br.com.unipac.gerenciadoratividadesapp.services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private GrupoConquistaRepository grupoConquistaRepository;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String email = event.getAuthentication().getName();
        Grupo grupo = grupoService.findByEmail(email);

        List<GrupoConquista> conquistas = grupoConquistaRepository.findByGrupo(grupo);

        GrupoConquista bemVindoConquista = conquistas.stream()
                .filter(conquista -> "Bem-vindo!".equals(conquista.getConquista().getNome()))
                .findFirst()
                .orElse(null);

        if (bemVindoConquista != null && !bemVindoConquista.getConquistaConcluida()) {
            bemVindoConquista.setConquistaConcluida(true);
            grupoConquistaRepository.save(bemVindoConquista);
        }
    }
}
