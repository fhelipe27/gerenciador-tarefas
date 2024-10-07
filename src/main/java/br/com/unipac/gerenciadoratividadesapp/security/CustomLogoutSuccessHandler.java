package br.com.unipac.gerenciadoratividadesapp.security;

import br.com.unipac.gerenciadoratividadesapp.models.Grupo;
import br.com.unipac.gerenciadoratividadesapp.models.GrupoConquista;
import br.com.unipac.gerenciadoratividadesapp.repositories.GrupoConquistaRepository;
import br.com.unipac.gerenciadoratividadesapp.services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private GrupoService grupoService;
    private GrupoConquistaRepository grupoConquistaRepository;

    @Autowired
    @Lazy
    public void setGrupoService(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    @Autowired
    public void setGrupoConquistaRepository(GrupoConquistaRepository grupoConquistaRepository) {
        this.grupoConquistaRepository = grupoConquistaRepository;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication != null && authentication.getName() != null) {
            String email = authentication.getName();
            Grupo grupo = grupoService.findByEmail(email);

            List<GrupoConquista> conquistas = grupoConquistaRepository.findByGrupo(grupo);

            // Verifique se a conquista "Até Breve!" está marcada como concluída
            GrupoConquista ateBreveConquista = conquistas.stream()
                    .filter(conquista -> "Até Breve!".equals(conquista.getConquista().getNome()))
                    .findFirst()
                    .orElse(null);

            if (ateBreveConquista != null && !ateBreveConquista.getConquistaConcluida()) {
                ateBreveConquista.setConquistaConcluida(true);
                grupoConquistaRepository.save(ateBreveConquista);
            }
        }

        response.sendRedirect("/signin?logout=true");
    }
}