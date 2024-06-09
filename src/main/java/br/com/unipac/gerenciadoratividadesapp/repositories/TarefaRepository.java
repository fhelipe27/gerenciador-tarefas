package br.com.unipac.gerenciadoratividadesapp.repositories;

import br.com.unipac.gerenciadoratividadesapp.models.Grupo;
import br.com.unipac.gerenciadoratividadesapp.models.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByGrupo(Grupo grupo);

    List<Tarefa> findByGrupoAndIsConcluida(Grupo grupo, boolean isConcluida);

    List<Tarefa> findByGrupoAndIsRemovida(Grupo grupo, boolean isRemovida);


}
