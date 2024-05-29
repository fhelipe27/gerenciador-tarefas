package br.com.unipac.gerenciadoratividadesapp.utils;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class SessionUtilityBean {

    public void removeMessageFromSession() {
        try {
            System.out.println("Removendo mensagem da sessão");
            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            session.removeAttribute("msg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}