package br.com.unipac.gerenciadoratividadesapp.repositories;

import br.com.unipac.gerenciadoratividadesapp.models.Conquista;
import br.com.unipac.gerenciadoratividadesapp.models.Grupo;
import br.com.unipac.gerenciadoratividadesapp.models.GrupoConquista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrupoConquistaRepository extends JpaRepository<GrupoConquista, Long> {
    List<GrupoConquista> findByGrupo(Grupo grupo);

    GrupoConquista findByGrupoAndConquista(Grupo grupo, Conquista primeiraTarefaConquista);
}
