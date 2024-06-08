package br.com.unipac.gerenciadoratividadesapp.services;

import br.com.unipac.gerenciadoratividadesapp.models.Grupo;
import br.com.unipac.gerenciadoratividadesapp.repositories.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface GrupoService {

    public Grupo createGrupo(Grupo grupo);

    public boolean checkEmail(String email);


    public Grupo findByEmail(String email);
}


