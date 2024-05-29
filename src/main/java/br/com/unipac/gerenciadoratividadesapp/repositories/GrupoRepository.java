package br.com.unipac.gerenciadoratividadesapp.repositories;

import br.com.unipac.gerenciadoratividadesapp.models.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    public boolean existsByEmail(String email);

    public Grupo findByEmail(String email);

}