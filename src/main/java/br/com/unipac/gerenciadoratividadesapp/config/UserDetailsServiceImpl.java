package br.com.unipac.gerenciadoratividadesapp.config;

import br.com.unipac.gerenciadoratividadesapp.models.Grupo;
import br.com.unipac.gerenciadoratividadesapp.repositories.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Grupo grupo = grupoRepository.findByEmail(email);

        if(grupo != null) {
            return new CustomUserDetails(grupo);
        }

        throw new UsernameNotFoundException("User not available")
;    }
}
