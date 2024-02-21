package br.com.unipac.gerenciadoratividadesapp.repositories;

import br.com.unipac.gerenciadoratividadesapp.models.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {

}
