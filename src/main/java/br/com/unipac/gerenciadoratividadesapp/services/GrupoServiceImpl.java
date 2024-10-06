package br.com.unipac.gerenciadoratividadesapp.services;

import br.com.unipac.gerenciadoratividadesapp.models.Conquista;
import br.com.unipac.gerenciadoratividadesapp.models.Grupo;
import br.com.unipac.gerenciadoratividadesapp.models.GrupoConquista;
import br.com.unipac.gerenciadoratividadesapp.repositories.ConquistaRepository;
import br.com.unipac.gerenciadoratividadesapp.repositories.GrupoRepository;
import br.com.unipac.gerenciadoratividadesapp.repositories.GrupoConquistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoServiceImpl implements GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private ConquistaRepository conquistaRepository;

    @Autowired
    private GrupoConquistaRepository grupoConquistaRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Grupo createGrupo(Grupo grupo) {
        grupo.setSenha(bCryptPasswordEncoder.encode(grupo.getSenha()));
        grupo.setRole("ROLE_USER");

        // Salvar o novo grupo
        Grupo novoGrupo = grupoRepository.save(grupo);

        // Buscar todas as conquistas existentes
        List<Conquista> todasConquistas = conquistaRepository.findAll();

        // Criar um registro GrupoConquista para cada conquista com conquistaConcluida = false
        for (Conquista conquista : todasConquistas) {
            GrupoConquista grupoConquista = new GrupoConquista();
            grupoConquista.setGrupo(novoGrupo);
            grupoConquista.setConquista(conquista);
            grupoConquista.setConquistaConcluida(false);
            grupoConquistaRepository.save(grupoConquista);
        }

        return novoGrupo;
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
