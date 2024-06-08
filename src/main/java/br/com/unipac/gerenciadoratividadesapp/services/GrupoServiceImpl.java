package br.com.unipac.gerenciadoratividadesapp.services;

import br.com.unipac.gerenciadoratividadesapp.models.Grupo;
import br.com.unipac.gerenciadoratividadesapp.repositories.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class GrupoServiceImpl implements GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Grupo createGrupo(Grupo grupo) {

        grupo.setSenha(bCryptPasswordEncoder.encode(grupo.getSenha()));
        grupo.setRole("ROLE_USER");
        return grupoRepository.save(grupo);
    }

    @Override
    public boolean checkEmail(String email) {
        return grupoRepository.existsByEmail(email);
    }

    @Override
    public Grupo findByEmail(String email) {
        return grupoRepository.findByEmail(email);
    }
}
