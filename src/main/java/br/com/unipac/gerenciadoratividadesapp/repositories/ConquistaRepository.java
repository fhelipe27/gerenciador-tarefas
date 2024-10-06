package br.com.unipac.gerenciadoratividadesapp.repositories;

import br.com.unipac.gerenciadoratividadesapp.models.Conquista;
import br.com.unipac.gerenciadoratividadesapp.models.Grupo;
import br.com.unipac.gerenciadoratividadesapp.models.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConquistaRepository extends JpaRepository<Conquista, Long> {

}
