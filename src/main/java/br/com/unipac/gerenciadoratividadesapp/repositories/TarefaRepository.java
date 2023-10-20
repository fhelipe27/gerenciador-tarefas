package br.com.unipac.gerenciadoratividadesapp.repositories;

import br.com.unipac.gerenciadoratividadesapp.models.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
